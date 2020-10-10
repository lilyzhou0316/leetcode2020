package day39;

/*
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 * */
public class RemoveDuplicatesfromSortedList_83 {
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null)return head;
		
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode pre = head, //指向重复节点的第一个出现的节点
				cur = head.next;//指向与pre不同的第一个节点
		
		while(cur != null) {
			//cur跳过重复节点指向第一个不重复节点,cur不能为null,因为null与数字无法比较相等
			while(cur != null && pre.val == cur.val)cur = cur.next;
			pre.next = cur;
			if(cur == null)return dummy.next;//cur为null说明遍历完了，直接返回结果
			//cur不为null,则继续找下一个
			pre = cur;
			cur = cur.next;	
		}
		return dummy.next;
		
		//recursion
	    //     if (head == null || head.next == null) return head;
	    // head.next = deleteDuplicates(head.next);
	    // return head.val == head.next.val ? head.next : head;
	}
}
