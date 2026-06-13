package lesson_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    private final TriangleArea triangle = new TriangleArea();

    @Test
    void testValidTriangle() {
        assertEquals(6.0, triangle.calculate(3, 4, 5), 0.001);
    }

    @Test
    void testInvalidSides() {
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(0, 5, 5));
    }

    @Test
    void testTriangleInequality() {
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(1, 2, 3));
    }
}