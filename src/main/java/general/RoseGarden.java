package general;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static java.util.Comparator.comparingInt;

/**
 * https://aonecode.com/google-online-assessment#rose-garden
 *
 * Rose Garden
 *
 * Given an array a of roses. For example [1, 2, 4, 9, 3, 4, 1]
 * a[i] means rose i will bloom on day a[i].
 * Here the first rose will bloom on day 1, the second rose will bloom on day 2, etc. etc.
 * Also given an int k, which is the minimum number of adjacent bloom roses required for a bouquet.
 * and an int n, which is the number of bouquets we need.
 * return the earliest day that we can get n bouquets of roses.
 *
 * for example:
 *
 *  a = [1, 2, 4, 9, 3, 4, 1], k = 2, n = 2
 *  day 1:
 *
 *  the first and the last rose bloom.
 *  [b, n, n, n, n, n, b]
 *
 *  day 2:
 *  the second rose bloom
 *  [b, b, n, n, n, n, b]
 *  Here the first two bloom roses make a bouquet.
 *
 *  day 3: [b, b, n, n, b, n, b]
 *  day 4: [b, b, n, n, b, b, b]
 *  Here the last three bloom roses make a bouquet, meeting the required n = 2 bouquets of bloom roses.
 *
 *  So return day 4.
 */
public class RoseGarden {

    public static int daysToBouquet(final int[] bloomDays, final int minAdjacency, final int neededBouquets) {
        final AtomicInteger ai = new AtomicInteger();
        class DisjointSet {
            final List<Integer> disjointSet;
            final int id;
            DisjointSet(final int value) {
                this.disjointSet = new ArrayList<>();
                this.disjointSet.add(value);
                this.id = ai.getAndIncrement();
            }

            boolean tryAdd(final DisjointSet other, final Set<DisjointSet> bouquet) {
                final boolean success = this.disjointSet.size() < minAdjacency &&
                    this.disjointSet.addAll(other.disjointSet);
                if(this.disjointSet.size() == minAdjacency) {
                    bouquet.add(this);
                }
                return success;
            }

            @Override
            public boolean equals(final Object other) {
                if(other instanceof final DisjointSet ds) {
                    return this.id == ds.id;
                }
                return false;
            }
        }

        final Map<Integer, DisjointSet> disjointSetMap = new HashMap<>();
        final PriorityQueue<int[]> heap = new PriorityQueue<>(comparingInt((final int[] ivp) -> ivp[1]).thenComparing((final int[] ivp) -> ivp[0]));
        for(int i = 0; i < bloomDays.length; i++) {
            disjointSetMap.put(i, new DisjointSet(bloomDays[i]));
            heap.offer(new int[] {i, bloomDays[i]});
        }
        final Set<DisjointSet> bouquets = new HashSet<>();

        final BiConsumer<Integer, Integer> consumeSet = (final Integer index, final Integer direction) -> {
            final DisjointSet current = disjointSetMap.get(index);
            final DisjointSet neighbor = disjointSetMap.get(index + direction);
            if(bloomDays[index + direction] <= bloomDays[index] && current.tryAdd(neighbor, bouquets)) {
                disjointSetMap.put(index + direction, current);
                disjointSetMap.put(index, current);
            }
        };

        while (!heap.isEmpty()) {
            final int[] nextFlower = heap.poll();
            final int index = nextFlower[0];
            final int value = nextFlower[1];

            if (index == 0) {
                consumeSet.accept(index, 1);
            } else if (index == bloomDays.length - 1) {
                consumeSet.accept(index, -1);
            } else {
                consumeSet.accept(index, 1);
                consumeSet.accept(index, -1);
            }

            if(bouquets.size() == neededBouquets) {
                return value;
            }
        }

        return -1;
    }
}
