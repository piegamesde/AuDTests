package frame;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ HashTests1.class, HashTests2.class, HashTests3.class, DotTests1.class, DotTests2.class, AllTests.class })
public class AllTestSuite {
}
