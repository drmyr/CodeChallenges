package trees;

import models.Node;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trees.TopologicalSort.topologicalSortImperative;

class TopologicalSortTest {

    @Test
    void topologicalSortImperativeTest() {
        final AtomicInteger ai = new AtomicInteger();
        final Node left = new Node(ai.getAndIncrement(), emptyList());
        final Node right = new Node(ai.getAndIncrement(), emptyList());
        final Node root = new Node(ai.getAndIncrement(), List.of(left, right));

        final Deque<Node> result = topologicalSortImperative(root);

        assertTrue(result.pollFirst().getId() < 2);
        assertTrue(result.pollFirst().getId() < 2);
        assertEquals(2, result.pollFirst().getId());
    }
}