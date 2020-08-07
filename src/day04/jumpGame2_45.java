package day04;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of 
 * the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
    
Note:

You can assume that you can always reach the last index.
 * */

//思路1:greedy
public class jumpGame2_45 {
  public int jump(int[] nums) {
	  int n = nums.length;
	  if(n == 1 || (nums[0] == 0))return 0;
	  
	  int res = 0;//记录最少跳了几次
	  int last = 0, current = 0;//记录用最少的跳次数下，上一个位置上能跳的最远位置，和当前位置上能跳的最远位置
	  for (int i = 0; i < n; i++) {
		if (i > last) {//如果当前所在位置大于上一个位置上能跳的最远位置，说明需要再跳一次了
			res++;
			last = current;//跳到了下一个位置，把之前位置上能跳的最远位置的值赋给last
		}
		current = Math.max(current, i+nums[i]);//计算新位置上能跳的最远位置
	}
	  return res;
	      
    }

}
