package day40;

/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, 
 * return null.

There is a cycle in a linked list if there is some node in the list that can be reached 
again by continuously following the next pointer. Internally, pos is used to denote the 
index of the node that tail's next pointer is connected to. Note that pos is not passed 
as a parameter.

Notice that you should not modify the linked list.


Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up:

Can you solve it using O(1) (i.e. constant) memory?

 * */

//思路：这里还是要设快慢指针，不过这次要记录两个指针相遇的位置，当两个指针相遇了后，让其中一个指针从
//链表头开始，一步一步遍历链表，此时再相遇的位置就是链表中环的起始位置
public class LinkedListCycleII_142 {
public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        
        while(fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        	if (slow == fast) break;
        }
        if(fast == null || fast.next == null)return null;//只要出现null就没有环
        
        //有环，让慢指针从头开始遍历链表，当快慢指针再次相遇，则找到环起点
        slow = head;
        while(slow != fast) {
        	slow = slow.next;
        	fast = fast.next;
        }
        return fast;
    }
}
