package heap;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

public class NumberGame {

    /**
     * https://aonecode.com/amazon-online-assessment-oa2-number-game
     *
     * You are given an array of 2N positive integers. There are N rounds in total.
     * In each round, you have to choose any two positive integers from the array and delete them.
     * Your score in each round will be gcd(num, num2) x round_number, where num1 & num2 are the
     * numbers you have chosen and round_number is the current round. Your total score will be
     * the summation of the scores that you have obtained in every round.
     *
     * Task
     * Determine the maximum total score.
     * Note: The round number starts from 1
     *
     * Example
     * N=3
     * A = [8, 5, 6, 25, 6, 16]
     *
     * Then round can proceed as shown below:
     * Round_number 1: Select num = 5, num2 = 25. So the value of gcd(5, 25) * round number equals 5*1 = 5
     * Round_number 2: Select num = 6, num2 = 6. So the value of gcd(6, 6) * round number equals 6*2 = 12
     * Round_number 3: Select num1 = 8. num2 = 16. So the value of gcd(8, 16) * round number equals 8*3 = 24
     * Therefore, the maximum total Score equals 5 + 12 + 24 = 41.
     * @return
     */
    public static int getMaxScore(final int rounds, final int[] array) {
        final BinaryOperator<Integer> gcdByEuclidsAlgorithm = (final Integer n1, final Integer n2) -> {
            Integer temp1 = n1;
            Integer temp2 = n2;
            while(temp2 != 0) {
                final Integer mod = temp1 % temp2;
                temp1 = temp2;
                temp2 = mod;
            }
            return temp1;
        };

        final List<Integer> minSet = new ArrayList<>();
        for(final int value : array) {
            minSet.add(value);
        }

        minSet.sort(naturalOrder());

        int maxScore = 0;
        int roundCount = 1;
        while(!minSet.isEmpty()) {
            final int smallest = minSet.remove(0);
            int gcd = 0;
            int matchIndex = 0;
            for(int i = 0; i < minSet.size(); i++) {
                final int maybeMatch = minSet.get(i);
                final int localGcd = gcdByEuclidsAlgorithm.apply(smallest, maybeMatch);
                if(localGcd > gcd) {
                    gcd = localGcd;
                    matchIndex = i;
                }
            }
            maxScore += gcd * roundCount++;
            minSet.remove(matchIndex);
        }

        return maxScore;

    }
}
