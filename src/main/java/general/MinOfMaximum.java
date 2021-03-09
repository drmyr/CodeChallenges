package general;

import java.util.*;

import static java.util.Collections.reverseOrder;

public class MinOfMaximum {

    /**
     * https://aonecode.com/interview-question/MaxOfMinima
     *
     * Use a monotonic queue to find the minimum over a sliding window.
     *
     * @param vacationDays
     * @param temps
     * @param tripLength
     * @return
     */
    public static int minOfMaximums(final int vacationDays, final int[] temps, final int tripLength) {
        /**
         * https://labuladong.gitbook.io/algo-en/ii.-data-structure/monotonic_queue
         */
        class MethodLocalMonotonicMinimumQueue {
            private final Deque<Integer> valueQueue;
            MethodLocalMonotonicMinimumQueue() {
                this.valueQueue = new ArrayDeque<>();
            }

            void push(final int value) {
                while(!this.valueQueue.isEmpty() && this.valueQueue.getLast() > value) {
                    this.valueQueue.removeLast();
                }
                this.valueQueue.offerLast(value);
            }

            int min() {
                return this.valueQueue.getFirst();
            }

            void pop(final int value) {
                if(!this.valueQueue.isEmpty() && this.valueQueue.getFirst() == value) {
                    this.valueQueue.removeFirst();
                }
            }
        }

        final MethodLocalMonotonicMinimumQueue windowQueue = new MethodLocalMonotonicMinimumQueue();
        final TreeSet<Integer> result = new TreeSet<>(reverseOrder());
        for(int i = 0; i < temps.length; i++) {
            if(i < tripLength - 1) {
                windowQueue.push(temps[i]);
            } else {
                windowQueue.push(temps[i]);
                result.add(windowQueue.min());
                windowQueue.pop(temps[i - tripLength + 1]);
            }
        }

        return result.first();
    }
}
