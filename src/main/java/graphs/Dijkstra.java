package graphs;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparing;

public class Dijkstra {

    private record Vertex(int cost, String name, Vertex from) {
        @Override
        public boolean equals(final Object other) {
            return other instanceof final Vertex vertex && this.name.equals(vertex.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.name);
        }
    }

    public static Optional<String> shortestPath(final String[][] edgesWithCosts, final String start, final String end) {
        return doShortestPath(edgesWithCosts, start, end).map((final Vertex vertex) -> {
            final StringBuilder sb = new StringBuilder();
            Vertex current = vertex;
            while(current != null) {
                sb.append(">-").append(current.name);
                current = current.from;
            }
            return sb.reverse().delete(sb.length() - 2, sb.length()).toString();
        });
    }

    public static Optional<Integer> shortestPathLength(final String[][] edgesWithCosts, final String start, final String end) {
        return doShortestPath(edgesWithCosts, start, end).map(Vertex::cost);
    }

    private static Optional<Vertex> doShortestPath(final String[][] edgesWithCosts, final String start, final String end) {
        final Map<String, Vertex> vertexByName = new HashMap<>();
        final Vertex vertexStart = new Vertex(0, start, null);
        vertexByName.put(start, vertexStart);

        final Map<String, List<String[]>> graph = new HashMap<>();
        final BinaryOperator<List<String[]>> merger = (a, b) -> { a.addAll(b); return a; };
        for(final String[] edge : edgesWithCosts) {
            final List<String[]> first = new ArrayList<>(); first.add(new String[] { edge[1], edge[2] });
            graph.merge(edge[0], first, merger);
            final List<String[]> second = new ArrayList<>(); second.add(new String[] { edge[0], edge[2] });
            graph.merge(edge[1], second, merger);
            vertexByName.computeIfAbsent(edge[0], name -> new Vertex(Integer.MAX_VALUE, name, null));
            vertexByName.computeIfAbsent(edge[1], name -> new Vertex(Integer.MAX_VALUE, name, null));
        }

        final Set<String> visited = new HashSet<>();
        final PriorityQueue<Vertex> heap = new PriorityQueue<>(comparing(Vertex::cost));
        heap.offer(vertexStart);

        while(!heap.isEmpty()) {
            final Vertex thisVertex = heap.poll();

            if(thisVertex.name.equals(end)) {
                return Optional.of(thisVertex);
            }

            for(final String[] neighbors : graph.get(thisVertex.name)) {
                Vertex neighbor = vertexByName.get(neighbors[0]);
                final int maybeCheaper = thisVertex.cost + Integer.parseInt(neighbors[1]);
                if(maybeCheaper < neighbor.cost) {
                    vertexByName.put(neighbors[0], (neighbor = new Vertex(maybeCheaper, neighbor.name, thisVertex)));
                }
                if(!visited.contains(neighbor.name)) {
                    heap.remove(neighbor);
                    heap.offer(neighbor);
                }
            }

            visited.add(thisVertex.name);
        }

        return Optional.empty();
    }
}
