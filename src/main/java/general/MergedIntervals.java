package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class MergedIntervals {

    /**
     * https://leetcode.com/problems/merge-intervals/
     *
     * Example 1:
     *
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     *
     * @param intervals
     * @return
     */
    public static int[][] mergeIntervals(final int[][] intervals) {
        Arrays.sort(intervals, comparingInt(arr -> arr[0]));

        final List<int[]> mergedIntervals = new ArrayList<>();

        int[] currentInterval = intervals[0];
        for(final int[] interval : intervals) {
            if(currentInterval[1] >= interval[0]) {
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = interval;
            }
        }
        mergedIntervals.add(currentInterval);

        return mergedIntervals.toArray(int[][]::new);
    }
}
