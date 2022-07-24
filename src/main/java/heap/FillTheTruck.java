package heap;

import java.util.PriorityQueue;

import static java.util.Comparator.comparing;

public class FillTheTruck {

    /**
     * https://cybergeeksquad.co/2021/06/fill-the-truck-maximum-units-on-a-truck.html
     *
     * Fill The Truck Maximum Units on a Truck Solution
     *
     * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes,
     * where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
     *
     *     numberOfBoxesi is the number of boxes of type i.
     *     numberOfUnitsPerBoxiis the number of units in each box of the type i.
     *
     * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
     * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
     */
    public static int fillTheTruck(final int[][] boxCountAndCapacities, final int truckCapacity) {
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparing((final int[] boxCountAndLoad) -> boxCountAndLoad[1]).reversed());
        for(final int[] boxCountAndCapacity : boxCountAndCapacities) heap.offer(boxCountAndCapacity);

        int currCapacity = truckCapacity;
        int maxLoad = 0;
        while(!heap.isEmpty()) {
            final int[] boxCountAndCapacity = heap.poll();
            if(currCapacity >= boxCountAndCapacity[0]) {
                currCapacity -= boxCountAndCapacity[0];
                maxLoad += boxCountAndCapacity[0] * boxCountAndCapacity[1];
            } else {
                maxLoad += currCapacity * boxCountAndCapacity[1];
                break;
            }
        }

        return maxLoad;
    }
}
