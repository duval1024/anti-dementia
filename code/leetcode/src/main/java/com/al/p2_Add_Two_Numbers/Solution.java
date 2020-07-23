package com.al.p2_Add_Two_Numbers;

import org.junit.Test;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author duval
 * @date 2019-10-13 11:38
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class Solution {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;

        ListNode l1Head = l1;
        ListNode l2Head = l2;
        int inc = 0;

        while (l1Head != null || l2Head != null) {
            int sum = inc;
            if (l1Head != null) {
                sum += l1Head.val;
            }

            if (l2Head != null) {
                sum += l2Head.val;
            }
            int val;
            if (sum <= 9) {
                val = sum;
                inc = 0;
            } else {
                val = sum % 10;
                inc = 1;
            }
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                tail = head;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
            if (l1Head != null) {
                l1Head = l1Head.next;
            }
            if (l2Head != null) {
                l2Head = l2Head.next;
            }
        }

        if(inc > 0) {
            tail.next = new ListNode(inc);
        }

        return head;
    }


}
