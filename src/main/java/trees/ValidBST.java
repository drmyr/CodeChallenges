package trees;

import models.BinaryNode;

import java.util.Stack;

public class ValidBST {

    public static boolean isValidBST(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        BinaryNode currInOrder = root;
        BinaryNode current = root;
        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                final BinaryNode top = stack.pop();

                if(top.getVal() > inorder) {
                    currInOrder = top;
                    inorder = top.getVal();
                    current = top.getRight();
                } else if(top.getVal() == inorder) {
                    if(currInOrder.getRight() == top) {
                        current = top.getRight();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
