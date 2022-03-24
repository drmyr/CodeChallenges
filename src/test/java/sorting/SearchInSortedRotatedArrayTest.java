package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static sorting.SearchInSortedRotatedArray.*;

class SearchInSortedRotatedArrayTest {

    @Test
    void partitionSearchTest() {
        assertThat(1, is(equalTo(partitionSearch(new int[] {3,1}))));
        assertThat(2, is(equalTo(partitionSearch(new int[] {3,5,1}))));
        assertThat(6, is(equalTo(partitionSearch(new int[] {4,5,6,7,8,9,0,1,2}))));
    }

    @Test
    void binarySearchTest() {
        final int[] test = new int[] {3,1};
        final int inflection = partitionSearch(test);
        assertThat(0, is(equalTo(binarySearch(Arrays.copyOfRange(test, 0, inflection), 3))));

        final int[] test2 = new int[] {4,5,1,2,3};
        final int inflection2 = partitionSearch(test2);
        assertThat(2, is(equalTo(binarySearch(Arrays.copyOfRange(test2, 0, inflection2), 3))));
    }

    @Test
    void searchTest() {
        assertThat(-1, is(equalTo(search(new int[] {4,5,6,7,0,1,2}, 3))));
        assertThat(2, is(equalTo(search(new int[] {5,1,3}, 3))));
        assertThat(0, is(equalTo(search(new int[] {3,1}, 3))));
    }
}