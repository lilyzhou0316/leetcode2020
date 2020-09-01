package day20;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers nums and and integer target, return the indices of the two numbers 
 * such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element 
twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1]


Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]


Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 * */

//因为每个数只能用一次，所以需要判断每次相加的数的索引是否重复，用map映射每个数及其索引值
public class TwoSum_01 {
	public int[] twoSum(int[] nums, int target) {
		
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			m.put(nums[i],i);
		}
		
		for (int i = 0; i < nums.length; i++) {//遍历数组取出每个组并与map中的另一个数相加看是否等于目标值
			if(m.containsKey(target - nums[i]) && m.get(target - nums[i]) != i) {
				int[] res = new int[2];
				res[0] = i;
				res[1] = m.get(target - nums[i]);
				return res;
				
			}
		}
		return null;
	}
}
