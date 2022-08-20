package heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void offer() {
        // given
        final Random random = new Random();
        final MinHeap minHeap = new MinHeap();

        // when
        for(int i = 1; i < 30; i++) {
            minHeap.offer(random.nextInt(1, 100));
        }

        // then
        minHeap.offer(0);
        assertEquals(30, minHeap.getSize());
        int top = minHeap.poll();
        while(!minHeap.isEmpty()) {
            final int next = minHeap.poll();
            assertTrue(top <= next);
            top = next;
        }

    }

}