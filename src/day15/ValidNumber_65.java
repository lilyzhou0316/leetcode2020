package day15;

/*
 * Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all 
requirements up front before implementing one. However, here is a list of characters that 
can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function 
\signature accepts a const char * argument, please click the reload button to reset your 
code definition.
 * */

//所有的字符可以分为六大类，空格，符号，数字，小数点，自然底数和其他字符，我们需要五个标志变量，
//num, dot, exp, sign分别表示数字，小数点，自然底数和符号（正负号）是否出现，numAfterE表示自然底数后面是否有数字，
//那么我们分别来看各种情况：

//- 空格： 我们需要排除的情况是，当前位置是空格而后面一位不为空格，但是之前有数字，小数点，
//自然底数或者符号出现时返回false。(即空格出现在中间)

//- 符号：符号前面如果有字符的话必须是空格或者是自然底数，标记sign为true。

//- 数字：标记num，numAfterE为true。

//- 小数点：如果之前出现过小数点或者自然底数，返回false，否则标记dot为true。

//- 自然底数：如果之前出现过自然底数或者之前从未出现过数字，返回false，否则标记exp为true，numAfterE为false。

//- 其他字符：返回false。

//最后返回num && numAfterE即可。

public class ValidNumber_65 {
	 public boolean isNumber(String s) {
		 boolean num = false, numAfterE = true, dot = false, exp = false, sign = false;
	        int n = s.length();
	        for (int i = 0; i < n; ++i) {
	            if (s.charAt(i) == ' ') {
	            	//空格前后都有字符时，返回false
	                if (i < n - 1 && s.charAt(i+1) != ' ' && (num || dot || exp || sign)) return false;
	            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
	            	//如果正负号不是出现在首尾，且前面不是e或者空格，返回false
	                if (i > 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != ' ') return false;
	                sign = true;
	            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
	            	//如果当前字符为0-9的数字
	                num = true;
	                numAfterE = true;
	            } else if (s.charAt(i) == '.') {
	            	//如果当前字符为. 但前面已经出现了.或者e，返回false
	                if (dot || exp) return false;
	                dot = true;
	            } else if (s.charAt(i) == 'e') {
	            	//如果当前字符为e，而前面已经出现了e或者前面没有数字出现，返回false
	                if (exp || !num) return false;
	                exp = true;
	                numAfterE = false;
	            } else return false;//其它非法字符直接返回false
	        }
	        return num && numAfterE; 
	    }
}
