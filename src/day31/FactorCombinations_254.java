package day31;
/*
 * Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations 
of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []

Example 2:

Input: 37
Output:[]

Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]


Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 * */

import java.util.ArrayList;
import java.util.List;

//思路：类似题39，只是从加法换成了乘法。从2开始遍历到n，如果当前的数n可以被i整除，说明i是n的一个因子，将其存入一位数组 out 中，然后递归调用 n/i，
//此时不从2开始遍历，而是从i遍历到 n/i，停止的条件是当n等于1时，如果此时 out 中有因子，将这个组合存入结果 res 中
public class FactorCombinations_254 {
public List<List<Integer>> getFactors(int n) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> output = new ArrayList<Integer>();
	helper(res, output, 2, n);//从2开始找目标数的因子数
	return res;
    }
public void helper(List<List<Integer>> res,List<Integer> output, int start, int target) {
	if(target == 1) {
		//不包含目标值本身的结果集才符合条件
		if(output.size() > 1)res.add(new ArrayList<Integer>(output));
		return;
	}
	
	for (int i = start; i <= target; i++) {//从2-目标值本身找它的因子数
		if(target % i != 0)continue;//当前i不是目标值的因子数
		output.add(i);//否则i是因子数，加入结果集
		helper(res, output, i, target / i);
		output.remove(output.size() - 1);
	}
}
}
