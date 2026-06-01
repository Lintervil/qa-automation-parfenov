package lesson_7;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTestNG {

    private final NumberComparator comparator = new NumberComparator();

    @Test
    public void testCompare() {
        assertEquals(comparator.compare(10, 5), "10 больше 5");
        assertEquals(comparator.compare(3, 7), "3 меньше 7");
        assertEquals(comparator.compare(6, 6), "6 равен 6");
    }
}