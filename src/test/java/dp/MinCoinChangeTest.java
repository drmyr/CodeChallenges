package dp;

import org.junit.jupiter.api.Test;

import static dp.MinCoinChange.getMinNumberOfCoins;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MinCoinChangeTest {

    @Test
    void getMinNumberOfCoinsTest() {
        assertThat(3, is(equalTo(getMinNumberOfCoins(new int[] {1,2,5}, 11))));
    }

    @Test
    void getMinNumberOfCoinsSmallerTest() {
    }
}