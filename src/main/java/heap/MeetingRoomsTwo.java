package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class MeetingRoomsTwo {

    /**
     Leetcode 253

     https://leetcode.com/problems/meeting-rooms-ii/

     Given an array of meeting time intervals intervals where
     intervals[i] = [starti, endi], return the minimum number of conference rooms required.

     Example 1:

     Input: intervals = [[0,30],[5,10],[15,20]]
     Output: 2
     Example 2:

     Input: intervals = [[7,10],[2,4]]
     Output: 1
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(final int[][] intervals) {
        // sort by starting time
        Arrays.sort(intervals, comparingInt(interval -> interval[0]));

        // but poll by ending time
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparingInt(interval -> interval[1]));

        heap.offer(intervals[0]);

        for(int i = 1; i < intervals.length; i++) {
            // if this interval starts before the prior one ends, a new room needs to be scheduled (heap grows in size)
            if(intervals[i][0] < heap.peek()[1]) {
                heap.offer(intervals[i]);

            // otherwise the current meeting will end before the next meeting starts, so the room becomes available
            // (remove it from the heap), and use that room for the next meeting.
            } else {
                heap.poll();
                heap.offer(intervals[i]);
            }
        }

        return heap.size();
    }
}
