package lesson_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    private final NumberComparator comparator = new NumberComparator();

    @Test
    void testGreater() {
        assertEquals("10 больше 5", comparator.compare(10, 5));
    }

    @Test
    void testLess() {
        assertEquals("3 меньше 7", comparator.compare(3, 7));
    }

    @Test
    void testEqual() {
        assertEquals("6 равен 6", comparator.compare(6, 6));
    }
}