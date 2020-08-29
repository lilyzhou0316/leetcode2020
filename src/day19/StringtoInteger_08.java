package day19;


/*
 * Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace
 character is found. Then, starting from this character, takes an optional initial plus or minus sign 
 followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, 
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such 
sequence exists because either str is empty or it contains only whitespace characters, no conversion
 is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit 
signed integer range: [−2^31,  2^31 − 1]. If the numerical value is out of the range of representable
 values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.


Example 1:

Input: "42"
Output: 42


Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
             
             
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.


Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
             
             
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
 * */

//思路：先去掉开头的所有空格，然后如果碰到正负号用一个变量保存起来，如果第一个读到的非空格和负号的字符不是数字，则直接返回0
//否则，读取连续的数字直到下一个读到的不是数字为止，然后将该数字转换成int并加上符号，超出范围的值则返回int的最大最小值
public class StringtoInteger_08 {
	 public int myAtoi(String str) {
		 if(str.length() == 0 || str == null)return 0;
		 
		 StringBuilder s = new StringBuilder(str);
		 int i = 0;//遍历s
		 boolean signed = false;//true表示符号为负号
		 while(i < s.length() && s.charAt(i) == ' ')i++;//跳过空格
		 
		  if(i < s.length() && s.charAt(i) == '-') {//读取符号
			 signed = true;
			 i++;
		 }else if(i < s.length() && s.charAt(i) == '+') {
			 i++;
		 }
		  
		 if(i < s.length() && ((s.charAt(i) - '0') > 9 || (s.charAt(i) - '0') < 0)) {
			 //读取到的是非数字非空格非符号的字符
			 return 0;
		 }
		  
		  if(i == s.length())return 0 ;//除了符号和空格就没有其它字符了
		  
		  //空格，符号都读完后，第一个读到的是数字
		  String temp = "";
		  long res = 0;
		  while(i < s.length() && ((s.charAt(i) - '0') <= 9 && (s.charAt(i) - '0') >= 0)) {
			  temp += str.charAt(i);
			  res = Long.parseLong(temp);
			 //检查res是否超出int范围
			  if((!signed && res > Integer.MAX_VALUE) || (signed && -res <  Integer.MIN_VALUE)) {
				  return signed?Integer.MIN_VALUE:Integer.MAX_VALUE;
			  }
			  i++;
		  }
		  res = signed?-res:res;
		 return  (int)res;
		 
	 }
}
