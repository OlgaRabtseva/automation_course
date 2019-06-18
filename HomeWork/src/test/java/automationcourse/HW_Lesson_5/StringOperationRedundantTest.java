package automationcourse.HW_Lesson_5;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationRedundantTest {
    StringOperation operation;

    @BeforeMethod
    public void setUp() {
        operation = new StringOperation();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(enabled = false)
    public void testCalculatePerimeterMatch() {
        assertTrue(operation.calculatePerimeter(10) > 50);
    }

    @Test
    public void testReplaceStringNotNull() {
        assertNotNull(operation.replaceString("минимум 3 класса"));
    }
}