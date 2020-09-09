package day24;

import java.util.ArrayList;
import java.util.List;

import day24.SymmetricTree_101.TreeNode;

/*
 * Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t 
invert a binary tree on a whiteboard so f*** off.
 * */

//思路1：递归，类似题101，让每个节点的左右节点交换
//思路2:非递归
public class InvertBinaryTree_226 {
public TreeNode invertTree(TreeNode root) {
		//解法1
//        if(root == null)return root;
//        TreeNode tempNode = root.left;
//        root.left = invertTree(root.right);
//        root.right = invertTree(tempNode);
//        return root;
    
	
	//解法2
	if(root == null)return root;
	List<TreeNode> list = new ArrayList<>();
	list.add(root);
	while(!list.isEmpty()) {
		//队列不为空，取第一个元素
		TreeNode tempNode = list.remove(0);
		//把当前元素的左右节点交换
		TreeNode t1 = tempNode.left;
		tempNode.left = tempNode.right;
		tempNode.right = t1;
		if(tempNode.left != null)list.add(tempNode.left);
		if(tempNode.right != null)list.add(tempNode.right);
	}
	return root;
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