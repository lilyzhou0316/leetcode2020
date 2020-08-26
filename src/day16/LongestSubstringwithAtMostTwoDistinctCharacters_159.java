package day16;

/*
 * Given a string  s  , find the length of the longest substring  t that contains at
 *  most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: _t_ is "ece" which its length is 3.


Example 2:

Input: "ccaabbb"
Output: 5
Explanation: _t_ is "aabbb" which its length is 5.
 * */

//思路：sliding window
public class LongestSubstringwithAtMostTwoDistinctCharacters_159 {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s.length() <= 2)return s.length();
		
		int max = 0;
		StringBuilder str = new StringBuilder();
		int count = 0;//记录子串里有几个不同字母
		int i = 0;
		while(i<s.length()) {
			if(str.length() == 0 || (count < 2 && str.indexOf(s.charAt(i)+"") < 0)) {
				//str为空，或者str不包含当前字符且count<2时，直接加入str,并让count加1
				str.append(s.charAt(i));
				count++;
				max = Math.max(max, str.length());
				i++;
			}else if(count <= 2 && str.indexOf(s.charAt(i)+"") >= 0) {
				//如果str已经有当前字符了，且count <= 2，则直接加入
				str.append(s.charAt(i));
				max = Math.max(max, str.length());
				i++;
			}else {
				//其它情况，则需要从左边开始删除str中的字符
				str.deleteCharAt(0);
			}
		}
		return max;
	}
}
