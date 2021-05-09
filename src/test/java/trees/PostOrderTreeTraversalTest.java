package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import static trees.PostOrderTreeTraversal.postOrderTraversalImperative;

class PostOrderTreeTraversalTest {

    @Test
    void postOrderTraversalImperativeTest() {
        final BinaryNode one = new BinaryNode(1, null, null);
        final BinaryNode four = new BinaryNode(4, null, null);
        final BinaryNode two = new BinaryNode(2, one, four);
        final BinaryNode six = new BinaryNode(6, null, null);
        final BinaryNode seven = new BinaryNode(7, null, null);
        final BinaryNode five = new BinaryNode(5, six, seven);
        final BinaryNode three = new BinaryNode(3, two, five);

        postOrderTraversalImperative(three);
    }
}