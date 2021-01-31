package dp;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class LongestPathInDAG {
    // https://leetcode.com/discuss/interview-question/algorithms/277534/Google-Largest-Value-Path-in-a-Directed-graph
    // https://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/11-Graph/Docs/longest-path-in-dag.pdf
    public static int longestPathInGraph(final String letter, final int[][] edges) {
        class Vertex {
            final int id;
            final List<Vertex> destinations;
            private boolean isRoot = true;
            Vertex(final int id) {
                this.id = id;
                this.destinations = new ArrayList<>();
            }

            private boolean isRoot() {
                return isRoot;
            }

            private void addDestination(final Vertex vertex) {
                vertex.isRoot = false;
                this.destinations.add(vertex);
            }
        }

        // build graph
        final Map<Integer, Vertex> vertexMap = new HashMap<>();
        for(final int[] edge : edges) {
            final Vertex src = vertexMap.computeIfAbsent(edge[0], Vertex::new);
            final Vertex dst = vertexMap.computeIfAbsent(edge[1], Vertex::new);
            src.addDestination(dst);
        }

        // add roots to stack
        final Deque<Vertex> stack = vertexMap.values().stream()
                .filter(Vertex::isRoot).collect(toCollection(ArrayDeque::new));

        // Topologically sort vertices. The vertices will pop off the stack in reverse topological order.
        final Set<Integer> visited = new HashSet<>();
        final Deque<Vertex> topologicalSort = new ArrayDeque<>();
        while(!stack.isEmpty()) {
            final Vertex top = stack.peek();
            if(visited.contains(top.id)) {
                topologicalSort.addFirst(stack.pop());
            } else {
                for(final Vertex child : top.destinations) {
                    if(!visited.contains(child.id)) {
                        stack.push(child);
                    }
                }
                visited.add(top.id);
            }
        }

        // create costmap and populate most expensive parent
        final Map<Integer, Integer> vertexIdAndItsMostExpensiveCost = new HashMap<>();
        final Map<Integer, Vertex> vertexIdAndItsMostExpensiveParent = new HashMap<>();
        final int[] maxCostAndItsVertexId = new int[2];
        for(final Vertex vertex : topologicalSort) {
            for(final Vertex child : vertex.destinations) {
                final int newCost = vertexIdAndItsMostExpensiveCost.getOrDefault(vertex.id, 0) + 1;
                if(newCost > maxCostAndItsVertexId[0]) {
                    maxCostAndItsVertexId[0] = newCost;
                    maxCostAndItsVertexId[1] = child.id;
                }
                final int currentCost = vertexIdAndItsMostExpensiveCost.getOrDefault(child.id, 0);
                if(newCost > currentCost) {
                    vertexIdAndItsMostExpensiveParent.put(child.id, vertex);
                    vertexIdAndItsMostExpensiveCost.put(child.id, newCost);
                }
            }
        }

        // walk child back through the path of the most expensive parent.
        Vertex lastVertexInSeries = vertexMap.get(maxCostAndItsVertexId[1]);
        final StringBuilder sb = new StringBuilder();
        while(lastVertexInSeries != null) {
            sb.append(letter.charAt(lastVertexInSeries.id));
            lastVertexInSeries = vertexIdAndItsMostExpensiveParent.get(lastVertexInSeries.id);
        }

        System.out.println("Path: " + sb.reverse().toString());
        return maxCostAndItsVertexId[0];
    }
}
