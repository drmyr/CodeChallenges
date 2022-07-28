package general;

import java.util.ArrayList;
import java.util.List;

public class GridConnectionCount {

    /**
     * https://cybergeeksquad.co/2022/02/grid-connections-amazon-oa-solution.html#Example_1
     *
     * A supply chain manager at Amazon Logisitcs wants to determine the number of connections between warehouses,
     * represented as nodes on a grid. A grid with m rows and n columns is used to form a cluster of nodes.
     * If a point in the grid has a value of 1, then it represents a node.
     *
     * Each node in the cluster has a level associated with it. A node located in the ith row of the grid is a level inode.
     *
     * Here are the rules for creating a cluster:
     *
     *     Every node at a level connects to the next level that contains at least 1 node
     *     (i.e., every node at level i connects to all the nodes at level k where k > i and k is the first level after level i than contains at least one note).
     *     When i reaches the last level in the grid, no more connections are possible.
     *
     * Given such a grid, please help the supply chain manager by finding the number of connections present in the cluster.
     * Input
     *
     *     grid: the nodes grid
     *
     * Output
     *
     * the total number of connections
     * Examples
     * Example 1:
     *
     * Input: 1grid = [[1, 1, 1], [0, 1, 0], [0, 0, 0], [1, 1, 0]]
     *
     * Output: 5
     * @return
     */
    public static int countGridConnections(final int[][] connections) {
        if(connections.length < 2) return 0;

        final List<Integer> rowCounts = new ArrayList<>();
        for(int r = 0; r < connections.length; r++) {
            int nodeCount = 0;
            for(int c = 0; c < connections[r].length; c++) {
                if(connections[r][c] == 1) {
                    nodeCount++;
                }
            }

            if(nodeCount > 0) {
                rowCounts.add(nodeCount);
            }
        }

        int connectionCount = 0;
        for(int i = 0; i < rowCounts.size() - 1; i++) {
            connectionCount += (rowCounts.get(i) * rowCounts.get(i + 1));
        }

        return connectionCount;
    }
}
