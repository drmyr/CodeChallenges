package mst;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry;

public class MinCostToRepairEdges {

    /**
     * https://aonecode.com/amazon-online-assessment-oa2/min-cost-to-repair-edges
     *
     * "There's an undirected connected graph with n nodes labeled 1..n.
     * But some of the edges have been broken disconnecting the graph.
     * Find the minimum cost to repair the edges so that all the nodes
     * are once again accessible from each other."
     *
     * The easiest solution is just to say that the cost to connect two nodes is zero, unless
     * there exists a value for that connection in the {@code edgesToRepair}. So for the example:
     *
     * n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
     *
     * we can say that [2,3], [4,5] both cost zero, and then continue to poll the {@code edgesToRepair}
     * for the next cheapest edge until we have built out the MST.
     * @param nodeCount
     * @param edges
     * @return
     */
    public static int repairEdgesMinCost(final int nodeCount, final int[][] edges, final int[][] edgesToRepair) {
        final int nodeOneIndex = 0, nodeTwoIndex = 1, connectionCost = 2;

        class MethodLocalEdgeWithCost {
            final int alphaNode, betaNode, cost;
            MethodLocalEdgeWithCost(final int[] connectionWithCost) {
                this.alphaNode = connectionWithCost[nodeOneIndex];
                this.betaNode = connectionWithCost[nodeTwoIndex];
                this.cost = connectionWithCost.length == 3 ? connectionWithCost[connectionCost] : 0;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.alphaNode, this.betaNode);
            }

            @Override
            public boolean equals(final Object other) {
                return other instanceof final MethodLocalEdgeWithCost instance &&
                    this.alphaNode == instance.alphaNode &&
                    this.betaNode == instance.betaNode;
            }
        }

        class MethodLocalDisjointSet {
            final Set<Integer> members;
            int cost = 0;
            MethodLocalDisjointSet() {
                this.members = new HashSet<>();
            }

            MethodLocalDisjointSet with(final MethodLocalEdgeWithCost edgeWithCost) {
                this.members.add(edgeWithCost.alphaNode);
                this.members.add(edgeWithCost.betaNode);
                this.cost += edgeWithCost.cost;
                return this;
            }

            MethodLocalDisjointSet union(final MethodLocalDisjointSet other) {
                this.members.addAll(other.members);
                this.cost += other.cost;
                return this;
            }
        }

        final PriorityQueue<MethodLocalEdgeWithCost> nextCheapestConnection = new PriorityQueue<>(comparingInt(ewc -> ewc.cost));
        for(final int[] edgeToRepair : edgesToRepair) {
            nextCheapestConnection.offer(new MethodLocalEdgeWithCost(edgeToRepair));
        }
        for(final int[] edge : edges) {
            final MethodLocalEdgeWithCost ewc = new MethodLocalEdgeWithCost(edge);
            if(!nextCheapestConnection.contains(ewc)) {
                nextCheapestConnection.offer(ewc);
            }
        }

        final TreeMap<Integer, MethodLocalDisjointSet> disjointSetMap = new TreeMap<>();
        while(!nextCheapestConnection.isEmpty()) {

            //If the MethodLocalDisjointSet has all the nodes of the MST, then we are done.
            final Entry<Integer, MethodLocalDisjointSet> head = disjointSetMap.firstEntry();
            if(head != null && head.getValue() != null && head.getValue().members.size() == nodeCount) {
                break;
            }

            final MethodLocalEdgeWithCost ewc = nextCheapestConnection.poll();

            final int alphaNode = ewc.alphaNode;
            final int betaNode = ewc.betaNode;
            if(!disjointSetMap.containsKey(alphaNode) && !disjointSetMap.containsKey(betaNode)) {
                final MethodLocalDisjointSet disjointSet = new MethodLocalDisjointSet().with(ewc);
                disjointSetMap.put(alphaNode, disjointSet);
                disjointSetMap.put(betaNode, disjointSet);
            } else if(!disjointSetMap.containsKey(alphaNode)) {
                final MethodLocalDisjointSet disjointSet = disjointSetMap.get(betaNode).with(ewc);
                disjointSetMap.put(alphaNode, disjointSet);
            } else if(!disjointSetMap.containsKey(betaNode)) {
                final MethodLocalDisjointSet disjointSet = disjointSetMap.get(alphaNode).with(ewc);
                disjointSetMap.put(betaNode, disjointSet);
            } else {
                final MethodLocalDisjointSet alphaDisjointSet = disjointSetMap.get(alphaNode);
                final MethodLocalDisjointSet betaDisjointSet = disjointSetMap.get(betaNode);

                if(alphaDisjointSet != betaDisjointSet) {
                    alphaDisjointSet.union(betaDisjointSet).with(ewc);
                    for(final Integer node : betaDisjointSet.members) {
                        disjointSetMap.put(node, alphaDisjointSet);
                    }
                }
            }
        }

        return disjointSetMap.firstEntry().getValue().cost;
    }
}
