package dp;

import org.junit.jupiter.api.Test;

import static dp.ShoppingOptions.getNumberOfOptions;
import static dp.UniquePathsInAGrid.uniquePathCount;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UniquePathsInAGridTest {

    @Test
    void uniquePathCountTest() {
        assertThat(2, is(equalTo(uniquePathCount(new int[][] {{0,0,0},{0,1,0},{0,0,0}}))));
    }
}