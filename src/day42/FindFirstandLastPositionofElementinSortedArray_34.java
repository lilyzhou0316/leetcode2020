package day42;

/*
 * Given an array of integers nums sorted in ascending order, find the starting and 
 * ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?

 

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 * */
public class FindFirstandLastPositionofElementinSortedArray_34 {
	public int[] searchRange(int[] nums, int target) {
		//思路1:数组已排序，则遍历每个元素看是否等于目标值，找到开始的位置和结束的位置， 时间复杂度 n
		
		//思路2:数组已排序，二分法查找
		int[] res = new int[2];
		res[0] = nums.length;//记录起始位置
		res[1] = -1;//记录最后位置
		
		helper(nums, target, res, 0, nums.length - 1);
		if(res[0] == nums.length)return new int[]{-1,-1};
		else return res;
	}
	
	public void helper(int[] nums, int target, int[] res, int l, int r) {
		if(l > r)return;
		
		int mid = l + (r - l) / 2;
		if(nums[mid] == target) {
			res[0] = Math.min(res[0], mid);
			res[1] = Math.max(res[1], mid);
			if(nums[l] == nums[mid]) {
				res[0] =  Math.min(res[0], l);
				helper(nums, target, res, mid + 1, r);
			}else if(nums[mid] == nums[r]) {
				res[1] =  Math.max(res[1], r);
				helper(nums, target, res, l, mid - 1);
			}else {
				helper(nums, target, res, l, mid - 1);
				helper(nums, target, res, mid + 1, r);
			}
		}else if(nums[mid] > target) {
			helper(nums, target, res, l, mid - 1);
		}else {
			helper(nums, target, res, mid + 1, r);
		}
	}
}
