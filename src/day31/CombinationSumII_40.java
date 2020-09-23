package day31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a collection of candidate numbers (candidates) and a target number
 *  (target), find all unique combinations in candidates where the candidate 
 *  numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 * */

//思路：比题39多了一步去重
public class CombinationSumII_40 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> output = new ArrayList<Integer>();
	      Arrays.sort(candidates); //给数组排序，以便于跳过重复元素
		helper(res,output,0, target,candidates);
		return res;
	    }

	public void helper(List<List<Integer>> res,List<Integer> output,int start, int target, int[] candidates) {
		if(target < 0)return;
		if(target == 0) {
	        res.add(new ArrayList<Integer>(output));
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
	        if (i > start && candidates[i] == candidates[i - 1]) continue;//跳过重复元素，因为它们得到的结果是一样的
			output.add(candidates[i]);
			helper(res, output, i + 1, target - candidates[i], candidates);
			output.remove(output.size() - 1);
		}
	}
}
