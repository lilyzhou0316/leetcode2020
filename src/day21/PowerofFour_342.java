package day21;


/*
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true


Example 2:

Input: 5
Output: false


Follow up: Could you solve it without loops/recursion?
 * */

//思路：类似题231，326

//思路2:输入的整数是<= 2^31 -1, 因此最大能取到的4的次方的数是4^15。把所有4的次方结果保存到一个list里，然后看n是否包含在该list里

//思路3:如果数字为 4 的幂 x = 4^a，则 a = log_4 (x) = 1/2 * log_2(x) 应为整数，那么我们检查log2(x) 是否为偶数就能判断 x 是否为 4 的幂。

public class PowerofFour_342 {
	 public boolean isPowerOfFour(int num) {
//	        if(num == 1)return true;
//	        if(num < 1)return false;
//	        
//	        while(num > 1) {
//	        	if(num % 4 != 0)return false;
//	        	num = num / 4;
//	        }
//	        return true;
		 
//		 //follow up
//		 if(num < 1)return false;
//		 double value = Math.log(num) / Math.log(4);
//		 return Math.ceil(value) - value  < 0.000000000001 ||
//		            value - Math.floor(value) < 0.00000000001 ;
		 
		 //思路3
		 return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
	    }
	 
}
