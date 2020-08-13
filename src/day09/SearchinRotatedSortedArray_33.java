package day09;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you 
 * beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, 
otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4


Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 * */

//我的思路：因为原数组是有序且升序的，所以旋转后的数组一定可以分成两个小数组且都是有序且升序的，在这两个
//子数组里都用二分法查找寻找目标数即可，但是时间复杂度不太符合要求

//思路：二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，列出例子里的数组的
//所有旋转后的情况，可以得出规律，如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，
//则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，
//这样就可以确定保留哪半边了



public class SearchinRotatedSortedArray_33 {
	public int search(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		int mid;
		while(l <= r) {
			mid = l + (r - l)/2;
			if(nums[mid] == target) return mid;
			if(nums[mid] < nums[r]) {
				//1.右半边数组有序
				if (nums[r] >= target && nums[mid] < target) {
					//说明目标值在当前半边数组（有序）里
					l = mid + 1;
				}else {
					//目标值在另一半数组（无序）里
					r = mid - 1;
				}
			}else {
				//2.左半边数组有序
				if (nums[l] <= target && nums[mid] > target) {
					//说明目标值在当前半边数组（有序）里
					r = mid - 1;
				}else {
					//目标值在另一半数组（无序）里
					l = mid + 1;
				}
			}
		}
		//出循环时没找到目标值
		return -1;
	}
}
