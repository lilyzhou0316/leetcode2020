package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given two arrays, write a function to compute their intersection.
找交集
Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]


Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]


Note:

Each element in the result must be unique.
The result can be in any order.
 * */

//我的思路1:暴力解法，两层遍历，短的那个做外层，如果当前元素在两个元素中都出现了，加入结果集
//时间复杂度：n^2

//我的思路2:用map(set)去重，找是否存在

//思路3:排序+双指针

//思路4:二分法查找。将长数组都排序，然后遍历短数组，将每一个元素当成target在长数组中查找
public class IntersectionofTwoArrays_349 {
public int[] intersection(int[] nums1, int[] nums2) {
	 
		int n1 = nums1.length;
		int n2 = nums2.length;
		if(n1 > n2) return intersection(nums2, nums1);//始终让第一个参数的长度更小
		List<Integer> resIntegers = new ArrayList<Integer>();
		//解法1
		
//		for (int i = 0; i < n2; i++) {
//			for (int j = 0; j < n1; j++) {
//				if(nums1[j] == nums2[i] && !resIntegers.contains(nums1[j])) resIntegers.add(nums1[j]);
//			}
//		}
//		
//		return resIntegers.stream().mapToInt(k->k).toArray();
		
		//解法2:
		//Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		

		//解法3:
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0;
		int j = 0;
		while(i < n1 && j< n2) {//遍历短数组
			if (nums1[i] > nums2[j]) {
				j++;
			}else if(nums1[i] < nums2[j]) {
				i++;
			}else {//两者当前数字相等,如果结果集为空，或者结果集的最后一个元素不等于当前数组元素，则加入
				if(resIntegers.isEmpty() || resIntegers.get(resIntegers.size()-1) != nums1[i])resIntegers.add(nums1[i]);
				i++;
				j++;
			}
		}
		
	
		return resIntegers.stream().mapToInt(k->k).toArray();
    }
}
