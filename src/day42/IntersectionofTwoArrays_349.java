package day42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given two arrays, write a function to compute their intersection.

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
public class IntersectionofTwoArrays_349 {
	public int[] intersection(int[] nums1, int[] nums2) {
		if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)return null;
		
		int n1 = nums1.length, n2 = nums2.length;
		if(n1 > n2)return intersection(nums2, nums1);//让长度短的做第一个参数
		
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		List<Integer> res1 = new ArrayList<Integer>();
		
		for (int i = 0; i < n1; i++) {
			//遍历短的数组，看每个元素是否在长的数组里面出现过，跳过重复元素
			if(i > 0 && nums1[i] == nums1[i - 1])continue;
			
			//如果当前n1中的元素也在n2里，则加入结果集
			if(binarySearch(nums2, nums1[i]))res1.add(nums1[i]);
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
