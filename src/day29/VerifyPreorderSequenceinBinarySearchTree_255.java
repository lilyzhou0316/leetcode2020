package day29;

import java.util.Stack;

/*
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of 
 * a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false

Example 2:

Input: [5,2,1,3,6]
Output: true

Follow up:
Could you do it using only constant space complexity?
 * */

//我的思路：根据bst特点，如果对bst进行前序遍历，则得到的数组特点是从第一个数开始，越往中间越小（即往左子树方向），
//一旦数开始变大，说明开始往当前节点的右子树上遍历了。设置一个当前最小值（即当前根节点），一旦当前节点值比当前最小值还小，则返回false，
//用栈存放左子树上的节点，遍历数组，一旦当前数大于栈顶元素说明开始往右子树上移动，此时需要更新当前最小值
//否则当前值直接入栈

//思路2:下面这种方法使用了分治法，跟之前那道验证二叉搜索树的题 Validate Binary Search Tree 的思路很类似，在递归函数中
//维护一个下界 lower 和上界 upper，那么当前遍历到的节点值必须在 (lower, upper) 区间之内，然后在给定的区间内搜第一个
//大于当前节点值的点，以此为分界，左右两部分分别调用递归函数，注意左半部分的 upper 更新为当前节点值 val，表明左子树的节点值
//都必须小于当前节点值，而右半部分的递归的 lower 更新为当前节点值 val，表明右子树的节点值都必须大于当前节点值，如果左右
//两部分的返回结果均为真，则整体返回真
public class VerifyPreorderSequenceinBinarySearchTree_255 {
	public boolean verifyPreorder(Integer[] preorder) {
		//解法1
//		if(preorder == null || preorder.length <= 1)return true;
//		Stack<Integer> stack = new Stack<Integer>();
//		int min = Integer.MIN_VALUE;
//		for (int i = 0; i < preorder.length; i++) {
//			if(preorder[i] < min)return false;//如果当前节点值小于当前最小值节点，则说明当前节点不满足正好是当前最小值节点的
//			//右子节点这个条件，直接返回false
//			while(!stack.isEmpty() && preorder[i] > stack.peek()) {
//				//如果当前节点大于当前最小值，即当前节点位于之前某个节点的右子树上时，更新最小节点值，使得当前节点
//				//正好是当前最小值节点的右子节点
//				min = stack.pop();
//			}
//			//如果栈为空，或者当前值小于栈顶元素（即当前节点是当前最小节点的左子树上的节点时），直接入栈
//			stack.push(preorder[i]);
//		}
//		return true;
		
//		//follow up1
//		if(preorder == null || preorder.length <= 1)return true;
//		//要使空间复杂度为常量，则不使用栈，而是将之前的左子树上的节点值存在给的数组上
//		int i = -1;//指针，指向当前最小值的索引位置
//		int min = Integer.MIN_VALUE;
//		for (Integer num : preorder) {
//			if(num < min)return false;
//			while(i >= 0 && num > preorder[i]) {
//				min = preorder[i];
//				i--;
//			}
//			preorder[++i] = num;
//		}
//		
//		return true;
		
		//follow up2
		//从数组的头尾开始
		return helper(preorder, 0, preorder.length, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
	}
	
	public boolean helper(Integer[] preorder, int start, int end, int min, int max) {
		if(start > end)return true;//已经遍历完所有节点了
		int cur = preorder[start];//取第一个元素为当前节点
		int i = start + 1;//从当前节点的下一个节点开始遍历数组
		if(cur <= min || cur >= max)return false;//如果当前节点超过当前取值范围，则返回false
		//否则遍历数组，找到当前节点的右子节点（即第一个比当前节点大的值）
		for (; i <= end; i++) {
			if(preorder[i] > cur)break;
		}
		//遍历剩下的节点
		//start + 1 到 i - 1是所有比cur节点小的节点，它们的最大取值不超过cur
		//i 到 end 是所有比cur节点大的节点，它们的最小值不小于cur
		return helper(preorder, start + 1, i - 1, min, cur) && helper(preorder, i, end, cur, max);
	}
}
