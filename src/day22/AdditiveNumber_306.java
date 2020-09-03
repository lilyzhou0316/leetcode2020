package day22;

/*
 * Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, 
each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive 
number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 
3 is invalid.

 

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
 

Constraints:

num consists only of digits '0'-'9'.
1 <= num.length <= 35
 * */

//思路:这道题就是问原字符串能否拆成斐波那契数列.这题可用Brute Force的思想来解，我们让第一个数字先从一位开始，
//第二个数字从一位，两位，往高位开始搜索，前两个数字确定了，相加得到第三位数字，三个数组排列起来形成一个字符串，
//和原字符串长度相比，如果小于原长度，那么取出上一次计算的第二个和第三个数，当做新一次计算的前两个数，用相同的方法得到
//第三个数，再加入当前字符串，再和原字符串长度相比，以此类推，直到当前字符串长度不小于原字符串长度，比较两者是否相同，
//相同返回true，不相同则继续循环。如果所有情况都遍历完了还是没有返回true，则说明不是Additive Number，返回false


public class AdditiveNumber_306 {
public boolean isAdditiveNumber(String num) {
        if(num.length() < 3)return false;
        
        int len = num.length();
        for (int i = 1; i < len - 1; i++) {
			String s1 = num.substring(0, i);//截取第一个数字的字符串，长度为1，2，3.....
			if(s1.length() > 1 && s1.charAt(0) == '0')break;//如果第一个数以0开头，则直接跳出循环
			for (int j = i+1; j <= len; j++) {
				String s2 = num.substring(i, j);//截取第二个数的字符串
				if(s2.length() > 1 && s2.charAt(0) == '0')break;//如果第一个数以0开头，则直接跳出当前循环
				//将两个数的字符串转成数字
				long n1 = Long.parseLong(s1);
				long n2 = Long.parseLong(s2);
				long n3 = n1 + n2;//第三个数
				String s3 = n3 + "";
				//如果当前s3不等于num里的前两数之和，则寻找下一个s3
				if((j + s3.length() < len)&& !s3.equals(num.substring(j,j+s3.length())))continue;
				if((j+s3.length() >= len) && !s3.equals(num.substring(j)))continue;
				
				//如果等于，则按照斐波那契数列的特点将当前str的长度补齐到和num一致，看该str是否和原str相等
				String curStr = s1 + s2 +s3;
				
				while(curStr.length() < len) {//补齐curstr的长度
					n1 = n2;
					n2 = n3;
					n3 = n1 + n2;
					curStr += n3;
				}
				
				if(curStr.equals(num))return true;
			}
		}
        //如果出循环，则返回false
        return false;
    }
}
