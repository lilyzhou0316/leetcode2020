package day39;

/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only 
 * distinct numbers from the original list.

Return the linked list sorted as well.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
 * */

//我的思路：用start,end分别记录重复节点的起点节点和终点节点的后一位，然后删除对应的重复节点

//思路2:也可以使用递归来做，首先判空，如果 head 为空，直接返回。然后判断，若 head 之后的结点存在，且值相等，
//那么先进行一个 while 循环，跳过后面所有值相等的结点，到最后一个值相等的点停下。比如对于例子2来说，head 
//停在第三个结点1处，然后对后面一个结点调用递归函数，即结点2，这样做的好处是，返回的值就完全把所有的结点1都
//删掉了。若 head 之后的结点值不同，那么还是对 head 之后的结点调用递归函数，将返回值连到 head 的后面，
//这样 head 结点还是保留下来了，因为值不同嘛，最后返回 head 即可
public class RemoveDuplicatesfromSortedListII_82 {
public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
//        ListNode dummy = new ListNode();
//        dummy.next = head;
//        ListNode pre = dummy,//记录重复节点的前一个节点
//        		start = head,//记录重复节点里的第一个相同节点
//        		end = head.next;//记录重复节点后的第一个不相同节点
//        
//        while(end != null) {
//        	if(start.val != end.val) {
//        		pre = pre.next;
//        		start = start.next;
//        		end = end.next;
//        		continue;
//        	}
//        	while(end != null && start.val == end.val)end = end.next;
//        	pre.next = end;
//        	start = end;
//        	if(end == null)return dummy.next;
//        	end = end.next;
//        }
//        return dummy.next;
        
        //recursion
        if (head.next != null && head.val == head.next.val) {
        	//当前head节点和它后一个节点值相同,则让head指向最后一个重复节点，跳过前面的重复节点
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);//删掉前面所有的重复节点
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
