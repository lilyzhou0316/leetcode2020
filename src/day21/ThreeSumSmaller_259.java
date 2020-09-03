package day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * Given an array of  n  integers  nums  and a  target , find the number of index triplets i, j, k 
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: _nums_ = [-2,0,1,3], and _target_ = 2
Output: 2 

Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
             
Follow up: Could you solve it in  O ( n ^2) runtime?
 * */

//思路:类似题15，16
public class ThreeSumSmaller_259 {
	public int threeSumSmaller(int[] nums, int target) {
		if(nums.length < 3)return 0;
		Arrays.sort(nums);
		if(nums[0] >= target)return 0;
		
		int res = 0;//保存最终结果
		
		for (int i = 0; i < nums.length - 2; i++) {
			int l = i + 1;
			int r = nums.length - 1;
			
			while(l < r) {
				int temp = nums[i] + nums[l] + nums[r];
				if(temp < target) {
					//当前总和小于目标值，让r一直左移那一定也满足条件，所以这种情况下，有right - left个满足情况的index
					res = r - l;
					//然后让l右移一位看是否还满足条件
					l++;
				}else if(temp >= target ) {
					//当前总和太大，让r左移
					r--;
				}
			}
			
		}
		return res;
	}
}
