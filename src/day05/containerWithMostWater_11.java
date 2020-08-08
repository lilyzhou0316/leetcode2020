package day05;

/*
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at 
 * coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i 
 * is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, 
 * such that the container contains the most water.

Note: You may not slant(倾斜) the container and n is at least 2.
图见同包下截图

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 *
 */

//思路1:暴力解法，遍历每个点求它与其它点之间能装的水量，找出最大值，时间复杂度o(n^2)， 空间复杂度o(1)

//思路2:双指针法，从头和尾开始，向中间移动，先计算当前面积并用一个变量保存最大面积值，然后让矮的一端移动。
//（此思路的关键是，一旦矮柱子确定了，那么索引值差最大的一定是面积最大的，相当于对矮柱子来说，与其它剩下的柱子都求过了，
//并找出了最大值）



public class containerWithMostWater_11 {
	 public int maxArea(int[] height) {
		 int low = 0, high = height.length - 1;
		 int max = 0;
		 while (low < high) {
			 int area = (high - low)*Math.min(height[low], height[high]);
			max = Math.max(max, area);
			if (height[low] < height[high]) {
				low++;
			} else {
				high--;
			}
		}
		 return max;
	 }
}
