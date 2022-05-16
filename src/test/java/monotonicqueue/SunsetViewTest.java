package monotonicqueue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static monotonicqueue.SunsetView.sunsetViews;
import static org.junit.jupiter.api.Assertions.*;

class SunsetViewTest {

    @Test
    void sunsetViewsTest() {
        final int[] buildings = {3,5,4,4,3,1,3,2};

        System.out.println(Arrays.toString(sunsetViews(buildings, "EAST").toArray()));

        System.out.println(Arrays.toString(sunsetViews(buildings, "WEST").toArray()));
    }
}