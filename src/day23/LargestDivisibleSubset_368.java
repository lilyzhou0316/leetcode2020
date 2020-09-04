package day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a set of distinct positive integers, find the largest subset such that every pair
 *  (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
 * */

//思路：先给数组排序，如果一个数x能整除当前结果集（有序升序）的最大元素y（即x % y == 0），则加入该元素到末尾满足条件;或者
//如果一个数x能整除当前结果集（有序升序）的最小元素y（即y % x == 0），则加入该元素到第一个位置也满足条件
public class LargestDivisibleSubset_368 {

	public List<Integer> largestDivisibleSubset(int[] nums) {
	    // Test case with empty set.
	    int n = nums.length;
	    if (n == 0) return new ArrayList();
	        
	    // Container to keep the size of the largest divisible subset
	    //   that ends with each of the nums.
	    Integer[] dp = new Integer[n];//dp[i]代表以num[i]为结尾的满足条件的最长子序列长度

	    /* Sort the original list in ascending order. */
	    Arrays.sort(nums);

	    Integer maxSubsetSize = -1, maxSubsetIndex = -1;//记录最长子序列长度和该子序列的末尾数字的索引
	    
	    /* Calculate the rest of EDS(X_i) */
	    for (int i = 0; i < n; ++i) {
	      Integer subsetSize = 0;//记录以当前num[i]结尾的子序列的长度

	      // Find the size of the largest divisible subset.
	      for (int k = 0; k < i; ++k) 
	        if (nums[i] % nums[k] == 0 && subsetSize < dp[k])
	          subsetSize = dp[k];

	      // Extend the found subset with the element itself.
	      dp[i] = subsetSize + 1;
	    
	      // We reuse this loop to obtain the largest subset size 
	      //   in order to prepare for the reconstruction of subset.
	      if (maxSubsetSize < dp[i]) {
	        maxSubsetSize = dp[i];
	        maxSubsetIndex = i;
	      }
	    }
	    
	    /* Reconstruct the largest divisible subset  */
	    LinkedList<Integer> subset = new LinkedList();
	    Integer currSize = maxSubsetSize;
	    Integer currTail = nums[maxSubsetIndex];
	    for (int i = maxSubsetIndex; i >= 0; --i) {
	      if (currSize == 0) break;
	    
	      if (currTail % nums[i] == 0 && currSize == dp[i]) {
	        subset.addFirst(nums[i]);
	        currTail = nums[i];
	        currSize -= 1;
	      }
	    }

	    return subset;
	  }  

}
