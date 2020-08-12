package day07;

/*
 * Given an integer array nums, find the contiguous subarray (containing at least one 
 * number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using 
the divide and conquer approach, which is more subtle.
 * */

//思路1:动态规划。用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，用ai代表 nums[i]
//于是可以写出这样的动态规划转移方程：
//f(i) = max{ f(i - 1) + ai, ai }
//不难给出一个时间复杂度O(n)、空间复杂度O(n) 的实现，即用一个 f 数组来保存 f(i) 的值，
//用一个循环求出所有 f(i)。考虑到 f(i) 只和 f(i - 1)相关，于是我们可以只用一个变量 pre 来维护对于当前
//f(i)的f(i−1) 的值是多少，从而让空间复杂度降低到O(1)，这有点类似「滚动数组」的思想。

//思路2:分治。解释见截图
public class MaximumSubarray_53 {
	 public int maxSubArray(int[] nums) {
		//解法1:动态规划
// 		 //初始化动态数组
// 		 int[] dp = new int[nums.length];
// 		 dp[0] = nums[0];
// 		 int max = dp[0];
// 		 for (int i = 1; i < dp.length; i++) {
// 			dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
             
//              max = Math.max(dp[i] , max);
// 		}
// 		 return max;
        
        //解法2:对1的优化,空间复杂度变为o(1)
        int pre = 0, max = nums[0];//pre保存前一个索引上的最大总和
    
		 for (int i = 0; i <nums.length; i++) {
			pre = Math.max(pre + nums[i], nums[i]);
             max = Math.max(max, pre);
		}
        return max;
	    }
}
