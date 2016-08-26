package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
    exercises.A_LambdasAndMethodReferences.class,
    exercises.Exercises.class
})
public class JUnit4TestSuite {
}
