package day08;

import java.util.ArrayList;
import java.util.List;

import day02.pascalsTriangle_118;

/*
 * A sequence of numbers is called a wiggle sequence if the differences between 
 * successive numbers strictly alternate between positive and negative. 
 * The first difference (if one exists) may be either positive or negative.
 *  A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) 
are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are 
not wiggle sequences, the first because its first two differences are positive and 
the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence 
that is a wiggle sequence. A subsequence is obtained by deleting some number 
of elements (eventually, also zero) from the original sequence, leaving the 
remaining elements in their original order.

Example 1:

Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.


Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length.
 One is [1,17,10,13,10,16,8].
 
 
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2


Follow up:
Can you do it in O(n) time?
 * */

//思路1:DP，为了更好地理解这一方法，用两个数组来 dp ，分别记作 up 和 down。
//每当我们选择一个元素作为摆动序列的一部分时，这个元素要么是上升的，要么是下降的，这取决于前一个元素的大小。
//up[i] 存的是目前为止最长的以第 i个元素结尾的上升摆动序列的长度。
//类似的， down[i] 记录的是目前为止最长的以第 i 个元素结尾的下降摆动序列的长度。
//我们每当找到将第 ii 个元素作为上升摆动序列的尾部的时候就更新 up[i]up[i] 。
//现在我们考虑如何更新 up[i]up[i] ，我们需要考虑前面所有的降序结尾摆动序列，
//也就是找到 down[j]down[j] ，满足 j < i 且 nums[i] >nums[j] 。类似的， down[i]也会被更新。


//思路2:Greedy算法正好可以达到O(n)的时间内完成这个要求，这里我们不在维护两个dp数组，
//而是维护两个变量p和q，然后遍历数组，如果当前数字比前一个数字大，则p=q+1，如果比前一个数字小，
//则q=p+1，最后取p和q中的较大值跟n比较，取较小的那个

//思路3:暴力解法

//思路4:我的思路的修正。维护一个变量prevdiff ，它的作用是只是当前数字的序列是上升还是下降的。
//如果prevdiff>0 （之前的差值是正），那么表示目前是上升序列，我们需要找一个下降的元素。
//因此，我们更新已找到的序列长度diff （nums[i]-nums[i-1]）为负数。
//类似的，如果prevdiff<0 ，我们就更新 diff （nums[i]-nums[i-1]）为正数。
//当整个数组都被遍历后，我们得到了最终的结果，它就是最长摆动子序列的长度。


public class WiggleSubsequence_376 {
public int wiggleMaxLength(int[] nums) { 
	
  int n = nums.length;
 if(n == 0 || nums == null)return 0;
  if(n == 1 ) return 1;
//解法1.dp
//  int[] up = new int[n];//up[i]是目前为止最长的以第 i个元素结尾的上升摆动序列的长度
//  int[] down = new int[n];// down[i] 记录的是目前为止最长的以第 i 个元素结尾的下降摆动序列的长度
//  up[0] = 1;
//  down[0] = 1;
//  int max1 = up[0];
//  int max2 = down[0];
//  for (int i = 1; i < n; i++) {
//		for (int j = 0; j < i; j++) {
//			if (nums[i] > nums[j]) {
//				up[i] = Math.max(up[i], down[j]+1);
//              max1 =  Math.max(up[i],max1);
//			}else if(nums[i] < nums[j]){
//				down[i] = Math.max(down[i], up[j]+1);
//               max2 =  Math.max(down[i],max2);
//			}
//		}
//	}
//  return Math.max(max1,max2);
	
	//解法2:greedy
//	int increase = 1;//用increase表示当前数字是上升的,同时又符合条件的最大长度个数
//	int decrease = 1;//用decrease表示当前数字是下降的,同时又符合条件的最大长度个数
//	for (int i = 1; i < n; i++) {
//		if(nums[i] > nums[i-1]){
//			//increase只能在前一个是decrease时+1，如果是连续增加的数，则increase不变
//			increase = decrease + 1;
//		}else if(nums[i] < nums[i-1]) {
//			//同上
//			decrease = increase + 1;
//		}
//		//nums[i] = nums[i-1]的情况跳过
//	}
//	
//	return Math.max(increase,decrease);
	
	//解法3:暴力解法
//	 private int calculate(int[] nums, int index, boolean isUp) {
//	        int maxcount = 0;
//	        for (int i = index + 1; i < nums.length; i++) {
//	            if ((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index]))
//	                maxcount = Math.max(maxcount, 1 + calculate(nums, i, !isUp));
//	        }
//	        return maxcount;
//	    }
//
//	    public int wiggleMaxLength(int[] nums) {
//	        if (nums.length < 2)
//	            return nums.length;
//	        return 1 + Math.max(calculate(nums, 0, true), calculate(nums, 0, false));
//	    }

	//解法4
	
    int prevdiff = nums[1] - nums[0];
    int count = prevdiff != 0 ? 2 : 1;
    for (int i = 2; i < nums.length; i++) {
        int diff = nums[i] - nums[i - 1];
        if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
            count++;
            prevdiff = diff;
        }
    }
    return count;

}
}
