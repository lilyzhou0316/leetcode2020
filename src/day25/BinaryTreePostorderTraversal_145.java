package day25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.

Follow up: Recursive solution is trivial, could you do it iteratively?

 

Example 1:
1
 \
  2
 /
3   

Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:

Input: root = []
Output: []


Example 3:

Input: root = [1]
Output: [1]

Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 * */

//我的思路：递归方法省略，遍历用模版方法，注意打印顺序，左右父
public class BinaryTreePostorderTraversal_145 {
	 public List<Integer> postorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();//保存最终结果
	        
	        List<TreeNode> queue = new ArrayList<>();//遍历树
	        TreeNode p = root;
	        while (!queue.isEmpty() || p != null) {
	        	//顺序：左右父
				if (p != null) {//p不为空
					queue.add(0,p);//将p本身加入遍历队首
					res.add(0, p.val);//把p加入结果集队首
					p = p.right;//移动到右子节点上，再加入结果集的第一位（保证最右子节点一定是在第一位）
				} else {//p为空时(此时移动到最右端了)
					p = queue.remove(0);//取出最右子节点
					p = p.left;//如果该节点的左子节点不为空则加入队列和结果集队首，即排列顺序是左右父
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
