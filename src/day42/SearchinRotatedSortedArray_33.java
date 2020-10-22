package day42;


/*
 * You are given an integer array nums sorted in ascending order, and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., 
[0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is guranteed to be rotated at some pivot.
-10^4 <= target <= 10^4

 * */
public class SearchinRotatedSortedArray_33 {
	public int search(int[] nums, int target) {
		//思路：每次找到的中点如果不等于目标值，则先看当前中点的哪半部分是有序的（一定有一半是有序的）
		//然后看目标值是否在有序的那半部分
		int l = 0, r = nums.length - 1, mid = 0;
		while(l < r) {
			mid = l + (r - l) / 2;
			if(nums[mid] == target)return mid;
			
			if(nums[l] < nums [mid]) {
				//1.当前中点的左半边有序
				if(nums[l] <= target && target < nums[mid]) {
					//且目标值在有序的这半边里时
					r = mid - 1;
				}else {
					//目标值不在有序的那边，则在无序的这半边
					l = mid + 1;
				}
			}else if(nums[mid] < nums[r]) {
				//2.右半边有序
				if(nums[mid] < target && target <= nums[r]) {
					//且目标值在有序的这半边里时
					l = mid + 1;
				}else {
					//目标值不在有序的那边，则在无序的这半边
					r = mid - 1;
				}
			}
		}
		return nums[l] == target ? l : -1;
	}
}
