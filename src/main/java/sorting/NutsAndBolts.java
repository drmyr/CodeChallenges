package sorting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NutsAndBolts {

    /**
     * Since {@link java.util.Comparator} is not allowed (or any nut-nut, or bolt-bolt comparison),
     * use QuickSort to sort the two arrays
     * @param nuts
     * @param bolts
     */
    public static int[][] createPairs(final int[] nuts, final int[] bolts) {
        final Deque<int[]> nutPartitions = new ArrayDeque<>();
        final Deque<int[]> boltPartitions = new ArrayDeque<>();
        nutPartitions.offerFirst(new int[] {0, nuts.length - 1});
        boltPartitions.offerFirst(new int[] {0, bolts.length - 1});

        while (!nutPartitions.isEmpty()) {
            final int[] nutRange = nutPartitions.poll();
            if(nutRange[0] < nutRange[1]) {
                int boltMatch = 0; while(bolts[boltMatch] != nuts[nutRange[1]]) boltMatch++;
                final int partition = partition(nuts, bolts[boltMatch], nutRange[0], nutRange[1]);
                nutPartitions.offerFirst(new int[] {nutRange[0], partition - 1});
                nutPartitions.offerLast(new int[] {partition + 1, nutRange[1]});
            }
        }

        while (!boltPartitions.isEmpty()) {
            final int[] boltRange = boltPartitions.poll();
            if(boltRange[0] < boltRange[1]) {
                int nutMatch = 0; while(nuts[nutMatch] != bolts[boltRange[1]]) nutMatch++;
                final int partition = partition(bolts, nuts[nutMatch], boltRange[0], boltRange[1]);
                boltPartitions.offerFirst(new int[] {boltRange[0], partition - 1});
                boltPartitions.offerLast(new int[] {partition + 1, boltRange[1]});
            }
        }

        final int[][] result = new int[nuts.length][];
        for(int i = 0; i < nuts.length; i++) {
            result[i] = new int[] {nuts[i], bolts[i]};
        }

        return result;
    }

    private static int partition(final int[] arr, final int pivotPoint, final int start, final int end) {
        int follower = (start - 1);

        for(int leader = start; leader < end; leader++) {
            if(arr[leader] <= pivotPoint) {
                follower++;

                final int swap = arr[leader];
                arr[leader] = arr[follower];
                arr[follower] = swap;
            }
        }

        final int swap = arr[follower + 1];
        arr[follower + 1] = arr[end];
        arr[end] = swap;

        return follower + 1;
    }

    /**
     * Nuts represented as array of character
     * char nuts[] = {‘@’, ‘#’, ‘$’, ‘%’, ‘^’, ‘&’}
     * Bolts represented as array of character
     * char bolts[] = {‘$’, ‘%’, ‘&’, ‘^’, ‘@’, ‘#’}
     * @param nuts
     * @param bolts
     * @return
     */
    public static void nutsAndBolts(final Integer[] nuts, final Integer[] bolts) {
        nutsAndBoltsRecursive(nuts, bolts, 0, nuts.length - 1);
    }

    public static void nutsAndBoltsRecursive(final Integer[] nuts, final Integer[] bolts, final int low, final int high) {
        if(low >= high) {
            return;
        }

        final int partition = partitionTwo(nuts, low, high, bolts[low]);
        partitionTwo(bolts, low, high, nuts[partition]);
        nutsAndBoltsRecursive(nuts, bolts, low, partition - 1);
        nutsAndBoltsRecursive(nuts, bolts, partition + 1, high);
    }

    private static int partitionTwo(final Integer[] collection, final int low, final int high, final int pivot) {
        int follow = low, lead = low;

        while (lead < high) {
            if (collection[lead] < pivot) {
                swap(collection, follow, lead);
                follow++;
            } else if (collection[lead] == pivot) {
                swap(collection, lead, high);
                lead--;
            }

            lead++;
        }

        swap(collection, follow, high);

        return follow;
    }

    private static void swap(final Integer[] collection, final int left, final int right) {
        final int temp = collection[left];
        collection[left] = collection[right];
        collection[right] = temp;
    }

}
