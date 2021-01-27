package general;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ClosestPointsToOrigin {
    public static int[][] kClosest(final int[][] points, final int K) {
        final PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparing((final int[] point) ->
            Math.sqrt(Math.pow(Math.abs(point[0]), 2) + Math.pow(Math.abs(point[1]), 2))));

        for(final int[] point : points) {
            heap.offer(point);
        }

        final int[][] result = new int[K][];

        int count = K;
        while(count > 0) {
            result[--count] = heap.poll();
        }

        return result;
    }
}
