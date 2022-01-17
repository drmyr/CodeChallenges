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

            boolean tryComplete(final int foundIndex) {
                if(!isComplete()) {
                    if(sequence[sequenceBookmark].equals(products[foundIndex])) {
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
                final Set<Sequence> sequencesToRemove = new HashSet<>();
                for(final Sequence next : sequences) {
                    if(next.tryComplete(i) && (next.getRange() < result.getRange())) {
                        result = next;
                    }
                    // remove any sequences that we know cannot be the answer, as we have already found a better answer.
                    if((i - next.startIndex) > result.getRange()) {
                        sequencesToRemove.add(next);
                    }
                }
                sequences.removeAll(sequencesToRemove);
            }
        }

        // +1 because the range is inclusive of the start and end.
        return result.isComplete() ? result.getRange() + 1 : -1;
    }
}
