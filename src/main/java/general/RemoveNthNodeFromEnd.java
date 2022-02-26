package general;

import java.util.HashMap;
import java.util.Map;

public class RemoveNthNodeFromEnd {

    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode removeNthFromEnd(final ListNode head, final int n) {
        final Map<Integer, ListNode> map = new HashMap<>();

        int i = 1;
        ListNode current = head;
        while(current != null) {
            map.put(i, current);
            current = current.next;
            i++;
        }

        if(map.size() == 1) return null;
        if(map.size() == 2) return n == 1 ? new ListNode(head.val, null) : head.next;
        if(map.size() == n) return head.next;

        map.get(map.size() - n).next = map.get(map.size() - n + 2);

        return head;
    }
}
