package dfsbfs;

import org.junit.jupiter.api.Test;

import static dfsbfs.UniquePathsII.uniquePathsWithObstacles;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class UniquePathsIITest {

    @Test
    void uniquePathsWithObstaclesTest() {
        final int[][] matrixOne = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        assertThat(2, is(equalTo(uniquePathsWithObstacles(matrixOne))));
    }
}