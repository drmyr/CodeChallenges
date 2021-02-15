package dfsbfs;

import java.util.*;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class TarjanCriticalServers {

    /*
     https://aonecode.com/amazon-interview-questions/find-critical-nodes

     Find the critical nodes of a graph.
     */
    public static Integer[] findCriticalNodesUndirected(final int nodeNum, final int[][] edges) {
        final Map<Integer, Node> graph = graph(edges, true);
        final Set<StronglyConnectedComponent> stronglyConnectedComponents = tarjan(edges[0][0], graph);
        return stronglyConnectedComponents.stream()
            .flatMap(scc -> scc.getCriticalNodesMutableList().stream())
            .collect(toUnmodifiableSet()).toArray(Integer[]::new);
    }

    public static List<List<Integer>> findCriticalEdgesUndirected(final int n, final List<List<Integer>> connections) {
        final Map<Integer, Node> graph = graph(connections, true);
        final Set<StronglyConnectedComponent> stronglyConnectedComponents = tarjan(connections.get(0).get(0), graph);
        return stronglyConnectedComponents.stream()
            .filter(scc -> scc.getCriticalEdge().isPresent())
            .map(scc -> Arrays.asList(scc.getCriticalEdge().get()))
            .collect(toUnmodifiableList());
    }

    /*
     https://www.youtube.com/watch?v=wUgWX0nc4NY
     */
    public static Integer[][] findStronglyConnectedComponentsDirected(final int nodeNum, final int[][] edges) {
        // Leaf nodes appear as their own SCC, but we don't care about them.
        return tarjan(edges[0][0], graph(edges, false)).stream()
                .filter(StronglyConnectedComponent::isMultiNodeSCC)
                .map(StronglyConnectedComponent::asArray).toArray(Integer[][]::new);
    }

    /**
     * Implementation of Tarjan's algorithm to find {@link StronglyConnectedComponent}s.
     * @param startId The starting point for DFS search.
     * @param graph The graph to explore.
     * @return a {@link Set} of {@link StronglyConnectedComponent}s.
     */
    private static Set<StronglyConnectedComponent> tarjan(final int startId, final Map<Integer, Node> graph) {
        final Set<Integer> visited = new HashSet<>();
        final Deque<Integer> stack = new ArrayDeque<>();
        final Node start = graph.get(startId);
        stack.push(start.id);
        visited.add(start.id);

        final Function<Node, Integer> continueDFS = (final Node top) -> {
            top.initialize();

            // maintain an iterator so that we don't have to loop through all neighbors each time.
            while(top.connectionsIterator.hasNext()) {
                final Integer nextId = top.connectionsIterator.next();
                final Node next = graph.get(nextId);
                if(!visited.contains(next.id)) {
                    next.setSelfLowLink(top.selfLowLink);
                    stack.push(next.id);
                    visited.add(next.id);
                    return -1; // -1 here indicates that we can continue DFS
                }
                if(stack.contains(next.id)) {
                    top.setCollectionsLowestLink(next.selfLowLink);
                }
            }

            // we cannot continue DFS, so we have to pop nodes of the stack until all nodes of this
            // scc have been removed.
            return top.collectionsLowLink;
        };

        final Set<StronglyConnectedComponent> stronglyConnectedComponents = new HashSet<>();

        Node top;
        while((top = graph.get(stack.peek())) != null) {
            final Integer lowestLink = continueDFS.apply(top);
            if(lowestLink != -1) {

                final Set<Integer> scc = new HashSet<>();
                while((top = graph.get(stack.peek())) != null && top.selfLowLink >= lowestLink) {
                    scc.add(stack.pop());
                }
                stronglyConnectedComponents.add(new StronglyConnectedComponent(scc, graph));

                if(stack.isEmpty()) {
                    // Find the nodes that have not yet been evaluated, and add at least one of them to
                    // the stack. Use a TreeSet so that we can call `.first()`
                    final TreeSet<Integer> unvisited = new TreeSet<>(graph.keySet());
                    unvisited.removeAll(visited);
                    if(!unvisited.isEmpty()) {
                        final Integer next = unvisited.first();
                        stack.push(next);
                        visited.add(next);
                    }
                }
            }
        }

        return stronglyConnectedComponents;
    }

    private static Map<Integer, Node> graph(final int[][] edges, final boolean isUndirected) {
        final Map<Integer, Node> graph = new HashMap<>();
        for(final int[] edge : edges) {
            final Node alpha = graph.computeIfAbsent(edge[0], Node::new);
            final Node beta = graph.computeIfAbsent(edge[1], Node::new);
            alpha.connections.add(beta.id);
            if(isUndirected) beta.connections.add(alpha.id);
        }
        return graph;
    }

    private static Map<Integer, Node> graph(final List<List<Integer>> connections, final boolean isUndirected) {
        final Map<Integer, Node> graph = new HashMap<>();
        for(final List<Integer> edge : connections) {
            final Node alpha = graph.computeIfAbsent(edge.get(0), Node::new);
            final Node beta = graph.computeIfAbsent(edge.get(1), Node::new);
            alpha.connections.add(beta.id);
            if(isUndirected) beta.connections.add(alpha.id);
        }
        return graph;
    }

    private static final class StronglyConnectedComponent {
        /**
         * All the Nodes in this {@link StronglyConnectedComponent}
         */
        private final Set<Integer> stronglyConnectedComponents;
        /**
         * All the {@link Node}s in this {@link StronglyConnectedComponent}, and all the {@link Node}s that members of this
         * {@link StronglyConnectedComponent} can reach.
         */
        private final Set<Integer> allConnections;
        /**
         * All the {@link Node}s that members of this {@link StronglyConnectedComponent} can reach.
         */
        private final Set<Integer> outboundConnections;
        /**
         * If this {@link StronglyConnectedComponent} has only one {@code outboundConnections},
         * then the {@link Node} on the other side of that connection is a critical node.
         */
        private final Optional<Integer> criticalNodeDest;
        /**
         * If this {@link StronglyConnectedComponent} has a {@code criticalNodeDest}, then that {@code criticalNodeDest}
         * and the {@link Node} that connects to it in this {@link StronglyConnectedComponent} form a {@code criticalEdge}.
         */
        private final Optional<Integer[]> criticalEdge;

        StronglyConnectedComponent(final Set<Integer> stronglyConnectedComponents, final Map<Integer, Node> graph) {
            this.stronglyConnectedComponents = stronglyConnectedComponents;
            this.allConnections = stronglyConnectedComponents.stream()
                .flatMap(scc -> graph.get(scc).connections.stream())
                .collect(toUnmodifiableSet());
            final Set<Integer> outboundConnections = new HashSet<>(allConnections);
            outboundConnections.removeAll(stronglyConnectedComponents);
            this.outboundConnections = outboundConnections;
            this.criticalNodeDest = outboundConnections.size() == 1
                ? Optional.of(new ArrayList<>(outboundConnections).get(0))
                : Optional.empty();
            this.criticalEdge = this.criticalNodeDest
                .map(criticalNodeDestId -> {
                    final Integer criticalNodeSrcId = this.stronglyConnectedComponents.stream()
                        .filter(id -> graph.get(id).connections.contains(criticalNodeDestId)).findFirst().get();
                    return new Integer[] {criticalNodeDestId, criticalNodeSrcId};
                });
        }

        Optional<Integer[]> getCriticalEdge() {
            return this.criticalEdge;
        }

        List<Integer> getCriticalNodesMutableList() {
            if(criticalEdge.isPresent() && isMultiNodeSCC()) {
                return new ArrayList<>(outboundConnections);
            } else if(criticalEdge.isPresent()) {
                return Arrays.asList(this.criticalEdge.get());
            } else {
                return emptyList();
            }
        }

        boolean isMultiNodeSCC() {
            return stronglyConnectedComponents.size() > 1;
        }

        Integer[] asArray() {
            return stronglyConnectedComponents.toArray(Integer[]::new);
        }
    }

    private static final class Node {
        private final int id;
        private final Set<Integer> connections;
        private boolean initialized = false;
        private Iterator<Integer> connectionsIterator;
        private int selfLowLink = 0;
        private int collectionsLowLink = 0;
        Node(final int id) {
            this.id = id;
            this.connections = new HashSet<>();
        }

        /**
         * We need to explore all the neighbors of a given {@link Node} once and only once.
         * Looping over all the {@code connections} of a {@link Node} every time it is encountered would potentially waste
         * many iterations if this {@link Node} is encountered many times.
         *
         * As an optimization, initialize and keep a reference to a specific {@link Iterator} of the {@code connections},
         * so that each time this {@link Node} is encountered, we can continue {@code connections} exploration where we
         * left off at the last time.
         *
         * This implementation is not normally thread-safe, but since the {@link Node} class is private and not available to
         * the caller, the instance of {@link Node} can only be referenced by the {@link Thread} that invoked
         * {@link TarjanCriticalServers#tarjan(int, Map<Integer, Node>)}, making it safe.
         */
        void initialize() {
            if(!initialized) {
                this.connectionsIterator = this.connections.iterator();
                this.collectionsLowLink = this.selfLowLink;
                initialized = true;
            }
        }

        void setCollectionsLowestLink(final int proposedLowLink) {
            this.collectionsLowLink = Math.min(this.collectionsLowLink, proposedLowLink);
        }

        void setSelfLowLink(final Integer previous) {
            this.selfLowLink = previous + 1;
        }
    }
}
