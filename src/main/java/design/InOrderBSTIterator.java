package design;

import models.BinaryNode;

import java.util.Iterator;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class InOrderBSTIterator implements Iterator<Integer> {

    private final BinaryNode root;
    private final Stack<BinaryNode> stack;

    public InOrderBSTIterator(final BinaryNode root) {
        this.root = root;
        this.stack = new Stack<>();
        inOrderWalk(root);
    }

    public Integer next() {
        final BinaryNode bottom = stack.pop();
        inOrderWalk(bottom.getRight());
        return bottom.getId();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void inOrderWalk(final BinaryNode currentRoot) {
        BinaryNode current = currentRoot;
        while(current != null) {
            stack.push(current);
            current = current.getLeft();
        }
    }
}
