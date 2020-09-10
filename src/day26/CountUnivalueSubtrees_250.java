package day26;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.AbstractDocument.LeafElement;

import day26.BinaryTreeMaximumPathSum_124.TreeNode;

/*
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
 * */

//思路1：第一种解法的思路是前序遍历树的所有的节点，然后对每一个节点调用判断以当前节点为根的子树的所有节点是否相同，
//判断方法可以参考之前那题 Same Tree，用的是分治法的思想，分别对左右字数分别调用递归

//思路2:迭代的写法，迭代写法是在后序遍历 Binary Tree Postorder Traversal 的基础上修改而来，需要用 HashSet 
//来保存所有相同值子树的根节点，对于遍历到的节点，如果其左右子节点均不存在，那么此节点为叶节点，符合题意，加入结果 
//HashSet 中，如果左子节点不存在，那么右子节点必须已经在结果 HashSet 中，而且当前节点值需要和右子节点值相同才能将
//当前节点加入结果 HashSet 中，同样的，如果右子节点不存在，那么左子节点必须已经存在 HashSet 中，而且当前节点值
//要和左子节点值相同才能将当前节点加入结果 HashSet  中。最后，如果左右子节点均存在，那么必须都已经在 HashSet 中，
//并且左右子节点值都要和根节点值相同才能将当前节点加入结果 HashSet 中，其余部分跟后序遍历的迭代写法一样
public class CountUnivalueSubtrees_250 {
	//解法1
//	int res = 0; 
//public int countUnivalSubtrees(TreeNode root) {
//	if(root == null)return 0;
//	if(isUni(root, root.val))res++;//先查看根接单，如果根节点和它的左右子树值一样，则结果值加1
//	//再分别查看根节点的左右节点
//	countUnivalSubtrees(root.left);
//	countUnivalSubtrees(root.right);
//	return res;
//}
//public boolean isUni(TreeNode t, int val) {//以t为当前子树的根节点，如果满足它和它的左右子树都有一样的值，则true
//	if(t == null)return true;
//	return t.val == val && isUni(t.left, val) && isUni(t.right, val);
//}
	
	//解法2
	public int countUnivalSubtrees(TreeNode root) {
		if(root == null)return 0;
		Set<TreeNode> res = new HashSet<>();//放所有有相同值子树的根节点
		Stack<TreeNode> stack = new Stack<>();//遍历树的所有节点
		stack.add(root);
		TreeNode head = root;//指向前一个被加入res中的元素
		while(!stack.isEmpty()) {
			TreeNode t = stack.peek();//取出栈顶节点
			if((t.left == null && t.right == null) || t.left == head || t.right == head) {
				//如果t为叶子节点,则一定是唯一值子树，直接加入res
				if(t.left == null && t.right == null)res.add(t);
				else if(t.left == null && res.contains(t.right) && t.right.val == t.val)
					//如果当前节点左子树为空，右子树又是唯一值子树，这时看当前节点与右子树值是否相等，相等则满足条件
					//加入res
					res.add(t);
				else if(t.right == null && res.contains(t.left) && t.left.val == t.val)res.add(t);//同上
				else if(t.left != null && t.right != null && res.contains(t.left) && res.contains(t.right) 
						&& t.left.val == t.val && t.right.val == t.val)
					//如果当前节点的左右子树都不为空，且左右子树都是唯一值子树，则看它们三者的值是否相等，若相等则加入res
					res.add(t);
				
				stack.pop();
				head = t;
			}else {
				//如果t上述条件都不满足，则接着往下找，按照后序顺序
				if(t.right != null)stack.add(t.right);
				if(t.left != null)stack.add(t.left);
			}
		}
		return res.size();
	}
}
