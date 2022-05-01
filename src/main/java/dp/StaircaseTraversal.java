package dp;

public class StaircaseTraversal {

    public int staircaseTraversal(final int height, final int maxSteps) {
        final int[] dp = new int[height + 1];
        dp[0] = 1;
        dp[1] = 1;

        int sum = 2;
        for(int i = 2; i <= height; i++) {
            dp[i] = maxSteps == 1 ? 1 : sum;

            if(i >= maxSteps) {
                sum -= dp[i - maxSteps];
            }

            sum += dp[i];
        }

        return dp[height];
    }
}
