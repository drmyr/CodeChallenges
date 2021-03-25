package dp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QualifyingTeams {

    /**
     * https://leetcode.com/discuss/interview-question/1122369/Amazon-Online-Assessment-for-SDE-role
     *
     * n! / r! * (n - r)!
     * @param teamMembers
     * @param skills
     * @param teamSize
     * @param minLevel
     * @param maxLevel
     * @return
     */
    public static int howManyQualifyingTeams(final int teamMembers, final int[] skills, final int teamSize, final int minLevel, final int maxLevel) {
        final int candidateCount = Arrays.stream(skills).filter(i -> i >= minLevel && i <= maxLevel).toArray().length;

        if(teamSize == 1) return candidateCount;

        final Map<Integer, BigInteger> factorialByValue = new HashMap<>();
        factorialByValue.put(0, BigInteger.ONE);
        factorialByValue.put(1, BigInteger.ONE);

        BigInteger start = BigInteger.ONE;
        for(int i = 2; i <= candidateCount; i++) {
            start = start.multiply(BigInteger.valueOf(i));
            factorialByValue.put(i, start);
        }

        // n! / r! * (n - r)!
        start = BigInteger.ZERO;
        final BigInteger numerator = factorialByValue.get(candidateCount);
        for(int i = teamSize; i <= candidateCount; i++) {
            final BigInteger denomLeft = factorialByValue.get(i);
            final BigInteger denomRight = factorialByValue.get(candidateCount - i);
            start = start.add(numerator.divide(denomLeft.multiply(denomRight)));
        }

        return start.intValue();
    }
}
