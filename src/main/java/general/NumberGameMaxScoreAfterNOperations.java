package general;

import java.util.*;

public class NumberGameMaxScoreAfterNOperations {

    /**
     * https://algo.monster/problems/amazon_oa_number_game
     * https://leetcode.com/problems/maximize-score-after-n-operations/
     *
     * https://cpexplanations.blogspot.com/2021/03/1799-maximize-score-after-n-operations.html
     */
    public static int maxScoreByGCD(final int[] nums) {
        class Tuple {
            int leftIndex;
            int rightIndex;
            int gcd;

            Tuple(int leftIndex, int rightIndex, int gcd) {
                this.leftIndex = leftIndex;
                this.rightIndex = rightIndex;
                this.gcd = gcd;
            }
        }

        List<Tuple> tupleList = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                tupleList.add(new Tuple(i, j, gcd(nums[i], nums[j])));
            }
        }

        if(tupleList.size() < 2) return tupleList.get(0).gcd;

        Collections.sort(tupleList, Comparator.comparingInt((final Tuple tuple) -> tuple.gcd).reversed());

        int sum = 0;
        int multiplier = nums.length / 2;
        Set<Integer> expiredIndexes = new HashSet<>();

        for(int i = 0; i < tupleList.size(); i++) {
            Tuple tuple = tupleList.get(i);
            if(!expiredIndexes.contains(tuple.leftIndex) &&
                !expiredIndexes.contains(tuple.rightIndex)) {

                sum += (multiplier * tuple.gcd);
                multiplier--;

                expiredIndexes.add(tuple.leftIndex);
                expiredIndexes.add(tuple.rightIndex);

            }
            if(multiplier < 1) {
                break;
            }
        }

        return sum;
    }

    public static int gcd(final int n1, final int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }
}
