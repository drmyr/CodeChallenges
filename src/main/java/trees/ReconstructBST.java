package trees;

import models.BinaryNodeMutable;

import java.util.List;
import java.util.TreeMap;

import static java.util.Collections.reverseOrder;

public class ReconstructBST {

    /**
     * Rebuild a BinaryNodeMutable given its pre-order traversal
     */
    public static BinaryNodeMutable reconstructBst(final List<Integer> preOrderTraversalValues) {
        final BinaryNodeMutable root = new BinaryNodeMutable(preOrderTraversalValues.get(0));

        final TreeMap<Integer, BinaryNodeMutable> map = new TreeMap<>(reverseOrder());
        map.put(root.getValue(), root);

        BinaryNodeMutable current = root;
        for(int index = 1; index < preOrderTraversalValues.size(); index++) {
            final int value = preOrderTraversalValues.get(index);
            if(value < current.getValue()) {
                current.setLeft(new BinaryNodeMutable(value));
                current = current.getLeft();
            } else {
                // get if it already exists in the case of BST's that allow duplicates
                final BinaryNodeMutable parent = map.getOrDefault(value, map.higherEntry(value).getValue());
                parent.setRight(new BinaryNodeMutable(value));
                current = parent.getRight();
            }
            map.put(value, current);
        }

        return root;
    }
}
