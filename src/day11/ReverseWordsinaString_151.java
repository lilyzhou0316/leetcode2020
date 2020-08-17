package day11;

import java.util.Arrays;
import java.util.Collections;

/*
 * Given an input string, reverse the string word by word.

 

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"


Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.


Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space 
in the reversed string.
 

Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain 
leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Follow up:

For C programmers, try to solve it in-place in O(1) extra space.
 * */

//我的思路：以空格将字符串分割成一个数组，然后再反转数组即可

//思路2:直接利用list的工具类函数
public class ReverseWordsinaString_151 {
public String reverseWords(String s) {
        if(s.length() == 0 || s == null)return "";
        //解法1
//        String[] tempString = s.split("\\s+");
//        if(tempString.length == 1)return tempString[0];
//        
//        String res = "";
//        //从末尾开始提取数组中的单词
//        for (int i = tempString.length - 1; i >= 0 ; i--) {
//			res += tempString[i] + " ";
//		}
//        return res.trim();
        
        //解法2
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
