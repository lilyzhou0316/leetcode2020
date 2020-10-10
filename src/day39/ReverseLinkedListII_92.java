package day39;

/*
 * Reverse a linked list from position m to n. Do it in one-pass（只通过一次遍历完成）.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
 * */

//我的思路：先遍历链表找到m位置节点，断开它与之前节点的连接，然后创建一个新链表从m开始进行（n - m + 1)次
//的反转（参考题206），记录新链表的尾部节点，最后把三部分节点连接起来

//思路2：遍历找到需要反转的子链表的第一个节点和它的前一个节点，从第一个节点的下一个节点开始，把它插入
//第一个节点的前一个节点的后面
public class ReverseLinkedListII_92 {
public ListNode reverseBetween(ListNode head, int m, int n) {
	if(head == null || head.next == null || m == n)return head;
    
//    ListNode newHead = new ListNode();
//    ListNode oldHead = new ListNode();
//    oldHead.next = head;
//    ListNode cur = head, //指向当前需要插入新链表的节点
//    		next = head, //指向cur的下一个节点
//    		pre = oldHead, //指向cur的前一个节点，注意要初始化为头节点的前一个节点，因为cur可能是头节点
//    		tail = new ListNode();//指向新链表的尾节点
//    int count1 = n - m + 1;//需要进行反转的节点的总个数
//    int count2 = 1;//用来找到m节点
//    while(count2 < m) {
//    	count2 ++;
//    	pre = cur;
//    	cur = cur.next;
//    }
//   //出循环时，cur指向m节点，断开之前节点的连接
//    pre.next = null;
//    //m节点为新链表的最后一个节点
//    tail = cur;
//    
//    //从m节点开始进行反转
//    while(count1 > 0 && cur != null) {
//    	next = cur.next;//next指向当前cur节点的下一个节点
//    	//将当前节点插入新链表
//    	cur.next = newHead.next;
//    	newHead.next = cur;
//    	//
//    	cur = next;
//    	count1--;
//    }
//    //最后把三部分链表连接起来
//    pre.next = newHead.next;
//    tail.next = next;
//    return oldHead.next; 
	
	//解法2:优化1
	 ListNode oldHead = new ListNode();
   oldHead.next = head;
   ListNode pre = oldHead ;
   for (int i = 0; i < m - 1; i++) {
	pre = pre.next;//pre移动到需要反转的第一个节点的前一个节点
}
   ListNode cur = pre.next;//当前需要反转的第一个节点
   ListNode temp = new ListNode();
   for (int i = m; i < n; i++) {
	temp = cur.next;
	cur.next = temp.next;
	temp.next = pre.next;
	pre.next = temp;
	
}
   return oldHead.next;
    }
}
 class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }