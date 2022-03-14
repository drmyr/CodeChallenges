package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static trees.InOrderTreeTraversal.inOrderTraversalImperative;

class InOrderTreeTraversalTest {

    @Test
    void inOrderTraversalImperativeTest() {
        final AtomicInteger ai = new AtomicInteger(0);
        final BinaryNode one = new BinaryNode(1, ai.incrementAndGet(), null, null);
        final BinaryNode four = new BinaryNode(4, ai.incrementAndGet(), null, null);
        final BinaryNode two = new BinaryNode(2, ai.incrementAndGet(), one, four);
        final BinaryNode six = new BinaryNode(6, ai.incrementAndGet(), null, null);
        final BinaryNode seven = new BinaryNode(7, ai.incrementAndGet(), null, null);
        final BinaryNode five = new BinaryNode(5, ai.incrementAndGet(), six, seven);
        final BinaryNode three = new BinaryNode(3, ai.incrementAndGet(), two, five);

        inOrderTraversalImperative(three);
    }
}