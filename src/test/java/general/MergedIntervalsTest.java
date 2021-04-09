package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.MergedIntervals.mergeIntervals;
import static org.hamcrest.MatcherAssert.assertThat;

class MergedIntervalsTest {

    @Test
    void mergeIntervalsTest() {
        assertThat(
            mergeIntervals(new int[][] {{1,4},{4,5}}),
            Matchers.arrayContaining(new int[][] {{1,5}})
        );

        assertThat(
            mergeIntervals(new int[][] {{1,3},{2,6},{8,10},{15,18}}),
            Matchers.arrayContaining(new int[][] {{1,6},{8,10},{15,18}})
        );
    }
}