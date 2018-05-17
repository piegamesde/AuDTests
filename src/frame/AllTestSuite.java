package frame;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ HashTests.class, HashTests2.class, DotTests.class, AllTests.class })
public class AllTestSuite {
}
