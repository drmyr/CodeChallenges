package general;

import org.junit.jupiter.api.Test;


import static general.MinOfMaximum.minOfMaximums;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MinOfMaximumTest {

    @Test
    void minOfMaximumsTest() {
        assertThat(64, is(equalTo(minOfMaximums(8, new int[] {62, 64, 77, 75, 71, 60, 79, 75}, 4))));
    }
}