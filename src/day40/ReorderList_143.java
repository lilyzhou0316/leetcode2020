package day40;

/*
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * */
//思路：类似题234回文，快慢指针找到中点，反转中点以后的所有节点（不包含中点），然后把反转后的那部分链表的节点按顺序
//一个个插入到前半部分链表中，如：1-2-3-4-5，反转3以后的节点，即4-5，变成5-4，然后把5插入1-2之间，4插入2-3之间

public class ReorderList_143 {
public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)return;//0,1,2个节点
        
        ListNode fast = head, slow = head;
        
        //找中点
        while(fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        }
        ListNode next2 = slow.next;
        slow.next = null;//分开前后链表
        slow = next2;
        
        //反转中点以后的节点
        ListNode dummy2 = new ListNode(0);
        ListNode head2 = dummy2.next;
        
        while(slow != null) {
        	next2 = slow.next;
        	slow.next = head2;
        	dummy2.next = slow;
        	head2 = slow;
        	slow = next2;
        }
//        
//        ListNode dummy1 = new ListNode(0);
//        dummy1.next = head;
        ListNode next1 = head;
        next2 = head2;
        //反转后的链表一一插入前半部分节点
        while(head2 != null) {
        	next2 = head2.next;
        	next1 = head.next;
        	head2.next = next1;
        	head.next = head2;
        	head2 = next2;
        	head = next1;	
        }
        
    }
}
