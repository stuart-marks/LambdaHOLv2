package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
    exercises.A_Lambdas.class,
    exercises.B_Comparators.class,
    exercises.C_DefaultMethods.class,
    exercises.D_SimpleStreams.class,
    exercises.E_IntermediateStreams.class,
    exercises.F_AdvancedStreams.class,
    exercises.G_MatcherScanner.class,
    exercises.H_Challenges.class
})
public class JUnit4TestSuite {
}
