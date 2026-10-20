import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
public class E4QOracle {
static Description nested(String name) { Description d=Description.createSuiteDescription(name);d.addChild(Description.createTestDescription("Example","leaf"));return d; }
static Result result(int runs,int failures) throws Exception { Result r=new Result(); RunListener l=r.createListener(); for(int i=0;i<runs;i++){ Description d=Description.createTestDescription("Oracle", "test"+i);l.testStarted(d); if(i<failures) l.testFailure(new Failure(d,new AssertionError("synthetic")));l.testFinished(d);} return r; }
public static void main(String[] args) throws Exception {
System.setOut(new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out), true, java.nio.charset.StandardCharsets.UTF_8));
try { Object v=BooleanUtils.isTrue(null); System.out.println("E4Q-01-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(true); System.out.println("E4Q-01-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(false); System.out.println("E4Q-01-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(null); System.out.println("E4Q-01-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(true); System.out.println("E4Q-01-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(false); System.out.println("E4Q-01-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(null); System.out.println("E4Q-01-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isTrue(true); System.out.println("E4Q-01-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-01-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(null); System.out.println("E4Q-02-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(true); System.out.println("E4Q-02-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(false); System.out.println("E4Q-02-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(null); System.out.println("E4Q-02-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(true); System.out.println("E4Q-02-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(false); System.out.println("E4Q-02-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(null); System.out.println("E4Q-02-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.isFalse(true); System.out.println("E4Q-02-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-02-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(true); System.out.println("E4Q-03-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(false); System.out.println("E4Q-03-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(true); System.out.println("E4Q-03-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(false); System.out.println("E4Q-03-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(true); System.out.println("E4Q-03-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(false); System.out.println("E4Q-03-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(true); System.out.println("E4Q-03-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=BooleanUtils.toInteger(false); System.out.println("E4Q-03-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-03-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)-128,(byte)127); System.out.println("E4Q-04-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)127,(byte)-128); System.out.println("E4Q-04-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)0,(byte)0); System.out.println("E4Q-04-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)-1,(byte)1); System.out.println("E4Q-04-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)1,(byte)-1); System.out.println("E4Q-04-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)-128,(byte)-128); System.out.println("E4Q-04-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)127,(byte)127); System.out.println("E4Q-04-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)2,(byte)3); System.out.println("E4Q-04-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)3,(byte)2); System.out.println("E4Q-04-D09\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D09\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)0,(byte)127); System.out.println("E4Q-04-D10\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D10\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)-128,(byte)0); System.out.println("E4Q-04-D11\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D11\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((byte)1,(byte)1); System.out.println("E4Q-04-D12\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-04-D12\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)-32768,(short)32767); System.out.println("E4Q-05-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)32767,(short)-32768); System.out.println("E4Q-05-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)0,(short)0); System.out.println("E4Q-05-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)-1,(short)1); System.out.println("E4Q-05-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)1,(short)-1); System.out.println("E4Q-05-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)-32768,(short)-32768); System.out.println("E4Q-05-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)32767,(short)32767); System.out.println("E4Q-05-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)2,(short)3); System.out.println("E4Q-05-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)3,(short)2); System.out.println("E4Q-05-D09\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D09\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)0,(short)32767); System.out.println("E4Q-05-D10\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D10\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)-32768,(short)0); System.out.println("E4Q-05-D11\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D11\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare((short)1,(short)1); System.out.println("E4Q-05-D12\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-05-D12\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(Long.MIN_VALUE,Long.MAX_VALUE); System.out.println("E4Q-06-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(Long.MAX_VALUE,Long.MIN_VALUE); System.out.println("E4Q-06-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(0L,0L); System.out.println("E4Q-06-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(-1L,1L); System.out.println("E4Q-06-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(1L,-1L); System.out.println("E4Q-06-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(Long.MIN_VALUE,Long.MIN_VALUE); System.out.println("E4Q-06-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(Long.MAX_VALUE,Long.MAX_VALUE); System.out.println("E4Q-06-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(2L,3L); System.out.println("E4Q-06-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(3L,2L); System.out.println("E4Q-06-D09\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D09\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(0L,Long.MAX_VALUE); System.out.println("E4Q-06-D10\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D10\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(Long.MIN_VALUE,0L); System.out.println("E4Q-06-D11\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D11\tEXCEPTION:"+t.getClass().getName());}
try { Object v=NumberUtils.compare(1L,1L); System.out.println("E4Q-06-D12\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-06-D12\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.EMPTY).getDisplayName(); System.out.println("E4Q-07-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("emptySuite")).getDisplayName(); System.out.println("E4Q-07-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createTestDescription("Example", "leaf")).getDisplayName(); System.out.println("E4Q-07-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(nested("nested")).getDisplayName(); System.out.println("E4Q-07-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("\u00e9\u6f22\u5b57(Example)")).getDisplayName(); System.out.println("E4Q-07-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("multi")).getDisplayName(); System.out.println("E4Q-07-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("deep")).getDisplayName(); System.out.println("E4Q-07-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("")).getDisplayName(); System.out.println("E4Q-07-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-07-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.EMPTY).isSuite(); System.out.println("E4Q-08-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("emptySuite")).isSuite(); System.out.println("E4Q-08-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createTestDescription("Example", "leaf")).isSuite(); System.out.println("E4Q-08-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(nested("nested")).isSuite(); System.out.println("E4Q-08-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("\u00e9\u6f22\u5b57(Example)")).isSuite(); System.out.println("E4Q-08-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("multi")).isSuite(); System.out.println("E4Q-08-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("deep")).isSuite(); System.out.println("E4Q-08-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("")).isSuite(); System.out.println("E4Q-08-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-08-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.EMPTY).isTest(); System.out.println("E4Q-09-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("emptySuite")).isTest(); System.out.println("E4Q-09-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createTestDescription("Example", "leaf")).isTest(); System.out.println("E4Q-09-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(nested("nested")).isTest(); System.out.println("E4Q-09-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("\u00e9\u6f22\u5b57(Example)")).isTest(); System.out.println("E4Q-09-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("multi")).isTest(); System.out.println("E4Q-09-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("deep")).isTest(); System.out.println("E4Q-09-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("")).isTest(); System.out.println("E4Q-09-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-09-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.EMPTY).testCount(); System.out.println("E4Q-10-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("emptySuite")).testCount(); System.out.println("E4Q-10-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createTestDescription("Example", "leaf")).testCount(); System.out.println("E4Q-10-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(nested("nested")).testCount(); System.out.println("E4Q-10-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("\u00e9\u6f22\u5b57(Example)")).testCount(); System.out.println("E4Q-10-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("multi")).testCount(); System.out.println("E4Q-10-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("deep")).testCount(); System.out.println("E4Q-10-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=(Description.createSuiteDescription("")).testCount(); System.out.println("E4Q-10-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-10-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(0,0).getRunCount(); System.out.println("E4Q-11-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(1,0).getRunCount(); System.out.println("E4Q-11-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(1,1).getRunCount(); System.out.println("E4Q-11-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,0).getRunCount(); System.out.println("E4Q-11-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,1).getRunCount(); System.out.println("E4Q-11-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,2).getRunCount(); System.out.println("E4Q-11-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(3,0).getRunCount(); System.out.println("E4Q-11-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(3,1).getRunCount(); System.out.println("E4Q-11-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-11-D08\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(0,0).wasSuccessful(); System.out.println("E4Q-12-D01\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D01\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(1,0).wasSuccessful(); System.out.println("E4Q-12-D02\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D02\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(1,1).wasSuccessful(); System.out.println("E4Q-12-D03\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D03\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,0).wasSuccessful(); System.out.println("E4Q-12-D04\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D04\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,1).wasSuccessful(); System.out.println("E4Q-12-D05\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D05\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(2,2).wasSuccessful(); System.out.println("E4Q-12-D06\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D06\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(3,0).wasSuccessful(); System.out.println("E4Q-12-D07\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D07\tEXCEPTION:"+t.getClass().getName());}
try { Object v=result(3,1).wasSuccessful(); System.out.println("E4Q-12-D08\t"+String.valueOf(v)); } catch(Throwable t){System.out.println("E4Q-12-D08\tEXCEPTION:"+t.getClass().getName());}
}
}