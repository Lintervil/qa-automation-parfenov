package lesson_7;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTestNG {

    private final TriangleArea triangle = new TriangleArea();

    @Test
    public void testValidTriangle() {
        assertEquals(triangle.calculate(3, 4, 5), 6.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidSides() {
        triangle.calculate(0, 5, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleInequality() {
        triangle.calculate(1, 2, 3);
    }
}