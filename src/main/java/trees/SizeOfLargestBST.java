package trees;

import models.BinaryNode;

import java.util.Stack;

public class SizeOfLargestBST {

    public static int sizeOfLargestBST(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        final int[] maxBstSizeAndRoot = new int[2];

        double inorder = -Double.MAX_VALUE;
        int currentBstSize = 0;
        BinaryNode current = root;

        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                final BinaryNode next = stack.pop();

                if(next.getId() > inorder) {
                    inorder = next.getId();
                    final int newBstSize = currentBstSize + 1;
                    if(newBstSize > maxBstSizeAndRoot[0]) {
                        maxBstSizeAndRoot[0] = newBstSize;
                        maxBstSizeAndRoot[1] = next.getId();
                    }
                } else {
                    inorder = -Double.MAX_VALUE;
                    currentBstSize = 0;
                }
            }
        }
        return maxBstSizeAndRoot[0];
    }
}
