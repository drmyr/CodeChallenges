package general;

import org.junit.jupiter.api.Test;

import static general.FindLowestPrice.findLowestPrice;
import static org.junit.jupiter.api.Assertions.*;

class FindLowestPriceTest {

    @Test
    void findLowestPriceTest() {

        assertEquals(35, findLowestPrice(new String[][] {{"10", "d0", "d1"}, {"15", "EMPTY", "EMPTY"}, {"20", "d1", "EMPTY"}}, new String[][] {{"d0", "1", "27"}, {"d1", "2", "5"}}));
    }
}