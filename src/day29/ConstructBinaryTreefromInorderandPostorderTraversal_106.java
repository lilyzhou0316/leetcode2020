package day29;

/*
 * Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 * */

//我的思路：基本与题105一样，只不过这里由于后序的顺序的最后一个肯定是根，所以原二叉树的根节点可以知道，
//题目中给了一个很关键的条件就是树中没有相同元素，有了这个条件我们就可以在中序遍历中也定位出根节点的位置，
//并以根节点的位置将中序遍历拆分为左右两个部分，分别对其递归调用原函数
public class ConstructBinaryTreefromInorderandPostorderTraversal_106 {
public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0 , inorder.length - 1, postorder, 0 ,postorder.length - 1);
    }

public TreeNode helper(int[] inorder, int ileft, int iright, int[] postorder, int pleft, int pright) {
	if(pleft > pright || ileft > iright)return null;
	int i = 0;//在inorder数组中找到当前postorder数组的最后一个节点
	for ( i = ileft; i <= iright; i++) {
		if(postorder[pright] == inorder[i])break;
	}
	//确定当前根节点
	TreeNode cur = new TreeNode(inorder[i]);
	//找当前根节点的左子树
	cur.left = helper(inorder, ileft, i - 1, postorder, pleft, pleft + (i - ileft - 1));
	//找当前根节点的右子树
	cur.right = helper(inorder, i + 1, iright, postorder, pleft + (i - ileft - 1) + 1, pright - 1);
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
