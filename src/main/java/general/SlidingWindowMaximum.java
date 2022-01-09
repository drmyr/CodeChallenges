package general;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximum {

    /**
     * https://leetcode.com/problems/sliding-window-maximum/
     *
     * You are given an array of integers nums, there is a sliding window of size k
     * which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     *
     * Return the max sliding window.
     *
     * Example 1:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     *
     * Constraints:
     *
     *     1 <= nums.length <= 105
     *     -104 <= nums[i] <= 104
     *     1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public static Integer[] maxSlidingWindow(final int[] nums, final int k) {
        /**
         * A queue of elements that are strictly decreasing. The max item is always the first item in the queue.
         */
        class MonotonicMaxQueue {
            final Deque<Integer> queue = new ArrayDeque<>();

            void push(final Integer value) {
                if(!queue.isEmpty() && queue.getFirst() < value) {
                    queue.clear();
                }
                queue.offerLast(value);
            }

            Integer max() {
                return queue.getFirst();
            }

            void pop(final Integer value) {
                if(!queue.isEmpty() && queue.peekFirst().equals(value)) {
                    queue.pollFirst();
                }
            }
        }

        final MonotonicMaxQueue monotonicQueue = new MonotonicMaxQueue();
        final List<Integer> maxInWindow = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            if(i < k - 1) {
                monotonicQueue.push(nums[i]);
            } else {
                monotonicQueue.push(nums[i]);
                maxInWindow.add(monotonicQueue.max());
                monotonicQueue.pop(nums[i - k + 1]);
            }
        }

        return maxInWindow.toArray(Integer[]::new);
    }
}
