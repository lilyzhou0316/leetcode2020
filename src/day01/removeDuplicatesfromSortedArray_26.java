package day01;

/*
 Given a sorted array nums, remove the duplicates in-place such that 
 each element appear only once and return the new length.

Do not allocate extra space for another array, 
you must do this by modifying the input array in-place with O(1) extra memory. 

Example :Given nums = [1,1,2],
Your function should return length = 2,
 with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.

 * */

//思路：使用快慢指针来记录遍历的坐标，最开始时两个指针都指向第一个数字，
//如果两个指针指的数字相同，则快指针向前走一步，如果不同，则慢指针先向前走一步，
//并将快指针指向的值赋给慢指针，然后快指针向前一步
//这样当快指针走完整个数组后，慢指针当前的坐标加1就是数组中不同数字的个数


public class removeDuplicatesfromSortedArray_26 {
	
	public int removeDuplicatesfromSortedArray(int[] arr) {
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != arr[j]) arr[++j] = arr[i];
		}
		return j+1;
	}

}
