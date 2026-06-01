package lesson_7;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTestNG {

    private final Factorial factorial = new Factorial();

    @Test
    public void testFactorialZero() {
        assertEquals(factorial.calculate(0), 1);
    }

    @Test
    public void testFactorialFive() {
        assertEquals(factorial.calculate(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumber() {
        factorial.calculate(-1);
    }
}