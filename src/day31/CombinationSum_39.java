package day31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a set of candidate numbers (candidates) (without duplicates) and a 
 * target number (target), find all unique combinations in candidates where 
 * the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
Each element of candidate is unique.
1 <= target <= 500
 * */

//思路1：像这种结果要求返回所有符合要求解的题十有八九都是要利用到递归，而且解题的思路都大同小异，相类似的题目有 Path Sum II，
//Subsets II，Permutations，Permutations II，Combinations 等等，如果仔细研究这些题目发现都是一个套路，都是需要另
//写一个递归函数，这里我们新加入三个变量，start 记录当前的递归到的下标，out 为一个解，res 保存所有已经得到的解，每次调用
//新的递归函数时，此时的 target 要减去当前数组的的数

//思路2:也可以用迭代的解法来做，建立一个三维数组 dp，这里 dp[i] 表示目标数为 i+1 的所有解法集合。这里的i就从1遍历到 
//target 即可，对于每个i，都新建一个二维数组 cur，然后遍历 candidates 数组，如果遍历到的数字大于i，说明当前及之后的数字
//都无法组成i，直接 break 掉。否则如果相等，那么把当前数字自己组成一个数组，并且加到 cur 中。否则就遍历 
//dp[i - candidates[j] - 1] 中的所有数组，如果当前数字大于数组的首元素，则跳过，因为结果要求是要有序的。否则就将当前
//数字加入数组的开头，并且将数组放入 cur 之中即可
public class CombinationSum_39 {
	
	//解法2
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<List<Integer>>> dp = new ArrayList<List<List<Integer>>>();//dp[i]表示目标值i+1对应的符合条件的结果集
		
		Arrays.sort(candidates);
		
        for (int i = 1; i <= target; ++i) {
        	//从1开始到目标值，找出从candidates中元素相加得到每个数字的组合
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            for (int j = 0; j < candidates.length; ++j) {
                if (candidates[j] > i) break;//如果当前目标值小于当前candidates中的元素，直接跳出当前循环
                if (candidates[j] == i) {
                	//如果当前candidates中的元素正好等于当前目标值，则得到了一个符合的集合，加入结果集
                	List<Integer> temp1 = new ArrayList<Integer>();
                	temp1.add(candidates[i]);
                	cur.add(temp1); 
                	break;
                	}
                //如果当前元素小于当前目标值,则取出当前元素和目标值的差值对应的结果集合
                //比如目标值7，当前元素3，则取出能组成7 - 3 = 4的所有结果集合,给每个集合都加上当前元素，排序并去重
                for (List<Integer> a : dp.get(i - candidates[j] - 1)) {
                    List<Integer> temp2 = new ArrayList<Integer>(a);
                    temp2.add(candidates[j]);
                    Collections.sort(temp2);
                    if(!cur.contains(temp2))cur.add(temp2);
                }
            }
            dp.add(cur);
        }
        return dp.get(target - 1);
	}
	
	//解法1
//public List<List<Integer>> combinationSum(int[] candidates, int target) {
//	List<List<Integer>> res = new ArrayList<>();
//	List<Integer> output = new ArrayList<Integer>();
//	helper(candidates, 0, target, output, res);
//	return res;
//    }
//
//public void helper(int[] nums, int start,int target, List<Integer> output,List<List<Integer>> res) {
//	if(target < 0)return;
//	if(target == 0) {
//		res.add(new ArrayList<Integer>(output));
//		return;
//	}
//	
//	for (int i = start; i < nums.length; i++) {
//		output.add(nums[i]);
//		helper(nums, i, target - nums[i], output, res);
//		output.remove(output.size() - 1);
//	}
//	}
	
}
