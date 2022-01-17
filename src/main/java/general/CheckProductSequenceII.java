package general;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckProductSequenceII {

    /**
     * https://aonecode.com/Interview-Question/Check-Product-Sequence-II
     *
     * given a list, and a priority sequence, return the length of the shortest product sublist that satisfies the
     * priority sequence
     *
     * Ex1
     * products = ["bio", "nulo", "amaz", "nulo", "bioto", "candi"]
     * sequence = ["nulo", "bioto", "candi"]
     *
     * Valid subsequences are ["nulo", "amaz", "nulo", "bioto", "candi"] and ["nulo", "bioto", "candi"], but the second
     * one is shorter, so return 3
     */
    public static int shortestSubsequence(final String[] products, final String[] sequence) {
        class Sequence {
            final int startIndex;
            int sequenceBookmark;
            int endIndex;
            Sequence(final int startIndex) {
                this.startIndex = startIndex;
                this.sequenceBookmark = 1;
                this.endIndex = Integer.MAX_VALUE;
            }

            boolean isComplete() {
                return this.endIndex != Integer.MAX_VALUE;
            }

            int getRange() {
                return endIndex - startIndex;
            }

            boolean tryComplete(final String nextFound, final int foundIndex) {
                if(!isComplete()) {
                    if(sequence[sequenceBookmark].equals(nextFound)) {
                        sequenceBookmark++;
                    }
                    if(sequenceBookmark == sequence.length) {
                        endIndex = foundIndex;
                    }
                }
                return isComplete();
            }

            @Override
            public boolean equals(final Object other) {
                if(other instanceof final Sequence sequence1) {
                    return this == sequence1;
                }
                return false;
            }
        }

        final Set<String> sequenceSet = new HashSet<>(List.of(sequence));

        final Set<Sequence> sequences = new HashSet<>();

        Sequence result = new Sequence(0);
        for(int i = 0; i < products.length; i++) {
            if(products[i].equals(sequence[0])) {
                sequences.add(new Sequence(i));
            } else if(sequenceSet.contains(products[i])) {
                for(final Sequence next : sequences) {
                    if(next.tryComplete(products[i], i) && (next.getRange() < result.getRange())) {
                        result = next;
                    }
                }
            }
        }

        return result.getRange() + 1;
    }
}
