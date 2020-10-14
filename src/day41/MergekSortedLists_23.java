package day41;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
 * */

//思路：这里就需要用到分治法 Divide and Conquer Approach。简单来说就是不停的对半划分，比如k个链表先划分为
//合并两个 k/2 个链表的任务，再不停的往下划分，直到划分成只有一个或两个链表的任务，开始合并。举个例子来说比如
//合并6个链表，那么按照分治法，首先分别合并0和3，1和4，2和5。这样下一次只需合并3个链表，再合并1和3，最后和2
//合并就可以了。代码中的k是通过 (n+1)/2 计算的，这里为啥要加1呢，这是为了当n为奇数的时候，k能始终从后半段开始，
//比如当 n=5 时，那么此时 k=3，则0和3合并，1和4合并，最中间的2空出来。当n是偶数的时候，加1也不会有影响，比如
//当 n=4 时，此时 k=2，那么0和2合并，1和3合并，完美解决问题
public class MergekSortedLists_23 {
	public ListNode mergeKLists(ListNode[] lists) {
		//解法1
		//取出所有节点值，sort，然后把排序好的值变成一个链表
//		if(lists.length == 0 || lists == null)return null;
//		
//		List<Integer> listNodes = new ArrayList<Integer>();
//		for (ListNode list : lists) {
//			while(list != null) {
//				listNodes.add(list.val);
//				list = list.next;
//			}
//		}
//		
//		Collections.sort(listNodes);
//		
//		ListNode dummy = new ListNode();
//		ListNode cur = dummy;
//		for (int i = 0; i < listNodes.size(); i++) {
//			ListNode temp = new ListNode(listNodes.get(i));
//			cur.next = temp;
//			cur = cur.next;
//		}
//		return dummy.next;
		
		//解法2:
		if(lists.length == 0 || lists == null)return null;
		
		int len = lists.length;
	
		while(len > 1) {
			int k = (len + 1) / 2;//k指向当前list中点的后一个位置
			for (int i = 0; i < len / 2; i++) {//遍历前半部分链表，并让它和后半部分链表一一融合
				lists[i] = merge(lists[i], lists[i + k]);
			}
			len = k;//去掉后半部分已经融合的链表
		}
		return lists[0];
		
	}
	
	public ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode cur = dummy;
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
		
		if(l1 == null)cur.next = l2;
		if(l2 == null)cur.next = l1;
		return dummy.next;
		
	}
}
