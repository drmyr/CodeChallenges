package dp;

public class MaxPathInAGrid {

    /**
     * Find a path from the upper-left corner to the lower-right
     * corner of an n × n grid, such that we only move down and right. Each square
     * contains a positive integer, and the path should be constructed so that the sum of
     * the values along the path is as large as possible.
     *
     *  3 7 9 2 7
     *  9 8 3 5 5
     *  1 7 9 8 5
     *  3 8 6 4 10
     *  6 3 9 7 8
     *
     *      3>9>8>7>9>8>5>10>8 = 67
     *
     */
    public static int maxValuedPath(final int[][] matrix) {
        final int[][] dp = new int[matrix.length][matrix[0].length];

        int firstRowRunningSum = 0;
        for(int i = 0; i < matrix[0].length; i++) {
            firstRowRunningSum += matrix[0][i];
            dp[0][i] = firstRowRunningSum;
        }

        int firstColRunningSum = 0;
        for(int i = 0; i < matrix.length; i++) {
            firstColRunningSum += matrix[i][0];
            dp[i][0] = firstColRunningSum;
        }

        int max = 0;
        for(int r = 1; r < matrix.length; r++) {
            for(int c = 1; c < matrix[r].length; c++) {
                dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]) + matrix[r][c];
                max = Math.max(max, dp[r][c]);
            }
        }

        return max;
    }
}
