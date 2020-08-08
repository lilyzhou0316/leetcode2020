package day05;

/*
 * Given an unsorted array return whether an increasing subsequence of length 3 exists 
 * or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true

Example 2:

Input: [5,4,3,2,1]
Output: false
 * */

//思路1:使用两个指针m1和m2，初始化为整型最大值，我们遍历数组，如果m1大于等于当前数字，则将当前数字赋给m1；
//如果m1小于当前数字且m2大于等于当前数字，那么将当前数字赋给m2，一旦m2被更新了，说明一定会有一个数小于m2，
//那么我们就成功的组成了一个长度为2的递增子序列，所以我们一旦遍历到比m2还大的数，我们直接返回ture。
//如果我们遇到比m1小的数，还是要更新m1，有可能的话也要更新m2为更小的值，毕竟m2的值越小，
//能组成长度为3的递增序列的可能性越大.注意，i,j,k不要求是连着的


public class increasingTripletSubsequence_334 {
	 public boolean increasingTriplet(int[] nums) {
	        //解法1:
		 //两个指针，一个指向三个数中的较小数，一个指向较大数，如果下个出现数大于较大数则出现三个连续递增的数
		 int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
		 for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= m1) {
				m1 = nums[i];
			}else if (nums[i] <= m2) {
				m2 = nums[i];
			} else {
                return true;
			}
		}
		 return false;
		 
	    }
}
