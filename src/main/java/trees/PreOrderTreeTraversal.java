package trees;

import models.BinaryNode;

import java.util.Stack;

public class PreOrderTreeTraversal {

    public static void preOrderTraversalImperative(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            final BinaryNode current = stack.pop();
            /*
                Process `current` here
             */

            if(current.getRight() != null) {
                stack.push(current.getRight());
            }

            if(current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
    }
}
