package mst;

import org.junit.jupiter.api.Test;

import static mst.MinCostToRepairEdges.repairEdgesMinCost;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MinCostToRepairEdgesTest {

    @Test
    void repairEdgesMinCostTest() {

        final int[][] edgesOne = new int[][] {{1,2},{2,3},{3,4},{4,5},{1,5}};
        final int[][] edgesToRepairOne = new int[][] {{1,2,12},{3,4,30},{1,5,8}};
        assertThat(20, is(equalTo(repairEdgesMinCost(5, edgesOne, edgesToRepairOne))));

        final int[][] edgesTwo = new int[][] {{1,2},{2,3},{4,5},{3,5},{1,6},{2,4}};
        final int[][] edgesToRepairTwo = new int[][] {{1,6,410},{2,4,800}};
        assertThat(410, is(equalTo(repairEdgesMinCost(6, edgesTwo, edgesToRepairTwo))));

        final int[][] edgesThree = new int[][] {{1,2},{2,3},{4,5},{5,6},{1,5},{2,4},{3,4}};
        final int[][] edgesToRepairThree = new int[][] {{1,5,110},{2,4,84},{3,4,79}};
        assertThat(79, is(equalTo(repairEdgesMinCost(6, edgesThree, edgesToRepairThree))));
    }
}