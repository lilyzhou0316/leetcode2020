package day20;


/*
 * Given a positive integer num, write a function which returns True if num is a perfect square 
 * else False.

Follow up: Do not use any built-in library function such as sqrt.

 

Example 1:

Input: num = 16
Output: true


Example 2:

Input: num = 14
Output: false
 

Constraints:

1 <= num <= 2^31 - 1
 * */

//思路：类似题69找平方根
public class ValidPerfectSquare_367 {
public boolean isPerfectSquare(int num) {
	 if(num < 2)return true;
     
     long l = 1;
    long r = num;
    while(l <= r) {
    	long mid = l + (r - l)/2;
        long guessSquare = mid * mid;
    	if(guessSquare == num)return true;
    	else if(guessSquare > num)r = mid - 1;
    	else l = mid + 1;
    }
    return false;
    }
}
