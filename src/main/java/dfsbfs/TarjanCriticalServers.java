package dfsbfs;

import java.util.*;
import java.util.function.Function;

public class TarjanCriticalServers {

    /*
     https://www.youtube.com/watch?v=wUgWX0nc4NY
     */
    public static Integer[][] findStronglyConnectedComponents(final int nodeNum, final int[][] edges) {
        class Node {
            private final int id;
            private final Set<Integer> connections;
            private int lowLink = 0;
            Node(final int id) {
                this.id = id;
                this.connections = new HashSet<>();
            }

            void setLowLink(final Integer previous) {
                this.lowLink = previous + 1;
            }
        }

        final Map<Integer, Node> nodeById = new HashMap<>();
        for(final int[] edge : edges) {
            final Node alpha = nodeById.computeIfAbsent(edge[0], Node::new);
            final Node beta = nodeById.computeIfAbsent(edge[1], Node::new);
            alpha.connections.add(beta.id);
        }

        final Set<Integer> visited = new HashSet<>();
        final Deque<Integer> stack = new ArrayDeque<>();
        final Set<Set<Integer>> stronglyConnectedComponents = new HashSet<>();
        final Node start = nodeById.get(edges[0][0]);
        stack.push(start.id);
        visited.add(start.id);

        final Function<Node, Integer> continueDFS = (final Node top) -> {
            Integer lowestLink = top.lowLink;
            for(final Integer nextId : top.connections) {
                final Node next = nodeById.get(nextId);
                if(!visited.contains(next.id)) {
                    next.setLowLink(top.lowLink);
                    stack.push(next.id);
                    visited.add(next.id);
                    return -1; // -1 here indicates that we can continue DFS
                }
                if(stack.contains(next.id)) {
                    lowestLink = Math.min(lowestLink, next.lowLink);
                }
            }

            // we cannot continue DFS, so we have to pop nodes of the stack until all nodes of this
            // scc have been removed.
            return lowestLink;
        };

        Node top;
        while((top = nodeById.get(stack.peek())) != null) {
            final Integer lowestLink = continueDFS.apply(top);
            if(lowestLink != -1) {

                final Set<Integer> scc = new HashSet<>();
                while((top = nodeById.get(stack.peek())) != null && top.lowLink >= lowestLink) {
                    scc.add(stack.pop());
                }
                stronglyConnectedComponents.add(scc);

                if(stack.isEmpty()) {
                    // Find the nodes that have not yet been evaluated, and add at least one of them to
                    // the stack. Use a TreeSet so that we can call `.first()`
                    final TreeSet<Integer> unvisited = new TreeSet<>(nodeById.keySet());
                    unvisited.removeAll(visited);
                    if(!unvisited.isEmpty()) {
                        final Integer next = unvisited.first();
                        stack.push(next);
                        visited.add(next);
                    }
                }
            }
        }

        return stronglyConnectedComponents.stream().map(set -> set.toArray(Integer[]::new)).toArray(Integer[][]::new);
    }

    /*
     https://aonecode.com/amazon-interview-questions/find-critical-nodes

     Find the critical nodes of an undirected graph.

          1    7
          |    |
     2 -- 3 -- 6 -- 0     critical nodes are [3,6]
          |    |
          4    5



        0 --> 1
        |     |
        v     v
        2 --> 3 --> 4
        |
        v
        5 --> 6

      Input:
        nodeNum = 7
        edges = [[0,1], [0,2], [1,3], [2,3], [2,5], [5,6], [3,4]]
      Output:
        [2,3,5]
     */
    public static int[] findCriticalNodes() {
        throw new UnsupportedOperationException("findCriticalNodes");
    }
}
