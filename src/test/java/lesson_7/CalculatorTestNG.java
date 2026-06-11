package lesson_7;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CalculatorTestNG {

    private final Calculator calc = new Calculator();

    @Test
    public void testAddition() {
        assertEquals(calc.add(3, 4), 7);
    }

    @Test
    public void testDivision() {
        assertEquals(calc.divide(5, 2), 2.5, 0.001);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        calc.divide(10, 0);
    }
}