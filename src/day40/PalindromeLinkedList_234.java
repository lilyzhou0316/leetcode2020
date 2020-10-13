package day40;

import java.util.Stack;

/*
 * Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true


Follow up:
Could you do it in O(n) time and O(1) space?
 * */

//思路1：用stack放入每个节点（反序），然后和原链表一一比较，如果一样则是回文
//思路2:使用快慢指针找中点的原理是 fast 和 slow 两个指针，每次快指针走两步，慢指针走一步，等快指针走完时，
//慢指针的位置就是中点。然后从中点开始翻转后半部分链表，看是否与前半部分链表相等

//follow up:快慢指针找中点
public class PalindromeLinkedList_234 {
	public boolean isPalindrome(ListNode head) {
		//解法1
//		if(head == null || head.next == null)return true;
//		Stack<Integer> stack = new Stack<Integer>();
//		ListNode dummy = new ListNode(0);
//		dummy.next = head;
//		
//		while(head != null) {
//			//放入栈
//			stack.add(head.val);
//			head = head.next;
//		}
//		
//		head = dummy.next;
//		
//		//与原链表比较
//		while(head != null) {
//			int temp = stack.pop();
//			if(temp != head.val)return false;
//			head = head.next;
//		}
//		return true;
		
		//follow up:
		if(head == null || head.next == null)return true;//0或1个节点
		ListNode fast = head;
		ListNode slow = head;
		
		if(fast.next.next == null && fast.next.val != fast.val)return false;//2个节点
		if(fast.next.next == null && fast.next.val == fast.val)return true;//2个节点
		//3个及以上节点
		
		while(fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		//出循环时，slow指向中间节点
		//复制一个中间节点作为后半部分反转节点的起点
		ListNode dummy = new ListNode(0);
		ListNode mid = new ListNode(slow.val);
		dummy.next = mid;
		
		
		ListNode next = slow.next;
		slow.next = null;//分开前后两部分链表
		
		slow = next;
		while(slow != null) {//反转后半部分
			next = slow.next;
			dummy.next = slow;
			slow.next = mid;
			mid = dummy.next;
			slow = next;
		}
		
		mid = dummy.next;
		while(head != null && mid != null) {
			if(head.val != mid.val)return false;
			head = head.next;
			mid = mid.next;
		}
		
		return true;
	}
}
