package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.SlidingWindowMaximum.maxSlidingWindow;
import static org.hamcrest.MatcherAssert.assertThat;

class SlidingWindowMaximumTest {

    @Test
    void maxSlidingWindowTest() {
        assertThat(maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3), Matchers.arrayContaining(3,3,5,5,6,7));
        assertThat(maxSlidingWindow(new int[] {1,2,3,2,1,0,0,0}, 3), Matchers.arrayContaining(3,3,3,2,1,0));
    }
}