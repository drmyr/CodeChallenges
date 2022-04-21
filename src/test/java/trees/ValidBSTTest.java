package trees;

import models.BinaryNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static trees.ValidBST.isValidBST;

class ValidBSTTest {

    @Test
    void isValidBSTTest() {
        final BinaryNode ten = new BinaryNode(10, 10, null, null);
        final BinaryNode five = new BinaryNode(5,5, null, ten);
        final BinaryNode fifteen = new BinaryNode(15, 15, null, null);
        final BinaryNode root = new BinaryNode(10, 10, five, fifteen);
        isValidBST(root);
    }
}