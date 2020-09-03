package day22;

/*
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers 
 * less than or equal to n.

Example:

Input: 13
Output: 6 
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 * */

//思路：见截图。



public class NumberofDigitOne_233 {
public int countDigitOne(int n) {
	 int num = n;
	    long i = 1;
	    int s = 0;

	    while(num > 0)            
	    { 
	        if(num % 10 == 0) // 不包含1 -9 
	            s += (num / 10) * i; // 修正值是 0
	        
	        if(num % 10 == 1) // 包含 1 - 9的一部分
	            s += (num / 10) * i + (n % i) + 1; // 修正值是(n % i) + 1
	        
	        if(num % 10 > 1) // 包含1 - 9
	            s += (num / 10) * i + i; // i   
	     
	        num = num / 10; // 比如109/10 = 10， 可以按10位的处理，因为i增量了10倍
	        i = i * 10; // 每次1的个数是增加10倍
	    }   

	    return s;


    }
}
