package day31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an integer array with all positive numbers and no duplicates, find the
 *  number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
 * */

//思路1：使用递归+记忆数组的形式，不过这里的记忆数组用的是一个 HashMap。在递归函数中，首先判断若 target 小于0，
//直接返回0，若 target 等于0，则返回1。若当前 target 已经在 memo 中存在了，直接返回 memo 中的值。然后遍历 nums 
//中的所有数字，对每个数字都调用递归，不过此时的 target 要换成 target-nums[i]，然后将返回值累加到结果 res 中即可

//思路2:dp.这道题的真正解法应该是用 DP 来做，解题思想有点像之前爬梯子的那道题 Climbing Stairs，这里需要一个一维数组 dp，
//其中 dp[i] 表示目标数为i的解的个数，然后从1遍历到 target，对于每一个数i，遍历 nums 数组，如果 i>=x, 
//dp[i] += dp[i - x]。这个也很好理解，比如说对于 [1,2,3] 4，这个例子，当计算 dp[3] 的时候，3可以拆分为 1+x，
//而x即为 dp[2]，3也可以拆分为 2+x，此时x为 dp[1]，3同样可以拆为 3+x，此时x为 dp[0]，把所有的情况加起来就是
//组成3的所有情况了
public class CombinationSumIV_377 {
	//解法1
//public int combinationSum4(int[] nums, int target) {
//       Map<Integer, Integer> map = new HashMap<Integer, Integer>();//用map保存每个临时target对应的值
//        return helper(map, nums, target);
//    }
//public int helper(Map<Integer, Integer> map,int[] nums,int target) {
//	if(target < 0)return 0;
//	if(target == 0) {
//		return 1;
//	}
//	if(map.containsKey(target))return map.get(target);
//	int res = 0;
//	for (int i = 0; i < nums.length; i++) {
//		res += helper(map, nums, target - nums[i]);
//	}
//	map.put(target, res);
//	return map.get(target);
//}

	
	//解法2
	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];//dp[i] 表示目标数为i的解的个数
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {//求从1到target的dp值
			for (int num : nums) {
				//按顺序取出当前数组里的元素
				if(i >= num) {
					//如果当前目标值大于等于当前元素值，说明当前元素值需要加上某一个数才能得到目标值
					dp[i] += dp[i - num];
				}
			}
			
		}
		return dp[target];
	}
}
