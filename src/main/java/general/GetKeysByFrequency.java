package general;

import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparingInt;

public class GetKeysByFrequency {

    public static int[][] orderKeysByFrequency(final int[] array) {
        final Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(final int value : array) frequencyMap.merge(value, 1, Integer::sum);

        return frequencyMap.entrySet().stream()
            .sorted(comparingInt(Map.Entry<Integer, Integer>::getValue).reversed())
            .map((final Map.Entry<Integer, Integer> entry) -> new int[] { entry.getKey(), entry.getValue() })
            .toArray(int[][]::new);
    }
}
