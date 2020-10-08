package day36;

import java.util.Arrays;

/*
 * You are a professional robber planning to rob houses along a street. Each house has 
 * a certain amount of money stashed. All houses at this place are arranged in a circle. 
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses 
 * have a security system connected, and it will automatically contact the police if two 
 * adjacent houses were broken into on the same night.

Given a list of non-negative integers nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), 
because they are adjacent houses.

Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.


Example 3:

Input: nums = [0]
Output: 0
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 * */

//思路：这道题是之前那道 House Robber 的拓展，现在房子排成了一个圆圈，则如果抢了第一家，就不能抢最后一家，
//因为首尾相连了，所以第一家和最后一家只能抢其中的一家，或者都不抢，那这里变通一下，如果把第一家和最后一家
//分别去掉，各算一遍能抢的最大值，然后比较两个值取其中较大的一个即为所求
public class HouseRobberII_213 {
	 public int rob(int[] nums) {
	        if(nums.length <= 1)return nums.length == 0? 0 : nums[0];
	        if(nums.length == 2)return Math.max(nums[0], nums[1]);
	        //长度大于3时才需要考虑首尾相邻的情况
	        //分别计算只含首，和只含尾的情况的最大值
	        //再取两者的较大值即为所求
	        int total1 = helper(nums, 0, nums.length - 2);
	        int total2 = helper(nums, 1, nums.length - 1);
	        return Math.max(total1, total2);
	    }

	public int helper(int[] nums, int start,int end) {
		int[] dp = new int[end - start + 1];
		dp[0] = nums[start];
		dp[1] = Math.max(nums[start], nums[start + 1]);
		for (int i = 2; i < end - start + 1; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i + start], dp[i - 1]);
		}
		return dp[end - start];
	}
}
