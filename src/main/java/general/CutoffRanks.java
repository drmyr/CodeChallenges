package general;

import java.util.TreeMap;

import static java.util.Collections.reverseOrder;

public class CutoffRanks {

    /*
     https://aonecode.com/amazon-online-assessment-cutoff-ranks
     */
    public static int rankCutoff(final int cutoffRank, final int arrLength, final int[] scores) {
        final TreeMap<Integer, Integer> scoreAndCount = new TreeMap<>(reverseOrder());

        for(final int score : scores) {
            scoreAndCount.merge(score, 1, Integer::sum);
        }

        int count = cutoffRank;
        int result = 0;
        while(count > 0) {
            final Integer passingPlayers = scoreAndCount.pollFirstEntry().getValue();
            result += passingPlayers;
            count -= passingPlayers;
        }

        return result;
    }
}
