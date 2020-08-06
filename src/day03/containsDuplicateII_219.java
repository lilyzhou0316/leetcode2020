package day03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given an array of integers and an integer k, find out whether there are two distinct 
 * indices i and j in the array such that nums[i] = nums[j] and the absolute difference 
 * between i and j is at most k.（<=k）

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false

 * */
//思路1:哈希表，类似217

//思路2:slide window：The front of the window is at i, the rear of the window is k steps back. 
//The elements within that window are maintained using a Set. While adding new element to the set,
//if add() returns false, it means the element already exists in the set. At that point, 
//we return true. If the control reaches out of for loop, it means that inner return true 
//never executed, meaning no such duplicate element was found.
//中心思想为：让set里始终保持只有k+1个元素，一旦按遍历顺序加入set中时失败（set中不能有重复元素），说明找到了
//而一旦set里元素个数超过k+1个就要删除最先加入的那个元素

public class containsDuplicateII_219 {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
	
	//解法1
	  //boolean res = false;
//	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//	for (int i = 0; i < nums.length; i++) {
//	if (map.containsKey(nums[i])) {	
//		if(Math.abs(i-map.get(nums[i]))<=k) {
//			res = true;
//			break;
//		}else{
//            map.put(nums[i],i);//前面一个值相等的索引值差太大了，舍弃前一个，用当前的替换
//       }
//		
//	} else {
//        map.put(nums[i],i);
//	}
//}
	//return res;
	  
	  //解法2:
	  boolean res = false;
	  Set<Integer> set = new HashSet<Integer>();
	  for (int i = 0; i < nums.length; i++) {
		if (i>k) set.remove(nums[i-k-1]);//set元素个数超出了
		
		if(!set.add(nums[i])) res = true;//加不进去说明找到了符合条件的元素
	}
	  return res;
}   
    }
