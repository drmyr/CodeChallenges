package general;

import java.util.function.Function;

public class StorageOptimization {

    /*
     https://aonecode.com/aplusplus/interviewctrl/getInterview/90812391
     */
    public static int largestSpace(final int colDivisions, final int rowDivisions, final int[] colRemovals, final int[] rowRemovals) {
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
