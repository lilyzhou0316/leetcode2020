package day42;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you 
 * beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
 * */
public class FindMinimuminRotatedSortedArrayII_154 {
	public int findMin(int[] nums) {
		//思路：类似题81，需要跳过末尾重复元素(nums[mid] == nums[r] && mid < r)
		int l = 0, r = nums.length - 1, mid = 0;
		int res = Integer.MAX_VALUE;
		while(l <= r) {
			mid = l + (r - l) / 2;
            
			if(nums[mid] == nums[r] && mid < r){
                r--;
                continue;
            }
            
			if(nums[mid] < nums[r]) {
				res = Math.min(res, nums[mid]);
				r = mid - 1;
			}else {
				res = Math.min(res, nums[l]);
				l = mid + 1;
			}
		}
		return res;
	}
}
