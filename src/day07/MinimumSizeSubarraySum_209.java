package day07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of n positive integers and a positive integer s, 
 * find the minimal length of a contiguous subarray of which the sum ≥ s.
 *  If there isn't one, return 0 instead.

Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:
If you have figured out the O(n) solution, try coding another solution 
of which the time complexity is O(n log n). 
 * */

//思路1： O(n) 的解法，需要定义两个指针 left 和 right，分别记录子数组的左右的边界位置，
//然后让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾，此时更新最短距离，
//并且将 left 像右移一位，然后再 sum 中减去移去的值，然后重复上面的步骤，直到 right 到达末尾，
//且 left 到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值。


//思路2：O(nlgn) 的解法，这个解法要用到二分查找法，思路是，建立一个比原数组长一位的 sums 数组，
//其中 sums[i] 表示 nums 数组中 [0, i - 1] 的和，然后对于 sums 中每一个值 sums[i]，
//用二分查找法找到子数组的右边界位置，使该子数组之和大于 sums[i] + s（即sums[j] - sums[i] >= s），然后更新最短长度的距离即可。
public class MinimumSizeSubarraySum_209 {
	 public int minSubArrayLen(int s, int[] nums) {
		 if(nums.length == 0 || nums == null)return 0;
		 
		 //解法1:双指针
//	        int left = 0, right = 0;//两个指针，记录子数组的左右边界位置
//	        int sum = 0;//记录累加和
//	        int len = nums.length;
//	        int minLen = Integer.MAX_VALUE;//记录最短子数组长度
//	        
//	        while (right < len) {
//	        	//left从0开始，left不动，让right移动找到满足条件的右边界位置
//				while (sum < s && right < len) {
//					sum += nums[right];
//					right++;//指向了满足条件的下一位
//				}
//				while (sum >= s) {
//					//一旦找到，left移动一位，重新找满足条件的right
//					minLen = Math.min(minLen, right - left);
//					sum -= nums[left];//重新计算left移动后的累加和
//					left++;
//				}
//			}
//	        
//	        return minLen == Integer.MAX_VALUE? 0:minLen;
		 
		 //解法二：二分法查找
		 int minLen = Integer.MAX_VALUE;//记录最短子数组长度
		 int n = nums.length;
		 int[] sums = new int[n+1];//用于记录前i个数的累加和
		 
		 sums[0] = 0;
		 for (int i = 1; i < sums.length; i++) {
			sums[i] = sums[i-1] + nums[i-1];	//该数组一定是一个递增有序数组，所以可以用二分法
		}
		 
		 for (int i = 0; i < n+1; i++) {
			
			int target = sums[i-1]+ s;
			int bound = Arrays.binarySearch(sums, target);
//			while (low <= high) {
//			  int mid = low+ (high - low)/2;
//			  if (sums[mid] < sums[i] + s) {//i即为当前左边界
//				  //即sums[mid] - sums[i] < s,说明mid太小
//				low = mid +1;
//			}else if(sums[mid] > sums[i] + s){
//				high = mid -1;
//			}else {
//				target = mid;
//			}
			  if (bound < 0) {//没找到，返回一个负值
				  bound = -bound - 1;
	            }
	            if (bound <= n) {
	                minLen = Math.min(minLen, bound - (i - 1));
	            }

			
		}
		 return minLen == Integer.MAX_VALUE ? 0 : minLen;
	    }
}
