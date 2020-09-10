package day26;

import java.util.ArrayList;
import java.util.List;



/*
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 *  (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 * */

//我的思路：类似题107，102，多加一个变量表示当前加入结果集的顺序，1表示从左到右，-1表示从右到左
public class BinaryTreeZigzagLevelOrderTraversal_103 {
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	List<List<Integer>> res = new ArrayList<>();
	if(root == null)return res;
	
	List<TreeNode> queue = new ArrayList<>();
	queue.add(root);
	
	int flag = 1;//初始化为从左到右
	while(!queue.isEmpty()) {
		int len = queue.size();//当前层数的节点总数
		List<Integer> temp = new ArrayList<Integer>();//存放当前层数的所有节点
		
		for (int i = 0; i < len; i++) {
			TreeNode t = queue.remove(0);
			if(flag == 1) {//正常放入
				temp.add(t.val);
			}else {//倒序放入
				temp.add(0, t.val);
			}
			
			if(t.left != null)queue.add(t.left);
			if(t.right != null)queue.add(t.right);
		}
		
		res.add(temp);
		flag = -flag;//加入顺序每一层结束后发生变化
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
