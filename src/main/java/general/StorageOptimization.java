package general;

import java.util.Arrays;
import java.util.function.Function;

public class StorageOptimization {

    /*
     https://aonecode.com/aplusplus/interviewctrl/getInterview/90812391

     Removing row and column dividers to create the largest possible cubby in the shelving unit.
     Since all rows are guaranteed to intersect all columns, just find the longest consecutive number of
     removed column dividers, and multiply it by the longest consecutive number of row dividers.
     */
    public static int largestSpace(final int colDivisions, final int rowDivisions, final int[] colRemovals, final int[] rowRemovals) {
        Arrays.sort(colRemovals);
        Arrays.sort(rowRemovals);
        final Function<int[], Integer> longestRun = (final int[] removals) -> {
            int tail = 0;
            int head = 1;
            int longest = 1;
            while(head < removals.length) {
                if(removals[head] == (removals[head - 1] + 1)) {
                    head++;
                } else {
                    tail = head;
                    head++;
                }
                longest = Math.max(longest, (head - tail));
            }
            return longest + 1;
        };
        return longestRun.apply(colRemovals) * longestRun.apply(rowRemovals);
    }
}
