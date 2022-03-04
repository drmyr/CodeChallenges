package dp;

import java.util.Arrays;

import static java.util.Comparator.comparingInt;

public class ZeroOneKnapsack {

    /**
     * https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60
     * @param weights
     * @param profits
     * @param capacity
     * @return
     */
    public static int knapsack(final int[] weights, final int[] profits, final int capacity) {
        final int[][] weightProfitPair = new int[weights.length][];
        for(int i = 0; i < weights.length; i++) {
            weightProfitPair[i] = new int[] {weights[i], profits[i]};
        }
        Arrays.sort(weightProfitPair, comparingInt(wp -> wp[0]));

        final int[][] dp = new int[weights.length][capacity + 1];

        for(int i = 1; i <= capacity; i++) {
            if(weights[0] <= i) {
                dp[0][i] = profits[0];
            }
        }

        for(int r = 1; r < weightProfitPair.length; r++) {
            for(int c = 1; c <= capacity; c++) {
                dp[r][c] = dp[r - 1][c]; // we can at least take what we have already taken
                if(weightProfitPair[r][0] <= c) { // see if we can take this item
                    // and if we can take it, would we make more profit
                    final int newProfit = weightProfitPair[r][1];
                    final int weightPenalty = weightProfitPair[r][0];
                    final int reducedCapacity = c - weightPenalty;
                    dp[r][c] = Math.max(dp[r][c], newProfit + dp[r - 1][reducedCapacity]);
                }
            }
        }

        return dp[weights.length - 1][capacity];
    }
}
