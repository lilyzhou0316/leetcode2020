package day25;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals 
 * the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 * */

//思路：类似题257，112
public class PathSumII_113 {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		 List<List<Integer>> res = new ArrayList<List<Integer>>();
		
	        List<Integer> paths = new ArrayList<Integer>();//保存符合条件的路径
	        helper(root, sum, paths,res);
	       
	        return res;
	    }
	 public void helper(TreeNode t, int sum,List<Integer> paths,List<List<Integer>> res) {
        if(t == null)return;
		 paths.add(t.val);//加入当前节点到当前路径，并从目标值里减去当前节点值，
		 //如果最后一个叶子节点值等于前面所有值从sum里减去后sum剩下的值，则找到目标路径了
		if(t.left == null && t.right == null && sum == t.val) {
			
			res.add(new ArrayList(paths));
           
		}else{
           helper(t.left, sum - t.val, paths, res);
			helper(t.right, sum - t.val, paths, res);
       }
		paths.remove(paths.size() - 1);//如果当前叶子节点不是目标值，则删除当前叶子节点回到上一个节点
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
