package day22;

import java.util.HashSet;
import java.util.Set;

/*
 * Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:

Input: 6
Output: true
Explanation: 6 = 2 × 3


Example 2:

Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2


Example 3:

Input: 14
Output: false 
Explanation: 14 is not ugly since it includes another prime factor 7.
Note:

1 is typically treated as an ugly number.
Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * */

//我的思路：类似题202
public class UglyNumber_263 {
public boolean isUgly(int num) {
        if(num == 1)return true;
        //解法1
//        Set<Integer> s = new HashSet<Integer>();
//        s.add(num);
//        while(num > 1) {
//        	if(num % 2 == 0) {
//        		num = num / 2;
//        	}
//        	if(num % 3 == 0) {
//        		num = num / 3;
//        	}
//        	if(num % 5 == 0) {
//        		num = num / 5;
//        	}
//        	
//        	if(num == 1)return true;
//        	else if(!s.add(num))return false;
//        	else s.add(num);
//        }
//        return num == 1;
        
       //解法2:快慢指针
        int slow = num, fast = num;
        while(true) {
        	slow = helper(slow);
        	fast = helper(fast);
        	fast = helper(fast);
        	if(slow == fast)break;
        }
        return slow == 1;
    }

public int helper(int n) {
    	if(n % 2 == 0) {
    		n = n / 2;
    	}
    	if(n % 3 == 0) {
    		n = n / 3;
    	}
    	if(n % 5 == 0) {
    		n = n / 5;
    	}
    	return n;
}
}
