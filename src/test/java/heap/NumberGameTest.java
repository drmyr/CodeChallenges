package heap;

import org.junit.jupiter.api.Test;

import static heap.NumberGame.getMaxScore;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class NumberGameTest {

    @Test
    void getMaxScoreTest() {
        assertThat(41, is(equalTo(getMaxScore(3, new int[] {8, 5, 6, 25, 6, 16}))));
    }
}