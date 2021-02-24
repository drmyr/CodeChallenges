package heap;

import org.junit.jupiter.api.Test;

import static heap.MinCostToAddNewRoads.minCostForRoad;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MinCostToAddNewRoadsTest {

    @Test
    void minCostForRoadTest() {
        /*
         [1] -<5>- [2]
           \       /
           <6>   <1>
             \   /
              [3]
         */
        assertThat(6, is(equalTo(minCostForRoad(3, new int [][] {{1,2,5},{1,3,6},{2,3,1}}))));


        /*
         [1] -<3>- [2]

         [3] -<4>- [4]

         Disconnected graph
         */
        assertThat(-1, is(equalTo(minCostForRoad(4, new int[][] {{1,2,3},{3,4,4}}))));
    }
}