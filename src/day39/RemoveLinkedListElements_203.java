package day39;

/*
 * Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 * */
//我的思路：两个指针，一个指向找到的目标节点，一个指向目标节点的前一个节点，然后删除目标节点

//思路2:可以用递归来解，写法很简洁，通过递归调用到链表末尾，然后回来，需要要删的元素，将链表next指针指向下一个元素
//即可
public class RemoveLinkedListElements_203 {
public ListNode removeElements(ListNode head, int val) {
        if(head == null)return head;
//        ListNode dummy = new ListNode();
//        dummy.next = head;
//        ListNode pre = dummy, cur = head;
//        
//        while(cur != null) {
//        	if(cur.val == val) {
//        		//找到目标值，删除节点
//        		pre.next = cur.next;
//        		cur = pre.next;
//        	}else {
//        		pre = cur;
//        		cur = cur.next;
//        	}
//        }
//        return dummy.next;
        
        //递归：
        head.next = removeElements(head.next, val);//递归到最后一个节点，然后从它开始往回一个一个节点添加
        return head.val == val ? head.next : head;//如果当前节点等于目标值，则舍弃当前节点返回它的下一个节点
    }
}
