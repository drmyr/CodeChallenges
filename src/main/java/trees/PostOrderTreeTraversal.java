package trees;

import models.BinaryNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PostOrderTreeTraversal {

    public static void postOrder(final BinaryNode root) {
        final Set<Integer> visited = new HashSet<>();
        final Stack<BinaryNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final BinaryNode next = stack.peek();
            if(visited.contains(next.getId())) {
                final BinaryNode current = stack.pop();
                /*
                    Process `current` here
                 */
            } else {
                // The right node has to be pushed before the left, so that the left is popped first.
                if(next.getRight() != null) {
                    stack.push(next.getRight());
                }
                if(next.getLeft() != null) {
                    stack.push(next.getLeft());
                }
                visited.add(next.getId());
            }
        }
    }
}
