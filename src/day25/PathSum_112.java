package day25;

import java.util.ArrayList;
import java.util.List;

import day25.BinaryTreeLevelOrderTraversal_102.TreeNode;

/*
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that 
 * adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * */

//思路：类似题257， 关键是怎么找到叶子节点（左右子节点为空的节点）并保存每个path的值
public class PathSum_112 {
public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)return false;
        List<Integer> sums = new ArrayList<Integer>();
        helper(root, 0,sums);
        
        for (int i = 0; i <sums.size(); i++) {
			if(sums.get(i) == sum)return true;
		}
        return false;
        
    }
public void helper(TreeNode t, int temp, List<Integer> sums) { 
	
	if(t.left == null && t.right == null) {
		//找到叶子节点
		temp += t.val;//计算总和
		sums.add(temp);//把所有可能总和结果保存起来	
		
	}
	if(t.left != null) {
		 helper(t.left,temp + t.val,sums);
	}
	if(t.right != null) {
		 helper(t.right, temp + t.val,sums);
	}
	
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
