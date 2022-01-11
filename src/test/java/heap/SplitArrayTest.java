package heap;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static heap.SplitArray.splitArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitArrayTest {

    @Test
    void splitArrayTest() {
        assertThat(splitArray(new Integer[] {3,2,1,4,0,6,5}), Matchers.arrayContainingInAnyOrder(4,6,5));
        assertThat(splitArray(new Integer[] {6,-1,9}), Matchers.arrayContainingInAnyOrder(9));
        assertEquals(null, splitArray(new Integer[] {9,9,9}));
    }
}