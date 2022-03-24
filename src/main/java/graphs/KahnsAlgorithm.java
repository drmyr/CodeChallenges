package graphs;

import java.util.*;

import static java.util.Collections.*;

public class KahnsAlgorithm {

    /**
     * https://leetcode.com/problems/course-schedule
     * @param numCourses leetcode lies to you for funsies, so ignore this variable.
     * @param prerequisites the course graph
     * @return
     */
    public static boolean canFinish(final int numCourses, final int[][] prerequisites) {
        if(prerequisites.length == 0) return true;

        // node id is key, indegree is value
        final Map<Integer, Integer> indegree = new HashMap<>();
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for(final int[] prerequisite : prerequisites) {
            indegree.merge(prerequisite[0], 1, Integer::sum);
            // must be merged into the map in case it is not already present
            indegree.merge(prerequisite[1], 0, Integer::sum);
            graph.merge(prerequisite[1], new ArrayList<>(singletonList(prerequisite[0])), (a, b) -> { a.addAll(b); return a; });
            // must be merged into the map in case it is not already present
            graph.merge(prerequisite[0], new ArrayList<>(), (a, b) -> { a.addAll(b); return a; });
        }

        // Add any nodes with an indegree of 0 to the queue
        final Deque<Integer> topoSortQueue = new ArrayDeque<>();
        for(final Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if(entry.getValue() == 0) {
                topoSortQueue.offerLast(entry.getKey());
            }
        }

        final List<Integer> topoSort = new ArrayList<>();

        while(!topoSortQueue.isEmpty()) {
            final int next = topoSortQueue.pollFirst();
            topoSort.add(next);
            for(final int neighbor : graph.get(next)) {
                indegree.merge(neighbor, -1, Integer::sum);
                if(indegree.get(neighbor) == 0) {
                    topoSortQueue.offerLast(neighbor);
                }
            }
        }

        return graph.size() == topoSort.size();
    }
}
