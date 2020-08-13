package day09;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you 
 * beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, 
otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true


Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may 
contain duplicates.
Would this affect the run-time complexity? How and why?
 * */

//思路：与题33思路大致一样，只是要考虑出现重复数的问题，这里判断下right和mid是否是重复数，如果是的话，跳过
//该重复数（因为如果mid==目标数，会直接返回结果，所以可以跳过）

public class SearchinRotatedSortedArrayII_81 {
	 public boolean search(int[] nums, int target) {
	        int l = 0, r = nums.length - 1;
	        int mid;
	        while(l <= r) {
	        	mid = l + (r - l)/2;
	        	if (nums[mid] == target) return true;
	        	
	        	if(nums[mid] == nums[r]) {//跳过重复数
	        		r--;
	        		continue;
	        	}
	        	
	        	if (nums[mid] < nums[r]) {//右半边数组有序
					if (nums[mid] < target && nums[r] >= target) {
						//目标数在右半边有序子数组里
						l = mid + 1;
					}else {
						//目标数在左半边无序子数组里
						r = mid - 1;
					}
				}else {//左半边数组有序
					if (nums[mid] > target && nums[l] <= target) {
						//目标数在左半边有序子数组里
						r = mid - 1;
					}else {
						//目标数在右半边无序子数组里
						l = mid + 1;
					}
				}
	        }
	        //出循环说明没找到
	        return false;
	    }
}
