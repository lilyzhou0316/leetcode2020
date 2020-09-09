package day24;

import java.util.ArrayList;
import java.util.List;

import day24.InvertBinaryTree_226.TreeNode;

/*
 * Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?

* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * */

//思路：前序遍历，分别用递归和非递归做
public class BinaryTreePreorderTraversal_144 {
	
	//解法1
//public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        if(root == null)return res;
//        helper(root, res);
//        return res;
//    }
//public void helper(TreeNode n, List<Integer> res) {
//	res.add(n.val);
//    if(n.left != null) helper(n.left, res);
//    if(n.right != null)helper(n.right, res);
//}
	
	
	public List<Integer> preorderTraversal(TreeNode root) {
		 List<Integer> res = new ArrayList<Integer>();
		 if(root == null)return res;
		 
		 List<TreeNode> list = new ArrayList<>();
		 //解法3
		 TreeNode p =root;
		 while(!list.isEmpty() || p != null) {
			 //前序：父，左，右
			 if(p != null) {
				 list.add(p);
				 res.add(p.val);
				 p = p.left;
			 }else {
				 p = list.remove(list.size() - 1);
				 p = p.right;
			 }
		 }
		 return res;
		 
		//解法2
//		 list.add(root);
//		 
//		 while(!list.isEmpty()) {
//			//前序遍历，先加自己，再加左右,但因为如果按照这个顺序加入list的话，取出时的顺序不对（右节点会比左节点的子树早取出）
//			 //所以先加入右节点再加入左节点，然后取的时候从list的最后一位从后往前取
//			 TreeNode temp = list.remove(list.size() - 1);
//			 
//			 res.add(temp.val);
//			 
//			 if(temp.right != null)list.add(temp.right);
//			 if(temp.left != null)list.add(temp.left); 
//		 }
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
