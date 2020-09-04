package day23;

import java.util.Arrays;

/*
 * Given a sorted array of integers  nums  and integer values  a ,  b and  c. 
 * Apply a quadratic function of the form f( x ) =  a * x^2 +  b*x  +  c  to each element  x  
 * in the array.

The returned array must be in sorted order.

Expected time complexity: O( n )

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]


Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
 * */

//我的思路：挨个计算后排序

//思路：其实这道题用到了大量的高中所学的关于抛物线的数学知识，我们知道，对于一个方程f(x) = ax2 + bx + c 来说，
//如果a>0，则抛物线开口朝上，那么两端的值比中间的大，而如果a<0，则抛物线开口朝下，则两端的值比中间的小。
//而当a=0时，则为直线方法，是单调递增或递减的。那么我们可以利用这个性质来解题，题目中说明了给定数组nums是有序的，
//如果不是有序的，我想很难有O(n)的解法。正因为输入数组是有序的，我们可以根据a来分情况讨论：
//当a>0，说明两端的值比中间的值大，那么此时我们从结果res后往前填数，用两个指针分别指向nums数组的开头和结尾，
//指向的两个数就是抛物线两端的数，将它们之中较大的数先存入res的末尾，然后指针向中间移，重复比较过程，直到把res都填满。
//当a<0，说明两端的值比中间的小，那么我们从res的前面往后填，用两个指针分别指向nums数组的开头和结尾，指向的两个数就是抛物线两端的数，将它们之中较小的数先存入res的开头，然后指针向中间移，重复比较过程，直到把res都填满。
//当a=0，函数是单调递增或递减的，那么从前往后填和从后往前填都可以，我们可以将这种情况和a>0合并
public class SortTransformedArray_360 {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		//解法1
//		int[] res = new int[nums.length];
//		
//		for (int i = 0; i < nums.length; i++) {
//			res[i] = nums[i]*nums[i]*a + b*nums[i] + c;
//		}
//		
//		Arrays.sort(res);
//		return res;
		
		
		//解法2
		int l = 0, r = nums.length - 1;
		int[] res = new int[nums.length];
		int idx = a >= 0 ? nums.length - 1 : 0;
        while (l <= r) {
            if (a >= 0) {
                res[idx--] = cal(nums[l], a, b, c) >= cal(nums[r], a, b, c) ? cal(nums[l++], a, b, c) : cal(nums[r--], a, b, c);
            } else {
                res[idx++] = cal(nums[l], a, b, c) >= cal(nums[r], a, b, c) ? cal(nums[r--], a, b, c) : cal(nums[l++], a, b, c);
            }
        }
        return res;
		}
	
	public int cal(int num, int a, int b, int c) {
		return num * num * a + num * b + c;
	}
	}


