package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.MaximumBoundedArray.maxBoundedArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class MaximumBoundedArrayTest {

    @Test
    void maxBoundedArrayTest() {
        assertThat(maxBoundedArray(4, 10, 12), Matchers.arrayContaining(11,12,11,10));
        assertNull(maxBoundedArray(6, 1, 3));
    }
}