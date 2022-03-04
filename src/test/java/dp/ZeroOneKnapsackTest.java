package dp;

import org.junit.jupiter.api.Test;

import static dp.ZeroOneKnapsack.knapsack;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ZeroOneKnapsackTest {

    @Test
    void knapsackTest() {
        assertThat(22, is(equalTo(knapsack(new int[] {1,2,3,5}, new int[] {1,6,10,16}, 7))));
    }
}