package general;

import java.util.Stack;
import java.util.function.Predicate;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleOfHistogram {

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
