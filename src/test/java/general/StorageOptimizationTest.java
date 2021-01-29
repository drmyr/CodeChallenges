package general;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StorageOptimizationTest {

    @Test
    void largestSpaceTest() {
        assertThat(4, is(equalTo(StorageOptimization.largestSpace(6, 6, new int[] {4}, new int[] {2}))));
        assertThat(4, is(equalTo(StorageOptimization.largestSpace(3, 3, new int[] {2}, new int[] {2}))));
        assertThat(12, is(equalTo(StorageOptimization.largestSpace(3, 2, new int[] {1,2,3}, new int[] {1,2}))));
    }
}