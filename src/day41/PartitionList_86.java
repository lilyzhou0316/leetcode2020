package day41;

/*
 * Given a linked list and a value x, partition it such that all nodes less than x come 
 * before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 * */

//思路：找到所有比目标值小的数按顺序插入前面，后面的顺序保持不变
public class PartitionList_86 {
public ListNode partition(ListNode head, int x) {
	 if(head == null || head.next == null)return head;
     
     ListNode dummy = new ListNode();
     dummy.next = head;
     ListNode pre = dummy, cur = dummy, next = head;
      while(head != null && head.val < x ) {
     	cur = head;
     	head = head.next;
     }
     if(head == null)return dummy.next;//原链表里所有元素都小于目标值
     while(head != null) {
     	next = head.next;
     	if(head.val < x) {
     		pre.next = head.next;
     		head.next = cur.next;
     		cur.next = head;
     		cur = head;
     		head = next;
     	}else {
     		pre = head;
     		head = head.next;
     	}
     }
     return dummy.next;
    }
}
