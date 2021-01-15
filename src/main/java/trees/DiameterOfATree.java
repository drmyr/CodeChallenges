package trees;

import models.BinaryNode;

import java.util.*;

// A post-order traversal is necessary to find the diameter of the tree
public class DiameterOfATree {

    public static int diameterOfTree(final BinaryNode root) {

        final int heightOfMyself = 1;
        final Set<Integer> visited = new HashSet<>();
        final Stack<BinaryNode> stack = new Stack<>();
        final Map<Integer, Integer> heightById = new HashMap<>();
        stack.push(root);

        int maxDiameter = 0;

        while(!stack.isEmpty()) {
            final BinaryNode top = stack.peek();
            if(visited.contains(top.getId())) {
                final BinaryNode current = stack.pop();
                final int heightOfLeft = current.getLeft() == null ? 0 : heightById.get(current.getLeft().getId());
                final int heightOfRight = current.getRight() == null ? 0 : heightById.get(current.getRight().getId());
                final int heightOfTallestChild = Math.max(heightOfLeft, heightOfRight);
                final int heightOfCurrent = heightOfTallestChild + heightOfMyself;
                maxDiameter = Math.max(maxDiameter, heightOfLeft + heightOfRight + heightOfMyself);
                heightById.put(current.getId(), heightOfCurrent);
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

        return maxDiameter;
    }
}
