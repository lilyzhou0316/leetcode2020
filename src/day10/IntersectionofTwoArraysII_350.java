package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]


Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]


Note:
结果集不去重
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.


Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that 
you cannot load all elements into the memory at once?
 * */

//思路1:暴力解法，类似349解法1
//思路2:排序+双指针，类似349解法3
//思路3:map记录长数组中某个元素出现次数，遍历短数组，看是否在map中出现
public class IntersectionofTwoArraysII_350 {
public int[] intersect(int[] nums1, int[] nums2) {
        //解法1略
	
	int n1 = nums1.length;
	int n2 = nums2.length;
	if(n1 > n2) return intersect(nums2, nums1);//始终让第一个参数的长度更小
	List<Integer> resIntegers = new ArrayList<Integer>();
	//解法2:
//	Arrays.sort(nums1);
//	Arrays.sort(nums2);
//	int i = 0;
//	int j = 0;
//	while(i < n1 && j< n2) {//遍历短数组
//		if (nums1[i] > nums2[j]) {
//			j++;
//		}else if(nums1[i] < nums2[j]) {
//			i++;
//		}else {//两者当前数字相等,如果结果集为空，或者结果集的最后一个元素不等于当前数组元素，则加入
//			resIntegers.add(nums1[i]);
//			i++;
//			j++;
//		}
//	}
	
	
	//解法3:
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	for (int i = 0; i < n2; i++) {
		if(map.containsKey(nums2[i])) {
			int value = map.get(nums2[i]);
			map.put(nums2[i], value+1);
			}else {
				map.put(nums2[i], 1);
			}
	}
	
	
	for (int i = 0; i < n1; i++) {
		
		if(map.containsKey(nums1[i]) && map.get(nums1[i]) != 0) {
			resIntegers.add(nums1[i]);
			
			int value = map.get(nums1[i]);
			map.put(nums1[i], value-1);
		}
	}

	return resIntegers.stream().mapToInt(k->k).toArray();
    }

//public static void main(String[] args) {
//	IntersectionofTwoArraysII_350 arraysII_350 = new IntersectionofTwoArraysII_350();
//	int[] n1 = {1,2};
//	int[] n2 = {2,1};
//	arraysII_350.intersect(n1, n2);
//}
}
