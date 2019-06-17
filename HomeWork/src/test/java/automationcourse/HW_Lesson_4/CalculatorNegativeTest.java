package automationcourse.HW_Lesson_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorNegativeTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void divideOperationNegativeTest() {
        Exception ex = assertThrows(ArithmeticException.class, () -> calculator.divideOperation(1, 0));
        assertEquals("/ by zero", ex.getMessage());
    }
}
