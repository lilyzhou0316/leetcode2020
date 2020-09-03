package day21;

import java.util.Arrays;

/*
 * Given an array nums of n integers and an integer target, find three integers in nums such that 
 * the sum is closest to target. Return the sum of the three integers. You may assume that each 
 * input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 

Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
 * */

//我的思路：先排序，再对每个元素进行二分法查找,注意跳过重复元素
public class ThreeSumClosest_16 {
	 public int threeSumClosest(int[] nums, int target) {
		 int sum = 0;//记录当前最接近目标值的总和
		 int minDiff = Integer.MAX_VALUE;//记录当前与目标值的最小差值
		 
		 Arrays.sort(nums);
		 
		 for (int i = 0; i < nums.length - 2; i++) {//这里可以不用跳过重复i，因为这里是求最接近的，不是求正好相等的
			int l = i + 1;
			int r = nums.length - 1;
//			//去重，相同元素会得到同样的结果
//			 if(i>0 && nums[i]==nums[i-1])
//	                continue;
			while(l < r) {
				int temp = nums[i] + nums[l] + nums[r];
				if(temp == target) {
					return target;
				}else if(temp > target) {
					if(Math.abs(target - temp) < minDiff) {
						minDiff = Math.abs(target - temp);
						sum = temp;
					}
					while(l < r && nums[r] == nums[--r]);//跳过重复r
					
				}else {
					
					if(Math.abs(target - temp) < minDiff) {
						minDiff = Math.abs(target - temp);
						sum = temp;
					}
					while(l < r && nums[l] == nums[++l]);//跳过重复l
				}
			}
		}
		 return sum;
		 
	 }
}
