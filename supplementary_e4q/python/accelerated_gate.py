from pathlib import Path
import subprocess,hashlib,json,csv,zipfile,datetime,os,re,sys
BASE=Path(r'D:\EMSE_PhaseE1'); OUT=BASE/'E4Q_Accelerated_Gate_Review'; OUT.mkdir(exist_ok=True)
def sha(p):
 try:
  h=hashlib.sha256()
  with p.open('rb') as f:
   for b in iter(lambda:f.read(1024*1024),b''):h.update(b)
  return h.hexdigest()
 except Exception as e:return 'ERROR:'+str(e)
def run(args,timeout=40):
 try:
  p=subprocess.run([str(x) for x in args],capture_output=True,text=True,timeout=timeout,errors='replace');return {'exit':p.returncode,'stdout':p.stdout[-6000:],'stderr':p.stderr[-6000:]}
 except Exception as e:return {'error':repr(e)}
def rec(p):return {'exists':p.is_file(),'sha256':sha(p) if p.is_file() else None,'bytes':p.stat().st_size if p.is_file() else None}
report={'stage':'Gplus_Accelerated_Readiness','time_utc':datetime.datetime.now(datetime.timezone.utc).isoformat(),'policy':'READ_ONLY_FROZEN_INPUTS_NO_CANDIDATE_OR_HOLDOUT_EXECUTION','candidate_executions':0,'holdout_executions':0,'checks':{},'blockers':[]}
# Fixed paths from observed logs; no recursive reading of holdout files.
java_home=Path(r'C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot'); javac=java_home/'bin/javac.exe'; java=java_home/'bin/java.exe'
commons=BASE/'E3B_Repositories/commons-lang/target/commons-lang3-3.21.1-SNAPSHOT.jar'; junit=BASE/'E3B_Repositories/junit4/target/junit-4.13.3-SNAPSHOT.jar'; hamcrest=BASE/'defects4j-master/framework/projects/Cli/lib/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar'
stagee=BASE/'E4Q_StageE_Oracle_Kit/E4QOracle.java'; stageg=BASE/'E4Q_StageG_Qualification/E4QOracleStageG.java'
paths={'java':java,'javac':javac,'commons_jar':commons,'junit_jar':junit,'hamcrest_jar':hamcrest,'stage_e_source':stagee,'stage_g_source':stageg}
report['checks']['fixed_inputs']={k:rec(v) for k,v in paths.items()}
if not all(p.is_file() for p in (java,javac,commons,junit,hamcrest)):report['blockers'].append('Missing JDK or required dependency; Java checks not run')
# Inspect Stage G artifacts, without trusting status field as semantic validation.
for n in ['stage_g_report.json','stage_g_manifest.json','stage_g_cases.csv']:
 p=BASE/'E4Q_StageG_Qualification'/n;report['checks'][n]=rec(p)
 if n=='stage_g_report.json' and p.is_file():
  try:
   d=json.loads(p.read_text(encoding='utf-8-sig'));report['checks']['stage_g_claim']={k:d.get(k) for k in ('status','total','passed','candidate_executions','holdout_executions')}
  except Exception as e:report['blockers'].append('Stage G report unreadable: '+str(e))
# Candidate integrity: only filenames, byte sizes and hashes, no import/execution; do not read any holdout or outcome file.
manifest=BASE/'E4Q_candidate_manifest.json';report['checks']['candidate_manifest']=rec(manifest)
if manifest.is_file():
 try:
  d=json.loads(manifest.read_text(encoding='utf-8-sig'));report['checks']['candidate_manifest_structure']={'root_type':type(d).__name__,'top_level_keys':list(d)[:30] if isinstance(d,dict) else None,'entry_count':len(d) if isinstance(d,list) else None}
 except Exception as e:report['blockers'].append('Candidate manifest parsing failed: '+str(e))
