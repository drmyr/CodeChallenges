package general;

public class SplitArray2 {

    /**
     * https://aonecode.com/amazon-interview-questions
     * https://d2qikg3rnxq7n1.cloudfront.net/interviewquestions/240-splitarray2-min.png
     *
     * Given an integer array, split the array into 2 sub arrays such that the sum(subarray1) >= sum(subarray2).
     * Return the number of valid splits
     *
     * Ex. 1
     * arr = [2,3,1,0]
     *
     * Ans.
     * [2,3], [1,0]
     * [2,3,1], [0]
     *
     * There are 2 such splits
     *
     * Algorithm:
     * 1. Loop over the input array twice, once from left to right, and then once from right to left.
     * 2. Store the running sums from step 1 in two separate arrays. For Ex. 1 above, it would look like:
     *      leftToRight = [2,5,6,6], rightToLeft = [6,4,1,0]
     * 3. Compare split points (symbolized using '|' below). If the running sum in the leftToRight array is
     *      greater than the running sum in the rightToLeft array at that split point, then it is a valid split point
     *
     *      Not valid           Valid                   Valid
     *      2 is less than 4    5 is greater than 1     6 is greater than 0
     *      [2|5,6,6]           [2,5|6,6]               [2,5,6|6]
     *      [6|4,1,0]           [6,4|1,0]               [6,4,1|0]
     */

    public static int countSplitPoints(final int[] arr) {
        final int[] leftToRight = new int[arr.length];
        final int[] rightToLeft = new int[arr.length];

        int lrSum = 0; for(int i = 0; i < arr.length; i++) leftToRight[i] = (lrSum += arr[i]);

        int rlSum = 0; for(int i = arr.length - 1; i >= 0; i--) rightToLeft[i] = (rlSum += arr[i]);

        int count = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            if(leftToRight[i] >= rightToLeft[i]) count++;
        }

        return count;
    }
}
