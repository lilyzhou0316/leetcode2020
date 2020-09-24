package day32;

/*
 * Implement next permutation, which rearranges numbers into the lexicographically（字典序，即一位一位比较大小）
 *  next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted 
in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the 
right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 * */

//思路：这道题让我们求下一个排列顺序，由题目中给的例子可以看出来，如果给定数组是降序，则说明是全排列的最后一种情况，
//则下一个排列就是最初始情况，再来看下面一个例子，有如下的一个数组
//1　　2　　7　　4　　3　　1
//下一个排列为：
//1　　3　　1　　2　　4　　7
//那么是如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后再从后往前找
//第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：
//1　　2　　7　　4　　3　　1
//1　　3　　7　　4　　2　　1
//1　　3　　1　　2　　4　　7
public class NextPermutation_31 {
	 public void nextPermutation(int[] nums) {
	        if(nums.length <= 1)return;
	        int i = nums.length - 2;//i从数组最后开始往前找，看是否一直是升序，一旦不是停止移动
	        while(i >= 0 && nums[i + 1] <= nums[i]) {
	        	i--;
	        }
	        //如果整个数组从后往前看都是升序，则出循环时，i = -1
	        //否则i指向第一个不是升序（从后往前）的元素
	        //则下面开始在之前的升序序列里找第一个大于当前i指向的元素的数字
	        if(i >= 0) {
	        	int j = nums.length - 1;//从后往前找第一个大于当前i指向的元素的数字
	        	while(j > i && nums[j] <= nums[i]) {
	        		j--;
	        	}
	        	//找到后，交换i , j上的数字
	        	swap(nums, i, j);
	        }
	        
	        //最后把i以后的数组进行一次翻转
	        reverseIntArr(nums, i + 1);
	    }
	 public void reverseIntArr(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while(i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}
	 
	 public void swap(int[] nums, int i, int j) {
		 int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
	}
}
