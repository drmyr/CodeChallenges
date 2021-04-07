package general;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Integer> cacheMap;
    private final Deque<Integer> recency;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.recency = new ArrayDeque<>();
    }

    public int get(final int key) {
        final Integer value = cacheMap.get(key);
        if(value == null) {
            return Integer.MIN_VALUE;
        }
        this.recency.remove(key);
        this.recency.offerLast(key);
        return value;
    }

    public void put(final int key, final int value) {
        while(this.recency.size() > this.capacity) {
            this.cacheMap.remove(this.recency.pollFirst());
        }
        this.cacheMap.put(key, value);
        this.recency.offerLast(key);
    }
}
