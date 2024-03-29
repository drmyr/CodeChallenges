package mst;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class MinCostToAddNewRoads {

    /**
     * https://aonecode.com/amazon-online-assessment-oa2-min-cost-to-add-new-roads
     *
     * Min Cost To Add New Roads
     * There are N cities numbered from 1 to N.
     * You are given connections, where each connections[i] = [city1, city2, cost]
     * represents the cost to connect city1 and city2 together.
     * (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
     *
     * Return the minimum cost so that for every pair of cities,
     * there exists a path of connections (possibly of length 1)
     * that connects those two cities together. The cost is the sum of
     * the connection costs used. If the task is impossible, return -1.
     * @param cityCount
     * @param connectionsAndCost
     * @return
     */
    public static int minCostForRoad(final int cityCount, final int[][] connectionsAndCost) {
        final int cityOneIndex = 0, cityTwoIndex = 1, connectionCost = 2;

        final PriorityQueue<int[]> nextCheapestRoad = new PriorityQueue<>(comparingInt(connection -> connection[connectionCost]));
        for(final int[] connectionAndCost : connectionsAndCost) {
            nextCheapestRoad.offer(connectionAndCost);
        }

        class MethodLocalDisjointSet {
            final Set<Integer> members;
            int cost;
            MethodLocalDisjointSet() {
                this.members = new HashSet<>();
            }

            MethodLocalDisjointSet with(final int[] connection) {
                this.members.add(connection[cityOneIndex]);
                this.members.add(connection[cityTwoIndex]);
                this.cost += connection[connectionCost];
                return this;
            }

            void union(final MethodLocalDisjointSet other) {
                this.members.addAll(other.members);
                this.cost += other.cost;
            }
        }

        final TreeMap<Integer, MethodLocalDisjointSet> disjointSetMap = new TreeMap<>();

        while(!nextCheapestRoad.isEmpty()) {
            final int[] connection = nextCheapestRoad.poll();
            final int cityOne = connection[cityOneIndex];
            final int cityTwo = connection[cityTwoIndex];
            // Case 1: There is no disjoint set for either city
            if(!disjointSetMap.containsKey(cityOne) && !disjointSetMap.containsKey(cityTwo)) {
                final MethodLocalDisjointSet newDisjointSet = new MethodLocalDisjointSet().with(connection);
                disjointSetMap.put(cityOne, newDisjointSet);
                disjointSetMap.put(cityTwo, newDisjointSet);
            // Case 2: City two has a disjoint set, so add City one to its set
            } else if(!disjointSetMap.containsKey(cityOne)) {
                final MethodLocalDisjointSet cityTwoDisjointSet = disjointSetMap.get(cityTwo).with(connection);
                disjointSetMap.put(cityOne, cityTwoDisjointSet);
            // Case 3: City one has a disjoint set, so add City two to its set
            } else if(!disjointSetMap.containsKey(cityTwo)){
                final MethodLocalDisjointSet cityOneDisjointSet = disjointSetMap.get(cityOne).with(connection);
                disjointSetMap.put(cityTwo, cityOneDisjointSet);
            // Case 4: They both have a disjoint set, so attempt to merge them.
            } else {
                final MethodLocalDisjointSet cityOneDisjointSet = disjointSetMap.get(cityOne);
                final MethodLocalDisjointSet cityTwoDisjointSet = disjointSetMap.get(cityTwo);
                // If both are already in the same disjoint set, do nothing. This avoids adding the cost of going
                // between the two cities using the more expensive path. Otherwise, join the disjoint sets.
                if(cityOneDisjointSet != cityTwoDisjointSet) {
                    cityOneDisjointSet.union(cityTwoDisjointSet);
                    // update all node IDs that were in set two to now point at set one.
                    for(final Integer node : cityTwoDisjointSet.members) {
                        disjointSetMap.put(node, cityOneDisjointSet);
                    }
                }
            }
        }

        // by the end of the process, there should be only one disjoint set.
        // if there is more than one disjoint set, it means that the graphs are disconnected,
        // and there is no answer
        final MethodLocalDisjointSet top = disjointSetMap.firstEntry().getValue();
        for(final MethodLocalDisjointSet disjointSet : disjointSetMap.values()) {
            if(top != disjointSet) {
                return -1;
            }
        }
        return top.cost;
    }
}
