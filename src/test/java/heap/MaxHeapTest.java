package heap;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    @Test
    void test() {
        // given
        final Random random = new Random();
        final MaxHeap maxHeap = new MaxHeap();

        // when
        for(int i = 0; i < 40; i++) {
            maxHeap.offer(random.nextInt(0,50));
        }

        // then
        int top = maxHeap.poll();
        while(!maxHeap.isEmpty()) {
            final int next = maxHeap.poll();
            assertTrue(top >= next);
            top = next;
        }
    }
}