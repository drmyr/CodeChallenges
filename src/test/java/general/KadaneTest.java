package general;

import org.junit.jupiter.api.Test;

import static general.Kadane.maxSubArray;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class KadaneTest {

    @Test
    void maxSubArrayTest() {
        final int[] test = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        assertThat(6, is(equalTo(maxSubArray(test))));

        final int[] testTwo = new int[] {5,4,-1,7,8};
        assertThat(23, is(equalTo(maxSubArray(testTwo))));
    }
}