package day09;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you
 *  beforehand.

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

//思路：

public class FindMinimuminRotatedSortedArrayII_154 {
public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int mid;
        int min = Integer.MAX_VALUE;
        while(l <= r) {
        	mid = l + (r - l)/2;
        	min = Math.min(min, nums[mid]);
        	if(nums[mid] == nums[r] && mid < r) {//跳过重复数
        		r--;
        		continue;
        	}
        	if (nums[mid] < nums[r]) {
				//右半子数组有序
        		r = mid - 1;
			}else {
				//左半子数组有序
				min =  Math.min(min, nums[l]);
				l = mid + 1;
			}
        }
        return min;
    }
}
