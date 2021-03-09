package heap;

import java.util.*;

import static java.util.Map.Entry;

public class ProductRankings {

    /**
     * https://aonecode.com/interview-question/ranking-products
     *
     * Organize products into rows of a requested size, and ranked by name, relevance, or rating, depending
     * on the desire of the caller.
     *
     * @param numOfProducts
     * @param products
     * @param sortKey name = 0, relevance = 1, rating = 2
     * @param sortOrder true = asc, false = desc
     * @param productsPerRow
     * @param rowNumber
     * @return
     */
    public static List<String> rankingProducts(
            final int numOfProducts,
            final Map<String, Integer[]> products,
            final int sortKey,
            final boolean sortOrder,
            final int productsPerRow,
            final int rowNumber) {

        final int name = 0, relevance = 1, rating = 2;

        final Comparator<Entry<String, Integer[]>> comparator = (final Entry<String, Integer[]> o1, final Entry<String, Integer[]> o2) -> {
            if(sortKey == name) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                // subtract one since relevance is at index 0 in its array, and rating is at index 1
                final int index = sortKey - 1;
                return o1.getValue()[index].compareTo(o2.getValue()[index]);
            }
        };
        final PriorityQueue<Entry<String, Integer[]>> heap = new PriorityQueue<>(sortOrder ? comparator : comparator.reversed());
        for(final Entry<String, Integer[]> entry : products.entrySet()) {
            heap.offer(entry);
        }
        final Deque<List<String>> rowQueue = new ArrayDeque<>();

        List<String> row = new ArrayList<>();
        while(!heap.isEmpty() && rowQueue.size() < rowNumber) {
            if(row.size() < productsPerRow) {
                row.add(heap.poll().getKey());
            } else {
                rowQueue.offerLast(row);
                row = new ArrayList<>();
            }
            if(heap.isEmpty()) {
                rowQueue.offerLast(row);
            }
        }

        return rowQueue.getLast();
    }
}
