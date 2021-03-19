package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingDouble;

public class FiveStarSellers {

    /*
     https://aonecode.com/amazon-online-assessment-five-star-sellers

     Third-party companies that sell their products online are able to analyze the customer reviews for their products
     in real time. Imagine that there is creating a category called "five-star sellers" that will only display products
     sold by companies whose average percentage of five-star reviews per-product is at or above a certain threshold.
     Given the number of five-star and total reviews for each product a company sells, as well as the threshold percentage,
     what is the minimum number of additional fivestar reviews the company needs to become a five-star seller?

     For example, let's say there are 3 products (n = 3) where productRatings = [[4,4], [1,2], [3, 6]], and the percentage
     ratings Threshold = 77. The first number for each product in productRatings denotes the number of fivestar reviews,
     and the second denotes the number of total reviews. Here is how we can get the seller to reach the threshold with
     the minimum number of additional five-star reviews:

     Before we add more five-star reviews, the percentage for this seller is ((4 / 4) + (1/2) + (3/6))/3 = 66.66%
     If we add a five-star review to the second product, the percentage rises to ((4 / 4) + (2/3) +(3/6))/3 = 72.22%
     If we add another five-star review to the second product, the percentage rises to ((4 / 4) + (3/4) + (3/6))/3 = 75.00%
     If we add a five-star review to the third product, the percentage rises to ((4/4) + (3/4) + (4/7))/3 = 77.38%

     At this point, the threshold of 77% has been met. Therefore, the answer is 3 because that is the minimum number of
     additional five-star reviews the company needs to become a five-star seller.

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
