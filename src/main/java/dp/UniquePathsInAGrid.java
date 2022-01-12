package dp;

public class UniquePathsInAGrid {

    /**
     * https://leetcode.com/problems/unique-paths-ii
     *
     * Given a grid of size m * n, let us assume you are starting at (1, 1) and your goal is to reach (m, n).
     * At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space are marked as 1 and 0 respectively in the grid.
     *
     * Examples:
     *
     * Input: [[0, 0, 0],
     *         [0, 1, 0],
     *         [0, 0, 0]]
     * Output : 2
     * There is only one obstacle in the middle.
     * @param matrix
     * @return
     */
    public static int uniquePathCount(final int[][] matrix) {
        if(matrix[0][0] == 1) return 0;

        final int[][] dp = new int[matrix.length][matrix[0].length];

        for(int c = 0; c < matrix[0].length; c++) {
            if(matrix[0][c] == 1) {
                break;
            } else {
                dp[0][c] = 1;
            }
        }

        for(int r = 0; r < matrix.length; r++) {
            if(matrix[r][0] == 1) {
                break;
            } else {
                dp[r][0] = 1;
            }
        }

        for(int r = 1; r < matrix.length; r++) {
            for(int c = 1; c < matrix[r].length; c++) {
                if(matrix[r][c] == 1) {
                    continue;
                }
                if(matrix[r - 1][c] != 1) {
                    dp[r][c] = dp[r][c] + 1;
                }
                if(matrix[r][c - 1] != 1) {
                    dp[r][c] = dp[r][c] + 1;
                }
            }
        }

        return dp[matrix.length - 1][matrix[0].length - 1];
    }
}
