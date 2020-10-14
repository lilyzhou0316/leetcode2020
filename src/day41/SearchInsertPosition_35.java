package day41;

/*
 * Given a sorted array of distinct integers and a target value, return the index if 
 * the target is found. If not, return the index where it would be if it were inserted 
 * in order.

 

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2


Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4

Example 4:

Input: nums = [1,3,5,6], target = 0
Output: 0

Example 5:

Input: nums = [1], target = 0
Output: 0
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
 * */
public class SearchInsertPosition_35 {
	 public int searchInsert(int[] nums, int target) {
		 
		 //二分法查找target的值
		 int l = 0;
	        int r = nums.length-1;
	        int mid;
	        while (l <= r) {
	        	mid = l + (r - l)/2;
				if (nums[mid] == target) {
					return mid;
				}else if(nums[mid] < target) {
					l = mid + 1;
				}else {
					r = mid - 1;
				}	
			}
	        
	        //出循环时，一定没找到,如果目标值大于数组最大值，则需插入最后(增加一位)
	        if (target > nums[nums.length - 1]) {
				return nums.length;
			}
	        //否则插入l所在位置(第一个大于目标值的位置)
	       return l;
	 }
}
