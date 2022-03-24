package dfsbfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;

public class UniquePathsII {

    /**
     * https://leetcode.com/problems/unique-paths-ii/
     */
    public static int uniquePathsWithObstacles(final int[][] obstacleGrid) {
        final Deque<int[]> paths = new ArrayDeque<>();
        paths.offerLast(new int[] {0,0});

        final int[][] dirs = {{0,1},{1,0}};

        final Predicate<int[]> canEnter = (next) ->
            next[0] >= 0 && next[0] < obstacleGrid.length &&
            next[1] >= 0 && next[1] < obstacleGrid[next[0]].length &&
            obstacleGrid[next[0]][next[1]] != 1;

        int count = 0;
        while(!paths.isEmpty()) {
            final int[] next = paths.pollFirst();

            if(next[0] == obstacleGrid.length - 1 && next[1] == obstacleGrid[next[0]].length - 1) {
                count++;
            } else {
                for(final int[] dir : dirs) {
                    final int newRow = next[0] + dir[0];
                    final int newCol = next[1] + dir[1];
                    final int[] newCoord = {newRow, newCol};
                    if(canEnter.test(newCoord)) {
                        paths.offerLast(newCoord);
                    }
                }
            }
        }

        return count;
    }
}
