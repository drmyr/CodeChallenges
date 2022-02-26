package general;

import org.junit.jupiter.api.Test;

import static general.ThreeSum.threeSum;
import static org.junit.jupiter.api.Assertions.*;

class ThreeSumTest {

    @Test
    void threeSumTest() {
        threeSum(new int[] {-1,0,1,2,-1,-4});
    }
}