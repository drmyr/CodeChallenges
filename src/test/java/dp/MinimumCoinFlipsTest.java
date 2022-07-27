package dp;

import org.junit.jupiter.api.Test;

import static dp.MinimumCoinFlips.minimumFlipsMonotoneIncreasing;
import static org.junit.jupiter.api.Assertions.*;

class MinimumCoinFlipsTest {

    @Test
    void minimumFlipsMonotoneIncreasingTest() {
        assertEquals(2, minimumFlipsMonotoneIncreasing("THHHTH"));
    }
}