package trees;

import models.BinaryNode;
import models.BinaryNodeMutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// A pre-order tree traversal is needed to copy a tree
public class CopyTree {

    public static void copyTree(final BinaryNode root) {
        final Map<Integer, BinaryNodeMutable> copy = new HashMap<>();
        copy.put(root.getId(), new BinaryNodeMutable(root.getId()));

        final Stack<BinaryNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final BinaryNode current = stack.pop();

            if(current.getRight() != null) {
                final BinaryNodeMutable newRight = new BinaryNodeMutable(current.getRight().getId());
                copy.get(current.getId()).setRight(newRight);
                copy.put(newRight.getId(), newRight);
                stack.push(current.getRight());
            }

            if(current.getLeft() != null) {
                final BinaryNodeMutable newLeft = new BinaryNodeMutable(current.getLeft().getId());
                copy.get(current.getId()).setLeft(newLeft);
                copy.put(newLeft.getId(), newLeft);
                stack.push(current.getLeft());
            }
        }
    }
}
