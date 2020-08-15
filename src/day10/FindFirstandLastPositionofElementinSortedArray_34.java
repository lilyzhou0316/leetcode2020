package day10;

/*
 * Given an array of integers nums sorted in ascending order, find the starting and
 *  ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]


Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 

Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non decreasing array.
-10^9 <= target <= 10^9
 * */

//思路1：上升序列数组，时间复杂度要求log(n),使用两次二分查找法，第一次找到左边界，第二次调用找到右边界即可

//思路2:用二分法找到第一个index，以它为分界，再在左半边子序列和右半边子序列中找，以此类推，用两个变量记录出现的最大和
//最小index即可
public class FindFirstandLastPositionofElementinSortedArray_34 {
	 public int[] searchRange(int[] nums, int target) {
		 //解法1
		 int l = 0, r = nums.length;
		 int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
		 
		 while(l < r) {//先找左边界
			 int mid = l + (r - l)/2;
			 if(nums[mid] < target)l = mid + 1;
			 //用>=target控制r往左移动
			 else r = mid;
		 }
		 //出循环时，如果r没有移动过，说明数组里所有数都小于target，如果r对应的值不等于target说明没找到
		 if(r == nums.length || nums[r] != target)return res;
		 
		 res[0] = r;
        r = nums.length;//注意要将r值还原才能开始第二次循环
		 //如果不存在没找到的情况则开始找右边界
		 while(l < r) {
			 int mid = l + (r - l)/2;
			 //用<=target控制l往右移动
			 if(nums[mid] <= target)l = mid + 1;
			 else r = mid;
		 }
		 //出循环时，l=r所处位置正好在target后一位
		 res[1] = r - 1;
        return res;
	 }
	 
	//解法1:
	    //用binary search先找到第一个index，再以第一个index为分界，将nums分为两部分，接着找
	    //starting为所有找到的index里的最小值，ending为所有找到的index里的最大值
//	      int starting = Integer.MAX_VALUE;
//	      int ending = Integer.MIN_VALUE;
//	     public int[] searchRange(int[] nums, int target) {
//	         int[] result = new int[2];
//	         //binary search + recursion
//	          int low = 0;
//	         int high = nums.length -1;
//	         binarySearch(nums, low, high, target);
//	         result[0] = starting;
//	         result[1] = ending;
//	         if(result[0] == Integer.MAX_VALUE)return new int[]{-1,-1};//nums不包含target
//	         return result;
//	     }
	    
//	     public void binarySearch(int[] array, int low, int high, int target){
//	         if(low > high) return;
	        
//	         int mid = low + (high - low)/2;
//	         if(array[mid] == target){
//	             starting = Math.min(mid,starting);//所有找到的index里的最小值
//	             ending = Math.max(mid, ending);//所有找到的index里的最大值
//	             binarySearch(array, low, mid-1, target);//在左半边接着找下一个
//	             binarySearch(array, mid+1,high, target);//在右边接着找下一个
//	         }else if(array[mid] > target){
//	             binarySearch(array, low,mid-1, target);
//	         }else{
//	             binarySearch(array, mid+1,high, target);
//	         } 
	        
//	     }
}
