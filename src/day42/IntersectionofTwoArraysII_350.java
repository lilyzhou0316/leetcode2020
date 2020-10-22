package day42;

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

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you 
cannot load all elements into the memory at once?
 * */
public class IntersectionofTwoArraysII_350 {
	public int[] intersect(int[] nums1, int[] nums2) {
		//解法1:把长数组转成list，然后排序+二分法，这里注意每次在长数组中找到了当前出现在短数组中的数
		//并完成加入结果集的操作后，都要把该元素从list中删除
		//解法2:把长数组的值都放在map中，利用map来查找短数组中的元素是否也在长数组中出现了，且当前出现次数大于0
		//时间复杂度o(n)
if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)return null;
		
		int n1 = nums1.length, n2 = nums2.length;
		if(n1 > n2)return intersect(nums2, nums1);//让长度短的做第一个参数
		List<Integer> res1 = new ArrayList<Integer>();
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums2.length; i++) {
			if(map.containsKey(nums2[i])) {
				int count = map.get(nums2[i]);
				map.put(nums2[i], ++count);
			}
		}
		
		
		for (int i = 0; i < nums1.length; i++) {
			if(map.containsKey(nums1[i]) && map.get(nums1[i]) != 0) {
				res1.add(nums1[i]);
				int count = map.get(nums1[i]);
				map.put(nums1[i], --count);
			}else{
                map.put(nums2[i], 1);
            }
		}
		
		int[] res = new int[res1.size()];
		for (int i = 0; i < res1.size(); i++) {
			res[i] = res1.get(i);
		}
		return res;
	}
	
	public boolean binarySearch(int[] num, int target) {
		int l = 0, r = num.length - 1;
		while(l <= r) {
			int mid = l + (r - l) / 2;
			if(num[mid] == target)return true;
			else if(num[mid] < target)l = mid + 1;
			else r = mid - 1;
		}
		return false;
	}
	
}
