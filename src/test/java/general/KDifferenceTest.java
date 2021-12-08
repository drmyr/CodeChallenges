package general;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static general.KDifference.kDifference;
import static org.junit.jupiter.api.Assertions.*;

class KDifferenceTest {

    @Test
    void kDifferenceTest() {
        // given
        final int[] arr = new int[] {6,2,4,6,8,10,12};
        final int kdiff = 2;

        //when
        final int[][] result = kDifference(arr, kdiff);

        // then
        for(final int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }
}