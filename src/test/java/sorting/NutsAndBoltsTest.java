package sorting;

import org.junit.jupiter.api.Test;

import static sorting.NutsAndBolts.createPairs;
import static org.junit.jupiter.api.Assertions.*;

class NutsAndBoltsTest {

    @Test
    void createPairsTest() {
        final int[] nuts = {5,7,2,9,6,1,0,4,8,3};
        final int[] bolts = {6,3,8,0,2,1,9,5,7,4};
        for(final int[] pair : createPairs(nuts, bolts)) {
            assertEquals(pair[0], pair[1]);
        }
    }
}