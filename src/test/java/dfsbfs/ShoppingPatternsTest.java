package dfsbfs;

import org.junit.jupiter.api.Test;

import static dfsbfs.ShoppingPatterns.getMinScore;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ShoppingPatternsTest {

    @Test
    void getMinScoreTest() {
        assertThat(3, is(equalTo(getMinScore(6, 6, new int[] {1,2,2,3,4,5}, new int[] {2,4,5,5,5,6}))));
        assertThat(2, is(equalTo(getMinScore(5, 6, new int[] {1,1,2,2,3,4}, new int[] {2,3,3,4,4,5}))));
    }
}