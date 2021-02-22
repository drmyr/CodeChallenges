package dfsbfs;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * https://www.youtube.com/watch?v=2kREIkF9UAs
 * https://cp-algorithms.com/graph/cutpoints.html
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * https://www.cs.cornell.edu/courses/cs2112/2012sp/lectures/lec24/lec24-12sp.html
 * https://www.sanfoundry.com/java-program-tarjan-algorithm/
 *
 * Quoting CP-Algorithms (link above):
 *
 *      1. Let's say we are in the DFS, looking through the edges starting from vertex v ≠ root.
 *         If the current edge (v,to) is such that none of the vertices to or its descendants in the
 *         DFS traversal tree has a back-edge to any of ancestors of v, then v is an articulation point.
 *         Otherwise, v is not an articulation point.
 *
 *      2. Let's consider the remaining case of v = root. This vertex will be the point of
 *         articulation if and only if this vertex has more than one child in the DFS tree.
 */
public class FindArticulationPoints {

    public static <T> T[] criticalConnections(final T root, final T[][] edges) {
        final AtomicInteger ai = new AtomicInteger(0);

        class MethodLocalNode {
            final T id;
            final Set<T> connections;

            int rootChildCount = 0;
            boolean isRoot = false;

            boolean countersInitialized = false;
            int disc = 0;
            int low = 0;

            MethodLocalNode(final T id) {
                this.id = id;
                this.connections = new HashSet<>();
            }

            void setCounters() {
                if(!this.countersInitialized) {
                    final int next = ai.getAndIncrement();
                    this.disc = next;
                    this.low = next;
                    this.countersInitialized = true;
                }
            }

            /*
             * If this node is the root node, we will only attempt to increment its `rootChildrenCount` more than once
             * if it has two or more independent children (meaning this root node is an Articulation Point).
             * This is true because if this node is the root node, but not an Articulation Point, then in the
             * `continueDFS` predicate below this root node never be able to contribute another child node to the
             * DFS stack that has not already been visited.
             */
            void tryIncrementRootChildCount() {
                if(isRoot) {
                    rootChildCount++;
                }
            }

        }

        // Undirected graph
        final Map<T, MethodLocalNode> graph = new HashMap<>();
        for(final T[] edge : edges) {
            final MethodLocalNode alpha = graph.computeIfAbsent(edge[0], MethodLocalNode::new);
            final MethodLocalNode beta = graph.computeIfAbsent(edge[1], MethodLocalNode::new);
            alpha.connections.add(beta.id);
            beta.connections.add(alpha.id);
        }

        final Set<T> visited = new HashSet<>();
        final Deque<T> stack = new ArrayDeque<>();
        final MethodLocalNode start = graph.get(root);
        start.setCounters();
        start.isRoot = true;
        stack.push(start.id);

        final Predicate<MethodLocalNode> continueDFS = (final MethodLocalNode node) -> {
            visited.add(node.id);
            int low = node.low;
            for(final T id : node.connections) {
                final MethodLocalNode next = graph.get(id);
                if(!visited.contains(next.id)) {
                    next.setCounters();
                    stack.push(next.id);
                    node.tryIncrementRootChildCount();
                    return true;
                }
                low = Math.min(low, next.low);
            }
            node.low = low;
            return false;
        };

        final Set<T> articulationPoints = new HashSet<>();

        MethodLocalNode top;
        while((top = graph.get(stack.peek())) != null) {
            if(!continueDFS.test(top)) {
                /*
                 If we made it here, that means all children have been visited. Now, we need to evaluate the two cases outlined
                 in the javadoc at the top of this class.

                 Either:
                 1. the node at the top of the stack is the root node, in which case it needs to have
                 two independent children for it to be an articulation point, else

                 2. the node needs to have a discovery time that is lower than at least one of its
                 adjacent nodes, as a lower discovery time indicates that it is an articulation point.

                 */
                final MethodLocalNode node = graph.get(stack.pop());

                if(node.isRoot) {
                    if(node.rootChildCount > 1) {
                        articulationPoints.add(node.id);
                    }
                } else {
                    for(final T adjacentId : node.connections) {
                        if(node.disc <= graph.get(adjacentId).low) {
                            articulationPoints.add(node.id);
                            break;
                        }
                    }
                }
            }
        }

        final T[] result = (T[]) Array.newInstance(edges.getClass().getComponentType().getComponentType(), articulationPoints.size());
        System.arraycopy(articulationPoints.toArray(), 0, result, 0, articulationPoints.size());

        return result;
    }
}
