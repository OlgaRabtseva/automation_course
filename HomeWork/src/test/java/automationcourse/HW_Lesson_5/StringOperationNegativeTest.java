package automationcourse.HW_Lesson_5;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationNegativeTest {
    StringOperation operation;

    @BeforeMethod
    public void setUp() {
        operation = new StringOperation();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class)
    public void testGetSubString() {
        assertNotNull(operation.getSubString("kkjsd"));
    }

    @Test
    public void testIndexOfCharNegative() {
        int expectedResult = 1;
        assertNotEquals(operation.indexOfChar("sfjkerrejkhhf"), expectedResult);
    }
}