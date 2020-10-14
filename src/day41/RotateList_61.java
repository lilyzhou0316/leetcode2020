package day41;

/*
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
 * */

//思路：根据例子可看出，用k%len得到的数即为原链表后面多少位节点需要插入到前面的
public class RotateList_61 {
public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0)return head;
        
        //求长度
        int len = 1;
        ListNode cur = head;
        while(cur.next != null) {
        	cur = cur.next;
        	len++;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;//指向cur的前一个节点
        cur = dummy.next;//指向需要被插入的子链表的起点
        
        k = k % len;
        if(k == 0)return head;//要插入的子链表长度正好等于原链表长度
        
        //正数位置 = len + 1 - k
        for (int i = 1; i < len + 1 - k; i++) {
			pre = cur;
			cur = cur.next;
		}
        ListNode tail = cur;//指向需要被插入的子链表的终点
        while(tail.next != null)tail = tail.next;//找到终点
        
        pre.next = null;
        tail.next = head;
        dummy.next = cur;
        return dummy.next;
        
    }
}
