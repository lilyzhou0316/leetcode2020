package day40;

/*
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be
 *  made by splicing together the nodes of the first two lists. 

Example 1:

Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: l1 = [], l2 = []
Output: []
Example 3:

Input: l1 = [], l2 = [0]
Output: [0]
 

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
 * */
public class MergeTwoSortedLists_21 {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) { if(l1 == null)return l2;
    if(l2 == null)return l1;
    
	ListNode dummy = new ListNode(0);
	dummy.next = l1;
	ListNode next = l2;
	ListNode pre = dummy;//l1指向第一个大于当前l2节点的节点，pre指向链表1中l1的前一个节点
	
	while(l1 != null && l2 != null) {
		if( l1.val <= l2.val) {
			pre = l1;
			l1 = l1.next;
			continue;
		}
		//pre小于等于l2,l1大于l2
		next = l2.next;
		l2.next = l1;
		pre.next = l2;
		l2 = next;
        pre = pre.next;
	}
	
	if(l2 != null) {
		//则l1为null,直接把剩下的l2加到l1末尾
		pre.next = l2;
	}
	return dummy.next;
	}
}
