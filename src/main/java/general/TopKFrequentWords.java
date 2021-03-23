package general;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class TopKFrequentWords {

    /**
     * https://aonecode.com/amazon-online-assessment-top-n-buzzwords
     */
    public static Set<String> getTopGames(final int gameCount, final int topKGames, final Set<String> games, final int numReviews, final Set<String> reviews) {
        class ReviewFreqAndCount {
            int count = 0;
            final Set<String> reviews;
            ReviewFreqAndCount() {
                this.reviews = new HashSet<>();
            }
        }

        final Map<String, ReviewFreqAndCount> wordFrequencyAndReviewCount = new HashMap<>();

        final List<String> meetsThreshold = new ArrayList<>();

        for(final String review : reviews) {
            final String[] words = review.toLowerCase().split("\\W");
            for(final String word : words) {
                if(games.contains(word)) {
                    final ReviewFreqAndCount freqAndReviewCount = wordFrequencyAndReviewCount.getOrDefault(word, new ReviewFreqAndCount());
                    freqAndReviewCount.count++;
                    freqAndReviewCount.reviews.add(review);
                    if(freqAndReviewCount.count >= topKGames) {
                        meetsThreshold.add(word);
                    }
                    wordFrequencyAndReviewCount.put(word, freqAndReviewCount);
                }
            }
        }

        meetsThreshold.sort(comparingInt((final String word) -> wordFrequencyAndReviewCount.get(word).count)
                .thenComparing((final String word) -> wordFrequencyAndReviewCount.get(word).reviews.size()));
        return new HashSet<>(meetsThreshold);
    }
}
