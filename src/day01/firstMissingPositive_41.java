package day01;
/*
 * Given an unsorted integer array, find the smallest missing positive integer.
 * 
 * Example 1:
 * Input: [1,2,0]
   Output: 3
 * 
 * 
 * Example 2:
 * Input: [3,4,-1,1]
   Output: 2
 *
 *  
 * Example 3: 
 * 
 * Input: [7,8,9,11,12]
   Output: 1
 *
 *Your algorithm should run in O(n) time and uses constant extra space.
 * */

import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

//思路1:用一个变量temp记录最小的正整数，先对数组排序，然后遍历数组，找到第一个大于0的数与变量比较，如果相等，t++，
//如果t小于当前数组里的数，则返回t

public class firstMissingPositive_41 {
	public int firstMissingPositive(int[] arr) {
		if(arr.length == 0)return 1;
		Arrays.sort(arr);
		int temp = 1;
		for (int i = 0; i < arr.length-1; i++) {
			if(arr[i] > 0 && arr[i] != arr[i+1]) {//去重
				if(arr[i] == temp){
					temp++;
					}else {
					break;//找到目标数字，停止循环
				}
			}
		}
		
		if(temp == arr[arr.length-1]) return temp++;//temp与数组最后一个数字比较
		return temp;
	}

}
