package general;

import java.util.PriorityQueue;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class ClosestPointsToOrigin {

    /*
      https://leetcode.com/problems/k-closest-points-to-origin/
     */
    public static int[][] kClosest(final int[][] points, int K) {

        final Function<int[], Double> euclideanDistance = (final int[] point) ->
                Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));

        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparing(euclideanDistance));

        for(final int[] point : points) {
            heap.offer(point);
        }

        final int[][] result = new int[K][];

        while(K > 0) {
            result[--K] = heap.poll();
        }

        return result;
    }
}
