package day28;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given a non-empty binary search tree and a target value, find the value in the BST that 
 * is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \ 
1   3

Output: 4
 * */


//我的思路：用中序遍历,找到第一个大于目标值的节点，比较当前根节点值与目标值的差值 和当前根节点的前一个节点值与目标值的差值
//哪个更小，返回更小的那个节点的值

//思路2:类似题285，实际我们可以利用二分搜索树的特点 (左<根<右) 来快速定位，由于根节点是中间值，在往下遍历时，根据目标值和根节点的值
//大小关系来比较，如果目标值小于节点值，则应该找更小的值，于是到左子树去找，反之去右子树找，用一个变量保存差值更小的那个节点
public class ClosestBinarySearchTreeValue_270 {
	public int closestValue(TreeNode root, double target) {
		
		//解法1:中序遍历
		
//		int result = 0;
//		Stack<TreeNode> stack = new Stack<>();
//		List<Integer> resIntegers = new ArrayList<Integer>();
//		
//		TreeNode curNode = root;
//		while(!stack.isEmpty() || curNode != null) {
//			if(curNode != null) {
//				stack.add(curNode);
//				curNode = curNode.left;
//			}else {
//				curNode = stack.pop();
//				resIntegers.add(curNode.val);
//				if(curNode.val >= target) {
//					break;
//				}
//				curNode = curNode.right;
//			}
//		}
//		if(resIntegers.size() == 1)return resIntegers.get(0);//目标数太小，树中最小的树即为所求
//		if(resIntegers.size() > 1) {
//			result = Math.abs(resIntegers.get(resIntegers.size() - 1) - target) > 
//			Math.abs(resIntegers.get(resIntegers.size() - 2) - target)? resIntegers.get(resIntegers.size() - 2):
//				resIntegers.get(resIntegers.size() - 1);
//		}
//		return result;
		
		//解法2:
		int res = root.val;
		while(root != null) {
			//首先更新最小差值
			//如果之前保存的节点对应的差值大于当前节点对应的差值，则更新节点
			if(Math.abs(res - target) > Math.abs(root.val - target))res = root.val;
			//接着往当前节点的左右子树去遍历，根据当前节点值和目标值的大小
			root = target < root.val ?root.left : root.right;
		}
		//遍历完返回结果
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
