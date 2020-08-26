package day15;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string S and a string T, find the minimum window in S which will contain all
 *  the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique 
minimum window in S.
 * */


//思路：使用 HashMap或者数组，建立T中每个字母与其出现次数之间的映射，遍历S串，对于S中的每个遍历到的字母，
//都在 HashMap 中的映射值减1，如果减1后的映射值仍大于等于0，说明当前遍历到的字母是T串中的字母，
//使用一个计数器 cnt，使其自增1。当 cnt 和T串字母个数相等时，说明此时的窗口已经包含了T串中的所有字母，
//此时更新一个 minLen 和结果 res，这里的 minLen 是一个全局变量，用来记录出现过的包含T串所有字母的
//最短的子串的长度，结果 res 就是这个最短的子串。然后开始收缩左边界，由于遍历的时候，对映射值减了1，
//所以此时去除字母的时候，就要把减去的1加回来，此时如果加1后的值大于0了，说明此时少了一个T中的字母，
//那么 cnt 值就要减1了，然后移动左边界 left。对于不在T串中的字母，减1后，变-1，cnt 不会增加，之后收缩左边界的时候，
//映射值加1后为0，cnt 也不会减少，所以并没有什么影响啦
public class MinimumWindowSubstring_76 {
	 public String minWindow(String s, String t) {		 
       if(s.length() == 0 || s == null || t.length() == 0 || t == null) return "";
       if(t.length() > s.length())return "";
      
       char[] sArray = s.toCharArray();
       char[] tArray = t.toCharArray();
      
       int[] frequency = new int[128];//record each char's frequency in t string
       for(char c:tArray){
    	   if(!s.contains(c+""))return "";
           frequency[c]++;
       }
       int currentLen = t.length();//record t string's length, each time find a char in both s and t, currentlen minus 1

       int left = 0, right = Integer.MAX_VALUE;//record min len of target substring 
       //iterate each char in s tring
       for(int l=0,r=0;r<s.length();r++){//window的左右边界初始化
           if(frequency[sArray[r]] > 0){
              //check if current char of s also in t string, if true, currentlen-1;
               currentLen--;
           }
           frequency[sArray[r]]--;//在t里的char的frequency减1，不在t里在s里的char的frequency变成-1；
          
           while(currentLen == 0){
        	   //find out all chars of t string, may contain extra chars in front of the target string
               if(right - left > (r-l)) {
                   left = l;
                   right= r;
               }
               ++frequency[sArray[l]];//前面给所有的char的frequency都减1了，现在加回来
               if(frequency[sArray[l]] > 0){//如果l对应的字符是t里的，则把currentLen加1
                   //l：跳过所有不在t里的char,如果是t里的char,它的frequency>0；
                   //r:往后找看是否还有l对应的char出现
                   currentLen++; 
               }
               //移动左边界
               l++;
           }
       }
      
      return right==Integer.MAX_VALUE? "":s.substring(left,right+1);
		 
	 }
}
