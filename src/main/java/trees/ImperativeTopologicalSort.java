package trees;

import models.Node;

import java.util.*;

// A post-order traversal is needed for topological sorting
public class ImperativeTopologicalSort {

    /**
     * https://www.cs.cornell.edu/courses/cs2112/2012sp/lectures/lec24/lec24-12sp.html
     * "The key observation is that a node finishes (is marked black) after all of its descendants have been marked black.
     *  Therefore, a node that is marked black later must come earlier when topologically sorted. A a postorder
     *  traversal generates nodes in the reverse of a topological sort"
     * @param roots the roots of the graph.
     * @return The nodes topologically sorted
     */
    public static Deque<Node> imperativeTopologicalSort(final Node... roots) {
        final Deque<Node> reversePostOrder = new ArrayDeque<>();
        final Set<Integer> visited = new HashSet<>();
        final Stack<Node> stack = new Stack<>();

        for(final Node root : roots) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            final Node top = stack.peek();
            if(visited.contains(top.getId())) {
                reversePostOrder.addFirst(stack.pop());
            } else {
                for(final Node child : top.getNeighbors()) {
                    if(!visited.contains(child.getId())) {
                        stack.push(child);
                    }
                }
                visited.add(top.getId());
            }
        }
        return reversePostOrder;
    }
}
