package dp;

import java.util.*;

import static java.util.Collections.*;

public class FootballScoreCombos {

    /**
     * How many ways are there to reach a given final score in a football game, given the number
     * of ways there are to score
     */
    public static int countWaysToFinalScore(final int finalScore) {
        final int safety = 2;
        final int fieldGoal = 3;
        final int touchDown = 6;
        final int touchDownWithPointAfter = 7;

        final int[] waysToScore = {safety, fieldGoal, touchDown, touchDownWithPointAfter};

        final int[][] dp = new int[waysToScore.length][finalScore + 1];

        for(int r = 0; r < waysToScore.length; r++) dp[r][0] = 1; // There is 1 way to achieve a score of 0

        for(int r = 0; r < waysToScore.length; r++) {
            for(int c = 1; c < finalScore + 1; c++) {
                final int withoutThisScore = r - 1 >= 0 ? dp[r - 1][c] : 0;
                final int withThisScore = c >= waysToScore[r] ? dp[r][c - waysToScore[r]] : 0;
                dp[r][c] = withoutThisScore + withThisScore;
            }
        }

        return dp[waysToScore.length - 1][finalScore];
    }

    /**
     * Elements of Programming Interviews, page 308
     *
     * Variant: Write a program that takes a final score ands cores for individual plays, and returns the number of sequences
     * of plays that result in the final score. For example, 18 sequences of plays yield a score of 12.
     * Some examples are (2,2,2,3,3),(2,3,2,2,3),(2,3,7),(7,3,2).
     *
     * @param finalScore
     * @return
     */
    public static Set<List<Integer>> enumerateSequencesToFinalScore(final int finalScore) {
        final int safety = 2;
        final int fieldGoal = 3;
        final int touchDown = 6;
        final int touchDownWithPointAfter = 7;

        final int[] waysToScore = {safety, fieldGoal, touchDown, touchDownWithPointAfter};

        final Map<Integer, Set<List<Integer>>> scoreMap = new HashMap<>();
        scoreMap.put(0, emptySet());
        scoreMap.put(1, emptySet());
        scoreMap.put(2, singleton(singletonList(2)));
        scoreMap.put(3, singleton(singletonList(3)));
        scoreMap.put(4, singleton(List.of(2, 2)));

        for(int i = 5; i <= finalScore; i++) {
            for(int wayToScore : waysToScore) {
                if(scoreMap.containsKey(i - wayToScore)) {
                    if(scoreMap.get(i - wayToScore).isEmpty() && i == wayToScore) {
                        scoreMap.merge(i, singleton(singletonList(i)), (a, b) -> {
                            final Set<List<Integer>> newSet = new HashSet<>();
                            newSet.addAll(a);
                            newSet.addAll(b);
                            return newSet;
                        });
                    }
                    for (final List<Integer> ways : scoreMap.get(i - wayToScore)) {
                        final List<Integer> newWay = new ArrayList<>();
                        newWay.add(wayToScore);
                        newWay.addAll(ways);
                        scoreMap.merge(i, Set.of(newWay), (a, b) -> {
                            final Set<List<Integer>> newSet = new HashSet<>();
                            newSet.addAll(a);
                            newSet.addAll(b);
                            return newSet;
                        });
                    }
                }
            }
        }

        return scoreMap.get(finalScore);
    }
}
