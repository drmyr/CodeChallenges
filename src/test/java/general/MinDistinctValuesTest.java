package general;

import org.junit.jupiter.api.Test;

import static general.MinDistinctValues.minDistinctValues;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MinDistinctValuesTest {

    @Test
    void minDistinctValuesTest() {
        assertThat(2, is(equalTo(minDistinctValues(new int[] {1,1,1,2,2,2,3}, 2))));
        assertThat(2, is(equalTo(minDistinctValues(new int[] {1,1,2,4,4,3,3,3,5}, 4))));
    }
}