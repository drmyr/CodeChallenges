package dp;

import java.util.Arrays;

public class NumberOfWaysToMakeChange {

    /**
     * The number of ways to make change given a collection of coins with specific values
     * and a target value
     */
    public static int numberOfWaysToMakeChange(final int amount, final int[] denoms) {
        Arrays.sort(denoms);

        // We need to have a zero value, and the number of ways to make change for zero is one.
        final int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(final int denom : denoms) {
            for(int i = denom; i <= amount; i++) {
                // if the denom is less than or equal to the current amount (which is always true here since we
                // declare `i = denom`) then the number of ways to change is equal however many ways there was to
                // make change for the amount minus the current coin.
                dp[i] += dp[i - denom];
            }
        }

        return dp[amount];
    }
}
