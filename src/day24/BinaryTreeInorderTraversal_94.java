package day24;

import java.util.ArrayList;
import java.util.List;



/*
 * Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * */
public class BinaryTreeInorderTraversal_94 {
public List<Integer> inorderTraversal(TreeNode root) {
        //递归写法省略
	
	List<Integer> res = new ArrayList<Integer>();
	if(root == null)return res;
	
	List<TreeNode> list = new ArrayList<>();
	TreeNode p = root;
	
	while(!list.isEmpty() || p != null) {
		//中序：左，父，右
		if(p != null) {
			//从根节点开始，如果当前节点的左节点不为空，则加入list并持续往左移
			list.add(p);
			p = p.left;
		}else {
			//如果当前节点为空了，说明暂时到当前线路的左节点都加入了
			p = list.remove(list.size() - 1);
			//把当前最左端的值取出加入结果集，然后移动到它的右节点处
			res.add(p.val);
			p = p.right;
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
