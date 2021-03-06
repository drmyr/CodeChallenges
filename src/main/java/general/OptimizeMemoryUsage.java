package general;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import static java.util.Collections.emptyList;
import static java.util.Comparator.*;
import static java.util.Objects.nonNull;

public class OptimizeMemoryUsage {

    /**
     * https://aonecode.com/amazon-online-assessment-oa2-optimize-memory-usage
     *
     * Give a computer with total K memory space, and an array of foreground tasks and background
     * tasks the computer needs to do. Write an algorithm to find a pair of tasks from each array to
     * maximize the memory usage. Notice the tasks could be done without origin order.
     *
     * Input
     * The input to the function/method consists of three arguments :
     * foregroundTask, an array representing the memory usage of the foreground tasks,
     * backgroundTask, an array representing the memory usage of the background tasks,
     * K, the total memory space of the computer.
     *
     * Output
     * Return a list of pairs of the task ids.
     *
     * Examples 1
     * Input:
     * foregroundTasks = [1, 7, 2, 4, 5, 6]
     * backgroundTasks = [3, 1, 2]
     * K = 6
     *
     * Output:
     * [(3, 2), (4, 1), (5,-1)]
     *
     * Explanation:
     * Here we have 5 foreground tasks: task 0 uses 1 memory. task 1 uses 7 memory. task 2 uses 2 memory..
     * And 3 background tasks: task 0 uses 3 memory. task 1 uses 1 memory. task 2 uses 2 memory..
     * We need to find two tasks with total memory usage sum <= K.
     * Here we can return the foreground task 3 and background task 2, which total use 6 units of memory.
     * Or we can return the foreground task 4 and background task 1. Also use total 6 units of memory.
     * Or we can return the foreground task 5 only without any background task. Also use total 6 units of memory.
     * @param foregroundTasks
     * @param backgroundTasks
     * @param maxMemory
     * @return
     */
    public static Integer[][] optimizeMemoryUsage(final int[] foregroundTasks, final int[] backgroundTasks, final int maxMemory) {
        class Pair {
            final int value, index;
            Pair(final int value, final int index) {
                this.value = value;
                this.index = index;
            }
        }

        final List<Integer[]> pairs = new ArrayList<>();
        final AtomicInteger optimal = new AtomicInteger(0);

        final Consumer<Integer> trySetOptimal = (final Integer value) -> {
            if(optimal.get() != maxMemory && value <= maxMemory) {
                optimal.set(Math.max(optimal.get(), value));
            }
        };

        final TreeSet<Pair> priceIsRight = new TreeSet<>(comparingInt((final Pair pair) -> pair.value).reversed());
        for(int i = 0; i < foregroundTasks.length; i++) {
            if(foregroundTasks[i] < 1) continue;
            if(foregroundTasks[i] == maxMemory) {
                pairs.add(new Integer[] {i, -1});
            } else if(foregroundTasks[i] < maxMemory){
                priceIsRight.add(new Pair(foregroundTasks[i], i));
            }
            trySetOptimal.accept(foregroundTasks[i]);
        }

        final TreeMap<Integer, List<Integer[]>> pairsBySum = new TreeMap<>(reverseOrder());
        final BinaryOperator<List<Integer[]>> merger = (final List<Integer[]> listA, final List<Integer[]> listB) -> {
            final List<Integer[]> newList = new ArrayList<>();
            newList.addAll(listA);
            newList.addAll(listB);
            return newList;
        };
        for(int i = 0; i < backgroundTasks.length; i++) {
            if(backgroundTasks[i] < 1) continue;
            if(backgroundTasks[i] == maxMemory) {
                pairs.add(new Integer[] {-1, i});
                trySetOptimal.accept(backgroundTasks[i]);
            } else if(backgroundTasks[i] < maxMemory) {
                final int lacking = maxMemory - backgroundTasks[i];
                final Pair complementPair = priceIsRight.ceiling(new Pair(lacking, -1));
                final int complementValue = nonNull(complementPair) ? complementPair.value : 0;
                final int complementIndex = nonNull(complementPair) ? complementPair.index : -1;
                final int sum = backgroundTasks[i] + complementValue;
                trySetOptimal.accept(sum);
                pairsBySum.merge(sum, List.<Integer[]>of(new Integer[] {complementIndex, i}), merger);
            }
        }

        pairs.addAll(pairsBySum.getOrDefault(optimal.get(), emptyList()));
        return pairs.toArray(Integer[][]::new);
    }
}
