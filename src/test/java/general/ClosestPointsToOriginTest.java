package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static general.ClosestPointsToOrigin.kClosest;

class ClosestPointsToOriginTest {

    @Test
    void kClosestTest() {
        assertThat(kClosest(new int[][] {{1,3},{-2,2}}, 1), Matchers.arrayContainingInAnyOrder(new int[][] {{-2, 2}}));
        assertThat(kClosest(new int[][] {{3,3},{5,-1},{-2,4}}, 2), Matchers.arrayContainingInAnyOrder(new int[][] {{3,3},{-2,4}}));
    }
}