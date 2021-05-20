package general;

import java.util.Arrays;

public class LoadBalancer {

    /**
     * https://aonecode.com/amazon-online-assessment-load-balancer
     *
     * Given an array containing only positive integers, return if you can pick two integers from the
     * array which cuts the array into three pieces such that the sum of elements in all pieces is equal.
     *
     * Example 1:
     *
     * Input:  array = [2, 4, 5, 3, 3, 9, 2, 2, 2]
     *
     * Output: true
     *
     * Explanation: choosing the number 5 and 9 results in three pieces [2, 4], [3, 3] and [2, 2, 2]. Sum = 6.
     * @param array
     * @return
     */
    public static Integer[] balanceLoad(final Integer[] array) {
        final int sum = Arrays.stream(array).reduce(0, Integer::sum);

        int leftDiv = 1;
        int rightDiv = array.length - 2;

        int leftSum = array[0];
        int rightSum = array[array.length - 1];
        int middleSum = sum - leftSum - rightSum - array[leftDiv] - array[rightDiv];
        while(leftDiv < rightDiv) {
            if(leftSum == rightSum && leftSum == middleSum) {
                return new Integer[] {array[leftDiv], array[rightDiv]};
            }
            if(leftSum <= rightSum) {
                leftSum += array[leftDiv];
                leftDiv++;
                middleSum -= array[leftDiv];
            } else {
                rightSum += array[rightDiv];
                rightDiv--;
                middleSum -= array[rightDiv];
            }
        }
        return new Integer[] {};
    }
}
