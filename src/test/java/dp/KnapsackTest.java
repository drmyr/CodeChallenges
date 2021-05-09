package dp;

import org.junit.jupiter.api.Test;

import static dp.Knapsack.maxKnapsackValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class KnapsackTest {

    @Test
    void maxKnapsackValueTest() {
        assertThat(90, is(equalTo(maxKnapsackValue(new int[][] {{5, 10}, {4, 40}, {6, 30}, {3, 50}}, 10))));
    }
}