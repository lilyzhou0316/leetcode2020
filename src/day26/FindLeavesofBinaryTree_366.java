package day26;

import java.util.ArrayList;
import java.util.List;

import day26.BinaryTreeMaximumPathSum_124.TreeNode;

/*
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove
 *  all leaves, repeat until the tree is empty.

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2 
        
2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         
 * */

//思路：用辅助函数找到当前树的所有叶子节点，并加入到list中，然后从这个树中删除这些叶子节点生成新的树，
//重复之前的步骤，直到树为空
public class FindLeavesofBinaryTree_366 {
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null)return res;
		
		
		while(root != null) {
			List<TreeNode> temp = new ArrayList<>();
			helper(root, temp);//得到当前树的叶子节点
			List<Integer> t = new ArrayList<Integer>();
			
			for (TreeNode treeNode : temp) {
				t.add(treeNode.val);
				treeNode = null;//把当前所有叶子节点设为空，得到新的树
			}	
			res.add(t);//加入结果集	
		}
		return res;
	}
	
	public void helper(TreeNode t, List<TreeNode> temp) {
		if(t.left == null && t.right == null) {
			//找到叶子节点，加入结果集
			temp.add(t);	
		}
		if(t.left != null)helper(t.left, temp);
		if(t.right != null)helper(t.right, temp);
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
