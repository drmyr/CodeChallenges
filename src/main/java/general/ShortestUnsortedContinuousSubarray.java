package general;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://www.algorithmsandme.com/monotonic-queue/
 */
public class ShortestUnsortedContinuousSubarray {

    /**
     * This problem is taken from leetcode and goes like :
     *
     *     Given an integer array, you need to find one continuous subarray
     *     that if you only sort this subarray in ascending order,
     *     then the whole array will be sorted in ascending order, too.
     *     You need to find the shortest such subarray and output its length.
     *
     * Input: [2, 6, 4, 8, 10, 9, 15]
     * Output: 5
     * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
     *
     * Input: [3, 8, 9, 4, 10, 14, 15, 6, 7], length = 9, monotonically increasing = 6
     * Output: 8
     *
     * Input: [3, 5, 2, 6, 4, 1, 7, 8, 9]
     * output: 6
     *
     * EXPLANATION
     *
     * Create a monotonic queue of the indices of values that are strictly increasing.
     *    I.E. if the values are {4, 5, 1, 6} then the indices would be {0, 1, 3}
     * Then keep tabs on the indices of
     *  1) the index of the first entry that was not strictly increasing, and
     *  2) the index of the last entry that was out of order. This is the index of the element that was
     *      farthest to the right of the largest element that was out of order.
     *
     * The index of number 2 above (called `endOutOfOrderIndex` in the code below) marks where should end
     * our subarray.
     *
     * To figure out where we should begin our subarray, we need to loop through the things that are in order
     * to figure where the first thing that was out of order (the value referenced by `firstOutOfOrderIndex` below)
     * belongs.
     *
     * The last thing out of order is equal to the end of things that are out of order.
     * The first thing out of order is not necessarily equal to the start of things that are out of order.
     */
    public static int shortestSubarray(final int[] array) {
        final Deque<Integer> monotonicallyIncreasingIndices = new ArrayDeque<>();
        int firstOutOfOrderIndex = -1;
        int endOutOfOrderIndex = 0;
        for(int i = 0; i < array.length; i++) {
            if(monotonicallyIncreasingIndices.isEmpty() || array[i] > array[monotonicallyIncreasingIndices.getLast()]) {
                monotonicallyIncreasingIndices.add(i);
            } else {
                if(firstOutOfOrderIndex == -1) firstOutOfOrderIndex = i;
                endOutOfOrderIndex = i;
            }
        }

        int startOutOfOrderIndex = 0;
        for(final var nextIndex : monotonicallyIncreasingIndices) {
            if(array[firstOutOfOrderIndex == -1 ? 0 : firstOutOfOrderIndex] < array[nextIndex]) {
                startOutOfOrderIndex = nextIndex;
                break;
            }
        }

        return endOutOfOrderIndex - startOutOfOrderIndex + 1; // +1 because the range is inclusive of the start
    }
}
