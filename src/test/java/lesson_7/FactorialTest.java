package lesson_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    private final Factorial factorial = new Factorial();

    @Test
    void testFactorialZero() {
        assertEquals(1, factorial.calculate(0));
    }

    @Test
    void testFactorialOne() {
        assertEquals(1, factorial.calculate(1));
    }

    @Test
    void testFactorialFive() {
        assertEquals(120, factorial.calculate(5));
    }

    @Test
    void testNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-1));
    }
}