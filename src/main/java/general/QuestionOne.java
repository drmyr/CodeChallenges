package general;

import java.util.HashSet;
import java.util.Set;

public class QuestionOne {

    public static int[] productOfArray(final int[] arr) {
        final Set<Integer> indicesThatAreZero = new HashSet<>();
        int product = 1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                indicesThatAreZero.add(i);
            } else {
                product *= arr[i];
            }
        }

        if(indicesThatAreZero.size() > 1) return new int[arr.length];

        final int[] result = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            if(indicesThatAreZero.isEmpty()) {
                result[i] = product / arr[i];
            } else {
                if(indicesThatAreZero.contains(i)) {
                    result[i] = product;
                } else {
                    result[i] = 0;
                }
            }
        }

        return result;
    }
}
