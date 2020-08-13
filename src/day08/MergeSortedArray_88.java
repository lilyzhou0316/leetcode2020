package day08;

import java.util.Arrays;

/*
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one 
 * sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is equal to m + n) to
 hold additional elements from nums2.
 
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

Constraints:

-10^9 <= nums1[i], nums2[i] <= 10^9
nums1.length == m + n
nums2.length == n
 * */

//我的思路：先插入尾部再排序

//解法2:由于两个数组都是有序的，所有只要按顺序比较大小即可。题目中说了 nums1 数组有足够大的空间，
//说明不用 resize 数组，又给了m和n，那就知道了混合之后的数组的大小，这样就从 nums1 和 nums2 数组的末尾开始
//一个一个比较，把较大的数，按顺序从后往前加入混合之后的数组末尾。需要三个变量 i，j，k，分别指向 
//nums1，nums2，和混合数组的末尾。进行 while 循环，如果i和j都大于0，再看如果 nums1[i] > nums2[j]，
//说明要先把 nums1[i] 加入混合数组的末尾，加入后k和i都要自减1；
//反之就把 nums2[j] 加入混合数组的末尾，加入后k和j都要自减1。循环结束后，有可能i或者j还大于等于0，
//若j大于0，那么还需要继续循环，将 nums2 中的数字继续拷入 nums1。若是i大于等于0，那么就不用管，因为混合数组本身就放在 nums1 中

public class MergeSortedArray_88 {
	 public void merge(int[] nums1, int m, int[] nums2, int n) {
		 //解法1
//		 if(n == 0 || nums2 == null) return;
//		 for (int i = m; i <m+n; i++) {
//			nums1[i] = nums2[i-m];
//		}
//		 Arrays.sort(nums1);
		 
		//三个变量 i，j，k，分别指向 nums1，nums2，和混合数组的末尾
		 int i = m-1, j = n-1, k = m+n-1;
		 while (i >=0 && j >= 0) {//遍历完某一个数组时退出循环
			if (nums1[i] > nums2[j]) {
				//对两个数组从后往前比较每一个元素，谁大谁往混合数组的末尾放
				nums1[k] = nums1[i];
				k--;
				i--;
			}else {
				nums1[k] = nums2[j];
				k--;
				j--;
			}
		}
		 
		 //出循环时，如果数组2还有数字，则继续添加元素到混合数组中，如果时数组1（即混合数组），则什么也不用做
		 while (j>=0) {
			 nums1[k] = nums2[j];
				k--;
				j--;
		}
	 }	 
		 
	 
}
