package day28;

/*
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling 
 * (a left node that shares the same parent node) or empty, flip it upside down and turn 
 * it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Example:

Input: [1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

Output: return the root of the binary tree [4,5,2,#,#,3,1]（层级遍历）

   4
  / \
 5   2
    / \
   3   1  右变左，左变中，中变右
   
Clarification:

Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.

The serialization of a binary tree follows a level order traversal, where '#' signifies 
a path terminator where no node exists below.

Here's an example:

   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
 * */

//思路1：这道题让我们把一棵二叉树上下颠倒一下，而且限制了右节点要么为空要么一定会有对应的左节点。上下颠倒后原来
//二叉树的最左子节点变成了根节点，其对应的右节点变成了其左子节点，其父节点变成了其右子节点，相当于顺时针旋转了一下。
//先来看看递归的解法。对于一个根节点来说，目标是将其左子节点变为根节点，右子节点变为左子节点，原根节点变为右子节点，
//首先判断这个根节点是否存在，且其有没有左子节点，如果不满足这两个条件的话，直接返回即可，不需要翻转操作。
//那么不停的对左子节点调用递归函数，直到到达最左子节点开始翻转，翻转好最左子节点后，开始回到上一个左子节点继续翻转即可，
//直至翻转完整棵树

//思路2:下面我们来看迭代的方法，和递归方法相反的时，这个是从上往下开始翻转，直至翻转到最左子节点
public class BinaryTreeUpsideDown_156 {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		//解法1，递归
//		if(root == null || root.left == null)return root;
//		TreeNode left = root.left;
//		TreeNode right = root.right;
//		TreeNode result = upsideDownBinaryTree(left);//只要有左子节点则一直往左移直到到达最左端的子节点
//		//从最左子节点开始，让当前左子节点作为根节点
//		left.left = right;//它的左节点此时应该为当前的右子节点
//		left.right = root;//它的右子节点此时为当前的根节点
//		root.left = null;//断开原连接
//		root.right = null;
//		return result;
		
		//解法2，迭代，右变左，左变中，中变右
		TreeNode cur = root, pre = null, next = null, temp = null;
		//pre表示之前的根节点，next指向当前的左子节点，temp指向之前的右子节点
		while(cur != null) {
			next = cur.left;//保存当前左子节点，即下一个根节点
			cur.left = temp;//上一个右子节点变成当前左子节点
			temp = cur.right;//保存当前右子节点，即下一个左子节点
			cur.right = pre;//上一个根节点变成当前右子节点
			pre = cur;//保存当前根节点，即下一个右子节点
			cur = next;//上一个左子节点变成当前根节点
		}
		return pre;//返回当前根节点
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
