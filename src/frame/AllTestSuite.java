package frame;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ AllTests.class, DotTests.class, HashTests.class })
public class AllTestSuite {
}
