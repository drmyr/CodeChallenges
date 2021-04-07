package general;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class MinDistinctValues {

    /**
     * https://aonecode.com/aplusplus/interviewctrl/getInterview/87419231
     *
     * Given an int array of size N, return the minimum number of distinct values
     * in the array after k numbers are removed from it.
     * Function
     * int minDistinctValues(int[] array, int k)
     *
     * Constraints
     * N < 10 6 < N
     * 0 < k <= N
     *
     * Examples
     * Input
     * array = [1, 1, 1, 2, 2, 2, 3]
     * k = 2
     *
     * Output
     * 2
     *
     * Explanation
     * Remove 3 and any one of the other numbers.
     * The array has at least 2 distinct values 1 and 2 remaining.
     * @param array
     * @param k
     * @return
     */
    public static int minDistinctValues(final int[] array, final int k) {
        final Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(final int value : array) {
            frequencyMap.merge(value, 1, Integer::sum);
        }

        final PriorityQueue<Integer> heap = new PriorityQueue<>(comparingInt(frequencyMap::get));
        for(final int value : array) {
            heap.offer(value);
        }

        int i = k;
        while(i-- >= 0) {
            heap.poll();
        }

        return new HashSet<>(heap).size();
    }
}
