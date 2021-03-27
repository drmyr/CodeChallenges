package dp;

import org.junit.jupiter.api.Test;

import static dp.ShoppingOptions.getNumberOfOptions;
import static dp.ShoppingOptions.getNumberOfOptionsMinSpace;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ShoppingOptionsTest {

    @Test
    void getNumberOfOptionsTest() {
        assertThat(4, is(equalTo(getNumberOfOptions(new int[] {2,3}, new int[] {4}, new int[] {2,3}, new int[] {1,2}, 10))));

        assertThat(4, is(equalTo(getNumberOfOptionsMinSpace(new int[] {2,3}, new int[] {4}, new int[] {2,3}, new int[] {1,2}, 10))));
    }
}