else:report['blockers'].append('Candidate manifest not found')
# Source repositories are inspected with git show, never checkout/reset.
for name,repo in [('commons',BASE/'E3B_Repositories/commons-lang'),('junit',BASE/'E3B_Repositories/junit4')]:
 if (repo/'.git').exists():
  report['checks'][name+'_git_head']=run(['git','-C',str(repo),'rev-parse','HEAD'])
  report['checks'][name+'_git_status']=run(['git','-C',str(repo),'status','--porcelain'])
 else:report['blockers'].append(name+' git repository unavailable for provenance inspection')
# Independent actual JUnitCore execution vs synthetic listener. Compiled ONLY in fresh OUT.
source='''import org.junit.Test;\nimport org.junit.runner.JUnitCore;\nimport org.junit.runner.Result;\nimport org.junit.runner.Description;\nimport org.junit.runner.notification.RunListener;\npublic class GPlusJUnitCheck {\n public static class Good { @Test public void a(){} @Test public void b(){} }\n public static class Bad { @Test public void fails(){ org.junit.Assert.fail("expected"); } }\n public static void main(String[] args) throws Exception {\n  Result good=new JUnitCore().run(Good.class);\n  Result bad=new JUnitCore().run(Bad.class);\n  Result synthetic=new Result(); RunListener l=synthetic.createListener();\n  l.testRunStarted(Description.createSuiteDescription("synthetic"));\n  for(int i=0;i<2;i++){Description d=Description.createTestDescription("S","t"+i);l.testStarted(d);l.testFinished(d);}\n  l.testRunFinished(synthetic);\n  System.out.println("CORE_GOOD_COUNT="+good.getRunCount());\n  System.out.println("CORE_GOOD_SUCCESS="+good.wasSuccessful());\n  System.out.println("CORE_BAD_COUNT="+bad.getRunCount());\n  System.out.println("CORE_BAD_FAILURES="+bad.getFailureCount());\n  System.out.println("CORE_BAD_SUCCESS="+bad.wasSuccessful());\n  System.out.println("SYNTHETIC_COUNT="+synthetic.getRunCount());\n }\n}\n'''
src=OUT/'GPlusJUnitCheck.java';src.write_bytes(source.encode('utf-8'))
report['checks']['independent_java_source']=rec(src)
if all(p.is_file() for p in (java,javac,commons,junit,hamcrest)):
 cp=os.pathsep.join(map(str,(commons,junit,hamcrest)));report['checks']['independent_compile']=run([javac,'-encoding','UTF-8','-cp',cp,'-d',OUT,src]);
 if report['checks']['independent_compile'].get('exit')==0:
  report['checks']['independent_execution']=run([java,'-cp',os.pathsep.join((str(OUT),cp)),'GPlusJUnitCheck'])
 else:report['blockers'].append('Independent Java compile failed')
# Candidate and holdout gate deliberately not activated.
report['blockers']+=['Prospective dated protocol authorization not supplied','Signed/frozen G2 operational thresholds not established','Candidate lock and holdout specification not verified','Historical source-commit attribution remains unresolved']
report['decision']='NOT_AUTHORIZED_FOR_CANDIDATE_OR_HOLDOUT_EXECUTION'
reportfile=OUT/'accelerated_gate_report.json';reportfile.write_text(json.dumps(report,indent=2),encoding='utf-8')
summary=OUT/'accelerated_gate_summary.txt';summary.write_text('E4Q G+ accelerated readiness audit\nDecision: '+report['decision']+'\n\n'+ '\n'.join('- '+b for b in report['blockers'])+'\n',encoding='utf-8')
archive=BASE/'E4Q_Accelerated_Gate_Review.zip'
with zipfile.ZipFile(archive,'w',zipfile.ZIP_DEFLATED) as z:
 for p in (reportfile,summary,src):z.write(p,p.name)
print('Report:',reportfile);print('ZIP:',archive);print('Independent Java:',report['checks'].get('independent_execution','NOT RUN'))
