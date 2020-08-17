package day11;

/*
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 *  return the length of last word (last word means the last appearing word if we loop 
 *  from left to right) in the string.

If the last word does not exist, return 0.

Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 * */

//我的思路：将字符串以空格分割成数组

//思路2:从字符串尾部开始，过滤掉空格，找到第一个非空格的字符串，统计它的长度
public class LengthofLastWord_58 {
public int lengthOfLastWord(String s) {
        if(s.length() == 0 || s == null)return 0;
        //解法1
//        String[] reStrings = s.split("\\s+");
//        if(reStrings.length == 0)return 0;
//        
//        return reStrings[reStrings.length - 1].length();
        
        //解法2
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;//去除首尾空格,找到最后一个空格出现的位置
        //它所在位置+1即为前面所有包含空格在内的子字符串的长度，用去除首尾空格后的字符串总长度减去前面的长度，
        //即为所求
        
    }
}
