package day26;

import java.util.ArrayList;
import java.util.List;



/*
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 *  (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 * */

//我的思路：类似题102(bfs)，从上到下遍历，然后翻转
public class BinaryTreeLevelOrderTraversalII_107 {
	 public List<List<Integer>> levelOrderBottom(TreeNode root) {
		 List<List<Integer>> res = new ArrayList<>();
		 if(root == null)return res;
		 
		 List<TreeNode> queue = new ArrayList<>();
		 queue.add(root);
		 
		 while(!queue.isEmpty()) {
			 int len = queue.size();//当前层的节点个数
			 List<Integer> t = new ArrayList<Integer>();//记录当前层的节点值
			 for (int i = 0; i < len; i++) {
				 TreeNode temp = queue.remove(0);//按加入先后顺序取，先入先出
				 t.add(temp.val);
				 if(temp.left != null)queue.add(temp.left);
				 if(temp.right != null)queue.add(temp.right);
			}
			res.add(0, t);//倒序插入
			 
		 }
		 return res;
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
