package day26;

import javax.swing.tree.TreeNode;

/*
 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 左右子树层级一样多，或者最多相差一层

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
 * */

//思路1：要求每一个节点的左右子树的高度差不超过1，肯定需要一个求各个点深度的函数，然后对每个节点的两个子树来比较深度差
//时间复杂度为O(NlgN)

//思路2:上面那个方法正确但不是很高效，因为每一个点都会被上面的点计算深度时访问一次，我们可以进行优化。方法是如果我们发现子树不平衡，
//则不计算具体的深度，而是直接返回-1。那么优化后的方法为：对于每一个节点，我们通过checkDepth方法递归获得左右子树的深度，
//如果子树是平衡的，则返回真实的深度，若不平衡，直接返回-1，此方法时间复杂度O(N)，空间复杂度O(H)
public class BalancedBinaryTree_110 {
	
	//解法1
//	 public boolean isBalanced(TreeNode root) {
//	        if(root == null)return true;
//	       int leftH = helper(root.left);
//	       int rightH = helper(root.right);
//	       if(Math.abs(leftH - rightH) > 1)return false;//以根节点为基础，看它的左右子树的高度差
//	       return isBalanced(root.left) && isBalanced(root.right);//如果根节点符合要求，则分别往左移和右移，看下一个
//	        
//	    }
//	 public int helper(TreeNode t) {
//		 if(t == null)return 0;
//		return 1 + Math.max(helper(t.left), helper(t.right));
//	}
	 
	 //解法2
	 public boolean isBalanced(TreeNode root) {
		 if(checkDepth(root) == -1)return false;
		 else return true;
	 }
	 
	 public int checkDepth(TreeNode t) {
		if(t == null)return 0;
		int left = checkDepth(t.left);//得到当前节点的左子树高度
		if(left == -1)return -1;//-1代表当前子树的高度不平衡
		int right = checkDepth(t.right);
		if(right == -1)return -1;
		//当当前节点的左右子树本身都是平衡的时，看它们的差值是否超过了1
		if(Math.abs(left - right) > 1)return -1;//超过则返回-1
		else return 1 + Math.max(left, right);//没有则返回当前节点的整体高度
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
