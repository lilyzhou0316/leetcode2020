package day29;

import javax.swing.text.AbstractDocument.LeafElement;

import day29.LargestBSTSubtree_333.TreeNode;

/*
 * Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 * */

//思路：由于先序的顺序的第一个肯定是根，所以原二叉树的根节点可以知道，题目中给了一个很关键的条件就是树中没有相同
//元素，有了这个条件就可以在中序遍历中也定位出根节点的位置，并以根节点的位置将中序遍历拆分为左右两个部分，
//分别对其递归调用原函数
public class ConstructBinaryTreefromPreorderandInorderTraversal_105 {
public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

public TreeNode helper(int[] preorder, int pleft, int pright, int[] inorder, int ileft, int iright) {
	if(pleft > pright || ileft > iright)return null;//两个数组只要有一个遍历完了就返回null
	
	//pleft,pright是当前preorder数组的取值范围
	//inorder同理
	int i = 0;//找当前preorder的元素作为根节点，它在inorder里的位置
	for (i = ileft; i <= iright; i++) {
		if(preorder[pleft] == inorder[i])break;
	}
	TreeNode cur = new TreeNode(inorder[i]);//确定当前根节点，及它的左右子树部分
	cur.left = helper(preorder, pleft + 1, pleft + (i - ileft), inorder, ileft, i - 1);
	cur.right = helper(preorder, pleft + (i - ileft) + 1, pright, inorder, i + 1, iright);
	return cur;
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
