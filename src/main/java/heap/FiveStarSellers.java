package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToDoubleFunction;

public class FiveStarSellers {

    /*
     https://aonecode.com/amazon-online-assessment-five-star-sellers

     Order the rating pairs such that the pair with the largest potential gain from a new five-star review
     is at the top of the heap.
     */
    public static int fiveStartReviews(final Integer[][] productRatings, final double ratingsThreshold) {
        final Comparator<Integer[]> orderByLargestGains = Comparator.comparingDouble((final Integer[] arr) -> {
            final double currentRating = ((double)arr[0] / (double)arr[1]);
            final double additionalFiveStarRating = ((double)(arr[0] + 1) / (double)(arr[1] + 1));
            return additionalFiveStarRating - currentRating;
        }).reversed();

        final PriorityQueue<Integer[]> heap = new PriorityQueue<>(orderByLargestGains);
        for(final Integer[] productRating : productRatings) {
            heap.offer(productRating);
        }

        final ToDoubleFunction<Integer[][]> rating = (final Integer[][] ratings) -> {
            double result = 0;
            for (final Integer[] productRating : ratings) {
                result += ((double) productRating[0]) / ((double) productRating[1]);
            }
            return result / ratings.length * 100;
        };

        int additionalReviewsCount = 0;
        double currentRating;
        while((currentRating = rating.applyAsDouble(heap.toArray(Integer[][]::new))) < ratingsThreshold) {
            final Integer[] next = heap.poll();
            next[0]++;
            next[1]++;
            heap.offer(next);
            additionalReviewsCount++;
        }

        return additionalReviewsCount;
    }
}
