package models;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonicMinimumQueue<T extends Comparable<T>> {

    private final Deque<T> valueQueue;

    public MonotonicMinimumQueue() {
        this.valueQueue = new ArrayDeque<>();
    }

    public void push(final T value) {
        while(!this.valueQueue.isEmpty() && this.valueQueue.getLast().compareTo(value) > 0) {
            this.valueQueue.removeLast();
        }
        this.valueQueue.offerLast(value);
    }

    public T min() {
        return this.valueQueue.getFirst();
    }

    void pop(final T value) {
        if(!this.valueQueue.isEmpty() && this.valueQueue.getFirst().compareTo(value) == 0) {
            this.valueQueue.removeFirst();
        }
    }
}
