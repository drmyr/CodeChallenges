package graphs;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparing;

public class Dijkstra {

    public static int shortestPath(final String[][] edgesWithCosts, final String start, final String end) {
        record Vertex(int cost, String name, Vertex from) {}

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
                System.out.println(thisVertex);
                return thisVertex.cost;
            }

            for(final String[] neighbors : graph.get(thisVertex.name)) {
                final Vertex next = vertexByName.get(neighbors[0]);
                final int maybeCheaper = thisVertex.cost + Integer.parseInt(neighbors[1]);
                if(maybeCheaper < next.cost) {
                    vertexByName.put(neighbors[0], new Vertex(maybeCheaper, next.name, thisVertex));
                }
                final Vertex updatedNext = vertexByName.get(neighbors[0]);
                if(!visited.contains(neighbors[0])) {
                    heap.remove(updatedNext);
                    heap.offer(updatedNext);
                }
            }

            visited.add(thisVertex.name);
        }

        return Integer.MAX_VALUE;
    }
}
