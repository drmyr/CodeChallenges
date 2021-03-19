package heap;

import java.util.List;
import java.util.PriorityQueue;

public class MinCostToConnectRopes {

    public static int minCost(final List<Integer> ropes) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>(ropes);
        int totalCost = 0;
        while(queue.size() > 1) {
            final int cost = queue.poll() + queue.poll();
            queue.add(cost);
            totalCost += cost;
        }
        return totalCost;
    }
}
