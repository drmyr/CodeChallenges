package general;

import java.math.BigInteger;

public class AddTwoNumbers {

    /**
     * https://leetcode.com/problems/add-two-numbers/
     *
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example 1:
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     */
    public static ListNode addTwoNumbersLong(final ListNode l1, final ListNode l2) {
         long l1sum = 0;
         int currentPower = 0;
         ListNode l1ref = l1;
         while(l1ref != null) {
             l1sum += Math.pow(10, currentPower++) * l1ref.val;
             l1ref = l1ref.next;
         }

         long l2sum = 0;
         currentPower = 0;
         ListNode l2ref = l2;
         while(l2ref != null) {
             l2sum += Math.pow(10, currentPower++) * l2ref.val;
             l2ref = l2ref.next;
         }

         long sum = (l2sum + l1sum);
         final ListNode root = new ListNode();
         ListNode next = root;
         while(sum != 0) {
             next.val = (int)(sum % 10L);
             sum /= 10;
             if(sum != 0) {
                 next.next = new ListNode();
                 next = next.next;
             }
         }

        return root;
    }

    public static ListNode addTwoNumbersBigInteger(final ListNode l1, final ListNode l2) {
        final BigInteger ten = new BigInteger("10");
        final BigInteger zero = new BigInteger("0");

        BigInteger l1sum = zero;
        int currPow = 0;
        ListNode l1ref = l1;
        while(l1ref != null) {
            l1sum = l1sum.add(ten.pow(currPow++).multiply(BigInteger.valueOf(l1ref.val)));
            l1ref = l1ref.next;
        }

        BigInteger l2sum = zero;
        currPow = 0;
        ListNode l2ref = l2;
        while(l2ref != null) {
            l2sum = l2sum.add(ten.pow(currPow++).multiply(BigInteger.valueOf(l2ref.val)));
            l2ref = l2ref.next;
        }

        BigInteger sum = l1sum.add(l2sum);
        final ListNode root = new ListNode();
        ListNode next = root;
        while(!sum.equals(zero)) {
            next.val = sum.mod(ten).intValue();
            sum = sum.divide(ten);
            if(!sum.equals(zero)) {
                next.next = new ListNode();
                next = next.next;
            }
        }

        return root;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
