package day29;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/*
 * Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, 
the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space 
for this problem.
 

Example 1:
见截图117

Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]

Explanation: Given the above binary tree (Figure A), your function should populate each next 
pointer to point to its next right node, just like in Figure B. The serialized output is in level 
order as connected by the next pointers, with '#' signifying the end of each level.
 

Constraints:

The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100

 * */

//思路：类似题116，解法1非递归，解法2递归，解法3双指针
public class PopulatingNextRightPointersinEachNodeII_117 {
public Node connect(Node root) {
	if(root == null)return null;
	//解法1
//    List<Node> queue = new ArrayList<>();
//    queue.add(root);
//    while(!queue.isEmpty()) {
//    	int len = queue.size();
//    	
//    	for (int i = len; i > 0; i--) {
//    		Node tempNode = queue.remove(0);
//    		if(i > 1)tempNode.next = queue.get(0);
//    		//else tempNode.next = null;本身就指向null
//    		
//    		if(tempNode.left != null) queue.add(tempNode.left);
//    		if(tempNode.right != null)queue.add(tempNode.right);
//		}
//    	
//    }
	
    //解法2
	//找到第一个有子节点的next节点,cur表示当前第一个出现的子节点
//		Node cur = root.next;
//    	while(cur != null) {
//            if(cur.left != null){
//                //如果当前next节点的左子节点不为空
//                cur = cur.left;
//                break;
//            }
//            
//            if(cur.right != null){
//                 //如果当前next节点的右子节点不为空
//                cur = cur.right;
//                break;
//            }
//            //当前next节点没有子节点
//    		cur = cur.next;
//    	}
//
//		//因为当前节点的右子节点是直接指向当前节点的next节点中第一个出现的子节点的，所以先看右子节点
//		if(root.right != null)root.right.next = cur;
//		if(root.left != null)root.left.next = root.right == null ? cur : root.right;
//		
//		//继续递归下一层
//		connect(root.right);
//		connect(root.left);
	
	 //解法3
	 if (root == null) return root;
       
       Node refRoot = root;//保存根节点
       Node dummy = new Node(0);//记录当前层的下一层的起始节点位置
       Node pre = dummy;//遍历当前层的下一层的所有节点
       
       while (root != null) {//如果当前层还有节点
           
           //查看当前节点的左右子节点是否存在
           //如果左子节点存在。pre（dummy）作为当前层的下一层的开头节点，并让pre移动指向左子节点
           if (root.left != null) {
               pre.next = root.left;
               pre = pre.next;
           }
           
           if (root.right != null) {
               pre.next = root.right;
               pre = pre.next;
           }
           
           root = root.next;
           
           if (root == null) {//如果当前层遍历完了
               pre = dummy;
               root = pre.next;//去往下一层
               dummy.next = null;
           }
       }
       
       return refRoot;
		
		
		//return root; 
    }
	// Definition for a Node.
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	};
}
