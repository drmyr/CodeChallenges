package monotonics;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Collections.reverseOrder;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleOfHistogram {

    /*
     * http://www.algorithmsandme.com/monotonic-queue/
     *
     * Not mine, wanted the example handy
     */
    public static int largestRectangleOfHistogramMonotonicQueue(final int[] columns) {
        final Deque<Integer> stackOfIndices = new ArrayDeque<>();

        int maxArea = 0;
        for(int i = 0; i <= columns.length; i++) {
            final int height = i == columns.length ? 0 : columns[i];
            while(!stackOfIndices.isEmpty() && height < columns[stackOfIndices.peek()]){
                final int top = stackOfIndices.pop();
                final int leftLimit = stackOfIndices.isEmpty() ? -1 : stackOfIndices.peek();
                final int width = i - leftLimit - 1;
                final int area = width * columns[top];
                maxArea = Integer.max(area, maxArea);
            }
            stackOfIndices.push(i);
        }
        return maxArea;
    }

    public static int largestRectangleOfHistogram(final int[] columns) {
        final Stack<Integer> stackOfIndexes = new Stack<>();

        final Predicate<Integer> isHistogramStillGoingUp = (final Integer index) ->
            stackOfIndexes.isEmpty() || columns[stackOfIndexes.peek()] < columns[index];

        final Predicate<Integer> isHistogramFlatOrFalling = (final Integer index) ->
            !stackOfIndexes.isEmpty() && columns[index] <= columns[stackOfIndexes.peek()];

        int max = 0;
        Integer index = 0;

        while(index < columns.length) {
            if(!isHistogramStillGoingUp.test(index)) {
                while(isHistogramFlatOrFalling.test(index)) {
                    final int top = stackOfIndexes.pop();
                    if(stackOfIndexes.isEmpty()) {
                        max = Math.max(max, columns[top] * (index));
                    } else {
                        max = Math.max(max, (columns[top] * (index - stackOfIndexes.peek() - 1)));
                    }
                }
            }
            stackOfIndexes.push(index++);
        }
        while (!stackOfIndexes.isEmpty()) {
            final int top = stackOfIndexes.pop();
            if (stackOfIndexes.isEmpty()) {
                max = Math.max(max, columns[top] * (columns.length));
            } else {
                max = Math.max(max, columns[top] * (columns.length - stackOfIndexes.peek() - 1));
            }
        }
        return max;
    }
}
