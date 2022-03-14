package dfsbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Predicate;

public class RobotInPyramid {

    /**
     * https://www.aonecode.com/coding-interview-questions/Robot-in-Maze-Problem-Count-Number-of-Ways-in-Matrix
     *
     * A robot starts at the lower left corner of a matrix, and must go to the lower right corner.
     * The robot can only move up-right, right, and down-right. How many ways can the robot make it to the end?
     *
     * Ex 1.
     * [[ , , , ],
     *  [ , , , ],
     *  [S, , ,E]]
     *
     *  Robot must go from S to E. There are 4 total ways:
     *  [2,0],[2,1],[2,2],[2,3]
     *  [2,0],[1,1],[2,2],[2,3]
     *  [2,0],[2,1],[1,2],[2,3]
     *  [2,0],[1,1],[1,2],[2,3]
     */
    public static int numberOfWaysToEnd(final int[][] matrix) {
        final int[][] trimmedMatrix = trimMatrixToHeightOfPyramid(matrix);
        final int[][] dirs = {{0, 1}, {1, 1}, {-1, 1}};

        final Deque<int[]> pathDeque = new ArrayDeque<>();
        pathDeque.offerLast(new int[] {trimmedMatrix.length - 1, 0});

        final Predicate<int[]> haveReachedTheEnd = (path) ->
            path[0] == trimmedMatrix.length - 1 && path[1] == trimmedMatrix[trimmedMatrix.length - 1].length - 1;

        final List<int[]> allPaths = new ArrayList<>();

        while(!pathDeque.isEmpty()) {
            final int[] next = pathDeque.pollFirst();

            if(haveReachedTheEnd.test(next)) {
                allPaths.add(next);
            } else {
                for(final int[] dir : dirs) {
                    final int newRow = next[0] + dir[0];
                    final int newCol = next[1] + dir[1];
                    if(canEnterTrimmedMatrix(trimmedMatrix, newRow, newCol)) {
                        pathDeque.offerLast(new int[] {newRow, newCol});
                    }
                }
            }
        }

        return allPaths.size();
    }

    /**
     * After trimming the matrix to the height of the pyramid, we are left with this (with indexes):
     *     0 1 2 3 4
     * 0  [_,_,x,_,_]
     * 1  [_,x,x,x,_]
     * 2  [x,x,x,x,x]
     *
     *  X's label all cell's that can be moved to by the robot. From here, we must have logic for the following cases:
     *  1. assert the x is below the diagonal formed from [0,2] to [2,4]
     *  2. asser the x is below the diagonal formed from [2,0] to [0,2]
     *
     *  For case 1, the relationship is that the given column minus the midcolumn must be still be greater than or equal to the row.
     *  For case 2, the relationship is that the row plus the column must be greater than or equal to the midcolumn.
     * @param matrix
     * @param row
     * @param col
     * @return
     */
    public static boolean canEnterTrimmedMatrix(final int[][] matrix, final int row, final int col) {
        if(row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[row].length - 1) return false;

        final int midColumn = matrix[row].length / 2;
        if(col >= midColumn) {  // right side of pyramid
            return row >= (col - midColumn);
        } else {                // left side of pyramid
            final int oneLessIfEven = matrix[row].length % 2 == 0 ? 1 : 0;
            return (row + col) >= midColumn - oneLessIfEven;
        }
    }

    public static int[][] trimMatrixToHeightOfPyramid(final int[][] matrix) {
        final int heightOfPyramid = (int)Math.ceil(matrix[0].length / 2d);
        if(matrix.length > heightOfPyramid) {
            final int[][] upToPyramidHeight = new int[heightOfPyramid][];
            int pyramidStart = 0;
            for(int i = (matrix.length - heightOfPyramid); i < matrix.length; i++) {
                upToPyramidHeight[pyramidStart++] = matrix[i];
            }
            return upToPyramidHeight;
        } else {
            return matrix;
        }
    }
}
