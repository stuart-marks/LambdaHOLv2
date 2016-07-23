package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
// XXX @Suite.SuiteClasses(value={exercises.Exercises.class})
@Suite.SuiteClasses(value={solutions.Exercises.class})
public class JUnit4TestSuite {
}
