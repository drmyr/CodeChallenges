package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingOptionsAllIntegersThatSum {

    public static List<List<Integer>> getAllIntegersThatSumToTarget(final int target) {
        final List<List<Integer>> result = new ArrayList<>();
        rec(result, 0, target, 1);
        for(var entry : result) {
            System.out.println(Arrays.toString(entry.toArray()));
        }
        return result;
    }

    private static void rec(final List<List<Integer>> result, final int current, final int target, final int step) {
        if(current < target - step) {
            final List<Integer> list = new ArrayList<>();
            for(int i = 0; i < current; i += step) list.add(step);
            list.add(step);
            list.add(target - step - current);
            result.add(list);
            rec(result, current + step, target, step);
        } else if(step < (target / 2)) {
            rec(result, 0, target, step + 1);
        }
    }
}
