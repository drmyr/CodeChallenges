package trees;

import models.BinaryNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// A post-order traversal is needed to delete a tree
public class DeleteTree {

    public static void deleteTree(final BinaryNode root) {

        final Set<Integer> evaluated = new HashSet<>();

        final Stack<BinaryNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final BinaryNode current = stack.peek();

            if(evaluated.contains(current.getId())) {
                final BinaryNode top = stack.pop();
                /*
                    Set `top` to null, or delete as needed.
                 */
            } else {

                if(current.getRight() != null) {
                    stack.push(current.getRight());
                }

                if(current.getLeft() != null) {
                    stack.push(current.getLeft());
                }

                evaluated.add(current.getId());
            }
        }
    }
}
