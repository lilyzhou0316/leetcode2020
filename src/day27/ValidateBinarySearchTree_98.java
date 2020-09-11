package day27;

import java.util.ArrayList;
import java.util.List;

import day27.BinaryTreeRightSideView_199.TreeNode;

/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 * */

//思路：可以利用它本身的性质来做，即左<根<右，也可以通过利用中序遍历结果为有序数列来做，下面我们先来看最简单的一种，
//就是利用其本身性质来做，初始化时带入系统最大值和最小值，在递归过程中换成它们自己的节点值，用long代替int就是为了
//包括int的边界条件
public class ValidateBinarySearchTree_98 {
	//解法1
//public boolean isValidBST(TreeNode root) {
//       long min = Long.MIN_VALUE;
//       long max = Long.MAX_VALUE;
//       return helper(root, min, max);
//    }
//
//public boolean helper(TreeNode t, long min, long max) {//min, max指当前节点的取值范围
//	 if(t == null )return true;
//	 if(t.val <= min || t.val >= max)
//		 //如果当前节点超过当前的取值范围
//		 return false;
//	 //否则当前节点符合条件，分别看当前节点的左右子节点
//	 return helper(t.left, min, t.val) && helper(t.right, t.val, max);
//}
	
	//解法2 中序遍历
	public boolean isValidBST(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		inorder(root, res);
		
		for (int i = 0; i < res.size() - 1; i++) {
			if(res.get(i) >= res.get(i + 1))return false;
		}
		return true;
	}
	
	public void inorder(TreeNode t, List<Integer> res) {
		if(t== null)return;
		if(t.left != null)inorder(t.left, res);
		
		res.add(t.val);
		
		if(t.right != null)inorder(t.right, res);
		
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
