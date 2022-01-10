package dp;

import org.junit.jupiter.api.Test;

import static dp.LongestIncreasingSubsequence.lengthOfLongestIncreasingSubsequenceBottomUp;
import static dp.LongestIncreasingSubsequence.lengthOfLongestIncreasingSubsequenceTopDown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LongestIncreasingSubsequenceTest {

    @Test
    void lengthOfLongestIncreasingSubsequenceTest() {
        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceBottomUp(new int[] {6,2,5,1,7,4,8,3}))));
        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceBottomUp(new int[] {10,9,2,5,3,7,101,18}))));
        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceBottomUp(new int[] {0,1,0,3,2,3}))));
        assertThat(1, is(equalTo(lengthOfLongestIncreasingSubsequenceBottomUp(new int[] {7,7,7,7,7,7,7}))));

        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceTopDown(new int[] {6,2,5,1,7,4,8,3}))));
        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceTopDown(new int[] {10,9,2,5,3,7,101,18}))));
        assertThat(4, is(equalTo(lengthOfLongestIncreasingSubsequenceTopDown(new int[] {0,1,0,3,2,3}))));
        assertThat(1, is(equalTo(lengthOfLongestIncreasingSubsequenceTopDown(new int[] {7,7,7,7,7,7,7}))));
    }
}