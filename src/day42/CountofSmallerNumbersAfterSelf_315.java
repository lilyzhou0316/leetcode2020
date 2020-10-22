package day42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * You are given an integer array nums and you have to return a new counts array. 
 * The counts array has the property where counts[i] is the number of smaller elements 
 * to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 

Constraints:

0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 * */
public class CountofSmallerNumbersAfterSelf_315 {
	 public List<Integer> countSmaller(int[] nums) {
	        //思路1:暴力解法。双层遍历，时间复杂度n^2
		 List<Integer> res = new ArrayList<Integer>() ;
		 if(nums.length == 0 || nums == null)return res;
		
		 if(nums.length == 1) {
			 res.add(0);
			 return res;
		 }
		 //思路2:将给定数组从最后一个开始，用二分法插入到一个新的数组，这样新数组就是有序的，
		 //那么此时该数字在新数组中的坐标就是原数组中其右边所有较小数字的个数
         for (int i = 0; i < nums.length; i++) {
			res.add(-1);
		}
		 List<Integer> sortedArray = new ArrayList<Integer>();//保存通过二分法排序的数组、
		 
		 for (int i = nums.length - 1; i >= 0 ; i--) {
			//从最后一个元素开始，倒序遍历
			 int l = 0, r = sortedArray.size();//r表示当前已经经过排序的元素的个数
			 while(l < r) {
				 //通过二分法在已经部分排序的元素中找到当前元素应该插入的位置，即r的值
				 int mid = l + (r - l) / 2;
				 if(sortedArray.get(mid) >= nums[i])r = mid;
				 else l = mid + 1;
			 }
			 //则r值（index值）即为位于当前元素右边且比当前元素小的元素的个数
			 res.set(i, r);
			 //r也为当前元素在排序数组中需要插入的位置
			 sortedArray.add(r, nums[i]);
		}
		 return res;
	    }
}
