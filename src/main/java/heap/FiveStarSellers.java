package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingDouble;

public class FiveStarSellers {

    /*
     https://aonecode.com/amazon-online-assessment-five-star-sellers

     Order the rating pairs such that the pair with the largest potential gain from a new five-star review
     is at the top of the heap.
     */
    public static int fiveStarReviews(final Integer[][] productRatings, final double ratingsThreshold) {
        final Comparator<Integer[]> orderByLargestGains = comparingDouble((final Integer[] arr) -> {
            final double currentRating = ((double)arr[0] / (double)arr[1]);
            final double additionalFiveStarRating = ((double)(arr[0] + 1) / (double)(arr[1] + 1));
            return additionalFiveStarRating - currentRating;
        }).reversed();

        final PriorityQueue<Integer[]> heap = new PriorityQueue<>(orderByLargestGains);
        double currentRating = 0d;
        for(final Integer[] productRating : productRatings) {
            currentRating += ((double)productRating[0] / (double)productRating[1]);
            heap.offer(productRating);
        }

        int additionalReviewsCount = 0;
        while((currentRating / productRatings.length * 100) < ratingsThreshold) {
            final Integer[] next = heap.poll();
            currentRating -= ((double)next[0] / (double)next[1]);
            next[0]++;
            next[1]++;
            currentRating += ((double)next[0] / (double)next[1]);
            heap.offer(next);
            additionalReviewsCount++;
        }

        return additionalReviewsCount;
    }
}
