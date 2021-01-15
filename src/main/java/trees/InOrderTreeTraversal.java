package trees;

import models.BinaryNode;

import java.util.Stack;

public class InOrderTreeTraversal {

    public static void inOrder(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();

        BinaryNode current = root;
        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                final BinaryNode top = stack.pop();
                /*
                    Logic for processing a node goes here
                 */
                current = top.getRight();
            }
        }
    }
}
