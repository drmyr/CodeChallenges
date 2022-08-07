package dp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicReference;

public class JumpGameII {

    public static int jump(final int[] nums) {
        final int[] dp = new int[nums.length];

        final Deque<int[]> queue = new ArrayDeque<>();

        int[] currDis = {0, nums[0]};
        int dequeueCounter = 1;
        for(int i = 1; i < nums.length; i++) {
            if(currDis[1] == 0) {
                //final int[] newDis = queue.pollFirst();

                int[] newDis = queue.pollFirst();
                while((i - 1 - newDis[0]) < newDis[1]) newDis = queue.pollFirst();

                dequeueCounter++;
                currDis = new int[] {i, newDis[1] - (i - 1 - newDis[0])};
            }
            dp[i] = dequeueCounter;
            currDis[1]--;
            queue.offerLast(new int[] {i, nums[i]});

        }

        return dp[nums.length - 1];
    }

    /**
     * we need to loop backwards thru the array so that the memoization can faithfully reflect that we have already
     * exhaustively visited the subsequent points from a given point.
     * @param nums
     * @return
     */
    public static int jumpRecDFS(int[] nums) {
        final AtomicReference<Integer> minJumpCount = new AtomicReference<>(Integer.MAX_VALUE);
        rec(nums, 0, minJumpCount, 0);
        return minJumpCount.get();
    }

    private static void rec(int[] nums, int idx, AtomicReference<Integer> minJumpCount, int jumpCount) {
        if(idx >= nums.length - 1) {
            minJumpCount.set(Math.min(minJumpCount.get(), jumpCount));
            return;
        }

        if(nums[idx] == 0) {
            return;
        }

        for(int jump = 1; jump <= nums[idx]; jump++) {
            int nextPos = idx + jump;
            rec(nums, nextPos, minJumpCount, jumpCount + 1);
        }
    }

    /**
     * move the left pointer to right + 1
     * and then always jump the right pointer to the farthest allowable position
     * @param jumps
     * @return
     */
    public static int jumpGameOptimized(final int[] jumps) {
        int result = 0;
        int l = 0, r = 0;

        while(r < jumps.length - 1) {
            int farthestAllowable = 0;
            for(int i = l; i <= r; i++) {
                farthestAllowable = Math.max(farthestAllowable, i + jumps[i]);
            }
            l = r + 1;
            r = farthestAllowable;
            result++;
        }

        return result;
    }
}
