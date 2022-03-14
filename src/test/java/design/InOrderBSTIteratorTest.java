package design;

import models.BinaryNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InOrderBSTIteratorTest {

    @Test
    void nextTest() {
        final AtomicInteger ai = new AtomicInteger(0);
        final BinaryNode six = new BinaryNode(6, ai.incrementAndGet(), null, null);
        final BinaryNode seven = new BinaryNode(7, ai.incrementAndGet(), six, null);
        final BinaryNode nine = new BinaryNode(9, ai.incrementAndGet(), null, null);
        final BinaryNode eleven = new BinaryNode(11, ai.incrementAndGet(), null, null);
        final BinaryNode two = new BinaryNode(2, ai.incrementAndGet(), null, null);
        final BinaryNode one = new BinaryNode(1, ai.incrementAndGet(), null, two);
        final BinaryNode eight = new BinaryNode(8,ai.incrementAndGet(),  seven, nine);
        final BinaryNode five = new BinaryNode(5, ai.incrementAndGet(), null, eight);
        final BinaryNode ten = new BinaryNode(10, ai.incrementAndGet(), five, eleven);
        final BinaryNode twelve = new BinaryNode(12, ai.incrementAndGet(), ten, null);
        final BinaryNode four = new BinaryNode(4, ai.incrementAndGet(), null, twelve);
        final BinaryNode three = new BinaryNode(3, ai.incrementAndGet(), one, four);
        final BinaryNode eighteen = new BinaryNode(18, ai.incrementAndGet(), null, null);
        final BinaryNode fourteen = new BinaryNode(14, ai.incrementAndGet(), null, eighteen);
        final BinaryNode root = new BinaryNode(13, ai.incrementAndGet(), three, fourteen);

        int inOrder = 0;
        final InOrderBSTIterator inOrderBSTIterator = new InOrderBSTIterator(root);
        while(inOrderBSTIterator.hasNext()) {
            final int next = inOrderBSTIterator.next();
            if(next < inOrder) {
                Assertions.fail();
            } else {
                inOrder = next;
            }
        }
    }

}