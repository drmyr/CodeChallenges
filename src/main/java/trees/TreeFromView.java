package trees;

import models.BinaryNodeMutable;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public class TreeFromView {

    /**
     *           3
     *         /  \
     *        2    5
     *       / \  / \
     *      1  4 6  7
     * pre: 3 2 1 4 5 6 7
     * in:  1 2 4 3 6 5 7
     *
     *           3
     *         /  \
     *        2    5
     *       /    / \
     *      1    6  7
     * pre: 3 2 1 5 6 7
     * in:  1 2 3 6 5 7
     * Given pre and in-order traversal, construct the tree
     *
     * The root node of the tree is the 0th index of the pre-order traversal. With that information, you can find the
     * root node in the in-order traversal via a left-to-right scan. From there, everything to the left of the root node in the in-order
     * traversal is the left subtree, and everything to the right is the right subtree. Iteratively create left and right
     * sublists from the original lists, and leverage the same information above (that the 0th index of the pre-order
     * traversal is the root node) to create the next stage of the re-build.
     */
    public static BinaryNodeMutable buildFromPreAndInOrder(final int[] preOrderArray, final int[] inOrderArray) {
        if(preOrderArray.length == 0 || inOrderArray.length == 0) throw new IllegalArgumentException();
        if(preOrderArray.length != inOrderArray.length) throw new IllegalArgumentException();

        final List<Integer> preOrderList = Arrays.stream(preOrderArray).boxed().collect(toList());
        final List<Integer> inOrderList = Arrays.stream(inOrderArray).boxed().collect(toList());

        class NodeBuildTask {
            private final List<Integer> preOrder;
            private final List<Integer> inOrder;
            private final Consumer<BinaryNodeMutable> setter;

            public NodeBuildTask(final List<Integer> preOrder, final List<Integer> inOrder, final Consumer<BinaryNodeMutable> setter) {
                this.preOrder = preOrder;
                this.inOrder = inOrder;
                this.setter = setter;
            }
        }

        final AtomicReference<BinaryNodeMutable> root = new AtomicReference<>();
        final Queue<NodeBuildTask> queue = new ArrayDeque<>();
        queue.offer(new NodeBuildTask(preOrderList, inOrderList, root::set));

        while(!queue.isEmpty()) {
            final NodeBuildTask job = queue.poll();

            if(job.inOrder.isEmpty() && job.preOrder.isEmpty()) continue;

            int inOrderIdx = 0;
            while(!job.inOrder.get(inOrderIdx).equals(job.preOrder.get(0))) inOrderIdx++;
            final BinaryNodeMutable nextRoot = new BinaryNodeMutable(job.inOrder.get(inOrderIdx));

            job.setter.accept(nextRoot);
            queue.offer(new NodeBuildTask(
                    job.preOrder.subList(1, inOrderIdx + 1),
                    job.inOrder.subList(0, inOrderIdx),
                    nextRoot::setLeft));
            queue.offer(new NodeBuildTask(
                    job.preOrder.subList(inOrderIdx + 1, job.preOrder.size()),
                    job.inOrder.subList(inOrderIdx + 1, job.inOrder.size()),
                    nextRoot::setRight));
        }

        return root.get();
    }
}
