package dp;

public class SubsetSumProblem {

    public static boolean canFormSumFromSubset(final int[] set, final int target) {
        final boolean[][] dp = new boolean[set.length][target + 1];
        for(int i = 0; i < set.length; i++) dp[i][0] = true;

        for(int row = 0; row < set.length; row++) {
            for(int col = 1; col < target + 1; col++) {
                if(row == 0) {
                    dp[row][col] = set[row] == col;
                } else {
                    if(set[row] > col) {
                        dp[row][col] = dp[row - 1][col];
                    } else {
                        dp[row][col] = dp[row - 1][col - set[row]];
                    }
                }
            }
        }
        return dp[set.length - 1][target];
    }
}
