package day27;

import day27.ConvertSortedArraytoBinarySearchTree_108.TreeNode;

/*
 * Given the head of a singly linked list where elements are sorted in ascending
 *  order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in 
which the depth of the two subtrees of every node never differ by more than 1.

 图见截图109

Example 1:

Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown 
height balanced BST.


Example 2:

Input: head = []
Output: []


Example 3:

Input: head = [0]
Output: [0]


Example 4:

Input: head = [1,3]
Output: [3,1]
 

Constraints:

The number of nodes in head is in the range [0, 2 * 104].
-10^5 <= Node.val <= 10^5


**
 * Definition for singly-linked list.
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 * */

//我的思路：与题108基本一致，但是每次需要先计算listnode长度并找到中间数,超时

//思路1:链表的查找中间点可以通过快慢指针来操作，找到中点后，要以中点的值建立一个数的根节点，
//然后需要把原链表断开，分为前后两个链表，都不能包含原中节点，然后再分别对这两个链表递归调用原函数，
//分别连上左右子节点即可
public class ConvertSortedListtoBinarySearchTree_109 {
public TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        if(head.next == null)return new TreeNode(head.val);//链表只有一个数时
        
      //用快慢指针找中间数，
        //出循环时，慢指针指向当前链表中间数，pre指向中间数的前一个数,快指针控制边界
        ListNode slow = head, fast = head, pre = head;
        while(fast.next != null && fast.next.next != null) {
        	pre = slow;
        	slow = slow.next;
        	fast = fast.next.next;
        }
        fast = slow.next;//出循环时让快指针指向中间数的下一个数
       
       TreeNode curNode = new TreeNode(slow.val);//取得中间数
       
       slow.next = null;//断开中间数与右边链表的连接
       pre.next = null;//断开中间数与左边链表的连接
       curNode.right = sortedListToBST(fast);
       if(head != slow) {
    	   //只有当当前链表长度大于3时，才会有中间数，否则，三个指针都指向head,则没有左子链表
    	   
    	   curNode.left = sortedListToBST(head);
       } 
       return curNode;
       
       
    }

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
}
