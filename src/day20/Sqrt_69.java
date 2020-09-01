package day20;


/*
 * Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part 
of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
 * */

//思路：因为一个数的平方根一定是小于等于它本身值的一半的，所以用二分法查找1到x，每次找中位数，直到找到第一个符合的值
public class Sqrt_69 {
	 public int mySqrt(int x) {
		 if(x == 1 || x == 0)return x;
		
         int l = 1;
        int r = x;
        long mid;//注意mid有可能超出int范围
        
        while( l <= r){
            mid = ((long)l+(long)r)/2;
            
            if( mid * mid == x) return (int)mid;
            else if ( mid* mid > x)
                r = (int)mid-1;
            else
                l = (int)mid+1;
        }
        
        return r;
	 }
}
