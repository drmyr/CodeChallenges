package heap;

import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class LoadCargo {

    /**
     * There are N container ships and M items in each ship. (ship[i] pais with items[i])
     *
     * You also have K extra items that need to be shipped as well. How many full shipments can be made?
     *
     * ships.length is guaranteed to equal items.length
     *
     * Ex1.
     * ships = [3,4,6,5]
     * items = [3,3,4,4]
     * K = 2
     *
     * Answer is 3
     * Because ships[0] == items[0], the first ship is full.
     * for ships[1], one additional item can be added, so add it and reduce K by one
     * for ships[3], one additional item can be added, so add it and reduce K by one
     *
     * At this point, ships[0], ships[1], and ships[3] are full, so return 3.
     */
    public static int maxCargoLoad(final int[] ships, final int[] items, int extraItems) {
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparingInt(kvp -> kvp[0] - kvp[1]));
        for(int i = 0; i < ships.length; i++) heap.offer(new int[] {ships[i], items[i]});

        int result = 0;
        while(!heap.isEmpty() && extraItems > 0) {
            final int[] next = heap.poll();
            final int needed = next[0] - next[1];
            if(extraItems >= needed) {
                extraItems -= needed;
                result++;
            }
        }

        return result;
    }
}
