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
        }).reversed(); // Needs to be a max heap.

        final PriorityQueue<Integer[]> heap = new PriorityQueue<>(orderByLargestGains);
        double ratingSum = 0d;
        for(final Integer[] productRating : productRatings) {
            ratingSum += ((double)productRating[0] / (double)productRating[1]);
            heap.offer(productRating);
        }

        int additionalReviewsCount = 0;
        while((ratingSum / productRatings.length * 100) < ratingsThreshold) {
            final Integer[] productRating = heap.poll();
            ratingSum -= ((double)productRating[0] / (double)productRating[1]);
            productRating[0]++;
            productRating[1]++;
            ratingSum += ((double)productRating[0] / (double)productRating[1]);
            heap.offer(productRating);
            additionalReviewsCount++;
        }

        return additionalReviewsCount;
    }
}
