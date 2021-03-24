package heap;

import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class ConcurrentSchedules {

    /**
     * https://leetcode.com/discuss/interview-question/679396/Bloomberg-or-Onsite-or-Concurrent-Meetings
     * @param startAndEndTimes
     * @return
     */
    public static int mostConcurrentSchedules(final int[][] startAndEndTimes) {
        final int startTime = 0, endTime = 1;
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparingInt(arr -> arr[endTime]));

        int max = 0;
        for(final int[] startAndEndTime : startAndEndTimes) {
            while(!heap.isEmpty() && heap.peek()[endTime] < startAndEndTime[startTime]) {
                heap.poll();
            }
            heap.offer(startAndEndTime);
            max = Math.max(max, heap.size());
        }

        return max;
    }
}
