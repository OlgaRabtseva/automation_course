package automationcourse.HW_Lesson_6;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AppTest {
    App app;

    @BeforeMethod
    public void setUp() {
        app = new App();
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testCalculatePerimeterPositive() {
        assertTrue(app.calculatePerimeter(5) == 31);
    }

    @Test
    public void testCalculatePerimeter() {
        assertNotEquals(app.calculatePerimeter(-5), app.calculatePerimeter(5));
    }

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class)
    public void testGetSubStringNegative() {
        assertNotNull(app.getSubString("as"));
    }
    @Test
    public void testGetSubString() {
        assertNotNull(app.getSubString("asrghhdfgjfghjgf"));
    }

    @Test
    public void testReplaceStringNegative() {
        assertFalse(app.replaceString("Вэтомжепроектедобавитьзависимость").contains("!"));
    }


    @Test
    public void testReplaceStringPositive() {
        assertTrue(app.replaceString("В этом же проекте добавить зависимость").contains("!"));
    }

    @Test
    public void testIndexOfChar() {
        assertTrue(app.indexOfChar("sxxdgf") > 1);
    }

    @Test
    public void testMatchTwoStrings() {
        assertFalse(app.matchTwoStrings("5454","454"));
    }

    @Test
    public void testStringListCreator() {
        String str1 = "e";
        String str2 = "q";
        int number = 5;
        assertNotNull(app.stringListCreator(str1,str2,number));
    }
}
