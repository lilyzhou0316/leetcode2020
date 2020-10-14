package day41;

/*
 * Sort a linked list using insertion sort.
 * Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted 
output list.
At each iteration, insertion sort removes one element from the input data, finds the
 location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 * */
public class InsertionSortList_147 {
public ListNode insertionSortList(ListNode head) {
        //插入排序：每次找到当前节点需要插入的位置，时间复杂度o(n^2)
	if(head == null || head.next == null)return head;
	
  ListNode dummy = new ListNode();
  ListNode next = head;
  ListNode pre = dummy;//指向新链表中最后一个比当前原链表节点小的节点
  
  while(head != null) {
  	next = head.next;
//  	if(dummy.next == null) {
//  		
//  		//新链表中还没有节点,直接插入
//  		head.next = dummy.next;
//  		dummy.next = head;
//  		pre = head;
//  		head = next;
//  	}else {
//  		//新链表已经有节点了
//  		
//  		//如果当前pre的位置正好是目标位置
//  		if((pre.next != null && pre.next.val > head.val && pre.val <= head.val) || (pre.next == null && pre.val <= head.val)){
//  			
//  			head.next = pre.next;
//  			pre.next = head;
//  			pre = pre.next;
//  			head = next;
//  			
//  		}else {
//  			//如果当前pre位置不是目标位置，则从新链表的第一个节点开始找
//      		pre = dummy.next;
//      		while(pre.next != null && pre.val <= head.val && pre.next.val <= head.val) {
//      			pre = pre.next;
//      		}
//      		
//      		if(pre.val > head.val) {
//      			//如果当前链表的第一个节点就大于需要插入的节点，则把该节点插入pre之前
//      			head.next = pre;
//      			dummy.next = head;
//      			pre = head;
//      			head = next;	
//      		}else {
//      			//否则插入目标位置（pre之后，pre.next之前）
//      			head.next = pre.next;
//      			pre.next = head;
//      			pre = pre.next;
//      			head = next;
//      		}
//  		}
  		
  	//}
  	
  	//简洁写法:每次都从开头开始找，不考虑其它情况
  	pre = dummy;
  	//如果新链表中有节点，进入循环，找到目标位置
  	while(pre.next != null && pre.next.val <= head.val) {
  		pre = pre.next;
  	}
  	//出循环时，找到目标位置，或者是根本没进入循环，当前节点都插入pre之后
  	head.next = pre.next;
  	pre.next = head;
  	head = next;
  }
  return dummy.next;
	
    }
}
