package general;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumBoundedArray {

    /*
     https://aonecode.com/interview-question/maximum-bounded-array
     */
    public static Integer[] maxBoundedArray(final int range, final int lowerBound, final int upperBound) {
        if(range > 2 * (upperBound - lowerBound)) return null;

        final Deque<Integer> span = new ArrayDeque<>();

        int decrementingForwardBound = upperBound;
        while(decrementingForwardBound >= lowerBound) {
            span.addLast(decrementingForwardBound--);
        }

        int decrementingBackwardBound = upperBound - 1;
        while(span.size() < range) {
            span.addFirst(decrementingBackwardBound--);
        }

        return span.toArray(Integer[]::new);
    }
}
