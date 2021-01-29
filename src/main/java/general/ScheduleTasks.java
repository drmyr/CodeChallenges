package general;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ScheduleTasks {

    /*
     https://aonecode.com/interview-question/schedule-tasks
     */
    public static int minTimeToFinish(final int num, final int[] powers, int taskLoad) {
        final PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for(final int power : powers) {
            heap.offer(power);
        }

        int seconds = 0;
        while(taskLoad > 0) {
            final int power = heap.poll();
            taskLoad -= power;
            heap.offer(power / 2);
            seconds++;
        }

        return seconds;
    }
}
