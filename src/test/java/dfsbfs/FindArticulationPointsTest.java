package dfsbfs;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static dfsbfs.FindArticulationPoints.criticalConnections;
import static org.hamcrest.MatcherAssert.assertThat;

class FindArticulationPointsTest {

    @Test
    void criticalConnectionsTest() {
        /*
         * B -- C -- D -- E -- F -- H
         * |  /           |  /
         * A              G
         */
        final Character[][] graph = {{'a','b'},{'b','c'},{'a','c'},{'c','d'},{'d','e'},{'e','g'},{'g','f'},{'e','f'},{'f','h'}};

        assertThat(criticalConnections('a', graph), Matchers.arrayContainingInAnyOrder('c','d','e','f'));

        assertThat(criticalConnections('d', graph), Matchers.arrayContainingInAnyOrder('c','d','e','f'));

        assertThat(criticalConnections('h', graph), Matchers.arrayContainingInAnyOrder('c','d','e','f'));

        assertThat(criticalConnections('e', graph), Matchers.arrayContainingInAnyOrder('c','d','e','f'));

        /*
         * 0 -- 1
         * |    |
         * 2 -- 3 -- 4
         * |
         * 5 -- 6
         */
        final Integer[][] graph1 = {{0,1}, {0,2}, {1,3}, {2,3}, {2,5}, {5,6}, {3,4}};

        assertThat(criticalConnections(0, graph1), Matchers.arrayContainingInAnyOrder(2,3,5));

        assertThat(criticalConnections(4, graph1), Matchers.arrayContainingInAnyOrder(2,3,5));

        assertThat(criticalConnections(6, graph1), Matchers.arrayContainingInAnyOrder(2,3,5));

        assertThat(criticalConnections(2, graph1), Matchers.arrayContainingInAnyOrder(2,3,5));

        /*
         https://leetcode.com/problems/critical-connections-in-a-network/

          2 - 0
          |  /
          | /
          1 -- 3
         */
        final Integer[][] graph2 = {{0,1},{1,2},{2,0},{1,3}};
        assertThat(criticalConnections(3, graph2), Matchers.arrayContainingInAnyOrder(1));

        assertThat(criticalConnections(1, graph2), Matchers.arrayContainingInAnyOrder(1));

        assertThat(criticalConnections(2, graph2), Matchers.arrayContainingInAnyOrder(1));

        /*
         https://aonecode.com/amazon-interview-questions/find-critical-nodes

               1    7
               |    |
          2 -- 3 -- 6 -- 0
               |    |
               4    5
         */
        final Integer[][] graph3 = {{2,3},{1,3},{3,4},{3,6},{7,6},{6,5},{6,0}};

        assertThat(criticalConnections(2, graph3), Matchers.arrayContainingInAnyOrder(3,6));

        assertThat(criticalConnections(3, graph3), Matchers.arrayContainingInAnyOrder(3,6));

        assertThat(criticalConnections(0, graph3), Matchers.arrayContainingInAnyOrder(3,6));
    }
}