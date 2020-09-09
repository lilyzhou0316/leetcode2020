package day25;

import day25.PathSumII_113.TreeNode;

/*
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down 
to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
 * */

//思路：二叉树的经典问题之最小深度问题就是就最短路径的节点个数，还是用深度优先搜索 DFS 来完成，万能的递归啊。
//首先判空，若当前结点不存在，直接返回0。然后看若左子结点不存在，那么对右子结点调用递归函数，并加1返回。
//反之，若右子结点不存在，那么对左子结点调用递归函数，并加1返回。若左右子结点都存在，则分别对左右子结点调用递归函数，
//将二者中的较小值加1返回即可
public class MinimumDepthofBinaryTree_111 {
public int minDepth(TreeNode root) {
       if(root == null)return 0;
       if(root.left == null)return 1 + minDepth(root.right);
       if(root.right == null)return 1 + minDepth(root.left);
       return 1 + Math.min(minDepth(root.right), minDepth(root.left));
    }


}
