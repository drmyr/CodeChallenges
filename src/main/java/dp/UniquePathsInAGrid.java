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
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathCount(final int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;
        if(obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) return 0;

        final int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for(int r = 0; r < obstacleGrid.length; r++) {
            for(int c = 0; c < obstacleGrid[r].length; c++) {
                if(r == 0 && c == 0) {
                    dp[r][c] = 1;
                } else if(r == 0) {
                    dp[r][c] = (obstacleGrid[r][c] == 0) ? dp[r][c - 1] : dp[r][c];
                } else if(c == 0) {
                    dp[r][c] = (obstacleGrid[r][c] == 0) ? dp[r - 1][c] : dp[r][c];
                } else {
                    dp[r][c] = (obstacleGrid[r][c] == 0) ? dp[r - 1][c] + dp[r][c - 1] : dp[r][c];
                }
            }
        }

        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}
