package lesson_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void testAddition() {
        assertEquals(7, calc.add(3, 4));
    }

    @Test
    void testSubtraction() {
        assertEquals(1, calc.subtract(5, 4));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, calc.multiply(3, 4));
    }

    @Test
    void testDivision() {
        assertEquals(2.5, calc.divide(5, 2), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}