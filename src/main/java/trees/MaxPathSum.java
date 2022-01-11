package trees;

import models.BinaryNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MaxPathSum {

    /**
     * Find the maximum valued path in a non-empty tree
     * @param root
     * @return
     */
    public static int maxPathSumOfTree(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        final Set<Integer> visited = new HashSet<>();
        stack.push(root);

        final Map<Integer, Integer> maxValueById = new HashMap<>();
        int globalMax = Integer.MIN_VALUE;
        while(!stack.isEmpty()) {
            final BinaryNode top = stack.peek();
            if(visited.contains(top.getId())) {
                final BinaryNode pop = stack.pop();
                final int leftValue = pop.getLeft() != null ? maxValueById.getOrDefault(pop.getLeft().getId(), 0) : 0;
                final int rightValue = pop.getRight() != null ? maxValueById.getOrDefault(pop.getRight().getId(), 0) : 0;
                final int maxValue = Math.max(pop.getId() + leftValue, pop.getId() + rightValue);
                maxValueById.put(pop.getId(), maxValue);
                globalMax = Math.max(globalMax, maxValue);
            } else {
                if(top.getRight() != null) {
                    stack.push(top.getRight());
                }

                if(top.getLeft() != null) {
                    stack.push(top.getLeft());
                }
                visited.add(top.getId());
            }
        }
        return globalMax;
    }
}
