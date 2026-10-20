"""E4Q Stage 1B development-only runner. No holdout files are accepted or read.
Separate version; frozen candidates/manifests remain unchanged. Requires signed investigator authorization and approved Stage 1 amendment.
"""
import argparse,csv,hashlib,json,subprocess,sys,datetime
from pathlib import Path, PureWindowsPath
from collections import defaultdict

def sha(p): return hashlib.sha256(p.read_bytes()).hexdigest()
def require(condition,message):
    if not condition: raise RuntimeError('BLOCKED: '+message)
def main():
    ap=argparse.ArgumentParser()
    ap.add_argument('--authorization',required=True)
    ap.add_argument('--out',required=True)
    args=ap.parse_args()
    root=Path(__file__).resolve().parent
    dest=Path(args.out).resolve()
    require(dest != root and root not in dest.parents and dest not in root.parents,'output must be outside package/source tree')
    authpath=Path(args.authorization).resolve()
    require(authpath.is_file(),'authorization record missing')
    auth=json.loads(authpath.read_text(encoding='utf-8'))
    require(auth.get('status')=='SIGNED_FINAL_DEVELOPMENT_ONLY','signed final development-only authorization absent')
    require(bool(auth.get('investigator_signature')) and bool(auth.get('signed_at_with_timezone')),'investigator signature/date absent')
    require(auth.get('stage1_amendment_approved') is True,'Stage 1 amendment not formally approved in authorization')
    require(auth.get('scope')=='DEVELOPMENT_ONLY','scope must be DEVELOPMENT_ONLY')
    manifest_path=root/'V9_AMENDED_CASE_ORACLE_MANIFEST.json'
    cand_path=root/'E4Q_candidate_manifest.json'
    amendment_path=root/'STAGE1_AMENDMENT.json'
    require(auth.get('evaluator_sha256')==sha(Path(__file__)),'runner SHA-256 mismatch')
    require(auth.get('manifest_sha256')==sha(manifest_path),'development manifest SHA-256 mismatch')
    require(auth.get('candidate_manifest_sha256')==sha(cand_path),'candidate manifest SHA-256 mismatch')
    require(auth.get('amendment_sha256')==sha(amendment_path),'Stage 1 amendment SHA-256 mismatch')
    amendment=json.loads(amendment_path.read_text(encoding='utf-8'))
    require(amendment.get('status')=='FORMALLY_APPROVED','Stage 1 amendment is not formally approved')
    require(bool(amendment.get('formal_investigator_signature')) and bool(amendment.get('signed_at_with_timezone')),'formal amendment signature/date absent')
    require(amendment.get('scope')=='DEVELOPMENT_ONLY','amendment scope must be DEVELOPMENT_ONLY')
    require(amendment.get('policy',{}).get('G1','').startswith('coverage-first: n<8 ABSTAIN'),'G1 policy mismatch')
    require(amendment.get('policy',{}).get('G2','').startswith('mismatch-first: any boundary mismatch REJECT'),'G2 policy mismatch')
    require('E4Q-03' in amendment.get('policy',{}).get('G3',''),'G3 E4Q-03 exception missing')
    require(auth.get('reference_jar_lineage_disposition')=='UNVERIFIED_BINARY_HASH_FIXED_DISCLOSED','reference JAR lineage limitation must be disclosed')
    cm=json.loads(cand_path.read_text(encoding='utf-8'))
    require(cm.get('status')=='CANDIDATES_REGISTERED_NOT_VALIDATED','unexpected candidate registration state')
    for item in cm['candidates']:
        fn=PureWindowsPath(item['path']).name
        path=root/fn
        require(path.is_file() and sha(path)==item['sha256'],'candidate hash mismatch: '+fn)
    manifest=json.loads(manifest_path.read_text(encoding='utf-8'))
    cases=manifest['cases']
    require(len(cases)==108 and len({c['case_id'] for c in cases})==108,'expected 108 unique development cases')
    require(len({c['candidate'] for c in cases})==12,'expected 12 candidate clusters')
    require(not dest.exists() or not any(dest.iterdir()),'output directory must be new or empty')
    # Execution begins only after all gates above pass.
    dest.mkdir(parents=True,exist_ok=True)
    by=defaultdict(list)
    for c in cases: by[c['candidate']].append(c)
    rows=[]
    for fn in sorted(by):
        payload=json.dumps(by[fn],ensure_ascii=False)
        proc=subprocess.run([sys.executable,str(root/'worker.py'),str(root/fn)],input=payload,text=True,capture_output=True,encoding='utf-8',timeout=40,cwd=root)
        require(proc.returncode==0,'worker failed for '+fn+': '+proc.stderr[-1200:])
        result=json.loads(proc.stdout)
        require(len(result)==len(by[fn]),'worker result count mismatch for '+fn)
        for c,r in zip(by[fn],result):
            require(c['case_id']==r['case_id'],'case ID mismatch')
            expected=str(c['java_output']); actual=r.get('actual')
            match=(r.get('status')=='OK' and actual==expected)
            rows.append({'case_id':c['case_id'],'candidate':fn,'boundary':bool(c['boundary']),'expected':expected,'actual':actual,'status':r.get('status'),'match':match})
    with (dest/'case_results.csv').open('w',newline='',encoding='utf-8') as f:
        w=csv.DictWriter(f,fieldnames=list(rows[0]));w.writeheader();w.writerows(rows)
    decisions=[]
    for fn in sorted(by):
        rr=[x for x in rows if x['candidate']==fn]; bb=[x for x in rr if x['boundary']]
        g1='ABSTAIN' if len(rr)<8 else ('REJECT' if not all(x['match'] for x in rr) else 'ACCEPT')
        g2='NOT_APPLICABLE' if fn=='E4Q-03.py' else ('REJECT' if any(not x['match'] for x in bb) else ('ABSTAIN' if len(rr)<4 or len(bb)<2 else 'ACCEPT'))
        g3='ACCEPT' if g1=='ACCEPT' and (g2=='ACCEPT' or (fn=='E4Q-03.py' and g2=='NOT_APPLICABLE')) else ('REJECT' if g1=='REJECT' or g2=='REJECT' else 'ABSTAIN')
        decisions.append({'candidate':fn,'cases':len(rr),'boundary_cases':len(bb),'exact_matches':sum(x['match'] for x in rr),'G1':g1,'G2':g2,'G3':g3})
    (dest/'development_decisions.json').write_text(json.dumps(decisions,indent=2),encoding='utf-8')
    receipt={'status':'DEVELOPMENT_ONLY_EXECUTED','created_utc':datetime.datetime.now(datetime.timezone.utc).isoformat(),'candidate_executions':12,'development_cases':108,'holdout_executions':0,'holdout_outcomes_opened':False,'runner_sha256':sha(Path(__file__)),'authorization_sha256':sha(authpath),'candidate_manifest_sha256':sha(cand_path),'case_manifest_sha256':sha(manifest_path),'reference_jar_lineage':'UNVERIFIED_BINARY_HASH_FIXED_DISCLOSED','note':'Development-only descriptive evidence; no holdout or gate-superiority claim.'}
    (dest/'execution_receipt.json').write_text(json.dumps(receipt,indent=2),encoding='utf-8')
    print(json.dumps({'status':'DEVELOPMENT_ONLY_EXECUTED','cases':len(rows),'candidates':len(by),'out':str(dest)},indent=2))
if __name__=='__main__':
    try: main()
    except Exception as e: print(str(e),file=sys.stderr);sys.exit(2)
