package day08;

/*
 * Given an array with n objects colored red, white or blue, sort them in-place 
 * so that objects of the same color are adjacent, with the colors in the order red, 
 * white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, 
and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]


Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array 
with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 * */

//思路1:用计数排序，需要遍历数组两遍，那么先来看这种方法，因为数组中只有三个不同的元素，所以实现起来很容易。
//- 首先遍历一遍原数组，分别记录 0，1，2 的个数。
//- 然后更新原数组，按个数分别赋上 0，1，2。

//思路2:用双指针来做，分别从原数组的首尾往中心移动。
//- 定义red指针指向开头位置，blue指针指向末尾位置。
//- 从头开始遍历原数组，如果遇到0，则交换该值和red指针指向的值，并将red指针后移一位,i后移一位。
//若遇到2，则交换该值和blue指针指向的值，并将blue指针前移一位,i不动（因为此时不知道被换到i位置的是什么值）。若遇到1，则继续遍历。

public class SortColors_75 {
	public void sortColors(int[] nums) {
		//解法1:计数排序
//		int red = 0, white = 0, blue = 0;
//		for (int i = 0; i < nums.length; i++) {
//			if(nums[i] == 0)red++;
//			if(nums[i] == 1)white++;
//			if(nums[i] == 2) blue++;
//		}
//		for (int i = 0; i < nums.length; i++) {
//			if (red > 0) {
//				nums[i] = 0;
//				red--;
//			}else if(white > 0) {
//				nums[i] = 1;
//				white--;
//			}else if(blue > 0) {
//				nums[i] = 1;
//				blue--;
//			}	
//		}
		
		//解法2:双指针,一个指向数组头部位置，用来放0， 一个指向数组尾部位置，用来放2
		//注意，这里i只遍历到blue的前一个位置，因为blue后面的都是2，不需要再交换位置了
		//注意，这里i只遍历到blue的前一个位置，因为blue后面的都是2，不需要再交换位置了
				int i = 0;
				int red = 0;
				int blue = nums.length-1;
				while(i <= blue) {
					if (nums[i] == 0) {
						int temp = nums[red];
						nums[red] = nums[i];
						nums[i] = temp;
						red++;
						i++;
					}else if(nums[i] == 2) {
						int temp = nums[blue];
						nums[blue] = nums[i];
						nums[i] = temp;
						blue--;
					}else if(nums[i] ==1){
		                i++;
		            }
				}
	}
}
