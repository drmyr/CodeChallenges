package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.MergeTwoSortedLists.mergeSortedLists;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedListsTest {

    @Test
    void mergeSortedListsTest() {
        assertThat(new Integer[] {1,1,2,3,4,4}, Matchers.arrayContaining(mergeSortedLists(new int[] {1,2,4}, new int[] {1,3,4})));
    }
}