package day27;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * Given a binary tree, imagine yourself standing on the right side of it, return the 
 * values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 * */

//思路：从根节点开始，如果当前节点的右子树高度比左子树高度大或相等，则加入当前节点，节点右移；如果右子树高度小于左子树高度
//则

//思路：从第一层（根节点）开始，每一层都取队列里的最后一个节点（即最右节点）即可
public class BinaryTreeRightSideView_199 {
public List<Integer> rightSideView(TreeNode root) {
	List<Integer> res = new ArrayList<>();
	if(root == null)return res;
	
	List<TreeNode> queue = new ArrayList<>();
	queue.add(root);
	
	while(!queue.isEmpty()) {
		int len = queue.size();//当前层数的节点个数
		
		for (int i = 0; i < len; i++) {
			TreeNode temp = queue.remove(0);//按加入顺序取出当前层的所有节点
			if(i == len - 1)res.add(temp.val);//如果取出当前节点后队列为空，则说明当前节点为当前层的最后一个节点
			if(temp.left != null)queue.add(temp.left);
			if(temp.right != null)queue.add(temp.right);
		}	
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
