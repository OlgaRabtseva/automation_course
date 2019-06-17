package automationcourse.HW_Lesson_4;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        CalculatorAdditionalTest.class,
        CalculatorPositiveTest.class,
        CalculatorNegativeTest.class
})

public class TestSuiteCalculator {

}
