package day38;

/*
 * Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes. Only nodes itself may be changed.

图见https://leetcode.com/problems/swap-nodes-in-pairs/description/
Example 1:

Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]
 

Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 * */
public class SwapNodesinPairs_24 {
	 public ListNode swapPairs(ListNode head) {
		 if(head == null || head.next == null)return head;//0或者1个node时
		 ListNode newHead = new ListNode();
		 newHead.next = head;//新链表的头节点
		 ListNode first = head;
		 ListNode second = new ListNode();
		 ListNode third = new ListNode();
		 head = newHead;
		while(first != null) {
			second = first.next;
			if(second != null)third = second.next;//当前还剩两个及以上节点时，还可以进行交换
			else break;//当前只剩下一个节点了，则不能交换了，跳出循环
			second.next = first;
			first.next = third;
			head.next = second;
			head = first;
			first = third;
		}
		 return newHead.next;
	 }
}

