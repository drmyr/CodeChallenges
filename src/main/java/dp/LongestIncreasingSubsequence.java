package dp;

public class LongestIncreasingSubsequence {

    /*
     https://leetcode.com/problems/longest-increasing-subsequence

     Not mine
     */
    public static int lengthOfLongestIncreasingSubsequenceBottomUp(final int[] nums) {
        final int[] dp = new int[nums.length];
        int max = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = 0;
            for (int d = i + 1; d < nums.length; d++) {
                if (nums[i] < nums[d]) {
                    dp[i] = Math.max(dp[i], dp[d]);
                }
            }
            dp[i] = dp[i] + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /*
     Not mine
     */
    public static int lengthOfLongestIncreasingSubsequenceTopDown(final int[] nums) {
        final int[] dp = new int[nums.length];
        int max = dp[0];
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1; // longest subsequence at a given position is at least as long as the element itself.
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
