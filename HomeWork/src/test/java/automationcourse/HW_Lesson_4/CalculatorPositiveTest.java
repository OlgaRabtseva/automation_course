package automationcourse.HW_Lesson_4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculatorPositiveTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void plusOperationTest2() {
        int result = calculator.plusOperation(500, 200);
        assertEquals(700, result);
    }

    @Test
    void multipleOperationTest() {
        int result = calculator.multipleOperation(5, 3);
        assertEquals(15, result);

    }

    @ParameterizedTest
    @CsvSource({"500, 5, 505", "122, 44, 166", "-11, -5454, -5465"})
    void plusOperationTest2(long val1, long val2, long expectedResult) {
        int result = calculator.plusOperation(val1, val2);
        assertEquals(result, expectedResult);
    }

    @Test
    void divideOperationPositiveTest() {
        int result = calculator.divideOperation(5, 3);
        assertEquals(1, result);
    }

    @Test
    @Disabled
    void divideOperationPositiveTest2() {
        int result = calculator.divideOperation(-16, 4);
        assertEquals(-4, result);
    }

    @Test
    @DisplayName("Super important test for minus operation")
    void minusOperationTest() {
        int result = calculator.minusOperation(9, 18);
        assertEquals(-9, result);
    }

    @Test
    void getMessageTest() {
        String message = calculator.getMessage("*");
        assertNotNull(message);
    }
}