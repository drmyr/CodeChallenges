package general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoscalePolicyTest {

    @Test
    void computeScalePolicy() {
        // when
        final int instanceCount = AutoscalePolicy.computeScalePolicy(2, new int[] {25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80});

        // then
        Assertions.assertEquals(2, instanceCount);
    }
}