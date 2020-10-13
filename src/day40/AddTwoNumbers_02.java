package day40;

/*
 * You are given two non-empty linked lists representing two non-negative integers. The
 *  digits are stored in reverse order, and each of their nodes contains a single digit. 
 *  Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself. 

Example 1:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 * */
public class AddTwoNumbers_02 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		//解法1,直接修改链表1为累加后的结果链表
//		ListNode dummy = new ListNode(0);
//		dummy.next = l1;
//		
//		int carry = 0;
//		int sum = 0;
//		int num = 0;
//		ListNode pre1 = new ListNode(0);
//		ListNode pre2 = new ListNode(0);
//		while(l1 != null && l2 != null) {
//			sum = l1.val + l2.val + carry;
//			num = sum % 10;
//			carry = sum / 10;
//			l1.val = num;
//			pre1 = l1;
//			l1 = l1.next;
//			pre2 = l2;
//			l2 = l2.next;
//		}
//		
//		if(l1 == null && l2 != null) {
//			pre1.next = l2;
//			while(carry != 0 && l2 != null) {
//				sum = carry + l2.val;
//				num = sum % 10;
//				carry = sum / 10;
//				l2.val = num;
//				pre2 = l2;
//				l2 = l2.next;
//			}
//			if(carry != 0) {
//				ListNode temp = new ListNode(carry);
//				pre2.next = temp;
//			}
//			return dummy.next;
//		}else if(l1 != null && l2 == null) {
//			while(carry != 0 && l1 != null) {
//				sum = carry + l1.val;
//				num = sum % 10;
//				carry = sum / 10;
//				l1.val = num;
//				pre1 = l1;
//				l1 = l1.next;
//			}
//			if(carry != 0) {
//				ListNode temp = new ListNode(carry);
//				pre1.next = temp;
//			}
//			return dummy.next;
//		}else {
//			if(carry != 0) {
//				ListNode temp = new ListNode(carry);
//				pre1.next = temp;
//			}
//			return dummy.next;
		
		//解法2，新建一个链表保存结果
      ListNode a = l1;
      ListNode b = l2;
      ListNode result = new ListNode(0);
      ListNode resultPoint = result;
     
      int carry = 0;
      while(a != null || b != null){
          int x = (a != null)? a.val : 0;//a不为null时取出当前值，否则值为0
          int y = (b != null)? b.val : 0;
          int sum = x+y+carry;
          carry = sum/10;
          resultPoint.next = new ListNode(sum%10);
          resultPoint = resultPoint.next;//pointer后移一位
          if(a!=null) a = a.next;//这里必须要检查，因为可能一开始a,b中有一个是null
           if(b!=null) b = b.next;
      }
      if(carry != 0){
          resultPoint.next = new ListNode(carry);
      }
     
     return result.next; //从head的下一位开始返回 
		}
	}

class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	  }
