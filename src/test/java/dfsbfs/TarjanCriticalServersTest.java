package dfsbfs;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static dfsbfs.TarjanCriticalServers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TarjanCriticalServersTest {

    @Test
    void findStronglyConnectedComponentsTest() {
        // https://www.youtube.com/watch?v=wUgWX0nc4NY @ 11 mins 57 secs
        final int[][] graph = {{3,7},{7,3},{3,4},{7,5},{4,5},{5,6},{6,4},{6,0},{5,0},{6,2},{2,0},{0,1},{1,2}};
        assertThat(findStronglyConnectedComponentsDirected(8, graph), Matchers.arrayContainingInAnyOrder(new Integer[][] {{0,1,2}, {4,5,6}, {3,7}}));

        final int[][] graph2 = {{0,1},{1,2},{2,0},{1,3}};
        assertThat(findStronglyConnectedComponentsDirected(4, graph2), Matchers.arrayContainingInAnyOrder(new Integer[][] {{0,1,2}}));
    }

    @Test
    void findCriticalNodesUndirectedTest() {
        /*
         https://leetcode.com/problems/critical-connections-in-a-network/

          2 - 0
          |  /
          | /
          1 -- 3
         */
        final int[][] graph = {{0,1},{1,2},{2,0},{1,3}};
        assertThat(findCriticalNodesUndirected(4, graph), Matchers.arrayContainingInAnyOrder(1));

//        final int[][] graph2 = {{2,3},{3,2},{1,3},{3,1},{3,4},{4,3},{3,6},{6,3},{7,6},{6,7},{6,5},{5,6},{6,0},{0,6}};
//        assertThat(findCriticalNodes(8, graph2), Matchers.arrayContainingInAnyOrder(3,6));

          /*
            0 --> 1
            |     |
            v     v
            2 --> 3 --> 4
            |
            v
            5 --> 6
           */
        final int[][] graph3 = {{0,1}, {0,2}, {1,3}, {2,3}, {2,5}, {5,6}, {3,4}};
        assertThat(findCriticalNodesUndirected(7, graph3), Matchers.arrayContainingInAnyOrder(2,3,5));
    }

    @Test
    void findCriticalEdgesUndirectedTest() {

    }
}