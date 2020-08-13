package day09;

import java.util.Arrays;

/*
 * Given an unsorted array nums, reorder it such that 
 * nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].


Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].


Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 * */

//思路1:先给数组排序，然后在做调整。调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，
//然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，
//然后从前半段取倒数第二个，从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，
//以此类推直至都取完

//思路2:对于follow up需要用到三路排序和虚拟地址

public class WiggleSortII_324 {
	public void wiggleSort(int[] nums) {
		 //解法1:
		Arrays.sort(nums);
        int[] res = nums.clone();
		int end1 = res.length/2;//将排序后的数组一分为2，end1取前半部分的最后一位
		if(res.length % 2 ==0)end1 = res.length/2 - 1;
		int end2 = res.length - 1;//end2取后半部分的最后一位
		
		for (int i = 0; i < nums.length; i++) {
			if(i % 2 ==0) {
				nums[i] =res[end1];
				end1--;
			}else {
				nums[i] = res[end2];
				end2--;
			}	
		}
	}
	
}
