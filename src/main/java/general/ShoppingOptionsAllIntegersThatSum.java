package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingOptionsAllIntegersThatSum {

    /**
     * https://cybergeeksquad.co/2021/06/shopping-options-amazon-online-assessment.html
     *
     * Shopping Options Amazon Online Assessment
     *
     * Alternative Question: Find All Combination of Numbers Sum to Target
     *
     * Given a positive integer, target, print all possible combinations of positive integers that sum up to the target number.
     *
     * For example, if we are given input ‘5’, these are the possible sum combinations.
     *
     * 1, 4
     * 2, 3
     * 1, 1, 3
     * 1, 2, 2
     * 1, 1, 1, 2
     * 1, 1, 1, 1, 1
     *
     * The output will be in the form a list of lists or an array of arrays. Each element in the list will be another list containing a possible sum combination.
     *
     * @param target
     * @return
     */
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
