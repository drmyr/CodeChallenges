package general;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    public static List<List<Integer>> powerset(final List<Integer> array) {
        final List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int i = 0; i < array.size(); i++) {
            final int currSize = result.size();
            for(int j = 0; j < currSize; j++) {
                final List<Integer> copy = new ArrayList<>(result.get(j));
                copy.add(array.get(i));
                result.add(copy);
            }
        }

        return result;
    }
}
