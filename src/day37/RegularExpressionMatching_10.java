package day37;

import javax.swing.text.AbstractDocument.LeafElement;

/*
 * Given an input string (s) and a pattern (p), implement regular expression matching with 
 * support for '.' and '*' where: 

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 
'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches
 "aab".
Example 5:

Input: s = "mississippi", p = "mis*is*p*."
Output: false
 

Constraints:

0 <= s.length <= 20
0 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
 * */

//思路：用 DP 来解，定义一个二维的 DP 数组，其中 dp[i][j] 表示 s[0,i) 和 p[0,j) 是否 match，然后有下面
//三种情况：

//1.  P[i][j] = P[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
//2.  P[i][j] = P[i][j - 2], if p[j - 1] == '' and the pattern repeats for 0 times;
//3.  P[i][j] = P[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*' and the pattern repeats for at least 1 times.

//思路2:这道求正则表达式匹配的题和那道 Wildcard Matching 的题很类似，不同点在于的意义不同，在之前那道题中，
//表示可以代替任意个数的字符，而这道题中的表示之前那个字符可以有0个，1个或是多个，就是说，字符串 ab，可以表示
//b或是 aaab，即a的个数任意，这道题的难度要相对之前那一道大一些，分的情况的要复杂一些，需要用递归 
//Recursion 来解，大概思路如下：

//- 若p为空，若s也为空，返回 true，反之返回 false。

//- 若p的长度为1，若s长度也为1，且相同或是p为 '.' 则返回 true，反之返回 false。

//- 若p的第二个字符不为*，若此时s为空返回 false，否则判断首字符是否匹配，且从各自的第二个字符开始调用
//递归函数匹配。

//- 若p的第二个字符为*，进行下列循环，条件是若s不为空且首字符匹配（包括 p[0] 为点），调用递归函数匹配s和
//去掉前两个字符的p（这样做的原因是假设此时的星号的作用是让前面的字符出现0次，验证是否匹配），若匹配返回 
//true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，
//所以不需要去掉），继续进行循环。

//- 返回调用递归函数匹配s和去掉前两个字符的p的结果（这么做的原因是处理星号无法匹配的内容，比如
//s="ab", p="ab"，直接进入 while 循环后，我们发现 "ab" 和 "b" 不匹配，所以s变成 "b"，那么此时跳出循环
//后，就到最后的 return 来比较 "b" 和 "b" 了，返回 true。再举个例子，比如 s="", p="a"，由于s为空，
//不会进入任何的 if 和 while，只能到最后的 return 来比较了，返回 true，正确）。
public class RegularExpressionMatching_10 {
	public boolean isMatch(String s, String p) {
		//解法1
		 //dp[i][j]表示s的前i个字符和p的前j个字符是match的
//	       int m = s.length(), n = p.length();
//	        boolean[][] dp = new boolean[m + 1][n + 1];
//	        dp[0][0] = true;
//	        
		//当s为空，p不为空时，如果p的奇数索引上的字符为*，且前面的子串为true，则为true
//	        for (int j = 1; j < n; j++) {
//	            //从p的第二个字符开始，如果它为*则可消去它前面位置的任意字符（出现次数为0或多次）
//	            //如a*b* 还是与""匹配
//	            //初始化s为空字符串的情况
//	            if (p.charAt(j) == '*' && dp[0][j - 1]) {
//	                dp[0][j+1] = true;
//	            }
//	        }
//	        
//	        //对两个字符串进行一一对比
//	        for (int i = 0; i < m; i++) {
//	            for (int j = 0; j < n; j++) {
//	                //如果p当前字符为.  或者p当前字符和s当前字符相同，则看前面的是否也匹配
//	                if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) dp[i + 1][j + 1] = dp[i][j];
//	                //如果p当前字符为*
//	                if (j > 0 && p.charAt(j) == '*') {
//	                    //则看p的前一个字符，如果前一个字符与s的当前字符相同或者前一个字符是.
//	                    if (p.charAt(j - 1) == '.' || s.charAt(i) == p.charAt(j - 1)) {
//	                        //则看p当前字符的之前两个字符及之前的子串是否与当前s匹配
//	                        //或者s当前字符的前一个字符及其子串是否与当前p匹配
//							dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i][j + 1]; 
//	                    } else {
//	                        dp[i + 1][j + 1] = dp[i + 1][j - 1];  
//	                    }
//	                }
//	            }
//	        }
//	        return dp[m][n];
		
		//解法2
		//若p为空，若s也为空，返回 true，反之返回 false
		 if (p.length() == 0) return s.length() == 0;
		 //若p的长度为1，若s长度也为1，且相同或是p为 '.' 则返回 true，反之返回 false。
	      if (p.length() == 1) {
	            return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
	        }
	        //长度大于1时，看p的第二个字符
	        
	        //若当前p的第二个字符不为*，若此时s为空返回 false，
	        //否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配。
	       if (p.charAt(1) != '*') {
	            if (s.length() == 0) return false;
	            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
	        }
	        //若当前p的第二个字符为*,且两者第一个字符匹配
	        while (s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
	        	//假设此时的星号的作用是让前面的字符出现0次，验证是否匹配,如abc与a*abc
	            if (isMatch(s, p.substring(2))) return true;
	            //去掉s中开头与p开头相同的多个字符，如aaabc,a*bc
	            s = s.substring(1);
	        }
	        //比较剩下的s和p
	        return isMatch(s, p.substring(2));
	}
}
