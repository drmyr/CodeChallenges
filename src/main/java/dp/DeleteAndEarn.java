package dp;

import java.util.*;

public class DeleteAndEarn {

    /**
     * https://www.youtube.com/watch?v=7FCemBxvGw0
     *
     *
     */
    public static int deleteAndEarn(final int[] input) {
        final TreeMap<Integer, Integer> valueCount = new TreeMap<>();
        for(final int value : input) {
            valueCount.merge(value, 1, Integer::sum);
        }

        final List<Integer> orderedSet = new ArrayList<>(valueCount.keySet());

        if(orderedSet.size() < 2) return orderedSet.get(0) * valueCount.get(orderedSet.get(0));

        final int[] dp = new int[orderedSet.size()];

        dp[0] = orderedSet.get(0) * valueCount.get(orderedSet.get(0));
        dp[1] = orderedSet.get(1) * valueCount.get(orderedSet.get(1));
        if(orderedSet.get(1) != orderedSet.get(0) + 1) {
            dp[1] += dp[0];
        }
        dp[1] = Math.max(dp[0], dp[1]);
        for(int i = 2; i < orderedSet.size(); i++) {
            int newValue = orderedSet.get(i) * valueCount.get(orderedSet.get(i));
            if(orderedSet.get(i) != (orderedSet.get(i - 1) + 1)) {
                newValue += dp[i - 1];
            } else {
                newValue += dp[i - 2];
            }
            dp[i] = Math.max(dp[i - 1], newValue);
        }

        return dp[orderedSet.size() - 1];
    }
}
