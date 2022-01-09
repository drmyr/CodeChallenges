package general;

import org.junit.jupiter.api.Test;

import static general.SplitArray2.countSplitPoints;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SplitArray2Test {

    @Test
    void countSplitPointsTest() {
        assertThat(2, is(equalTo(countSplitPoints(new int[] {2,3,1,0}))));
        assertThat(0, is(equalTo(countSplitPoints(new int[] {6,-1,9}))));
        assertThat(1, is(equalTo(countSplitPoints(new int[] {9,9,9}))));
        assertThat(1, is(equalTo(countSplitPoints(new int[] {1,1,1,2,3}))));
    }
}