package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static general.StorageOptimization.largestCake;
import static general.StorageOptimization.largestSpace;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageOptimizationTest {

    @Test
    void largestSpaceTest() {
        assertThat(4, is(equalTo(largestSpace(6, 6, new int[] {4}, new int[] {2}))));
        assertThat(4, is(equalTo(largestSpace(3, 3, new int[] {2}, new int[] {2}))));
        assertThat(12, is(equalTo(largestSpace(3, 2, new int[] {1,2,3}, new int[] {1,2}))));
    }

    @Test
    void largestCakeTest() {
        record Test(int answer, int height, int width, int[] horizontalCuts, int[] verticalCuts) {}
        final List<Test> tests = List.of(
            new Test(4, 5, 4, new int[] {1,2,4}, new int[] {1,3}),
            new Test(6, 5, 4, new int[] {3,1}, new int[] {1}),
            new Test(9, 5, 4, new int[] {3}, new int[] {3})
        );

        for(final Test test : tests) {
            assertEquals(test.answer(), largestCake(test.height(), test.width(), test.horizontalCuts(), test.verticalCuts()));
        }
    }
}