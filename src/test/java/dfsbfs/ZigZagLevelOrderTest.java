package dfsbfs;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static dfsbfs.MaximalMinimumValuePath.maxPathScore;
import static dfsbfs.ZigZagLevelOrder.zigzagLevelOrder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ZigZagLevelOrderTest {

    @Test
    void zigzagLevelOrderTest() {
        final AtomicInteger ai = new AtomicInteger(0);
        final BinaryNode fifteen = new BinaryNode(15, ai.incrementAndGet(), null, null);
        final BinaryNode seven = new BinaryNode(7, ai.incrementAndGet(), null, null);
        final BinaryNode twenty = new BinaryNode(20, ai.incrementAndGet(), fifteen, seven);
        final BinaryNode nine = new BinaryNode(9, ai.incrementAndGet(), null, null);
        final BinaryNode root = new BinaryNode(3, ai.incrementAndGet(), nine, twenty);
        zigzagLevelOrder(root);
    }
}