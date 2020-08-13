package day08;

/*
 * Given an integer array nums, find the contiguous subarray within an array 
 * (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * */

//思路1：用 DP 来做，而且要用两个 dp 数组，其中 f[i] 表示子数组 [0, i] 范围内并且一定包含 nums[i] 
//数字的最大子数组乘积，g[i] 表示子数组 [0, i] 范围内并且一定包含 nums[i] 数字的最小子数组乘积，
//初始化时 f[0] 和 g[0] 都初始化为 nums[0]，其余都初始化为0。那么从数组的第二个数字开始遍历，
//那么此时的最大值和最小值只会在这三个数字之间产生，即 f[i-1]*nums[i]，g[i-1]*nums[i]，和 nums[i]。
//所以用三者中的最大值来更新 f[i]，用最小值来更新 g[i]，然后用 f[i] 来更新结果 res 即可

//思路2:同样可以对1优化，减少空间复杂度，用两个变量保存最小子数组乘积和最大子数组乘积
public class MaximumProductSubarray_152 {
	 public int maxProduct(int[] nums) {
	       int n = nums.length;
	       int res = nums[0];
	       //解法1
//	       int[] maxDP = new int[n];//记录包含nums[i]的最大乘积
//	       int[] minDP = new int[n];//记录包含nums[i]的最小乘积（因为可能有负数）
//	       maxDP[0] = nums[0];
//	       minDP[0] = nums[0];
//	       
//	       
//	       for (int i = 1; i < n; i++) {
//			maxDP[i] =Math.max(Math.max(maxDP[i-1]*nums[i], minDP[i-1]*nums[i]),nums[i]);
//			minDP[i] =Math.min(Math.min(minDP[i-1]*nums[i], maxDP[i-1]*nums[i]),nums[i]);
//			res = Math.max(res, maxDP[i]);
//		}
	       
	       //解法2:
	       int maxProduct = nums[0];
	       int minProduct = nums[0];
	       for (int i = 1; i < n; i++) {
	    	   int temp1 = maxProduct, temp2 = minProduct;
	    	   //需要用一个局部变量暂时保存两个最值，否则在计算minP时使用的是新的maxP值
	    	   maxProduct = Math.max(Math.max(temp1*nums[i], temp2*nums[i]),nums[i]);
				minProduct = Math.min(Math.min(temp1*nums[i], temp2*nums[i]),nums[i]);
			res = Math.max(res, maxProduct);
		}
	       return res;
	 }
}
