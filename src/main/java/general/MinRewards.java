package general;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MinRewards {

    /**
     * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     *     Each child must have at least one candy.
     *     Children with a higher rating get more candies than their neighbors.
     *
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     */
    public static int minRewards(final int[] scores) {
        if (scores.length == 1) return 1;
        if (scores.length == 2) return 3;

        final int[] result = new int[scores.length];

        // make a queue of indexes of all the minima in the range
        final Queue<Integer> queue = new ArrayDeque<>();
        if (scores[0] < scores[1]) {
            queue.offer(0);
        }
        for (int i = 1; i < scores.length - 1; i++) {
            if (scores[i] < scores[i - 1] && scores[i] < scores[i + 1]) {
                queue.offer(i);
            }
        }
        if (scores[scores.length - 1] < scores[scores.length - 2]) {
            queue.offer(scores.length - 1);
        }

        // from each index minima, we look left and right, incrementing their result if they are
        // greater than the given index. Their index is then added to the queue.
        while (!queue.isEmpty()) {
            final int index = queue.poll();
            if (result[index] == 0) {
                result[index]++;
            }
            if (index - 1 >= 0 && scores[index - 1] > scores[index]) {
                result[index - 1] = result[index] + 1;
                queue.offer(index - 1);
            }
            if (index + 1 < scores.length && scores[index + 1] > scores[index]) {
                result[index + 1] = result[index] + 1;
                queue.offer(index + 1);
            }
        }

        return Arrays.stream(result).sum();
    }
}
