package general;

import java.util.*;
import java.util.function.Function;

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

    public List<String> topKFrequent(final String[] words, final int k) {
        final Map<String, Integer> freqMap = new HashMap<>();
        for(final String word : words) freqMap.merge(word, 1, Integer::sum);

        final PriorityQueue<String> kFreq = new PriorityQueue<>(Comparator.comparing(Function.identity(), (o1, o2) -> {
            final int diff = freqMap.get(o1) - freqMap.get(o2);
            if(diff == 0) {
                return o1.compareTo(o2);
            } else if(diff > 0) {
                return -1;
            } else {
                return 1;
            }
        }));

        for(final String word : freqMap.keySet()) kFreq.offer(word);

        final List<String> result = new ArrayList<>();
        int remaining = k;
        while(remaining > 0) {
            result.add(kFreq.poll());
            remaining--;
        }

        return result;
    }
}
