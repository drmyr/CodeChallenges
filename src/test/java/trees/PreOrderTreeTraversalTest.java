package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static trees.PreOrderTreeTraversal.preOrderTraversalImperative;

class PreOrderTreeTraversalTest {

    @Test
    void preOrderTraversalImperativeTest() {
        final BinaryNode one = new BinaryNode(1, null, null);
        final BinaryNode four = new BinaryNode(4, null, null);
        final BinaryNode two = new BinaryNode(2, one, four);
        final BinaryNode six = new BinaryNode(6, null, null);
        final BinaryNode seven = new BinaryNode(7, null, null);
        final BinaryNode five = new BinaryNode(5, six, seven);
        final BinaryNode three = new BinaryNode(3, two, five);

        preOrderTraversalImperative(three);
    }
}