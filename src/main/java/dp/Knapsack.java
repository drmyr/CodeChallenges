package dp;

import java.util.Arrays;

import static java.util.Comparator.comparingInt;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
 */
public class Knapsack {


    public static boolean[] possibleWeightCombos(final int[] weights) {
        final int sum = Arrays.stream(weights).sum();
        // plus 1 for the zero possibility
        final boolean[][] possibilities = new boolean[weights.length + 1][sum + 1];
        for(int i = 0; i < weights.length + 1; i++) possibilities[i][0] = true;

        for(int weightOption = 1; weightOption < weights.length + 1; weightOption++) {
            for(int specificWeight = 1; specificWeight < sum + 1; specificWeight++) {
                possibilities[weightOption][specificWeight] = possibilities[weightOption - 1][specificWeight];
                if(specificWeight >= weights[weightOption - 1]) {
                    possibilities[weightOption][specificWeight] |= possibilities[weightOption - 1][specificWeight - weights[weightOption - 1]];
                }
            }
        }

        return possibilities[weights.length];
    }
    /**
     * knapsack capacity = 10 units
     * item count = 4
     * weight and value input = [[5, 10], [4, 40], [6, 30], [3, 50]]
     *          0   1   2   3   4   5   6   7   8   9   10
     * (3, 50) [0,  0,  0, 50, 50, 50, 50, 50, 50, 50,  50]
     * (4, 40) [0,  0,  0, 50, 50, 50, 50, 90, 90, 90,  90]
     * (5, 10) [0,  0,  0, 50, 50, 50, 50, 90, 90, 90,  90]
     * (6, 30) [0,  0,  0, 50, 50, 50, 50, 90, 90, 90,  90]
     *
     */
    public static int maxKnapsackValue(final int[][] weightsAndValues, final int knapsackCapacity) {
        if(weightsAndValues.length == 0 || knapsackCapacity == 0) return 0;

        Arrays.sort(weightsAndValues, comparingInt((final int[] weightAndValue) -> weightAndValue[0]));
        final int[][] dp = new int[weightsAndValues.length][knapsackCapacity + 1];

        for(int row = 0; row < weightsAndValues.length; row++) {
            for(int col = 1; col <= knapsackCapacity; col++) {
                if(row == 0) {
                    dp[row][col] = weightsAndValues[row][0] <= col ? weightsAndValues[row][1] : 0;
                } else {
                    if(weightsAndValues[row][0] > col) {
                        dp[row][col] = dp[row - 1][col];
                    } else {
                        dp[row][col] = Math.max(dp[row - 1][col], weightsAndValues[row][1] + dp[row - 1][col - weightsAndValues[row][0]]);
                    }
                }
            }
        }
        return dp[weightsAndValues.length - 1][knapsackCapacity];
    }

    public static boolean[] possibleWeightCombosMinSpace(final int[] weights) {
        final int sum = Arrays.stream(weights).sum();
        // plus 1 for the zero possibility
        final boolean[] possibilities = new boolean[sum + 1];
        possibilities[0] = true;

        for(int weight = 0; weight < weights.length; weight++) {
            for(int specificWeight = sum; specificWeight > 0; specificWeight--) {
                possibilities[weight] |= possibilities[specificWeight - weight];
            }
        }

        return possibilities;
    }
}
