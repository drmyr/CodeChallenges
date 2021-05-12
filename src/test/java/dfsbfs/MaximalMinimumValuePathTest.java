package dfsbfs;

import org.junit.jupiter.api.Test;

import static dfsbfs.MaximalMinimumValuePath.maxPathScore;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MaximalMinimumValuePathTest {

    @Test
    void maxPathScoreTest() {
        assertThat(3, is(equalTo(maxPathScore(new int[][] {{7,5,3},{2,0,9},{4,5,9}}))));
        assertThat(4, is(equalTo(maxPathScore(new int[][] {{5,4,5},{1,2,6},{7,4,6}}))));
        assertThat(2, is(equalTo(maxPathScore(new int[][] {{2,2,1,2,2,2},{1,2,2,2,1,2}}))));
        assertThat(3, is(equalTo(maxPathScore(new int[][] {{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}}))));
    }
}