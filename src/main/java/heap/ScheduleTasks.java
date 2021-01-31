package heap;

import java.util.PriorityQueue;

import static java.util.Comparator.reverseOrder;

public class ScheduleTasks {

    /*
     https://aonecode.com/interview-question/schedule-tasks
     */
    public static int minTimeToFinish(final int num, final int[] powers, int taskLoad) {
        final PriorityQueue<Integer> heap = new PriorityQueue<>(reverseOrder());
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
