package day10;

/*
 * A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return
 its index.

The array may contain multiple peaks, in that case return the index to any one of the 
peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.即第一个数如果大于第二个数也是peak，同理最后一个数

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

//我的思路：暴力解法，两个指针遍历数组,如果满足nums[j] - nums[i])*(nums[j+1] - nums[j]) < 0则j一定是peak

//follow up:要使时间复杂度为log(n)，二分法查找，由于题目中提示了要用对数级的时间复杂度，
//那么我们就要考虑使用类似于二分查找法来缩短时间，由于只是需要找到任意一个峰值，
//那么我们在确定二分查找折半后中间那个元素后，和紧跟的那个元素比较下大小，如果大于，
//则说明峰值在前面，如果小于则在后面。这样就可以找到一个峰值了
public class FindPeakElement_162 {
public int findPeakElement(int[] nums) {
	if(nums.length == 0 || nums == null) return -1;
	if(nums.length == 1 || nums[0] > nums[1])return 0 ;//第一个数大于第二个数
	if(nums[nums.length-1] > nums[nums.length-2])return nums.length-1;//最后一位数大于倒数第二位数
        //解法1
//	int i =0, j = 1;
//	while( nums.length > 2 && j < nums.length - 1) {
//		if((nums[j] - nums[i])*(nums[j+1] - nums[j]) < 0) {
//			return j;
//		}else {
//			i++;
//			j++;    
//		}
//	}
	//return -1;
	
	//解法2:
	int l = 0, r = nums.length-1;
	while(l < r) {
		int mid = l + (r - l)/2;
		if(nums[mid] < nums[mid+1])l = mid+1;
		else r = mid;
	}
	//出循环时，l = r即为所求值，因为l是mid值比后一位小时才移动（升序），r是mid值比后一位大时才移动（降序），
	//所以重合时，一定满足它比前一位大也比后一位大
	return r;
  }
}
