package automationcourse.HW_Lesson_5;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationPositiveTest {
    StringOperation operation;

    @BeforeMethod
    public void setUp() {
        operation = new StringOperation();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(dataProvider = "calculatePerimeter")
    public void testCalculatePerimeter(int radius, int expectedResult) {
        assertEquals(operation.calculatePerimeter(radius), expectedResult);
    }

    @DataProvider
    public Object[][] calculatePerimeter() {
        return new Object[][]{
                {10, 62},
                {30, 188},
                {2, 12},
                {56, 351}
        };
    }

    @Test
    public void testReplaceString() {
        assertTrue(operation.replaceString("Создать тест-сьют, в который должны входить минимум 3 класса").contains(":)"));
    }

    @Test
    public void testIndexOfChar() {
        int expectedResult = 1;
        assertEquals(operation.indexOfChar("sdfjkerrejkhhf"), expectedResult);
    }

    @Test(dataProvider = "matchTwoStrings")
    public void testMatchTwoStrings(String str1, String str2, boolean condition) {
        assertEquals(operation.matchTwoStrings(str1,str2), condition);
    }

    @DataProvider
    public Object[][] matchTwoStrings() {
        return new Object[][]{
                {"123", "123", true},
                {"Alex", "Pushkin", false},
                {"df112 2545", "dF112 2545", true},
                {"tut", "TUT", true}
        };
    }
}