package day26;

import day26.BalancedBinaryTree_110.TreeNode;

/*
 * Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to 
any node in the tree along the parent-child connections. The path must contain at least one 
node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6(2+1+3)

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42(15+20+7)

 	4
   / \
  11 13
 / \
7   2

 * */

//思路：树的递归解法一般都是递归到叶节点，然后开始边处理边回溯到根节点。这里就假设此时已经递归到结点7了，其没有左右子节点，
//如果以结点7为根结点的子树最大路径和就是7。然后回溯到结点 11，如果以结点 11 为根结点的子树，最大路径和为 7+11+2=20。
//但是当回溯到结点4的时候，对于结点 11 来说，就不能同时取两条路径了，只能取左路径，或者是右路径，所以当根结点是4的时候，
//那么结点 11 只能取其左子结点7，因为7大于2。所以，对于每个结点来说，要知道经过其左子结点的 path 之和大还是经过右子节点的
//path 之和大。递归函数返回值就可以定义为以当前结点为根结点，到叶节点的最大路径之和，然后全局路径最大值放在参数中，
//用结果 res 来表示。
//在递归函数中，如果当前结点不存在，直接返回0。否则就分别对其左右子节点调用递归函数，
//由于路径和有可能为负数，这里当然不希望加上负的路径和，所以和0相比，取较大的那个，就是要么不加，
//加就要加正数。然后来更新全局最大值结果 res，就是以左子结点为终点的最大 path 之和加上以右子结点为终点的最大 
//path 之和，还要加上当前结点值，这样就组成了一个条完整的路径。而返回值是取 left 和 right 中的较大值加上当前结点值，
//因为返回值的定义是以当前结点为终点的 path 之和，所以只能取 left 和 right 中较大的那个值，而不是两个都要
public class BinaryTreeMaximumPathSum_124 {
	 int res;
	   public int maxPathSum(TreeNode root) {
	         res = Integer.MIN_VALUE;
	         helper(root);
	       return res;
	    }

	public int helper(TreeNode t) {//返回当前节点的最大路径和
		if(t== null)return 0;
		int left = Math.max(0, helper(t.left));//得到当前节点的左子数的最大路径和，如果它大于0，才考虑加上它
		int right = Math.max(0, helper(t.right));//得到当前节点的右子数的最大路径和
		res = Math.max(res, left + right + t.val);//计算当前节点的最大的全部路径和
		return Math.max(left, right) + t.val;//以当前节点为根节点，包含当前节点值的最大路径
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
