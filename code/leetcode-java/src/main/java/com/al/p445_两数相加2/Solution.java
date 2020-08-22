package com.al.p445_两数相加2;

/**
 * @author duval
 * @date 2020-08-19 23:03
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Solution {
    /**
     * 递归求链表长度
     * @param head
     * @return
     */
    public int getListLength(ListNode head) {
        if (head == null) {
            return 0;
        }

        return getListLength(head.next) + 1;
    }

    /**
     * 调整链表，处理进位
     * @param head
     * @return
     */
    public int adjustList(ListNode head) {
        if (head == null) {
            return 0;
        }

        int increment = adjustList(head.next);
        int val = increment + head.val;
        head.val = val % 10;
        return val / 10;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        // 获取两条链的长度
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);
        // 区分长链和短链
        ListNode longHead = len1 > len2 ? l1 : l2;
        ListNode shortHead = len1 > len2 ? l2 : l1;

        ListNode root = null;
        ListNode head = null;

        // 将长链多出来的一截腾挪到新链上
        for (int index = 0; index < Math.abs(len1 - len2); index++) {
            ListNode newNode = new ListNode(longHead.val);
            if (head == null) {
                head = newNode;
                root = head;
            } else {
                head.next = newNode;
                head = head.next;
            }
            longHead = longHead.next;
        }

        // 将两条链剩余相同长度的一截求和并保存到新链上
        for (int index = 0; index < Math.min(len1, len2); index++) {
            ListNode newNode = new ListNode(longHead.val + shortHead.val);
            if (head == null) {
                head = newNode;
                root = head;
            } else {
                head.next = newNode;
                head = head.next;
            }
            shortHead = shortHead.next;
            longHead = longHead.next;
        }

        // 调整链表
        int increment = adjustList(root);
        // 处理最后的进位
        if (increment > 0) {
            ListNode newNode = new ListNode(increment);
            newNode.next = root;
            root = newNode;
        }

        return root;
    }

    public ListNode addTwoNumberByStack(ListNode l1, ListNode l2) {
        // 将两条链表压栈，并计数
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int count = 0;
        ListNode head = l1;
        while (head != null) {
            stack1.push(head.val);
            head = head.next;
            count++;
        }

        head = l2;
        while (head != null) {
            stack2.push(head.val);
            head = head.next;
            count++;
        }


        head = null;
        // 进位
        int increment = 0;

        while (count > 0) {
            // 弹出栈1
            int num1 = 0;
            if (!stack1.isEmpty()) {
                num1 = stack1.pop();
                count--;
            }
            // 弹出栈2
            int num2 = 0;
            if (!stack2.isEmpty()) {
                num2 = stack2.pop();
                count--;
            }
            // 计算新节点值以及进位
            int val = num1 + num2 + increment;
            int newVal = val % 10;
            increment = val / 10;

            // 构建新链表头
            ListNode newNode = new ListNode(newVal);
            newNode.next = head;
            head = newNode;
        }

        // 处理最后的进位
        if (increment > 0) {
            ListNode newNode = new ListNode(increment);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    @Test
    public void test() {
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(4);

        node5.next = node6;
        node6.next = node7;

        ListNode result = addTwoNumbers(node1, node5);
        Assert.assertEquals(result.val, 7);
        Assert.assertEquals(result.next.val, 8);
        Assert.assertEquals(result.next.next.val, 0);
        Assert.assertEquals(result.next.next.next.val, 7);


        ListNode node8 = new ListNode(5);
        ListNode node9 = new ListNode(5);
        Assert.assertEquals(addTwoNumbers(node8, node9).val, 1);


        result = addTwoNumberByStack(node1, node5);
        Assert.assertEquals(result.val, 7);
        Assert.assertEquals(result.next.val, 8);
        Assert.assertEquals(result.next.next.val, 0);
        Assert.assertEquals(result.next.next.next.val, 7);


        Assert.assertEquals(addTwoNumberByStack(node8, node9).val, 1);


    }
}