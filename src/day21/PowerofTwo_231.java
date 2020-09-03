package day21;


/*
 * Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 2^0 = 1


Example 2:

Input: 16
Output: true
Explanation: 2^4 = 16


Example 3:

Input: 218
Output: false
 * */

//思路：让给的数一直除以2，直到结果小于等于1为止
public class PowerofTwo_231 {
	 public boolean isPowerOfTwo(int n) {
		 if (n == 1) return true;//2^0
		    if (n <= 0) return false;
		    
		    while (n > 1) {
		        if (n % 2 != 0) return false;
		        n = n/2;
		    }
		    
		    return true;
	    }
}
