package mst;

import java.util.*;

public class TheEarliestMomentWhenEveryoneBecomeFriends {

    /**
     * leetcode 1101
     */

    public static int earliestFriends(final int[][] logs, final int members) {
        Arrays.sort(logs, Comparator.comparingInt(arr -> arr[0]));

        Map<Integer, Set<Integer>> disjointSetMap = new HashMap<>();

        for(int i = 0; i < logs.length; i++) {
            if(!disjointSetMap.containsKey(logs[i][1]) && !disjointSetMap.containsKey(logs[i][2])) {
                Set<Integer> membership = new HashSet<>();
                membership.add(logs[i][1]);
                membership.add(logs[i][2]);
                disjointSetMap.put(logs[i][1], membership);
                disjointSetMap.put(logs[i][2], membership);
            } else if(!disjointSetMap.containsKey(logs[i][1])) {
                disjointSetMap.get(logs[i][2]).add(logs[i][1]);
                disjointSetMap.put(logs[i][1], disjointSetMap.get(logs[i][2]));
            } else if(!disjointSetMap.containsKey(logs[i][2])) {
                disjointSetMap.get(logs[i][1]).add(logs[i][2]);
                disjointSetMap.put(logs[i][2], disjointSetMap.get(logs[i][1]));
            } else {
                final Set<Integer> needUpdate = disjointSetMap.get(logs[i][2]);
                disjointSetMap.get(logs[i][1]).addAll(disjointSetMap.get(logs[i][2]));
                disjointSetMap.put(logs[i][2], disjointSetMap.get(logs[i][1]));

                for(final Integer update : needUpdate) {
                    disjointSetMap.put(update, disjointSetMap.get(logs[i][1]));
                }
            }

            if(disjointSetMap.get(logs[i][1]).size() == members) {
                return logs[i][0];
            }
        }

        return -1;
    }
}
