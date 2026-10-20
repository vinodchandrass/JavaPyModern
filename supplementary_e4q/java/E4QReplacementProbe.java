import org.junit.runner.Description;
public class E4QReplacementProbe {
 public static void main(String[] args) throws Exception {
  System.setOut(new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out),true,java.nio.charset.StandardCharsets.UTF_8));
  Description d=Description.createTestDescription("Example", "replacement");
  System.out.println("E4Q-07-D08\t"+d.getDisplayName());
  System.out.println("E4Q-08-D08\t"+d.isSuite());
  System.out.println("E4Q-09-D08\t"+d.isTest());
  System.out.println("E4Q-10-D08\t"+d.testCount());
 }
}
