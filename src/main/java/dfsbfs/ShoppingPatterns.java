package dfsbfs;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.Collections.*;

public class ShoppingPatterns {

    /*
     https://aonecode.com/amazon-online-assessment-shopping-patterns

     Example
        products_nodes = 6
        products_edges = 6
        products_from = [1,2,2,3,4,5]
        products_to = [2,4,5,5,5,6]

     Simple algorithm to find triangles in a graph:

            for each edge (u, v):
              for each vertex w:
                 if (v, w) is an edge and (w, u) is an edge:
                      return true
            return false
     */
    public static int getMinScore(final int productNodes, final int productEdges, final int[] productsFrom, final int[] productsTo) {
        final Map<Integer, Set<Integer>> neighborsByNodeId = new HashMap<>();
        final BinaryOperator<Set<Integer>> merge = (final Set<Integer> a, final Set<Integer> b) -> {
            final Set<Integer> set = new HashSet<>();
            set.addAll(a);
            set.addAll(b);
            return set;
        };
        for(int i = 0; i < productEdges; i++) {
            neighborsByNodeId.merge(productsFrom[i], Set.of(productsTo[i]), merge);
            neighborsByNodeId.merge(productsTo[i], Set.of(productsFrom[i]), merge);
        }

        final Set<Set<Integer>> trios = new HashSet<>();

        // Count number of triangles.
        for(int edge = 0; edge < productEdges; edge++) {
            for(int node = 0; node < productNodes; node++) {
                final int from = productsFrom[edge];
                final int to = productsTo[edge];
                final Set<Integer> neighbors = neighborsByNodeId.getOrDefault(node, emptySet());
                if(neighbors.contains(from) && neighbors.contains(to)) {
                    trios.add(Set.of(node, from, to));
                }
            }
        }

        if(trios.isEmpty()) return -1;

        int minSum = Integer.MAX_VALUE;
        for(final Set<Integer> trio : trios) {
            int localSum = 0;
            for(final Integer entry : trio) {
                // copy collection to new HashSet, so that removeAll operation does not mutate the original
                final Set<Integer> products = new HashSet<>(neighborsByNodeId.get(entry));
                products.removeAll(trio);
                localSum += products.size();
            }
            minSum = Math.min(minSum, localSum);
        }

        return minSum;
    }
}
