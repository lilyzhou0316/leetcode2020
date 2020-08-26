package day16;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 


Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.


Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not 
             a substring.
 * */

//我的思路：sliding window
public class LongestSubstringWithoutRepeatingCharacters_03 {
	 public int lengthOfLongestSubstring(String s) {
		 
		 
		 if(s.length() == 0 || s == null)return 0;
		 if(s.length() == 1)return 1;
		
		 int max = 0;//记录最长子串长度
		 
		//解法1
//		 StringBuilder str = new StringBuilder();
//		 for(int j=0; j < s.length(); j++) {
//			if(str.indexOf(s.charAt(j)+"") < 0) {
//				//当前字符不重复
//				str.append(s.charAt(j));
//				max = Math.max(str.length(), max);
//			}else {
//				//当前字符重复
//				
//				int t = str.indexOf(s.charAt(j)+"");
//				while(t + 1 > 0) {
//					str.deleteCharAt(0);
//					t--;
//				}
//				str.append(s.charAt(j));
//			}
//		 }
		//解法2
		 int n = s.length();
	         int  i = 0, j=0;//i控制出去，j控制进入
	         Set<Character> set = new HashSet<>();
	         while(i<n && j<n){
	             if(! set.contains(s.charAt(j))){//if set not contains the char which j point at,then add it to the set,j plus 1, record current max length 
	                 set.add(s.charAt(j));
	                 j++;
	                 max = Math.max(max,j-i);//max 记录当前最大长度
	             }
	             else{//如果发现重复字母，则开始从set中删除字母直到重复字母不在set中为止
	                 set.remove(s.charAt(i));
	                 i++;
	             }
	         }
		 return max;
		 
		 
	 }
}
