package general;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedLists {

    /**
     * https://cybergeeksquad.co/2021/06/merge-two-sorted-lists-solution-amazon-oa.html
     *
     *
     */
    public static Integer[] mergeSortedLists(final int[] alpha, final int[] beta) {
        final List<Integer> merged = new ArrayList<>();
        int a = 0;
        int b = 0;
        while(a < alpha.length || b < beta.length) {
            if(a == alpha.length) {
                while(b < beta.length) merged.add(beta[b++]);
                break;
            }
            if(b == beta.length) {
                while(a < alpha.length) merged.add(alpha[a++]);
                break;
            }

            if(alpha[a] < beta[b]) {
                merged.add(alpha[a++]);
            } else {
                merged.add(beta[b++]);
            }
        }

        return merged.toArray(Integer[]::new);
    }
}
