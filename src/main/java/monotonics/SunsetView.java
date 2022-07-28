package monotonics;

import models.BiDirectionalIterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static models.BiDirectionalIterator.*;

public class SunsetView {

    public static List<Integer> sunsetViews(final int[] buildings, final String direction) {
        final Deque<Integer> idxMono = new ArrayDeque<>();

        final BiDirectionalIterator iterator = "WEST".equals(direction) ?
                new RightToLeftIterator(buildings.length) :
                new LeftToRightIterator(buildings.length);

        for(; iterator.getStopCondition().get(); iterator.getStep().run()) {
            while(!idxMono.isEmpty() && buildings[iterator.getStart().get()] >= buildings[idxMono.getLast()]) {
                idxMono.removeLast();
            }
            idxMono.addLast(iterator.getStart().get());
        }

        return new ArrayList<>(idxMono.stream().sorted().toList());
    }
}
