package day19;

import day05.longestConsecutiveSequence_128;

/*
 * Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within 
the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, 
assume that your function returns 0 when the reversed integer overflows.
 * */

//思路：先把int转成string，然后翻转，一位一位取出数字，如果取出的结果超出范围则返回0，用一个变量记录原数字的正负号
public class ReverseInteger_07 {
	 public int reverse(int x) {
//		 boolean signed = x<0? true:false;//为true时为负数
//		 if(x < 0)x = -x;//去掉负号
//		 //int 转str
//		 StringBuilder t = new StringBuilder(x+"").reverse();
//		 long res = 0;
//		 for (int i = 0; i < t.length(); i++) {
//			res = res*10 + Integer.parseInt(t.charAt(i)+"");
//			if((signed == false && res > Integer.MAX_VALUE) || (signed == true && -res < Integer.MIN_VALUE))return 0;
//		}
//		 
//			 if(signed)res = -res;
//
//		 return (int)res;
		 
		 //优化
       int result = 0;
       long temp = 0;
     String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
   try {
       temp = Long.parseLong(reversed);//如果超出long的范围会报错，去到catch
       if(temp<=(Math.pow(2,31) - 1) && temp >=(-Math.pow(2,31))){
            result = (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
           return result;
       }else{//超出int范围 return 0
           return 0;
       }
      
   } catch (NumberFormatException e) {
       return 0;
   }finally{
       return result;
   }
	 }
}
