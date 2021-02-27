package general;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;

public class OptimizingBoxWeights {

    // https://leetcode.com/discuss/interview-question/1021441/Amazon-OA-or-optimizating-Box-Weight
    public static Integer[] optimizeBoxWeights(final Integer[] weights) {
        Arrays.sort(weights, reverseOrder());
        final double totalWeight = Arrays.stream(weights).reduce(0, Integer::sum);
        final double medianCeiling = Math.floor(totalWeight / 2);
        final List<Integer> result = new ArrayList<>();

        int runningSum = 0;
        int index = 0;
        while(runningSum < medianCeiling) {
            final int current = weights[index];
            result.add(current);
            runningSum += current;
            index++;
        }

        return result.stream().sorted(naturalOrder()).toArray(Integer[]::new);
    }
}
