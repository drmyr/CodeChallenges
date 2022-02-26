package general;

import org.junit.jupiter.api.Test;

import static general.RemoveNthNodeFromEnd.ListNode;

import static general.RemoveNthNodeFromEnd.removeNthFromEnd;

class RemoveNthNodeFromEndTest {

    @Test
    void removeNthFromEndTest() {
        final ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        removeNthFromEnd(head, 2);
    }
}