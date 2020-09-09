package day25;

import java.util.ArrayList;
import java.util.List;

import day25.PathSumII_113.TreeNode;

/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent
 *  a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.


Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
 * */

//思路：类似题112，113，257,找到每条路径，然后把数字相加即可（这里可以用字符串数字相加）
public class SumRoottoLeafNumbers_129 {
	 public int sumNumbers(TreeNode root) {
	       
	        return helper(root,0);
	    }
	 
	 
	 public int helper(TreeNode t, int sum) {
      if(t == null)return 0;//有可能某个节点的左或右节点为空,或者它本身为空
		 sum = sum * 10 + t.val;
		if(t.left == null && t.right == null) {
			return sum;
		}
		return helper(t.left, sum) + helper(t.right,sum);
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
