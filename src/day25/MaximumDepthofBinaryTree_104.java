package day25;

import day25.BinaryTreeLevelOrderTraversal_102.TreeNode;

/*
 * Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node 
down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
 * */

//思路：类似题111
public class MaximumDepthofBinaryTree_104 {
public int maxDepth(TreeNode root) {
        if(root == null)return 0;
        if(root.left == null)return 1 + maxDepth(root.right);
        if(root.right == null)return 1 + maxDepth(root.left);
        return 1 + Math.max(maxDepth(root.right), maxDepth(root.left));
    }
}
