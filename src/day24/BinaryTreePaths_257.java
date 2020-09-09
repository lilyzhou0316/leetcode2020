package day24;

import java.util.ArrayList;
import java.util.List;

import day24.InvertBinaryTree_226.TreeNode;

/*
 * Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 * */

//思路：递归找叶节点。 树的题目，十有八九都是递归，而递归的核心就是不停的DFS到叶结点，然后在回溯回去。在递归函数中，
//当我们遇到叶结点的时候，即没有左右子结点，那么此时一条完整的路径已经形成了，我们加上当前的叶结点后存入结果res中，
//然后回溯。
public class BinaryTreePaths_257 {
public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if(root == null)return res;
 
         helper(root, "",res);
         return res;
    }

public void helper(TreeNode n, String s, List<String> res) {
	if(n.left == null && n.right == null)
		res.add(s + n.val);//如果当前节点是叶节点时，则直接把str加上叶节点后的结果直接加入结果集
	if(n.left != null)
		helper(n.left, s + n.val + "->", res);//如果当前节点的左节点不为空,继续往左遍历,直到找到叶节点
	if(n.right != null)
		helper(n.right, s + n.val + "->", res);//如果当前节点的右节点不为空，继续往右遍历,直到找到叶节点
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