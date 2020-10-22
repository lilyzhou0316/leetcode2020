package day42;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown 
 * to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 * */
public class FindMinimuminRotatedSortedArray_153 {
	 public int findMin(int[] nums) {
		 //思路：类似题33,只是这里用一个变量保存当前最小值，且找到那有序的部分后不需要再在有序的部分
		 //找最小值了，只要找另外无序的半部分
		 int l = 0, r = nums.length - 1, mid = 0;
		 int res = Integer.MAX_VALUE;
		 while(l <= r) {
			 mid = l + (r - l) / 2;
			 
			 if(nums[l] <= nums[mid]) {//这里要取等于，因为l和mid可能重叠
				 //左半部分有序
				 res = Math.min(res, nums[l]);
				 //搜查无序部分
				 l = mid + 1;
			 }else {
				 //右半部分有序
				 res = Math.min(res, nums[mid]);
				 r = mid - 1;
			 }
			 
		 }
		 return res;
		 
	 }
}
