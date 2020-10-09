package day38;

/*
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 *  Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and
 O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
 

Constraints:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
The length of the linked list is between [0, 10^4].
 * */
public class OddEvenLinkedList_328 {
public ListNode oddEvenList(ListNode head) {
        //if(head == null || head.next == null || head.next.next == null)return head;//0,1,2个节点时
        
//        //从有3个节点开始需要交换
//        ListNode preEven = head,//指向当前even节点的前一个节点
//        		even = head.next, //指向当前第一个出现的even节点
//        		odd = head.next.next,//指向当前even节点后第一个出现的odd节点
//        		preOdd = even;//指向当前odd节点的前一个节点
//        int count = 1;
        //解法1
//        while(odd != null) {
//        	//交换当前even和odd的位置
//        	preOdd.next = odd.next;
//        	odd.next = even;
//        	preEven.next = odd;
//        	count++;
//        	int temp = count;//记录当前需要根据even的位置往后找几位
//        	//preEven后移一位，指向当前even节点的前一个节点
//        	preEven = preEven.next;
//        	//从当前even位置开始找下一个odd节点
//        	odd = even;
//        	//if(odd.next == null || odd.next.next == null)break;//后面没有需要交换的节点了
//        	while(odd != null && temp > 0) {
//        		preOdd = odd;
//        		odd = odd.next;
//        		temp--;
//        	}
//        }
    
	//解法2:优化1
 if (head == null) return null;
        
        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even;  // Need to save of the head so that we can append it to the end of the odd list
        
        while (even != null && even.next != null) {
            odd.next = odd.next.next;//始终连奇数
            even.next = even.next.next;//始终连偶数
            
            odd = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;  // Link odd and even list together
        
        return head;
        
   
    }
}
