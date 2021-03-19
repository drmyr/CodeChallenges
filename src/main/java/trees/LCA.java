package trees;

import models.BinaryNode;

import java.util.*;

public class LCA {

    public int lowestCommonAncestor(final BinaryNode root, BinaryNode p, BinaryNode q) {
        // Stack for tree traversal
        final Deque<BinaryNode> stack = new ArrayDeque<>();
        stack.push(root);
        // HashMap for parent pointers
        final Map<BinaryNode, BinaryNode> parent = new HashMap<>();
        parent.put(root, null);
        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            final BinaryNode node = stack.pop();
            // While traversing the tree, keep saving the parent pointers.
            if (node.getLeft() != null) {
                parent.put(node.getLeft(), node);
                stack.push(node.getLeft());
            }
            if (node.getRight() != null) {
                parent.put(node.getRight(), node);
                stack.push(node.getRight());
            }
        }
        // Ancestors set() for node p.
        final List<BinaryNode> ancestors = new ArrayList<>();
        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        int counter = 0;
        while (!ancestors.contains(q)) {
            q = parent.get(q);
            counter++;
        }

        return ancestors.indexOf(q) + counter;
    }
}
