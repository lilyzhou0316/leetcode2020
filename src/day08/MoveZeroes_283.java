package day08;

import day05.increasingTripletSubsequence_334;

/*
 * Given an array nums, write a function to move all 0's to the end of it 
 * while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 * */

//我的思路：冒泡排序？？遍历数组，找到第一个0，两两交换位置直到最后，重复该过程找下一个0放到最后

//思路2:用替换法in-place来做，需要用两个指针，一个不停的向后扫，找到非零位置，然后和前面那个指针交换位置即可

public class MoveZeroes_283 {
public void moveZeroes(int[] nums) {
	 //解法1:
//		int i = 0;
//		int start , end = nums.length-1;//end指向最后一位非0的数字
//		while(i <= end){
//			while (i < nums.length && nums[i] != 0) {
//				i++;	
//			}
//	        if(i >= nums.length)break;//说明没有0
//			start = i;
//			//找到第一个0,进行位置交换,需要交换end - start次
//			int n = end - start;
//			for (int j = 1; j <= n ; j++) {
//				int temp = nums[start];
//				nums[start] = nums[start+1];
//				nums[start+1] = temp;
//				start++;
//			}
//			end--;
//			}
	
	
	 //解法2：
    int i = 0, j = 1;//i,j始终指向两个相邻的位置,且i在前
    while(j < nums.length){
        //看相邻位置的前一位是否为0，如果为0，且后一位不为0，则交换位置
        if(nums[i] == 0 && nums[j] != 0){
            
            int temp = nums[i];
		    nums[i] = nums[j];
		    nums[j] = temp;
            i++;
            j++;
        }else if(nums[i] == 0 && nums[j] == 0){
            j++;//让j找到第一个不为0的位置交换
        }else{//两个位置都不为0时
            i++;
            j++;
        }
    }
	}
}
