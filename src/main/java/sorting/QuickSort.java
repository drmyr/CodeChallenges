package sorting;

import java.util.ArrayDeque;
import java.util.Deque;

public class QuickSort {

    public static int[] quickSort(final int[] input) {
        final Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[] {0, input.length - 1});

        while(!deque.isEmpty()) {
            final int[] bounds = deque.pollFirst();
            if(bounds[0] < bounds[1]) {
                final int partition = partition(input, bounds[0], bounds[1]);
                deque.offerFirst(new int[] {bounds[0], partition - 1});
                deque.offerLast(new int[] {partition + 1, bounds[1]});
            }
        }

        return input;
    }

    private static int partition(final int[] arr, final int begin, final int end) {
        final int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                final int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        final int properlySortedPosition = i + 1;
        final int swapTemp = arr[properlySortedPosition];
        arr[properlySortedPosition] = arr[end];
        arr[end] = swapTemp;

        return properlySortedPosition;
    }
}
