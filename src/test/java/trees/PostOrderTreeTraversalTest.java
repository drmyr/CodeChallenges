package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static trees.PostOrderTreeTraversal.*;

class PostOrderTreeTraversalTest {

    @Test
    void postOrderTraversalImperativeTest() {
        final AtomicInteger ai = new AtomicInteger(0);
        final BinaryNode one = new BinaryNode(1, ai.incrementAndGet(), null, null);
        final BinaryNode four = new BinaryNode(4, ai.incrementAndGet(), null, null);
        final BinaryNode two = new BinaryNode(2, ai.incrementAndGet(), one, four);
        final BinaryNode six = new BinaryNode(6, ai.incrementAndGet(), null, null);
        final BinaryNode seven = new BinaryNode(7, ai.incrementAndGet(), null, null);
        final BinaryNode five = new BinaryNode(5, ai.incrementAndGet(), six, seven);
        final BinaryNode three = new BinaryNode(3, ai.incrementAndGet(), two, five);

        postOrderTraversalImperative(three);
    }

    /**
     *         45
     *        / \
     *       25  55
     *      / \    \
     *     15  35   65
     *    /   /  \
     *   5   77   88
     *
     * Output: 5 15 77 88 35 25 65 55 45
     */
    @Test
    void postOrderTraversalImperativeTest2() {
        final AtomicInteger ai = new AtomicInteger(0);
        final BinaryNode five = new BinaryNode(5, ai.incrementAndGet(), null, null);
        final BinaryNode seventy_seven = new BinaryNode(77, ai.incrementAndGet(), null, null);
        final BinaryNode eighty_eight = new BinaryNode(88, ai.incrementAndGet(), null, null);
        final BinaryNode fifteen = new BinaryNode(15, ai.incrementAndGet(), five, null);
        final BinaryNode thirty_five = new BinaryNode(35, ai.incrementAndGet(), seventy_seven, eighty_eight);
        final BinaryNode sixty_five = new BinaryNode(65, ai.incrementAndGet(), null, null);
        final BinaryNode twenty_five = new BinaryNode(25, ai.incrementAndGet(), fifteen, thirty_five);
        final BinaryNode fifty_five = new BinaryNode(55, ai.incrementAndGet(), null, sixty_five);
        final BinaryNode forty_five = new BinaryNode(45, ai.incrementAndGet(), twenty_five, fifty_five);

        System.out.println("imperative");
        postOrderTraversalImperative(forty_five);
        System.out.println("------");
        System.out.println("recursive");
        //postOrderRecursive(forty_five);
    }


}