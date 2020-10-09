package day38;


/*
 * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 * */
public class ReverseLinkedList_206 {
	public ListNode reverseList(ListNode head) {
		
			 if(head == null || head.next == null )return head;//空链表或者只有一个元素的链表
				
			//解法1:iteratively
				// ListNode newHead = new ListNode();
				// ListNode next = head;
				
//			while (head != null) {
//				next = head.next;
//				head.next = newHead.next;
//				newHead.next = head;
//				head = next;	
//			}
//			return newHead.next;
			 
			 //解法2:递归
	       
	    
	    ListNode next = head.next;
	    head.next = null;//让每个node的next都指向空
	    ListNode newHead = reverseList(next);//让newHead指向最后一个node
	    next.next = head;//从最后一个node开始，让它的next指向之前一个node
	    return newHead;
	}
		
}


 class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }