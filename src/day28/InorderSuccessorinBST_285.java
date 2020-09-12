package day28;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

 

Example 1:
	2
   /  \
  1    3

Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is 
of TreeNode type.


Example 2:

			5
		   /  \
		  3    6
		 /  \
		2    4
	   /
	  1

Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
 

Note:

If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.
 * */

//思路：中序遍历反转，即按右中左的顺序加入（非递归），找到目标节点，然后返回它的前一个节点即可; 
//或者看当前根节点的值与p值的大小，如果当前根节点值小于等于p，则目标值一定在右子树中
//继续递归遍历右子树，如果正好大于则取当前遍历到的节点，即为目标节点，返回该节点（注意，如果该节点为空则返回当前根节点）
public class InorderSuccessorinBST_285 {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//		if(root == null)return null;
//		//中序遍历反序：非递归
//		Stack<TreeNode> stack = new Stack<>();
//		List<TreeNode> res = new ArrayList<>();
//		TreeNode curNode = root;
//		TreeNode result = null;
//		while(!stack.isEmpty() || curNode != null) {
//			if(curNode != null) {
//				stack.add(curNode);
//				curNode = curNode.right;
//			}else {
//				curNode = stack.pop();
//				res.add(curNode);
//				if(curNode.val == p.val) {
//					result = res.get(res.size() - 2);
//				}
//				curNode = curNode.left;
//			}
//		}
//		return result;
		
		//解法2
		if(root == null)return null;
		if(root.val <= p.val) {
			//目标节点在右子树上,继续递归
			return inorderSuccessor(root.right, p);
		}else {
			//当前根节点大于p，则往当前节点的左子树上找看还有没有比当前节点小，但大于p的值，返回当前节点
			TreeNode temp = inorderSuccessor(root.left, p);
			return temp == null?root:temp;
		}
		
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
}
