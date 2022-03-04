package monotonicqueue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static monotonicqueue.LargestRectangleOfHistogram.*;

class LargestRectangleOfHistogramTest {

    @Test
    void largestRectangleOfHistogramTest() {
        final int[][] a = { {9}, {3, 1, 6, 4, 3, 2, 1, 5}},
                      b = {{12}, {6, 2, 5, 4, 5, 1, 6}},
                      c = {{10}, {2, 1, 5, 6, 2, 3}},
                      d = {{15}, {1, 2, 3, 4, 5, 3, 3, 2}};
        for(final int[][] curr : List.of(a, b, c, d)) {
            assertThat(curr[0][0], is(equalTo(largestRectangleOfHistogram(curr[1]))));
        }
    }

    @Test
    void largestRectangleOfHistogramMonotonicQueueTest() {
        final int[][] a = { {9}, {3, 1, 6, 4, 3, 2, 1, 5}},
                      b = {{12}, {6, 2, 5, 4, 5, 1, 6}},
                      c = {{10}, {2, 1, 5, 6, 2, 3}},
                      d = {{15}, {1, 2, 3, 4, 5, 3, 3, 2}};
        for(final int[][] curr : List.of(a, b, c, d)) {
            assertThat(curr[0][0], is(equalTo(largestRectangleOfHistogramMonotonicQueue(curr[1]))));
        }
    }
}