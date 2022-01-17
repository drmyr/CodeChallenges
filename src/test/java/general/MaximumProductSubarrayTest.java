package general;

import org.junit.jupiter.api.Test;

import static general.MaximumProductSubarray.maxProduct;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MaximumProductSubarrayTest {

    @Test
    void maxProductTest() {
        assertThat(6, is(equalTo(maxProduct(new int[] {2,3,-2,4}))));
    }
}