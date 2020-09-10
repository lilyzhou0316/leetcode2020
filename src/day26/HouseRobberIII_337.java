package day26;

import java.util.HashMap;
import java.util.Map;

import day26.BinaryTreeMaximumPathSum_124.TreeNode;

/*
 * The thief has found himself a new place for his thievery again. There is only one 
 * entrance to this area, called the "root." Besides the root, each house has one and only 
 * one parent house. After a tour, the smart thief realized that "all houses in this place 
 * forms a binary tree". It will automatically contact the police if two directly-linked 
 * houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * */

//思路1：题目中给的例子看似好像是要每隔一个偷一次，但实际上不一定只隔一个，可以隔多个，只要和达到最大值，
//这种问题是很典型的递归问题，可以利用回溯法来做，因为当前的计算需要依赖之前的结果，那么对于某一个节点，
//如果其左子节点存在，通过递归调用函数，算出不包含左子节点返回的值，同理，如果右子节点存在，算出不包含右子节点返回的值，
//那么此节点的最大值可能有两种情况，一种是该节点值加上不包含左子节点和右子节点的返回值之和，另一种是
//左右子节点返回值之和不包含当前节点值，取两者的较大值返回即可，但是这种方法无法通过 OJ，超时了，所以必须优化这种方法，
//这种方法重复计算了很多地方，比如要完成一个节点的计算，就得一直找左右子节点计算，可以把已经算过的节点用 HashMap 保存起来，
//以后递归调用

//更多解法见：https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
public class HouseRobberIII_337 {
	//解法1
public int rob(TreeNode root) {
	
        Map<TreeNode, Integer> map = new HashMap<>();
        return helper(root, map);
    }
public int helper(TreeNode t, Map<TreeNode, Integer> map) {//计算当前节点的符合条件的最大和
	if(t == null)return 0;
	if(map.containsKey(t))return map.get(t);//已经计算过的节点的值直接返回
	
	int value = 0;
	if(t.left != null) {
		//如果当前节点的左子节点不为空，则计算不包含左子节点的返回值
		value += helper(t.left.left, map) + helper(t.left.right, map);
	}
	
	if(t.right != null) {
		//如果当前节点的右子节点不为空，则计算不包含右子节点的返回值(累加)
		value += helper(t.right.left, map) + helper(t.right.right, map);
	}
	//比较不包含左右子节点的返回值，和左右子节点相加的返回值哪个大，取较大值
	value = Math.max(value + t.val, helper(t.left, map) + helper(t.right, map));
	map.put(t, value);
	return value;
}
	
	
	}

