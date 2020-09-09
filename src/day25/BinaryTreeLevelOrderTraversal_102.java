package day25;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * Given a binary tree, return the level order traversal of its nodes' values.
 *  (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 * */

//思路：层级遍历二叉树，是典型的广度优先搜索 BFS 的应用（用queue），但是这里稍微复杂一点的是，要把各个层的数分开，
//存到一个二维向量里面，大体思路还是基本相同的，建立一个 queue，然后先把根节点放进去，这时候找根节点的
//左右两个子节点，这时候去掉根节点，此时 queue 里的元素就是下一层的所有节点，用一个 for 循环遍历它们，
//然后存到一个一维向量里，遍历完之后再把这个一维向量存到二维向量里，以此类推，可以完成层序遍历
public class BinaryTreeLevelOrderTraversal_102 {
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null)return res;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
        	List<Integer> temp = new ArrayList<Integer>();
        	int len = queue.size();//保存当前层的节点总数,即总共需要取多少次
        	//当队列不为空时，取出当前队列里的所有节点放入结果集，然后看每个节点是否还有子节点，有则加入队列
        	for (int i = 0; i < len; i++) {
        		TreeNode t = queue.remove(0);//每次都从队首取节点
        		temp.add(t.val);
        		if(t.left != null) {
            		queue.add(t.left);
            	}
            	if(t.right != null) {
            		queue.add(t.right);
            	}
			}
        	res.add(temp);
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
