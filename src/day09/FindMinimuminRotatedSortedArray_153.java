package day09;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you
beforehand.

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


//思路：又是无重复升序数组，与题33，81类似
public class FindMinimuminRotatedSortedArray_153 {
	 public int findMin(int[] nums) {
	        int l = 0, r = nums.length - 1;
	        int mid;
	        int min = Integer.MAX_VALUE;
	        while(l <= r) {
	        	mid = l + (r - l)/2;
	        	min = Math.min(min, nums[mid]);
	        	if (nums[mid] < nums[r]) {//右半边子数组有序(升序)，此时min取到的是右半边的最小值
	        		//因此只需要看剩下的半边数组还有没有更小的值
					r = mid - 1;
	        	}else {//左半边子数组有序，此时min取到的是左半边的最大值,而nums[l]是该数组的最小值
	        		min = Math.min(min, nums[l]);
	        		//因此只需要看剩下的右半边数组还有没有更小值
	        		l = mid + 1;
	        	}
	        }
	        return min;
	    }
}
