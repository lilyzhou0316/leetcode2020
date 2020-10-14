package day41;

/*
 * Given a linked list, reverse the nodes of a linked list k at a time and return its 
 * modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the 
number of nodes is not a multiple of k then left-out nodes, in the end, should remain as 
it is.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]


Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz

Follow up:

Could you solve the problem in O(1) extra memory space?
You may not alter the values in the list's nodes, only nodes itself may be changed.
 * */

//思路：这道题让我们以每k个为一组来翻转链表，实际上是把原链表分成若干小段，然后分别对其进行翻转，那么肯定总共需
//要两个函数，一个是用来分段的，一个是用来翻转的，以题目中给的例子来看，对于给定链表 1->2->3->4->5，一般在
//处理链表问题时，大多时候都会在开头再加一个 dummy node，因为翻转链表时头结点可能会变化，为了记录当前最新的
//头结点的位置而引入的 dummy node，加入 dummy node 后的链表变为 -1->1->2->3->4->5，如果k为3的话，
//目标是将 1,2,3 翻转一下，那么需要一些指针，pre 和 next 分别指向要翻转的链表的前后的位置，然后翻转后 
//pre 的位置更新到如下新的位置：
//-1->1->2->3->4->5
// |        |  |
//pre      cur next

//-1->3->2->1->4->5
//    |     |  |
//   cur   pre next
//以此类推，只要 cur 走过k个节点，那么 next 就是 cur->next，就可以调用翻转函数来进行局部翻转了，注意翻转
//之后新的 cur 和 pre 的位置都不同了，那么翻转之后，cur 应该更新为 pre->next，而如果不需要翻转的话，
//cur 更新为 cur->next

//我们也可以在一个函数中完成，首先遍历整个链表，统计出链表的长度，然后如果长度大于等于k，交换节点，当 k=2 时，
//每段只需要交换一次，当 k=3 时，每段需要交换2次，所以i从1开始循环，注意交换一段后更新 pre 指针，然后 num 
//自减k，直到 num<k 时循环结束
public class ReverseNodesinkGroup_25 {
public ListNode reverseKGroup(ListNode head, int k) {
       if(head == null || head.next == null || k == 1)return head; 
       
       //先遍历一遍链表求得总长度，从而计算出需要反转几次
//       int len = 1;
//       ListNode cur = head;
//       while(cur.next != null) {
//    	   cur = cur.next;
//    	   len++;
//       }
//       
//       ListNode dummy = new ListNode();
//       dummy.next = head;
//       ListNode pre = dummy;
//       cur = dummy;
//       while(len >= k) {
//    	   cur = pre.next;
//    	   for (int i = 1; i < k; i++) {//k-1为当前需要交换几次，比如k = 2，则需要交换一次
//			ListNode temp = cur.next;
//			cur.next = temp.next;
//			temp.next = pre.next;
//			pre.next = temp;
//		}
//    	   pre = cur;
//    	   len -= k;//把每次处理的节点个数减去
//       }
//       return dummy.next;
       
       //解法2:递归
       ListNode cur = head;//cur指向当前需要反转的子链表的下一个节点（即下一个子链表的起点）
       for (int i = 0; i <k; i++) {
		if(cur == null)return head;//当前长度不够需要反转的长度
		cur = cur.next;
	}
       
       ListNode newHead = reverse( head, cur);//反转当前子链表,经过反转后，head指向了cur所在位置
       head.next = reverseKGroup(cur, k);//递归反转下一个子链表
       return newHead;
       
    }

public ListNode reverse(ListNode head, ListNode tail) {
	ListNode pre = tail;
	while(head != tail) {
		ListNode temp = head.next;
		head.next = pre;
		pre = head;
		head = temp;
	}
	return pre;
}
}
