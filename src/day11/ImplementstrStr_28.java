package day11;

/*
 * Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 
if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2


Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1


Clarification:

What should we return when needle is an empty string? This is a great question 
to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. 
This is consistent to C's strstr() and Java's indexOf().

Constraints:

haystack and needle consist only of lowercase English characters.
 * */

//思路1：暴力解法，两层遍历,超出时间限制
//思路2:用substring判断

public class ImplementstrStr_28 {
public int strStr(String haystack, String needle) {
	 if(needle.length() == 0)return 0;
     if(needle.length() > haystack.length())return -1;
    
     //解法1:
//    for (int i = 0; ; i++) {//i遍历haystack,并且一旦找到相等的第一个元素i就保持那个位置不变,即为起始位置
// 	   for (int j = 0; ; j++) {
//			if(j == needle.length())return i;
//			if(i+j == haystack.length())return -1;//遍历完了haystack还是没有找到相等的第一个元素
//			if(needle.charAt(j) != haystack.charAt(i+j))break;
//		}	
//	}
     
     //解法2:
     for (int i = 0; i < haystack.length() - needle.length()+1; i++) {
		if(needle.equals(haystack.substring(i,needle.length()+i)))return i;
	 }
     return -1;
    }
}
