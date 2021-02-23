package dfsbfs;

import models.CriticalEdge;

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
 * https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
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

    public static <T> T[][] criticalConnections(final T root, final T[][] edges) {
        final Set<CriticalEdge<T>> criticalEdges = tarjan(root, edges);
        final Object[][] criticalEdgesArray = criticalEdges.stream()
            .map(ce -> new Object[] {ce.getCriticalNodeId(), ce.getCriticalNodeDestinationId()})
            .toArray(Object[][]::new);
        @SuppressWarnings("unchecked")
        T[][] result = (T[][]) Array.newInstance(edges.getClass().getComponentType().getComponentType(), criticalEdgesArray.length, criticalEdgesArray[0].length);
        for(int i = 0; i < criticalEdgesArray.length; i++) {
            System.arraycopy(criticalEdgesArray[i], 0, result[i], 0, criticalEdgesArray[i].length);
        }
        return result;
    }

    public static <T> T[] criticalNodes(final T root, final T[][] edges) {
        final Set<CriticalEdge<T>> criticalEdges = tarjan(root, edges);
        final Object[] criticalNodes = criticalEdges.stream().map(CriticalEdge::getCriticalNodeId).distinct().toArray();
        @SuppressWarnings("unchecked")
        final T[] result = (T[]) Array.newInstance(edges.getClass().getComponentType().getComponentType(), criticalNodes.length);
        System.arraycopy(criticalNodes, 0, result, 0, criticalNodes.length);
        return result;
    }

    private static <T> Set<CriticalEdge<T>> tarjan(final T root, final T[][] edges) {
        final AtomicInteger ai = new AtomicInteger(0);

        class MethodLocalNode {
            final T id;
            final Set<T> connections;

            final Set<CriticalEdge<T>> rootCriticalEdge;
            boolean isRoot = false;

            int disc = 0, low = 0;

            MethodLocalNode(final T id) {
                this.id = id;
                this.connections = new HashSet<>();
                this.rootCriticalEdge = new HashSet<>();
            }

            void setCounters() {
                final int next = ai.getAndIncrement();
                this.disc = next;
                this.low = next;
            }

            /*
             * If this node is the root node, we will only attempt to increment its `rootChildrenCount` more than once
             * if it has two or more independent children (meaning this root node is an Articulation Point).
             * This is true because if this node is the root node, but not an Articulation Point, then in the
             * `continueDFS` predicate below this root node never be able to contribute another child node to the
             * DFS stack that has not already been visited.
             */
            void tryIncrementRootChildCount(final T child) {
                if(isRoot) {
                    rootCriticalEdge.add(new CriticalEdge<>(id, child));
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
                    node.tryIncrementRootChildCount(id);
                    stack.push(next.id);
                    return true;
                }
                low = Math.min(low, next.low);
            }
            node.low = low;
            return false;
        };

        final Set<CriticalEdge<T>> criticalEdges = new HashSet<>();

        MethodLocalNode top;
        while((top = graph.get(stack.peek())) != null) {
            if(!continueDFS.test(top)) {
                /*
                 If we made it here, that means all children have been visited. Now, we need to evaluate the two cases outlined
                 in the javadoc at the top of this class.

                 Either:
                 1. the node at the top of the stack is the root node, in which case it needs to have
                 two independent children for it to be an articulation point, else

                 2. the node needs to have a discovery time that is lower than or equal to at least one of its
                 adjacent nodes, as a lower discovery time indicates that it is an articulation point.

                 */
                final MethodLocalNode node = graph.get(stack.pop());

                if(node.isRoot) {
                    if(node.rootCriticalEdge.size() > 1) {
                        criticalEdges.addAll(node.rootCriticalEdge);
                    }
                } else {
                    for(final T adjacentId : node.connections) {
                        if(node.disc <= graph.get(adjacentId).low) {
                            criticalEdges.add(new CriticalEdge<>(node.id, adjacentId));
                        }
                    }
                }
            }
        }

        return criticalEdges;
    }
}
