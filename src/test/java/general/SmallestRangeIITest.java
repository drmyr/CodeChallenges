package general;

import org.junit.jupiter.api.Test;

import static general.SmallestRangeII.smallestRangeII;
import static org.junit.jupiter.api.Assertions.*;

class SmallestRangeIITest {

    @Test
    void smallestRangeIITest() {
        smallestRangeII(new int[] {7,8,8,5,2}, 4);
        //smallestRangeII(new int[] {1,4,6,4}, 3);
        //smallestRangeII(new int[] {3,4,7,0}, 5);
    }
}