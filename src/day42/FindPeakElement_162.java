package day42;

/*
 * A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return 
its index.

The array may contain multiple peaks, in that case return the index to any one of 
the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
             
             
Follow up: Your solution should be in logarithmic complexity.
 * */
public class FindPeakElement_162 {
	public int findPeakElement(int[] nums) {
		if(nums == null || nums.length == 0)return -1;
		if(nums.length == 1)return 0;//只有一个元素，则该元素满足条件
		//两个及以上元素时，看第一个元素和最后一个元素是否符合条件
		if(nums.length > 1 && nums[0] > nums[1])return 0;
		if(nums.length > 1 && nums[nums.length - 1] > nums[nums.length - 2])return nums.length - 1;
		
		//思路1:按顺序遍历数组，从i = 0开始，到len - 1，
		//如果nums[i] > nums[i - 1] && nums[i] > nums[i + 1],则返回i
		//代码略
		
		
		//follow up:时间复杂度log(n)
		//思路：两个及以上元素时，看第一个元素和最后一个元素是否符合条件前面已经考虑了，现在就看中间部分有没有符合条件的了
		//任取一个中间数，如果它小于它后面那个数，则l右移，直到找到第一个比它后面那个数大的位置
		//然后让r标记那个位置，再查看它前面的数是否比它小
		int l = 0, r = nums.length - 1, mid = 0;
		while(l < r) {
			mid = l + (r - l) / 2;
			//如果nums[mid] < nums[mid + 1]则当前mid数一定不符合条件，先看它的后半部分
			if(nums[mid] < nums[mid + 1])l = mid + 1;
			else r = mid;
		}
		return r;
		
	}
}
