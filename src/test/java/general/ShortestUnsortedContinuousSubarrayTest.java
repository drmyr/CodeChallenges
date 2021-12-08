package general;

import org.junit.jupiter.api.Test;

import static general.ShortestUnsortedContinuousSubarray.shortestSubarray;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ShortestUnsortedContinuousSubarrayTest {

    @Test
    void shortestSubarrayTest() {
        // given
        final var array1 = new int[] {2, 6, 4, 8, 10, 9, 15};
        final var array2 = new int[] {3, 8, 9, 4, 10, 14, 15, 6, 7};
        final var array3 = new int[] {3, 5, 2, 6, 4, 1, 7, 8, 9};
        final var array4 = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // when
        final var result1 = shortestSubarray(array1);
        final var result2 = shortestSubarray(array2);
        final var result3 = shortestSubarray(array3);
        final var result4 = shortestSubarray(array4);

        // then
        assertThat(5, is(equalTo(result1)));
        assertThat(8, is(equalTo(result2)));
        assertThat(6, is(equalTo(result3)));
        assertThat(0, is(equalTo(result4)));
    }
}