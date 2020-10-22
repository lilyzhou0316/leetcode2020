package day42;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you 
 * beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise 
return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain 
duplicates.
Would this affect the run-time complexity? How and why?
 * */
public class SearchinRotatedSortedArrayII_81 {
	public boolean search(int[] nums, int target) {
		//思路：类似题33，除了要多考虑一种情况：类似[0,0,0,3]反转成[0,0,3,0]这种mid和r的值一样的情况
		//则需要跳过重复元素
		int l = 0, r = nums.length - 1, mid = 0;
		
		while(l <= r) {
			if(nums[mid] == target)return true;
			
			if(nums[mid] == nums[r]) {
				r--;
				continue;
			}
			
			if(nums[mid] < nums[r]) {
				if(nums[mid] < target && target <= nums[r]) {
					l = mid + 1;
				}else {
					r = mid - 1;
				}
			}else {
				if(nums[l] <= target && target < nums[mid]) {
					r = mid - 1;
					
				}else {
					l = mid + 1;
				}
			}
			
		}
		return false;		
	}
}
