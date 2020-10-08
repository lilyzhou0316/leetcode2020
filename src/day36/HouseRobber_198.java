package day36;

/*
 * You are a professional robber planning to rob houses along a street. Each house has a 
 * certain amount of money stashed, the only constraint stopping you from robbing each 
 * of them is that adjacent houses have security system connected and it will automatically 
 * contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 400
 * */

//思路：用dp一维数组保存到当前房子能抢到的最大钱数，
public class HouseRobber_198 {
public int rob(int[] nums) {
	  if(nums.length == 0 || nums == null)return 0;
      if(nums.length == 1)return nums[0];
      
      int[] dp = new int[nums.length];//dp[i]表示抢到索引为i的房子（不管当前房子抢不抢）的最大总值
      dp[0] = nums[0];//只有一栋房子时
      dp[1] = Math.max(nums[0], nums[1]);//有两栋房子时，取较大值
      
      for (int i = 2; i < nums.length; i++) {
			//超过两栋房子时，如果抢当前房子，则之前一栋被抢的应为i - 2，
      	//如果不抢当前房子，则之前被抢的应为i - 1
      	//取两种情况中的较大值
      	dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}
      
      return dp[nums.length - 1] ;
    }
}
