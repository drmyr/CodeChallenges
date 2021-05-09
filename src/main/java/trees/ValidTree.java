package trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidTree {

    /**
     * https://algomonster.medium.com/leetcode-261-graph-valid-tree-f27c212c1db1
     * @param undirectedEdges
     * @return
     */
    public static boolean isValidTree(final int nodeCount, final int[][] undirectedEdges) {
        class OrderedPair {
            final int src;
            final int dst;
            OrderedPair(final int a, final int b) {
                this.src = Math.min(a, b);
                this.dst = Math.max(a, b);
            }

            @Override
            public boolean equals(final Object other) {
                if(other instanceof OrderedPair) {
                    final OrderedPair op = (OrderedPair)other;
                    return op.src == this.src && op.dst == this.dst;
                }
                return false;
            }
        }

        class DisjointSet {
            final Set<Integer> disjointSet;
            DisjointSet() {
                this.disjointSet = new HashSet<>();
            }
        }
        final Set<OrderedPair> edges = new HashSet<>();
        for(final int[] edge : undirectedEdges) {
            edges.add(new OrderedPair(edge[0], edge[1]));
        }

        final Map<Integer, DisjointSet> nodeIdToDisjointSet = new HashMap<>();
        for(final OrderedPair orderedPair : edges) {
            // case 1: neither is in a DJS, so add them to the same set
            if(!nodeIdToDisjointSet.containsKey(orderedPair.src) && !nodeIdToDisjointSet.containsKey(orderedPair.dst)) {
                final DisjointSet newSet = new DisjointSet();
                newSet.disjointSet.add(orderedPair.src);
                newSet.disjointSet.add(orderedPair.dst);
                nodeIdToDisjointSet.put(orderedPair.src, newSet);
                nodeIdToDisjointSet.put(orderedPair.dst, newSet);
            }
            // case 2: src is not in a DJS, so add it to dst's DJS
            else if(!nodeIdToDisjointSet.containsKey(orderedPair.src)) {
                nodeIdToDisjointSet.get(orderedPair.dst).disjointSet.add(orderedPair.src);
            }
            // case 3: dst is not in a DJS, so add it to src's DJS
            else if(!nodeIdToDisjointSet.containsKey(orderedPair.dst)) {
                nodeIdToDisjointSet.get(orderedPair.src).disjointSet.add(orderedPair.dst);
            }
            // case 4:
            else {
                //  if DJS's are the same, we have a cycle
                //  else merge the sets
                final DisjointSet srcSet = nodeIdToDisjointSet.get(orderedPair.src);
                final DisjointSet dstSet = nodeIdToDisjointSet.get(orderedPair.dst);
                if(srcSet == dstSet) {
                    return false;
                } else {
                    srcSet.disjointSet.addAll(dstSet.disjointSet);
                    for(final int node : dstSet.disjointSet) {
                        nodeIdToDisjointSet.put(node, srcSet);
                    }
                }
            }
        }
        final DisjointSet comparison = nodeIdToDisjointSet.get(undirectedEdges[0][0]);
        for(final DisjointSet disjointSet : nodeIdToDisjointSet.values()) {
            if(comparison != disjointSet) {
                return false;
            }
        }
        return true;
    }
}
