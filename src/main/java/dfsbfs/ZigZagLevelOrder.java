package dfsbfs;

import models.BinaryNode;

import java.util.*;

public class ZigZagLevelOrder {

    /**
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
     */
    public static List<List<Integer>> zigzagLevelOrder(final BinaryNode root) {
        if(root == null) return new ArrayList<>();

        final Deque<BinaryNode> queueAlpha = new ArrayDeque<>();
        final Deque<BinaryNode> queueBeta = new ArrayDeque<>();
        queueAlpha.offer(root);

        final Deque<List<Integer>> result = new ArrayDeque<>();
        result.offerLast(List.of(root.getVal()));
        result.offerLast(new ArrayList<>());

        Deque<BinaryNode> activeQueue = queueAlpha;
        boolean leftToRight = false;
        List<Integer> last = result.getLast();
        while(!activeQueue.isEmpty()) {
            final BinaryNode next = activeQueue.poll();

            final Deque<BinaryNode> inactiveQueue = activeQueue == queueAlpha ? queueBeta : queueAlpha;

            if(leftToRight) {
                if(next.getLeft() != null) {
                    inactiveQueue.offerFirst(next.getLeft());
                    last.add(next.getLeft().getId());
                }
                if(next.getRight() != null) {
                    inactiveQueue.offerFirst(next.getRight());
                    last.add(next.getRight().getId());
                }
            } else {
                if(next.getRight() != null) {
                    inactiveQueue.offerFirst(next.getRight());
                    last.add(next.getRight().getId());
                }
                if(next.getLeft() != null) {
                    inactiveQueue.offerFirst(next.getLeft());
                    last.add(next.getLeft().getId());
                }
            }

            if(activeQueue.isEmpty()) {
                activeQueue = inactiveQueue;
                leftToRight = !leftToRight;
                if(queueAlpha.size() > 0 || queueBeta.size() > 0) {
                    result.offerLast(new ArrayList<>());
                }
                last = result.getLast();
            }
        }

        // A new array list is added to the `result` at the end of each level order traversal, in preparation for the
        // next level order traversal. This means that when we get to the last level, one extraneous array list has been
        // added, and needs to be removed.
        result.pollLast();

        return result.stream().map(Collections::unmodifiableList).toList();

    }
}
