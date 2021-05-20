package trees;

import models.BinaryNode;

import java.util.Stack;

public class ValidBST {

    public static boolean isValidBST(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        BinaryNode current = root;
        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                final BinaryNode top = stack.pop();

                if(top.getId() <= inorder) {
                    return false;
                } else {
                    inorder = top.getId();
                }

                current = top.getRight();
            }
        }

        return true;
    }
}
