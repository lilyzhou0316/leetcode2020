package day42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 * */
public class LongestIncreasingSubsequence_300 {
	public int lengthOfLIS(int[] nums) {
		
		if(nums.length == 0 || nums == null)return 0;
		if(nums.length == 1)return 1;
		//思路1:dp.求最值一般都可以用dp。
//		int[] dp = new int[nums.length];//dp[i]表示以nums[i]作为最后一个元素时，升序子序列的最大长度
//		//base case： 每个元素以自身为最后一个元素，则升序子序列长度至少为1（它自己）
//		Arrays.fill(dp, 1);
//		int max = 1;
//		for (int i = 1; i < dp.length; i++) {
//			for (int j = 0; j < i; j++) {
//				//从第一个元素开始，遍历它之前的所有元素
//				//如果当前元素j小于i则dp[i] 等于dp[j] + 1
//				if(nums[j] < nums[i]) {
//					dp[i]  = Math.max(dp[i], dp[j] + 1);
//				}
//			}
//			max = Math.max(max, dp[i]);
//		}
//		return max;
		 
		//思路2:类似题315，用二分法查找找到元素插入位置，但这里不是插入而是直接替换掉对应位置的元素值
		int[] sorted = new int[nums.length];
		sorted[0] = nums[0];
		
		int end = 0;//end指向已经排序的数组的最后一个元素位置
		
		for (int i = 1; i < nums.length; i++) {
			//从第二个元素开始，如果它大于end对应的值，则直接加入排序数组
			if(sorted[end] < nums[i]){
				end++;
				sorted[end] = nums[i];
			}else {
				//否则，用二分法在已经排序的数组里找到对应应该插入的位置的index值
				int l = 0, r = end;
				while(l < r) {
					int mid = l + (r - l) / 2;
					if(sorted[mid] >= nums[i])r = mid;
					else l = mid + 1;
				}
				//出循环时，r指向目标位置,替换掉对应位置的值，这样是为了尽可能的让每个位置的值更小，以便能扩大长度
				sorted[r] = nums[i];
			}
		}
		return end + 1;
	}
}
