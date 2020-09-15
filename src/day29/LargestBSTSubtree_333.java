package day29;



/*
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where 
 * largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3

Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
             
Follow up:
Can you figure out ways to solve it with O(n) time complexity?

Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each 
node of the tree, which will result in O(nlogn) time complexity.
 * */

//我的思路：用辅助函数检查以当前节点为跟节点的子树是否是bst，用另一个辅助函数计算当前树的节点总数， 然后从给的树的根节点开始检查，
//如果当前树是bst则直接计算节点数并返回，如果不是，则分别看其左右子树

//follow up:helper函数返回了一个一维数组，里面有三个数字，分别是以当前结点为根结点的数的最小值，最大值，以及最大的 BST 
//子树的结点个数。那么就可以在边验证 BST 的过程中边统计个数，首先判空，若空，则返回一个默认三元组，整型最大值，最小值，和0。
//那你可能有疑问，定义的不是说第一个值是最小值么？没错，后面再解释。若当前结点 node 存在，分别对其左右子结点调用递归函数，
//那么左子树和右子树的信息都保存到了 left 和 right 数组中，就算左右子结点不存在也没关系，由于第一句的判空，还是会得到一个
//默认的三元组。接下来就是根据左右子树的信息来更新结果 res 了，由于 BST 的定义，当前结点值肯定是大于左子树的最大值，
//小于右子树的最小值的。左子树的最大值保存在 left[1] 中，右子树的最小值保存在 right[0] 中，如果这两个条件满足了，
//说明左右子树都是 BST，那么返回的三元组的最小值就是当前结点值和左子树最小值中的较小者，最大值就是当前结点值和右子树最大值
//中的较大值，返回的 BST 结点个数就是左右子树的结点个数加上1，即算上了当前结点。好，现在解释下为空时返回的三元组为何顺序
//是整型最大值，整型最小值。如果当前是叶结点，其也算是 BST，那么肯定希望能进入 if 从句，从而使得三元组的第三项能加1，
//但是 if 的条件是当前结点值要大于左子树中的最大值，现在左子结点是空的，为了保证条件能通过，我们将空的左子树的最大值设置
//为整型最小值，这样一定能通过，同理，将空的右子树的最小值设置为整型最大值，这就是空结点的三元组的作用。好，继续看 else 
//中的内容，如果破坏了 BST 的规则，则返回的三元组的最小值就是整型最小值，最大值是整型最大值，BST 结点个数并不是0，
//因为其左右子树中有可能还有 BST，所以是左右子树中的 BST 结点个数中的较大值
public class LargestBSTSubtree_333 {
	public int largestBSTSubtree(TreeNode root) {
		
		//解法1
//		if(root == null)return 0;
//		if(isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE))return countTreeNode(root);
//		else {
//			return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
//		}
		
		//follow up
		//用一个数组保存以当前节点为根节点的取值最小值，最大值，和最大bst树的节点个数
		int[] res = helper(root);
		return res[2];
	}
	
	
	//解法1
//	public boolean isValid(TreeNode t, int min, int max) {
//		if(t == null )return true;
//		if(t.val <= min || t.val >= max)return false;
//		return isValid(t.left, min, t.val) && isValid(t.right, t.val, max);
//	}
//	
//	public int  countTreeNode(TreeNode t) {
//		if(t == null)return 0;
//		return countTreeNode(t.left) + countTreeNode(t.right) + 1;
//	}
	
	//follow up
	public int[] helper(TreeNode t) {
		//数组保存以当前节点为根节点的取值最小值，最大值，和最大bst树的节点个数
		if(t == null)return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE,0};//但当当前节点为叶子节点时，
		//叶子节点要大于整数最小值，小于整数最大值
		
		int[] left = helper(t.left);
		int[] right = helper(t.right);
		if(t.val > left[1] && t.val < right[0]) {
			//如果当前节点大于左子树的最大值，且小于右子树的最小值，则当前节点也满足bst条件
			return new int[] {Math.min(t.val, left[0]), Math.max(t.val, right[1]), 
					left[2] + right[2] + 1};
		}else {
			//当前节点不满足bst条件
			return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
		}
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
