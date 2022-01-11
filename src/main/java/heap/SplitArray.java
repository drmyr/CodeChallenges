package heap;

import java.util.*;

import static java.util.Collections.reverseOrder;

public class SplitArray {

    /**
     * Split an integer array into two sub arrays such that
     *
     * sum(subarray1) >= sum(subarray2)
     * length(subarray1) <= length(subarray2)
     *
     * Ex.1
     * arr = [3,2,1,4,0,6,5]
     *
     * subarray1 = [4,5,6]
     * subarray2 = [0,1,2,3]
     *
     * [4,5,6] is greater than [0,1,2,3] and has fewer elements
     */
    public static Integer[] splitArray(final Integer[] inputs) {
        if(inputs.length < 2) return null;

        int sum = Arrays.stream(inputs).reduce(0, Integer::sum);
        final PriorityQueue<Integer> heap = new PriorityQueue<>(reverseOrder());
        for(final int input : inputs) heap.offer(input);

        int runningTotal = 0;
        final List<Integer> result = new ArrayList<>();
        Integer next;
        while((next = heap.poll()) != null && heap.size() >= Math.ceil((double)inputs.length / 2)) {
            sum -= next;
            runningTotal += next;
            if(result.size() <= heap.size()) {
                result.add(next);
            }
        }

        return runningTotal >= sum ? result.toArray(Integer[]::new) : null;
    }
}
