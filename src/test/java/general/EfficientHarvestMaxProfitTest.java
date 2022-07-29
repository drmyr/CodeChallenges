package general;

import org.junit.jupiter.api.Test;

import static general.EfficientHarvestMaxProfit.maxProfit;
import static org.junit.jupiter.api.Assertions.*;

class EfficientHarvestMaxProfitTest {

    @Test
    void maxProfitTest() {
        assertEquals(16, maxProfit(2, new int[] {1,5,1,3,7,-3}));
        assertEquals(-2, maxProfit(1, new int[] {3, -5}));
        assertEquals(0, maxProfit(1, new int[] {-3, -6, 3, 6}));
    }
}