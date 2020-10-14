package day41;

/*
 * Given the head of a linked list, return the list after sorting it in ascending order.


Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []
 

Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. 
constant space)?

 * */

//思路1:建一个新链表，把原链表的节点按大小顺序插入新链表

//follow up:题目限定了时间必须为O(nlgn)，符合要求只有快速排序，归并排序，堆排序，而根据单链表的特点，最适于
//用归并排序。为啥呢？这是由于链表自身的特点决定的，由于不能通过坐标来直接访问元素，所以快排什么的可能不太容易
//实现,堆排序的话，如果让新建结点的话，还是可以考虑的，若只能交换结点，最好还是不要用。而归并排序（又称混合排序）
//因其可以利用递归来交换数字，天然适合链表这种结构。归并排序的核心是一个 merge() 函数，其主要是合并两个有序
//链表，这个在 LeetCode 中也有单独的题目 Merge Two Sorted Lists。由于两个链表是要有序的才能比较容易
//merge，那么对于一个无序的链表，如何才能拆分成有序的两个链表呢？我们从简单来想，什么时候两个链表一定都是有序的？
//就是当两个链表各只有一个结点的时候，一定是有序的。而归并排序的核心其实是分治法 Divide and Conquer，
//就是将链表从中间断开，分成两部分，左右两边再分别调用排序的递归函数 sortList()，得到各自有序的链表后，
//再进行 merge()，这样整体就是有序的了。因为子链表的递归函数中还是会再次拆成两半，当拆到链表只有一个结点时，
//无法继续拆分了，而这正好满足了前面所说的“一个结点的时候一定是有序的”，这样就可以进行 merge 了。然后再回溯
//回去，每次得到的都是有序的链表，然后进行 merge，直到还原整个长度。这里将链表从中间断开的方法，采用的就是
//快慢指针，大家可能对快慢指针找链表中的环比较熟悉，其实找链表中的中点同样好使，因为快指针每次走两步，慢指针
//每次走一步，当快指针到达链表末尾时，慢指针正好走到中间位置
public class SortList_148 {
public ListNode sortList(ListNode head) {
//	if(head == null || head.next == null)return head;//0,1个节点
//    
//    ListNode dummy = new ListNode();
//    ListNode next = head;
//    ListNode pre = dummy;//指向新链表中最后一个比当前原链表节点小的节点
//    //ListNode cur = new ListNode();//指向新链表中第一个比当前节点大的节点
//    
//    while(head != null) {
//    	next = head.next;
//    	if(dummy.next == null) {
//    		
//    		//新链表中还没有节点,直接插入
//    		head.next = dummy.next;
//    		dummy.next = head;
//    		pre = head;
//    		head = next;
//    	}else {
//    		//新链表已经有节点了
//    		
//    		//如果当前pre的位置正好是目标位置
//    		if((pre.next != null && pre.next.val > head.val && pre.val <= head.val) || (pre.next == null && pre.val <= head.val)){
//    			
//    			head.next = pre.next;
//    			pre.next = head;
//    			pre = pre.next;
//    			head = next;
//    			
//    		}else {
//    			//如果当前pre位置不是目标位置，则从新链表的第一个节点开始找
//        		pre = dummy.next;
//        		while(pre.next != null && pre.val <= head.val && pre.next.val <= head.val) {
//        			pre = pre.next;
//        		}
//        		if(pre.val > head.val) {
//        			head.next = pre;
//        			dummy.next = head;
//        			pre = head;
//        			head = next;	
//        		}else {
//        			head.next = pre.next;
//        			pre.next = head;
//        			pre = pre.next;
//        			head = next;
//        		}
//    		}
//    		
//    	}
//    }
//    return dummy.next;
	
	//解法2:follow up
	if(head == null || head.next == null)return head;//0,1个节点
 
  ListNode pre = head, slow = head, fast = head;//快慢指针找中点，pre指向中点的前一个节点，以分开前后两部分链表
  
  while(fast != null && fast.next != null) {
	  pre = slow;
	  slow = slow.next;
	  fast = fast.next.next;
  }
  pre.next = null;//切分链表
  
  return merge(sortList(head),sortList(slow));//把每个链表切分到只剩一个节点，然后通过排序融合成一个有序子链表
  
    }
public ListNode merge(ListNode l1, ListNode l2) {
	ListNode dummy = new ListNode();
	ListNode cur = dummy;
	//给两个链表排序融合成一个链表
	while(l1 != null && l2 != null) {
		if(l1.val < l2.val) {
			cur.next = l1;
			l1 = l1.next;
		}else {
			cur.next = l2;
			l2 = l2.next;
		}
		cur = cur.next;
	}
	//出循环时，可能有一个链表为空
	if(l1 == null)cur.next = l2;
	if(l2 == null)cur.next = l1;
	return dummy.next;
}

}

class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }
