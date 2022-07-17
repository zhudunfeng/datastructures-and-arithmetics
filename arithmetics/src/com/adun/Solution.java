package com.adun;

import java.util.Stack;
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
public class Solution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next=node2;
        node2.next=node3;
        node3.next=null;
        ListNode listNode = reverseList(node1);

        while (listNode!=null){
            System.out.println(listNode);
            listNode=listNode.next;
        }

    }

    public static ListNode reverseList(ListNode head) {
        Stack<ListNode> stack=new Stack();
        ListNode curr=head;
        ListNode next=null;
        while(curr!=null){
            next=curr.next;
            curr.next=null;
            stack.push(curr);
            curr=next;
        }
        ListNode pop1 = stack.pop();
        pop1.next=null;
        ListNode reverHead=pop1;
        ListNode tmp=reverHead;
        while(!stack.isEmpty()){
            ListNode pop2 = stack.pop();
            pop2.next=null;
            tmp.next=pop2;
            tmp=tmp.next;
        }
        return reverHead;
    }
}
