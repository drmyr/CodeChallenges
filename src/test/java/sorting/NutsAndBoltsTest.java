package sorting;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.IntFunction;

import static sorting.NutsAndBolts.createPairs;
import static org.junit.jupiter.api.Assertions.*;
import static sorting.NutsAndBolts.nutsAndBolts;

class NutsAndBoltsTest {

    @Test
    void createPairsTest() {
        final int[] nuts = {5,7,2,9,6,1,0,4,8,3};
        final int[] bolts = {6,3,8,0,2,1,9,5,7,4};
        for(final int[] pair : createPairs(nuts, bolts)) {
            assertEquals(pair[0], pair[1]);
        }
    }

    @Test
    public void testNutsAndBoltsOne() {
        final Integer[] nuts = {5,7,2,9,6,1,0,4,8,3};
        final Integer[] bolts = {6,3,8,0,2,1,9,5,7,4};
        nutsAndBolts(nuts, bolts);
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
        for(int i = 0; i < nuts.length; i++) {
            assertSame(nuts[i], bolts[i]);
        }
    }

    @Test
    public void testNutsAndBoltsTwo() {
        final Random random = new Random();
        final Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 30; i++) {
            set.add(random.nextInt(0, 100));
        }
        final List<Integer> list = new ArrayList<>(set);

        Collections.shuffle(list);
        final Integer[] nuts = list.toArray(Integer[]::new);

        Collections.shuffle(list);
        final Integer[] bolts = list.toArray(Integer[]::new);

        nutsAndBolts(nuts, bolts);
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }
}