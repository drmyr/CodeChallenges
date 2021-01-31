package dp;

import java.util.Arrays;

public class MinCoinChange {

    /*
     https://leetcode.com/problems/coin-change/

    1: [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    2: [ 0, 1, 1, 2, 2, 3, 3, 4, 4, 5,  5,  6]
    5: [ 0, 1, 1, 2, 2, 1, 2, 2, 3, 3,  2,  3]

     */
    public static int getMinNumberOfCoins(final int[] coins, final int total) {
        Arrays.sort(coins);

        final int[][] memoization = new int[coins.length][total + 1];

        for(int coin = 0; coin < coins.length; coin++) {
            for(int value = 1; value < (total + 1); value++) {
                if(coin == 0) {
                    memoization[coin][value] = coins[coin] > value ? 0 : value;
                } else {
                    if(coins[coin] > value) {
                        memoization[coin][value] = memoization[coin - 1][value];
                    } else {
                        memoization[coin][value] = Math.min(memoization[coin - 1][value], memoization[coin][value - coins[coin]] + 1);
                    }
                }
            }
        }

        return memoization[coins.length - 1][total];
    }

    public static int getMinNumberOfCoinsSmaller(int[] coins, int total) {
        Arrays.sort(coins);

        final int[] memoization = new int[total + 1];

        for(int coin = 0; coin < coins.length; coin++) {
            for(int value = 1; value < total + 1; value++) {
                if(coin == 0) {
                    memoization[value] = coins[coin] > value ? 0 : value;
                } else {
                    if(coins[coin] < value) {
                        memoization[value] = Math.min(memoization[value], memoization[value - coins[coin]] + 1);
                    }
                }
            }
        }
        return memoization[total];
    }
}
