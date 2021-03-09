package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static general.MinOfMaximum.minOfMaximums;
import static general.SpiralOrder.spiralOrder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MinOfMaximumTest {

    @Test
    void minOfMaximumsTest() {
        assertThat(64, is(equalTo(minOfMaximums(8, new int[] {62, 64, 77, 75, 71, 60, 79, 75}, 4))));
    }
}