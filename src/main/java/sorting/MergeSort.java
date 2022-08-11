package sorting;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(final int[] array) {
        if (array.length <= 1) return array;

        final int midIndex = array.length / 2;
        final int[] leftHalf = Arrays.copyOfRange(array, 0, midIndex);
        final int[] rightHalf = Arrays.copyOfRange(array, midIndex, array.length);
        return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    private static int[] mergeSortedArrays(final int[] leftHalf, final int[] rightHalf){
        final int[] sortedArray = new int[leftHalf.length + rightHalf.length];

        int left = 0, right = 0, arrIdx = 0;

        while(left < leftHalf.length && right < rightHalf.length) {
            if(leftHalf[left] <= rightHalf[right]) {
                sortedArray[arrIdx++] = leftHalf[left++];
            } else {
                sortedArray[arrIdx++] = rightHalf[right++];
            }
        }
        while(left < leftHalf.length) {
            sortedArray[arrIdx++] = leftHalf[left++];
        }
        while(right < rightHalf.length) {
            sortedArray[arrIdx++] = rightHalf[right++];
        }
        return sortedArray;
    }
}
