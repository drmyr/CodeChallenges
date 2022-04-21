package graphs;

import java.util.*;

public class TopoSortWithCycleDetection {

    public static List<Integer> topologicalSort(final List<Integer> jobs, final List<Integer[]> deps) {
        class Vertex {
            final int id;
            final List<Vertex> neighbors;
            int pointer = 0;
            Vertex(final int id) {
                this.id = id;
                this.neighbors = new ArrayList<>();
            }

            boolean hasNext() {
                return pointer < neighbors.size();
            }

            Vertex next() {
                return neighbors.get(pointer++);
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof Vertex asVertex) {
                    return this.id == asVertex.id;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(this.id);
            }
        }

        final Map<Integer, Vertex> map = new HashMap<>();
        for(Integer[] dep : deps) {
            Vertex parent = map.computeIfAbsent(dep[1], Vertex::new);
            Vertex child = map.computeIfAbsent(dep[0], Vertex::new);
            parent.neighbors.add(child);
        }

        final Set<Integer> visited = new HashSet<>();
        final Deque<Integer> topoSort = new ArrayDeque<>();
        final Set<Integer> topoSortContains = new HashSet<>();

        for(final Vertex next : map.values()) {
            if(!visited.contains(next.id)) {

                final Stack<Vertex> stack = new Stack<>();
                final Set<Integer> cycleCheck = new HashSet<>();
                stack.push(next);
                cycleCheck.add(next.id);

                while(!stack.isEmpty()) {
                    if(stack.peek().hasNext()) {
                        final Vertex child = stack.peek().next();
                        if(cycleCheck.contains(child.id)) {
                            return new ArrayList<>();
                        }
                        stack.push(child);
                        cycleCheck.add(child.id);
                    } else {
                        final Vertex top = stack.pop();
                        cycleCheck.remove(top.id);
                        if(!topoSortContains.contains(top.id)) {
                            topoSort.add(top.id);
                            topoSortContains.add(top.id);
                        }
                        visited.add(top.id);
                    }
                }

            }
            visited.add(next.id);
        }

        final Set<Integer> missing = new HashSet<>(jobs);
        missing.removeAll(new HashSet<>(topoSort));
        for(final Integer missingInt : missing) topoSort.addFirst(missingInt);

        return new ArrayList<>(topoSort);
    }
}
