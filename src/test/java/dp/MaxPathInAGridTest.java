package dp;

import org.junit.jupiter.api.Test;

import static dp.MaxPathInAGrid.maxValuedPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MaxPathInAGridTest {

    @Test
    void maxValuedPathTest() {
        final int[][] matrix = {{3, 7, 9, 2, 7},
                                {9, 8, 3, 5, 5},
                                {1, 7, 9, 8, 5},
                                {3, 8, 6, 4, 10},
                                {6, 3, 9, 7, 8}};
        assertThat(67, is(equalTo(maxValuedPath(matrix))));
    }
}