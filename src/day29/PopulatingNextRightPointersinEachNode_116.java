package day29;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given a perfect binary tree where all leaves are on the same level, and every parent
 *  has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate(填充) each next pointer to point to its next right node(next指向当前节点的右手边的节点，不一定是兄弟节点).
 If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space 
for this problem.
 

Example 1:
见截图题116

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]

Explanation: Given the above perfect binary tree (Figure A), your function should populate each 
next pointer to point to its next right node, just like in Figure B. The serialized output is 
in level order as connected by the next pointers, with '#' signifying the end of each level.
 

Constraints:

The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000

 * */

//思路1：层级遍历，递归的解法，由于是完全二叉树，所以若节点的左子结点存在的话，其右子节点必定存在，所以左子结点的 next 
//指针可以直接指向其右子节点，对于其右子节点的处理方法是，判断其父节点的 next 是否为空，若不为空，则指向其 next 
//指针指向的节点的左子结点，若为空则指向 NULL

//思路2:非递归的解法要稍微复杂一点，但也不算特别复杂，需要用到 queue 来辅助，由于是层序遍历，每层的节点都按顺序加入 
//queue 中，而每当从 queue 中取出一个元素时，将其 next 指针指向 queue 中下一个节点即可，对于每层的开头元素开始遍历之前，
//先统计一下该层的总个数，用个 for 循环，这样当 for 循环结束的时候，该层就已经被遍历完了

//思路3:对于follow up，要求用常量空间，则考虑指针，用两个指针 start 和 cur，其中 start 标记每一层的起始节点，cur 用来遍历该层的节点
public class PopulatingNextRightPointersinEachNode_116 {
	 public Node connect(Node root) {
		 if(root == null)return root;
		 //解法1，非递归
	       
//	        List<Node> queue = new ArrayList<>();
//	        queue.add(root);
//	        while(!queue.isEmpty()) {
//	        	int len = queue.size();
//	        	
//	        	for (int i = len; i > 0; i--) {
//	        		Node tempNode = queue.remove(0);
//	        		if(i > 1)tempNode.next = queue.get(0);
//	        		//else tempNode.next = null;本身就指向null
//	        		
//	        		if(tempNode.left != null) {
//	        			//因为是完美二叉树，所以一个节点如果有左子节点则一定有右子节点
//	        			queue.add(tempNode.left);
//	        			queue.add(tempNode.right);
//	        		}
//				}
//	        	
//	        }
		 
		 //解法2，递归
		 //如果根节点存在，则不用管根节点本身，因为它的next已经指向了null
//		 if(root.left != null)
//			 //如果当前节点的左子节点不为空，则它一定有右子节点，直接让左子节点next指向右子节点
//			 root.left.next = root.right;
//		 if(root.right != null)
//			 //如果当前节点的右子节点不为空，则看当前节点的next节点是否存在，如果存在则让右子节点的next指向当前节点的next节点的左子节点
//			 root.right.next = root.next == null ? null :  root.next.left;
//		 
//		 //继续递归下一层
//		 connect(root.left);
//		 connect(root.right);
		 
		 
		 //解法3:双指针
		 Node start = root, cur = null;
		 
		 while(start.left != null) { //start指向当前层的起始节点，如果当前层有下一层节点，则给下一层设置对应的next关系
			
			 cur = start;//cur遍历当前层的所有节点
			 while(cur != null) {
				 //设置当前节点的左右节点的next
				 if(cur.left != null)cur.left.next = cur.right;
				 if(cur.right != null)cur.right.next = cur.next == null ? null :  cur.next.left;
				 //设置完后，移动到当前层的下一个节点
				 cur = cur.next;
			 }
			 //当前层已经遍历完，移动到下一层
			 start = start.left;
		 }
	        return root;
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
