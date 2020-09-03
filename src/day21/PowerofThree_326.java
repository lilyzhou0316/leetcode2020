package day21;


/*
 * Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true


Example 2:

Input: 0
Output: false


Example 3:

Input: 9
Output: true


Example 4:

Input: 45
Output: false


Follow up:
Could you do it without using any loop / recursion?
 * */

//思路：类似题231

//follow up:This method using Math.log method in java to check if the number is a power of 3 by 
//using Math.log(INPUT)/Math.log(3). This method uses double, so there can be rounding errors, 
//we use epsilon to ignore minor diff in double value.
public class PowerofThree_326 {
public boolean isPowerOfThree(int n) {
//        if(n == 1)return true;//3^0
//        if(n < 1)return false;
//        
//        while(n > 1) {
//        	if(n % 3 != 0)return false;
//        	n = n / 3;
//        }
//        return true;
	
	//follow up:
	
	 if(n == 0) {
         return false;
     }
     double logValue = Math.log(n)/Math.log(3);//Math.log(n)/Math.log(3)，如果n是3的x次方，则结果应该是x.0
     return Math.ceil(logValue) - logValue < 0.000000000001 ||
            logValue - Math.floor(logValue) < 0.00000000001 ;
    }


public static void main(String[] args) {
	System.out.println(Math.log(81)/Math.log(3));
	
}
}
