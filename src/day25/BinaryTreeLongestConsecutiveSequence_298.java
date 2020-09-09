package day25;

import day25.BinaryTreeLevelOrderTraversal_102.TreeNode;

/*
 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree 
along the parent-child connections. The longest consecutive path need to be from parent 
to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.


Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 * */

//思路：consecutive sequence path是指值为连续递增的自然数路径，因此从根开始，如果左子节点等于 根 + 1，或者右子节点等于
//根 + 1，则移动并更新长度，否则则重新设置起点节点和长度
public class BinaryTreeLongestConsecutiveSequence_298 {
	
public int longestConsecutive(TreeNode root) {
	if(root == null)return 0 ;
	int max = 1;
	int cur = 1;
	helper(root, root.val, max, cur);
	return max;
}

public void helper(TreeNode t, int val, int max, int cur) {
	if(t== null)return;
	if(t.val == val + 1)cur++;
	else cur = 1;
	max = Math.max(max, cur);
	helper(t.left, t.val, max, cur);
	helper(t.right, t.val, max, cur);
	
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
