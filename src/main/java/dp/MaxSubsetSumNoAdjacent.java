package dp;

public class MaxSubsetSumNoAdjacent {

    public static int maxSubsetSumNoAdjacent(final int[] array) {
        if(array.length == 0) return 0;
        if(array.length < 2) return array[0];
        if(array.length == 2) return Math.max(array[0], array[1]);

        final int[] dp = new int[array.length];
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);

        for(int i = 2; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1], array[i] + dp[i - 2]);
        }

        return dp[array.length - 1];
    }
}
