package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static sorting.QuickSort.*;

class QuickSortTest {

    @Test
    void quickSortTest() {
        assertThat(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, is(equalTo(quickSort(new int[] {3,8,4,1,9,2,6,7,5}))));
        assertThat(
            new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},
            is(equalTo(quickSort(new int[] {5,3,13,15,20,19,7,4,9,11,14,2,17,1,6,18,8,10,12,16})))
        );
        final int[] oneToOneHundred = {
            61, 86, 15, 1, 34, 53, 33, 60, 10, 79, 46, 72, 43, 84, 85, 47, 14, 97, 59, 69, 90, 4, 98, 44, 81, 22, 92,
            67, 68, 37, 29, 55, 25, 51, 82, 8, 26, 30, 52, 50, 78, 41, 13, 16, 77, 12, 87, 7, 35, 93, 83, 66, 45, 2,
            57, 20, 32, 23, 39, 31, 18, 24, 64, 3, 63, 70, 74, 95, 36, 76, 5, 71, 6, 19, 73, 56, 94, 80, 9, 40, 62, 17,
            11, 75, 49, 91, 88, 96, 27, 54, 28, 48, 89, 42, 100, 38, 58, 65, 99, 21
        };
        final int[] oneToOneHundredSorted = oneToOneHundred.clone();
        Arrays.sort(oneToOneHundredSorted);
        assertThat(
            oneToOneHundredSorted,
            is(equalTo(quickSort(oneToOneHundred)))
        );
    }

    @Test
    void quickSortThreeTest() {
        assertThat(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, is(equalTo(quickSortThree(new int[] {3,8,4,1,9,2,6,7,5}))));
        final int[] oneToOneHundred = {
            61, 86, 15, 1, 34, 53, 33, 60, 10, 79, 46, 72, 43, 84, 85, 47, 14, 97, 59, 69, 90, 4, 98, 44, 81, 22, 92,
            67, 68, 37, 29, 55, 25, 51, 82, 8, 26, 30, 52, 50, 78, 41, 13, 16, 77, 12, 87, 7, 35, 93, 83, 66, 45, 2,
            57, 20, 32, 23, 39, 31, 18, 24, 64, 3, 63, 70, 74, 95, 36, 76, 5, 71, 6, 19, 73, 56, 94, 80, 9, 40, 62, 17,
            11, 75, 49, 91, 88, 96, 27, 54, 28, 48, 89, 42, 100, 38, 58, 65, 99, 21
        };
        final int[] oneToOneHundredSorted = oneToOneHundred.clone();
        Arrays.sort(oneToOneHundredSorted);
        assertThat(
            oneToOneHundredSorted,
            is(equalTo(quickSortThree(oneToOneHundred)))
        );
    }

    @Test
    void quickSortOtherTest() {
        assertThat(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, is(equalTo(quickSortOther(new int[] {3,8,4,1,9,2,6,7,5}))));
        final int[] oneToOneHundred = {
            61, 86, 15, 1, 34, 53, 33, 60, 10, 79, 46, 72, 43, 84, 85, 47, 14, 97, 59, 69, 90, 4, 98, 44, 81, 22, 92,
            67, 68, 37, 29, 55, 25, 51, 82, 8, 26, 30, 52, 50, 78, 41, 13, 16, 77, 12, 87, 7, 35, 93, 83, 66, 45, 2,
            57, 20, 32, 23, 39, 31, 18, 24, 64, 3, 63, 70, 74, 95, 36, 76, 5, 71, 6, 19, 73, 56, 94, 80, 9, 40, 62, 17,
            11, 75, 49, 91, 88, 96, 27, 54, 28, 48, 89, 42, 100, 38, 58, 65, 99, 21
        };
        final int[] oneToOneHundredSorted = oneToOneHundred.clone();
        Arrays.sort(oneToOneHundredSorted);
        assertThat(
            oneToOneHundredSorted,
            is(equalTo(quickSortOther(oneToOneHundred)))
        );
    }
}