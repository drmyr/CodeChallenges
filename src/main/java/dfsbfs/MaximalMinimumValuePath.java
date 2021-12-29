package dfsbfs;

import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import static java.util.Comparator.comparingInt;

public class MaximalMinimumValuePath {

    /**
     * https://aonecode.com/path-with-maximum-minimum-value
     * https://www.junhaow.com/lc/problems/heap/1102_path-with-maximum-minimum-value.html
     *
     * The problem is very poorly worded on the website. The problem is trying to ask "given a 2-dimensional matrix,
     * how many unique paths are there that pass through the cell of the matrix with the lowest value". A unique path cannot
     * pass through the same cell twice. So, in the following matrix:
     *
     * [7,5,3]
     * [2,0,9]
     * [4,5,9]
     *
     * how many unique paths are there that pass through [1,1], ie the '0' cell?
     *
     * Answer: there are 3 such paths:
     * [7>5>0>9>9]
     * [7>2>0>9>9]
     * [7>2>0>5>9]
     *
     * The fact that you can move in any direction makes this problem a little more difficult.
     *
     * Given a matrix of integers A with R rows and C columns,
     * find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
     *
     * Use a Max Heap to always choose the highest valued path available, and keep track of the minimum encountered
     * along that path.
     *
     * @param matrix
     * @return
     */
    public static int maxPathScore(final int[][] matrix) {
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparingInt((final int[] coords) -> matrix[coords[0]][coords[1]]).reversed());
        heap.offer(new int[] {0, 0});
        final boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        final BiPredicate<Integer, Integer> canEnter = (final Integer x, final Integer y) ->
            x >= 0 && x < matrix.length && y >= 0 && y < matrix[x].length && !visited[x][y];

        final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int min = Integer.MAX_VALUE;
        while(!heap.isEmpty()) {
            final int[] coords = heap.poll();

            if(coords[0] == matrix.length - 1 && coords[1] == matrix[coords[0]].length - 1) {
                break;
            }

            visited[coords[0]][coords[1]] = true;
            min = Math.min(min, matrix[coords[0]][coords[1]]);
            for(final int[] dir : dirs) {
                final int newX = coords[0] + dir[0];
                final int newY = coords[1] + dir[1];
                if(canEnter.test(newX, newY)) {
                    heap.offer(new int[] {newX, newY});
                }
            }
        }
        return min;
    }
}
