package day07;

/*
 * Given an array nums of n integers where n > 1,  
 * return an array output such that output[i] is equal to the product of all the elements 
 * of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix 
or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).不能用除法

Follow up:
Could you solve it with constant space complexity?
 (The output array does not count as extra space for the purpose of space complexity
  analysis.)
 * */

//思路：对于某一个数字，如果我们知道其前面所有数字的乘积，同时也知道后面所有的数乘积，
//那么二者相乘就是我们要的结果，所以我们只要分别创建出这两个数组即可，
//分别从数组的两个方向遍历就可以分别创建出乘积累积数组。

//思路2:可以对上面的方法进行空间上的优化，由于最终的结果都是要乘到结果 res 中，
//所以可以不用单独的数组来保存乘积，而是直接累积到结果 res 中，我们先从前面遍历一遍，
//将乘积的累积存入结果 res 中，然后从后面开始遍历，用到一个临时变量 right，初始化为1，
//然后每次不断累积，最终得到正确结果
public class ProductofArrayExceptSelf_238 {
public int[] productExceptSelf(int[] nums) {
	
	
	int n = nums.length;
        int[] res = new int[n];//保存最终结果
      //解法1
//        int[] foward = new int[n];//从前往后计算位于索引i时，前i+1个数的累乘值
//        foward[0] = 1;
//        int[] backward = new int[n];//从后往前计算位于索引i时，后n-i-2个数的累乘值
//        backward[n-1] = 1;
//        for (int i = 1; i <n; i++) {
//			foward[i] = foward[i-1]*nums[i-1];
//		}
//        
//        for (int i = n -2; i >= 0; i--) {
//			backward[i] = backward[i+1]*nums[i+1];
//		}
//        
//        for (int i = 0; i < n; i++) {
//			res[i] = foward[i]*backward[i];
//		}
        
        //解法2
        int backward = 1;//计算从后往前的累乘值
        res[0] = 1;
        for (int i = 1; i < n; i++) {//从前往后计算累乘值
			res[i] = res[i-1]*nums[i-1];
		}
        for (int i = n - 2; i >=0 ; i--) {
        	backward *= nums[i+1];
			res[i] = backward*res[i];
		}
        return res;
    }
}
