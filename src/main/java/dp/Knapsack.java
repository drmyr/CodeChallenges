package dp;

import java.util.Arrays;

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
