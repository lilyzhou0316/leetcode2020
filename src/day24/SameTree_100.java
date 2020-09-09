package day24;


/*
 * Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes 
have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true

Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false


Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false





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
 *
 * */

//我的思路：用前序/中序/后序中任意一个顺序同时遍历两个树，看当前节点上的值是否相等,这里用前序
public class SameTree_100 {
public boolean isSameTree(TreeNode p, TreeNode q) {
	 if(p == null && q == null)return true;//当前两节点都为空
     if((p == null && q != null) || (p != null && q == null) || (p.val != q.val))return false;//当前两节点一个为空时，或者两节点值不相等时
    
     //当前两节点都不为空且值相等，则遍历其左子树,右子树，当左子树和右子树都相等时才是全等
     
     return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
