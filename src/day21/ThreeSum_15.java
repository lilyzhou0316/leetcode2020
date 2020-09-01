package day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 *  Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 * */

//思路，先排序，再二分法查找
public class ThreeSum_15 {
	public List<List<Integer>> threeSum(int[] nums) {
		 
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums.length < 3)return res;
		
		Arrays.sort(nums);
        if(nums[0]>0) return res;
		
		for (int i = 0; i < nums.length - 2 ; i++) {
			int l = i + 1, r = nums.length - 1;
			//去重，相同元素会得到同样的结果
			 if(i>0 && nums[i]==nums[i-1])
	                continue;
            
			while(l < r) {
                List<Integer> temp = new ArrayList<Integer>();
				if(nums[l] + nums[r] == -nums[i]) {
					
					temp.add(nums[i]);
					temp.add(nums[l]);
					temp.add(nums[r]);
					res.add(temp);
					 while(l<r && nums[l] == nums[++l]);//跳过重复的nums[left]
	                 while(l<r && nums[r]==nums[--r]);//跳过重复的nums[right]
				}else if(nums[l] + nums[r] > -nums[i]) {
					
					while(l<r && nums[r]==nums[--r]);//跳过重复的nums[right]
				}else {
					 while(l<r && nums[l] == nums[++l]) ;//跳过重复的nums[left]
				}
			}
		
		}
		return res;
			
	}
}
