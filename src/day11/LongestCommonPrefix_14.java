package day11;

import day05.increasingTripletSubsequence_334;

/*
 * Write a function to find the longest common prefix string among an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"


Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Note:

All given inputs are in lowercase letters a-z.
 * */


//思路1:找长度最短的那个元素作为子字符串，从它的整个字符串开始与其它剩下的元素对比看是否包含，如果不包含，减一位再对比
public class LongestCommonPrefix_14 {
public String longestCommonPrefix(String[] strs) {
	if(strs.length == 0 || strs == null)return "";
	if(strs.length == 1)return strs[0];
	
        String minLenStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
        	//找出最短长度的元素
			if(minLenStr.length() > strs[i].length())minLenStr = strs[i];
		}
        
        //将最短字符串与其它字符串比较，看是否包含最短字符串,且位置在开头
       while(minLenStr != "") {
    	   int i;
    	   for ( i = 0; i < strs.length; i++) {
			if (strs[i].indexOf(minLenStr) != 0) {
				minLenStr = minLenStr.substring(0,minLenStr.length()-1);
				break;
			}
		} 
    	   if(i == strs.length)return minLenStr;
       }
       
       return "";
    }
}
