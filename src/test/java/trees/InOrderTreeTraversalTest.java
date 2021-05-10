package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import static trees.InOrderTreeTraversal.inOrderTraversalImperative;

class InOrderTreeTraversalTest {

    /**
     *           3
     *         /  \
     *        2    5
     *       / \  / \
     *      1  4 6  7
     * pre: 3 2 1 4 5 6 7
     * in:  1 2 4 3 6 5 7
     * Given pre and in-order traversal, construct the tree
     */
    @Test
    void inOrderTraversalImperativeTest() {
        final BinaryNode one = new BinaryNode(1, null, null);
        final BinaryNode four = new BinaryNode(4, null, null);
        final BinaryNode two = new BinaryNode(2, one, four);
        final BinaryNode six = new BinaryNode(6, null, null);
        final BinaryNode seven = new BinaryNode(7, null, null);
        final BinaryNode five = new BinaryNode(5, six, seven);
        final BinaryNode three = new BinaryNode(3, two, five);

        inOrderTraversalImperative(three);
    }
}