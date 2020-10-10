package day39;



/*
 * Given a non-negative integer represented as non-empty a singly linked list of digits, 
 * plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example :

Input: [1,2,3]
Output: [1,2,4]
 * */

//我的思路：反转链表，取出数字，加1后，得到每个位置上的数字，然后再反转

//思路2:也可以通过递归来实现，这样我们就不用翻转链表了，通过递归一层一层的调用，最先处理的是链尾元素，我们将其
//加1，然后看是否有进位，返回进位，然后回溯到表头，加完进位，如果发现又产生了新的进位，那么我们在最开头加上一个
//新节点即可
public class PlusOneLinkedList_369 {
	
	//解法1
//public ListNode plusOne(ListNode head) {
//        ListNode newHead = reverListNode(head);//把当前链表反转
//        ListNode dummy = new ListNode();
//        dummy.next = newHead;
//        
//        int carry = 0, sum = 0 , num = 0;
//        newHead.val += 1;//最高位加1
//        
//        while(newHead != null) {
//        	sum = newHead.val + carry;
//        	num = sum % 10;
//        	carry = sum / 10;
//        	
//        	newHead.val = num;
//        	
//        	newHead = newHead.next;
//        	
//        }
//        if(carry != 0) {//9,99,999这种数字
//        	ListNode temp = new ListNode(carry);
//        	temp.next = dummy.next;
//        	dummy.next = temp;
//        	return dummy.next;
//        }else {
//        	newHead = dummy.next;
//        	return reverListNode(newHead);
//        }
//        
//    }
//
//public ListNode reverListNode(ListNode head) {
//	 if(head == null || head.next == null )return head;
//	    ListNode next = head.next;
//	    head.next = null;//让当前的head节点都独立存在，脱离链表
//	    ListNode newHead = reverListNode(next);//让newHead指向最后一个node
//	    next.next = head;//从最后一个node开始，让它的next指向之前一个node
//	    return newHead;
//}
	
	//解法2
	public ListNode plusOne(ListNode head) {
		if (head == null) return head;
		int carry = helper(head);
        if (carry == 1) {
            ListNode res = new ListNode(1);
            res.next = head;
            return res;
        }
        return head;
    }
	
   public int helper(ListNode node) {//改变每个节点的值（计算后），并返回最后的carry
        if (node == null) return 1;
        int carry = helper(node.next);
        int sum = node.val + carry;
        node.val = sum % 10;
        return sum / 10;//返回carry
	}
}
