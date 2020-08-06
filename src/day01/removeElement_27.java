package day01;
/*
 * Given an array nums and a value val, remove all instances of that value in-place 
 * and return the new length.

*Do not allocate extra space for another array, 
*you must do this by modifying the input array in-place with O(1) extra memory.

*The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*Example :Given nums = [3,2,2,3], val = 3,

*Your function should return length = 2, with the first two elements of nums being 2.

*It doesn't matter what you leave beyond the returned length.
 * 
 * */
//我的思路，两个pointer,一个指向当前元素（遍历数组），一个指向当前元素的下一个，一旦当前元素=val，
//看另一pointer指向的值是否等于val，如果不等于，直接交换两个pointer指向的值，如果等于则往后移动一位，
//直到指向的值不等于val为止。结束交换后，第一个pointer往后移动。



public class removeElement_27 {
	public int removeElement(int[] arr,int val) {
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]!= val) arr[res++] = arr[i];
		}
		return res;
	}
	

}
