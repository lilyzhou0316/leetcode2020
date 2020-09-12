package day28;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a non-empty binary search tree and a target value, find  k  values in the BST that are 
 * closest to the target.

Note:

Given target value is a floating point.
You may assume  k  is always valid, that is:  k ≤ total nodes.
You are guaranteed to have only one unique set of  k  values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and _k_ = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]


Follow up:
Assume that the BST is balanced, could you solve it in less than  O ( n ) runtime (where  
n  = total nodes)?
 * */

//我的思路：先用中序遍历取出所有值放入list，然后遍历当前list找到当前差值最小对应的节点

//还有一种解法是直接在中序遍历的过程中完成比较，当遍历到一个节点时，如果此时结果数组不到k个，直接将此节点值加入结果 
//res 中，如果该节点值和目标值的差值的绝对值小于结果 res 的首元素和目标值差值的绝对值，说明当前值更靠近目标值，
//则将首元素删除，末尾加上当前节点值，反之的话说明当前值比结果 res 中所有的值都更偏离目标值，由于中序遍历的特性，
//之后的值会更加的遍历，所以此时直接返回最终结果即可
public class ClosestBinarySearchTreeValueII_272 {
	public int[] closestKValues(TreeNode root, double target, int k) {
		//解法1
//		List<Integer> list = new ArrayList<Integer>();
//		inorder1(root, list);//中序取出所有值
//		
//		int[] res = new int[k];
//		
//		//遍历list找到第一个符合条件节点
//			int min = Integer.MAX_VALUE;
//			int curIndex = 0;//记录第一个找到的节点的索引
//			for ( int i = 0; i < list.size(); i++) {
//				if(Math.abs(list.get(i) - target) < min) {
//					min = (int)Math.abs(list.get(i) - target);
//                    curIndex = i;
//			} 				
//		}
//			
//			//找到第一个节点后，则list里它的左边的节点或者右边节点中的一个一定为下一个目标节点
//			int l = curIndex - 1, r = curIndex + 1;
//			for (int i = 0; i < res.length; i++) {
//				
//				//把找到的当前节点放入结果集
//				res[i] = list.get(curIndex);
//				if(l >= 0 && r <= list.size() - 1) {
//					//如果当前节点有左右节点（在list中）
//					//则比较谁的差值更小
//					if( Math.abs(list.get(l) - target) > Math.abs(list.get(r) - target)) {
//						curIndex = r;
//						r++;
//					}else {
//						curIndex = l;
//						l--;
//					}
//			}else if(l >= 0) {
//				//如果当前节点的左节点还有数字，而右节点为空时，直接加入左指针指向的节点
//				curIndex = l;
//				l--;
//			}else  if(r <= list.size() - 1) {
//				//同上
//				curIndex = r;
//				r++;
//			}
//	
//	}
//			return res;
		
		//解法2
		List<Integer> resIntegers = new ArrayList<Integer>();
		inorder2(root, target, k, resIntegers);
		int[] result = new int[k];
		for (int i = 0; i < result.length; i++) {
			result[i] = resIntegers.get(i);
		}
		return result;
	}
	public void inorder1(TreeNode t, List<Integer> list) {
		
		if(t.left != null)inorder1(t.left, list);
		list.add(t.val);
		if(t.right != null)inorder1(t.right, list);
	}
	
	public void inorder2(TreeNode t, double target, int k, List<Integer> res) {
		if(t.left != null)inorder2(t.left, target, k, res);
		if(res.size() < k) {
			//如果结果集还没有满（有k个节点）,则直接加入当前节点值
			res.add(t.val);
		}else if(Math.abs(t.val - target) < Math.abs(res.get(0) - target)) {
			//如果结果集已满，且当前节点与目标值的差值小于结果集第一个节点与目标值的差值，则删除第一个节点加入当前节点
			res.add(t.val);
			res.remove(0);
		}else {
			//如果结果集已满，且当前节点与目标值的差值不在小于结果集第一个节点与目标值的差值，则它与它后面的节点都不再满足条件
			//因为节点值会越来越大，所以直接返回
			return;
		}
			
		if(t.right != null)inorder2(t.right, target, k, res);
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
