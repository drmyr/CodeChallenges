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

    public static int[] quickSortOther(final int[] array) {
        quickSortRec(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortRec(final int[] array, final int pivot, final int end) {
        if(pivot >= end) {
            return;
        }

        int left = pivot + 1;
        int right = end;

        while(left <= right) {
            if(array[left] > array[pivot] && array[right] < array[pivot]) {
                swap(left, right, array);
            }
            if(array[left] <= array[pivot]) {
                left++;
            }
            if(array[right] >= array[pivot]) {
                right--;
            }
        }
        swap(pivot, right, array);
        quickSortRec(array, pivot, right - 1);
        quickSortRec(array, right + 1, end);
    }

    private static void swap(final int left, final int right, final int[] array) {
        final int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }


    public static int[] quickSortThree(final int[] array) {
        quickSortThreeRec(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortThreeRec(final int[] array, final int low, final int high) {
        if(low >= high) {
            return;
        }

        final int partition = quickSortLeaderFollower(array, low, high);
        quickSortThreeRec(array, low, partition - 1);
        quickSortThreeRec(array, partition + 1, high);
    }

    private static int quickSortLeaderFollower(final int[] array, final int low, final int high) {
        final int pivot = array[high];
        int follow = low, leader = low;

        while(leader < high) {
            if(array[leader] <= pivot) {
                swap(follow, leader, array);
                follow++;
            }
            leader++;
        }
        swap(follow, high, array);
        return follow;
    }

}
