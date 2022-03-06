package monotonicqueue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static monotonicqueue.MaximalRectangle.largestAreaOfHistogramMonotonicQueue;
import static monotonicqueue.MaximalRectangle.maximalRectangle;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MaximalRectangleTest {

    @Test
    void largestAreaOfHistogramMonotonicQueueTest() {
        final int[][] a = {{9}, {3, 1, 6, 4, 3, 2, 1, 5}},
                b = {{12}, {6, 2, 5, 4, 5, 1, 6}},
                c = {{10}, {2, 1, 5, 6, 2, 3}},
                d = {{15}, {1, 2, 3, 4, 5, 3, 3, 2}},
                e = {{4}, {2,4}};
        for(final int[][] curr : List.of(a, b, c, d, e)) {
            assertThat(curr[0][0], is(equalTo(largestAreaOfHistogramMonotonicQueue(curr[1]))));
        }
    }

    @Test
    void maximalRectangleTest() {
        final char[][] matrixOne = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        assertThat(6, is(equalTo(maximalRectangle(matrixOne))));

        final char[][] matrixTwo = {
            {'0','1'},
            {'1','0'}
        };
        assertThat(1, is(equalTo(maximalRectangle(matrixTwo))));
    }
}