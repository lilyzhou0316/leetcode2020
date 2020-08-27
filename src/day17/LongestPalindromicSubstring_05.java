package day17;


/*
 * Given a string s, find the longest palindromic substring in s. You may assume that 
 * the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.


Example 2:

Input: "cbbd"
Output: "bb"
 * */

//思路：双指针，以s的每个字符为中心点（奇数为单个字符，偶数为两个字符）向左右扩展，看能得到的最长回文子串是多少
public class LongestPalindromicSubstring_05 {
	public String longestPalindrome(String s) {
		if(s.length() < 2)return s;
		
		
		String res = "";//记录最长回文子串
		
		int len = s.length();
		for (int i = 0; i < len; i++) {
			//要考虑s长度的奇偶性
			String s1 = maxPalindrome(s,i,i);//odd
			String s2 = maxPalindrome(s, i, i+1);//even
			if(Math.max(s1.length(), s2.length()) > res.length())
			res = s1.length() > s2.length()? s1 : s2;
		}
		return res;
	}
	
	public String maxPalindrome(String str, int l, int r) {
		
		while(l >= 0 && r <= str.length() - 1 && str.charAt(l) == str.charAt(r)) {
			l--;
			r++;
		}
		//出循环时l,r上的字符一定不相等（或者是超出边界了），因为取子串包含开头不包含结尾，所以要用l+1
		return str.substring(l+1,r);
	}
}
