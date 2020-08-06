package day03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 * 
 * Example 1:
 * Input: [1,2,3,1]
Output: true


Example 2:

Input: [1,2,3,4]
Output: false

Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true

 * */

//思路1:哈希表

//思路2:给数组排序，比较相邻的元素是否相等

public class containsDuplicate_217 {
public boolean containsDuplicate(int[] nums) {
	//时间复杂度不符合要求
//        boolean res = false;
//        int i = 0;//第一个pointer
//        int j = i+1;//第二个pointer
//       while (i<nums.length-1) {
//		if(nums[i] == nums[j]) {
//			res = true;
//			break;
//		}else if(j == nums.length - 1) {
//			if (nums[i] == nums[j]) {
//				res = true;
//				break;
//			}
//			i++;
//			j = i+1;
//		}else {
//			j++;
//		}	
//	}
//       return res;
	
	
	//解法1:哈希表
//	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//	boolean res = false;
//	for (int i = 0; i < nums.length; i++) {
//		if (map.containsKey(nums[i])) {
//			res = true;
//			break;
//		} else {
//            map.put(nums[i], 1);
//		}
//	}
//	return res;
	
	
	
	//解法2:数组排序,两两比较
	boolean res = false;
	Arrays.sort(nums);
	for (int i = 0; i < nums.length-1; i++) {
		if (nums[i] == nums[i+1]) {
			res = true;
			break;
		}
	}
	return res;
}
}
