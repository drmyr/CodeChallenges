package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.OptimizeMemoryUsage.optimizeMemoryUsage;
import static org.hamcrest.MatcherAssert.assertThat;

class OptimizeMemoryUsageTest {

    @Test
    void optimizeMemoryUsageTest() {
        assertThat(optimizeMemoryUsage(new int[] {1,7,2,4,5,6}, new int[] {3,1,2}, 6), Matchers.arrayContainingInAnyOrder(new int[][] {{3, 2}, {4, 1}, {5,-1}}));

        assertThat(optimizeMemoryUsage(new int[] {1,7,2,4,5,6}, new int[] {1,1,2}, 10), Matchers.arrayContainingInAnyOrder(new int[][] {{1, 2}}));
    }
}