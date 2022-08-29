package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NewAreaEachDay {

    public static List<Integer> newAreaEachDay(final int[][] ranges) {
        final TreeMap<Integer, Boolean> treeMap = new TreeMap<>();
        for(final int[] range : ranges) {
            treeMap.put(range[0], false);
            treeMap.put(range[1], false);
        }

        final List<Integer> colorPerDay = new ArrayList<>();
        for(final int[] range : ranges) {
            Map.Entry<Integer, Boolean> current = Map.entry(range[0], treeMap.get(range[0]));
            while(current.getValue()) {
                current = treeMap.higherEntry(current.getKey());
            }

            int start = current.getKey();
            int counter = 0;
            while(current.getKey() < range[1]) {
                if(!current.getValue()) {
                    treeMap.put(current.getKey(), true);
                    current = treeMap.higherEntry(current.getKey());
                    counter += current.getKey() - start;
                    start = current.getKey();
                } else {
                    current = treeMap.higherEntry(current.getKey());
                    start = current.getKey();
                }
            }
            colorPerDay.add(counter);
        }

        return colorPerDay;
    }
}
