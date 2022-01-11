package trees;

import org.junit.jupiter.api.Test;

import static trees.TreeFromView.buildFromPreAndInOrder;

class TreeFromViewTest {

    @Test
    void buildFromPreAndInOrderTest() {
        buildFromPreAndInOrder(new int[] {3, 2, 1, 4, 5, 6, 7}, new int[] {1, 2, 4, 3, 6, 5, 7});
        buildFromPreAndInOrder(new int[] {3, 2, 1, 5, 6, 7}, new int[] {1, 2, 3, 6, 5, 7});
        buildFromPreAndInOrder(new int[] {3}, new int[] {3});
    }
}