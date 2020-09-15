package day29;

import java.util.ArrayList;
import java.util.List;

import day29.LargestBSTSubtree_333.TreeNode;

/*
 * Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. It can have between 1 and 2h 
nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 * */

//我的思路1：以各种遍历顺序（前，中， 后，层级）遍历树，统计节点数

//思路2:这道题给的完全二叉树就有可能是完美二叉树，若是完美二叉树，节点个数很好求，为2的h次方减1，h为该完美二叉树的
//高度。若不是的话，只能老老实实的一个一个数结点了。思路是由 root 根结点往下，分别找最靠左边和最靠右边的路径长度，
//如果长度相等，则证明二叉树最后一层节点是满的，是满二叉树，直接返回节点个数，如果不相等，则节点个数为左子树的节点
//个数加上右子树的节点个数再加1(根节点)，其中左右子树节点个数的计算可以使用递归来计算

public class CountCompleteTreeNodes_222 {
public int countNodes(TreeNode root) {
	if(root == null)return 0;
        //解法1:递归
//	List<TreeNode> res = new ArrayList<>();
//	helper(root, res);
//	return res.size();
	
	//解法2:
	//return 1 + countNodes(root.left) + countNodes(root.right);
	
	//解法3
	int left = 1, right = 1;//记录最左路径和最右路径的高度
	TreeNode cur = root;
	while(cur.left != null) {
		left++;
		cur = cur.left;
	}
	cur = root;
	while(cur.right != null) {
		right++;
		cur = cur.right;
	}
	if(left == right) {
		//则当前树是完美二叉树
		return (int)(Math.pow(2, left) - 1);
	}else {
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
	
    }

//public void helper(TreeNode t, List<TreeNode> res) {
//	if(t.left != null)helper(t.left,res);
//	res.add(t);
//	if(t.right != null)helper(t.right, res);
//}



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
