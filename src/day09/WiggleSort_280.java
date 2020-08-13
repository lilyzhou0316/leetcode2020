package day09;

import java.util.Arrays;

/*
 * Given an unsorted array nums, reorder it in-place such that 
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 * */

//我的思路：用两个指针遍历数组相邻的元素，用一个变量flag表示当前是上升还是下降序列，如果是上升，则当前需要找一个下降的数，
//反之，则找上升的数，每次找完更新变量

//思路2:先来看一种时间复杂度为 O(nlgn) 的方法，思路是先给数组排个序，
//然后只要每次把第三个数和第二个数调换个位置，第五个数和第四个数调换个位置，以此类推直至数组末尾，
//这样就能完成摆动排序了

//思路3:根据数字的索引的奇偶性，当i为奇数时，一定大于前一个数，当i为偶数时，一定小于前一个数，如果不是则交换位置

public class WiggleSort_280 {
	public void wiggleSort(int[] nums) {
		if(nums.length == 0 || nums == null || nums.length == 1)return;
		
		//解法1
//		int flag = -1;//初始是找上升序列
//		int i = 0;
//		int j = 1;
//		while (i < nums.length - 1) {
//			if((flag == -1 && nums[i] <= nums[j]) || (flag == 1 && nums[i] >= nums[j])){//找上升序列或下降序列,直接找到
//				i++;
//				j++;
//				flag = -flag;
//			}
//			while(j < nums.length && (flag == -1 && nums[i] > nums[j]) || (flag == 1 && nums[i] < nums[j])){
//				//找上升序列或下降序列,下一个数不符合,则j后移直到找到第一个符合的数
//				j++;
//			}
//			
//			if((j - i) > 1 && (flag == -1 && nums[i] <= nums[j]) || (flag == 1 && nums[i] >= nums[j])) {
//				swap(i+1, j, nums);
//				i++;
//				j = i+1;
//			}
			
		//解法2:排序
//		Arrays.sort(nums);
//		for (int i = 2; i < nums.length; i = i + 2 ) {//从第三个数开始，与前面的数交换位置
//			swap(i, i - 1, nums);
//		}
		
		//解法3:奇偶性
		for (int i = 1; i < nums.length; i++) {
			if ((i %2 == 1 && nums[i] < nums[i-1]) || (i %2 == 0 && nums[i] > nums[i-1]) ){
				swap(i, i-1, nums);
			}
		}
		
	}
		
		
	
	
	public void swap(int i, int j, int[] arr) {//交换i,j位置上的数字
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
