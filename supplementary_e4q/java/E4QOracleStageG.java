import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/** Development-only typed Java oracle. No candidate loading or holdout inputs. */
public final class E4QOracleStageG {
    static String enc(String s) { return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8)); }
    static String dec(String s) { return new String(Base64.getDecoder().decode(s), StandardCharsets.UTF_8); }
    static Description description(String shape, String label) {
        Description d = Description.createSuiteDescription(label);
        if (shape.equals("one-child")) d.addChild(Description.createSuiteDescription("child"));
        else if (shape.equals("two-level")) {
            Description c = Description.createSuiteDescription("inner");
            c.addChild(Description.createSuiteDescription("leaf"));
            d.addChild(c);
        } else if (!shape.equals("leaf")) throw new IllegalArgumentException("Invalid description shape");
        return d;
    }
    public static void main(String[] args) {
        try {
            if (args.length < 2) throw new IllegalArgumentException("Usage: ID payload...");
            String id=args[0], kind="", val="";
            switch (id) {
                case "E4Q-01": { Boolean b = args[1].equals("null") ? null : Boolean.valueOf(args[1]); kind="boolean"; val=Boolean.toString(BooleanUtils.isTrue(b)); break; }
                case "E4Q-02": { Boolean b = args[1].equals("null") ? null : Boolean.valueOf(args[1]); kind="boolean"; val=Boolean.toString(BooleanUtils.isFalse(b)); break; }
                case "E4Q-03": { if (!args[1].equals("true") && !args[1].equals("false")) throw new IllegalArgumentException("Expected primitive boolean"); kind="int"; val=Integer.toString(BooleanUtils.toInteger(Boolean.parseBoolean(args[1]))); break; }
                case "E4Q-04": kind="int"; val=Integer.toString(NumberUtils.compare(Byte.parseByte(args[1]),Byte.parseByte(args[2]))); break;
                case "E4Q-05": kind="int"; val=Integer.toString(NumberUtils.compare(Short.parseShort(args[1]),Short.parseShort(args[2]))); break;
                case "E4Q-06": kind="int"; val=Integer.toString(NumberUtils.compare(Long.parseLong(args[1]),Long.parseLong(args[2]))); break;
                case "E4Q-07": case "E4Q-08": case "E4Q-09": case "E4Q-10": {
                    Description d=description(args[1],dec(args[2]));
                    if (id.equals("E4Q-07")) { kind="string-base64"; val=enc(d.getDisplayName()); }
                    if (id.equals("E4Q-08")) { kind="boolean"; val=Boolean.toString(d.isSuite()); }
                    if (id.equals("E4Q-09")) { kind="boolean"; val=Boolean.toString(d.isTest()); }
                    if (id.equals("E4Q-10")) { kind="int"; val=Integer.toString(d.testCount()); }
                    break;
                }
                case "E4Q-11": case "E4Q-12": {
                    int runs=Integer.parseInt(args[1]), failures=Integer.parseInt(args[2]);
                    if (runs<0 || runs>100 || failures<0 || failures>100) throw new IllegalArgumentException("Smoke-only bounded counters 0..100");
                    Result r=new Result(); RunListener listener=r.createListener();
                    listener.testRunStarted(Description.createSuiteDescription("StageG"));
                     for(int i=0;i<runs;i++) { Description td=Description.createTestDescription("Smoke","t"+i); listener.testStarted(td); listener.testFinished(td); }
                     listener.testRunFinished(r);
                    for(int i=0;i<failures;i++) listener.testFailure(new Failure(Description.createTestDescription("Smoke","f"+i),new AssertionError("smoke")));
                    if (id.equals("E4Q-11")) { kind="int"; val=Integer.toString(r.getRunCount()); }
                    else { kind="boolean"; val=Boolean.toString(r.wasSuccessful()); }
                    break;
                }
                default: throw new IllegalArgumentException("Unknown candidate ID");
            }
            System.out.println("OK\t"+kind+"\t"+val);
        } catch (Throwable e) {
            System.out.println("EXCEPTION\t"+e.getClass().getName());
            System.exit(2);
        }
    }
}
