package general;

public class MaximumProductSubarray {

    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * Not mine.
     */
    public static int maxProduct(final int[] nums) {
        int left = 1, right = 1, ans = nums[0];

        for(int i = 0; i < nums.length; i++) {
            // reset value to 1 if we encounter a zero.
            left = left == 0 ? 1 : left;
            right = right == 0 ? 1 : right;

            left *= nums[i];
            right *= nums[nums.length - 1 - i];

            ans = Math.max(ans, Math.max(left, right));

        }

        return ans;
    }
}
