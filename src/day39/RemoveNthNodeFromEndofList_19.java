package day39;

/*
 * Given the head of a linked list, remove the nth node from the end of the list and return 
 * its head.

Follow up: Could you do this in one pass?

图见https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

 * */

//我的思路：正数第m个等于总个数+1-倒数第n个，找到目标节点删除即可（需要两遍遍历）

//follow up:由于只允许一次遍历，所以不能用一次完整的遍历来统计链表中元素的个数，而是遍历到对应位置就应该移除了。
//那么就需要用两个指针来帮助解题，pre 和 cur 指针。首先 cur 指针先向前走N步，如果此时 cur 指向空，说明N为
//链表的长度(因为n只能小于等于长度)，则需要移除的为首元素，那么此时返回 head->next 即可，如果 cur 存在，
//再继续往下走，此时 pre 指针也跟着走，直到 cur 为最后一个元素时停止，此时 pre 指向要移除元素的前一个元素
//（因为pre和cur之间始终相差n），再修改指针跳过需要移除的元素即可，
public class RemoveNthNodeFromEndofList_19 {
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		 if(head == null || n == 0)return head;
//		 int len = 0;
//        ListNode dummy = new ListNode();
//        dummy.next = head;//需要一个空节点指向头节点，以防要删除的是第一个节点
//		 ListNode cur = head;
//		 while(cur != null) {
//			 len++;
//			 cur = cur.next;
//		 }
//		 
//		 int m = len + 1 - n;
//		 cur  = dummy;
//		 for (int i = 0; i < m - 1; i++) {
//			cur = cur.next;//移动到需要删除的节点的前一个节点
//		}
//		 ListNode temp = cur.next;
//		 
//            cur.next = temp.next;
//        temp.next = null;
//        
//		 
//		 return dummy.next;
		 
		 //follow up:
		 ListNode cur = head;
		 ListNode dummy = new ListNode();
		 dummy.next = head;
		 for (int i = 0; i < n; i++) {
			cur = cur.next;//cur先走n步
		}
		 if(cur == null) {
			 //即长度 = n，删除头元素
			 dummy.next = head.next;
			 
			 return dummy.next;
		 }else {
			 //n < len
			 //cur,pre一起走，直到cur走到最后一个节点
			 ListNode pre = head;
			 while(cur.next != null) {
				 cur = cur.next;
				 pre = pre.next;
			 }
			 //出循环时，pre指向需要删除节点的前一个节点
			 pre.next = pre.next.next;
			 return dummy.next;
		 }
		 
	 }
}
