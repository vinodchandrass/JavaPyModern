import argparse,csv,json,hashlib,collections,datetime,pathlib,sys
P=argparse.ArgumentParser();P.add_argument('--candidates',required=True);P.add_argument('--gates',required=True);P.add_argument('--holdout',required=True);P.add_argument('--protocol',required=True);P.add_argument('--out',required=True);a=P.parse_args()
def read(path):
 with open(path,encoding='utf-8-sig',newline='') as f:return list(csv.DictReader(f))
def digest(path):return hashlib.sha256(pathlib.Path(path).read_bytes()).hexdigest()
candidates=read(a.candidates);gates=read(a.gates);holdout=read(a.holdout);proto=json.loads(pathlib.Path(a.protocol).read_text(encoding='utf-8'))
if not candidates or not gates or not holdout:sys.exit('NOT EXECUTED: candidate, gate and holdout data must all be populated; no results synthesized')
if proto.get('status')!='DESIGN_ONLY_NO_NEW_EXPERIMENTS':sys.exit('Unexpected protocol status')
keys=lambda r:(r['candidate_id'],r['method_id'])
cr={}
for r in candidates:
 k=keys(r)
 if k in cr:sys.exit('Duplicate candidate: '+str(k))
 if r['cohort'] not in ('PRIMARY','EXPLORATORY'):sys.exit('Invalid cohort '+str(k))
 if r['cohort']=='PRIMARY' and not r['independent_development_evidence'].strip():sys.exit('Primary candidate missing provenance: '+str(k))
 path=pathlib.Path(r['candidate_path'])
 if not path.is_file() or digest(path)!=r['candidate_sha256']:sys.exit('Candidate hash mismatch: '+str(k))
 cr[k]=r
hr=collections.defaultdict(list)
for r in holdout:
 k=keys(r)
 if k not in cr:sys.exit('Unknown holdout candidate '+str(k))
 if r['oracle_status'] not in ('PASS','FAIL'):sys.exit('Invalid oracle status '+str(k))
 if not r['oracle_sha256'].strip():sys.exit('Missing oracle SHA256 '+str(k))
 hr[k].append(r)
gr={}; allowed={'ACCEPT','REJECT','ABSTAIN'}
for r in gates:
 k=keys(r);g=r['gate'];kk=(k,g)
 if k not in cr or kk in gr:sys.exit('Unknown/duplicate gate decision '+str(kk))
 if g not in ('G0','G1','G2','G3') or r['decision'] not in allowed:sys.exit('Invalid gate or decision '+str(kk))
 if not r['policy_sha256'].strip():sys.exit('Missing policy fingerprint '+str(kk))
 if g=='G0' and r['decision']!='ACCEPT':sys.exit('G0 must ACCEPT all eligible candidates')
 if any(r['decision_utc']>=h['evaluated_utc'] for h in hr[k]):sys.exit('Gate decision not prior to holdout evaluation '+str(kk))
 gr[kk]=r
if not hr:sys.exit('No holdout records')
for k in cr:
 if k not in hr:sys.exit('Candidate missing holdout '+str(k))
 for g in ('G0','G1','G2','G3'):
  if (k,g) not in gr:sys.exit('Missing gate decision '+str((k,g)))
rows=[]
for k,c in cr.items():
 status='PASS' if all(x['oracle_status']=='PASS' for x in hr[k]) else 'FAIL'
 for g in ('G0','G1','G2','G3'):
  d=gr[k,g]['decision'];rows.append(dict(candidate_id=k[0],method_id=k[1],cohort=c['cohort'],gate=g,holdout_correct=status,decision=d,escaped_defect=int(d=='ACCEPT' and status=='FAIL'),false_rejection=int(d=='REJECT' and status=='PASS'),abstention=int(d=='ABSTAIN'),accepted=int(d=='ACCEPT')))
out=pathlib.Path(a.out);out.mkdir(parents=True,exist_ok=True)
with (out/'candidate_gate_outcomes.csv').open('w',newline='') as f:
 w=csv.DictWriter(f,fieldnames=list(rows[0]));w.writeheader();w.writerows(rows)
summary={'phase':'E4Q','status':'OBSERVATIONAL_PAIRED_GATE_OUTCOMES','source_hashes':{x:digest(getattr(a,x)) for x in ('candidates','gates','holdout','protocol')},'groups':{},'limitations':['Not proof of prospective blinding without external provenance audit','Candidate-method units may be dependent','Descriptive rates only; no ungrounded significance tests']}
for cohort in ('PRIMARY','EXPLORATORY'):
 for g in ('G0','G1','G2','G3'):
  rr=[r for r in rows if r['cohort']==cohort and r['gate']==g]
  if rr:
   n=len(rr);summary['groups'][cohort+'_'+g]={'n':n,'escaped_defects':sum(r['escaped_defect'] for r in rr),'false_rejections':sum(r['false_rejection'] for r in rr),'abstentions':sum(r['abstention'] for r in rr),'accepted':sum(r['accepted'] for r in rr),'escaped_per_eligible':sum(r['escaped_defect'] for r in rr)/n,'acceptance_coverage':sum(r['accepted'] for r in rr)/n}
(out/'summary.json').write_text(json.dumps(summary,indent=2));print(json.dumps(summary,indent=2))
