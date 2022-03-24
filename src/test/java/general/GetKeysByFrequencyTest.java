package general;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static general.GetKeysByFrequency.orderKeysByFrequency;
import static org.junit.jupiter.api.Assertions.*;

class GetKeysByFrequencyTest {

    @Test
    void orderKeysByFrequencyTest() {
        for(final int[] arr : orderKeysByFrequency(new int[] {2,1,3,3,3,1,2,2,5,6,7})) {
            System.out.println(Arrays.toString(arr));
        }
    }
}