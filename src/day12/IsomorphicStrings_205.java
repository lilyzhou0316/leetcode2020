package day12;

import java.util.HashMap;
import java.util.Map;

/*
 * Given two strings s and t, determine if they are isomorphic(同构).

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while 
preserving the order of characters. No two characters may map to the same character 
but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true


Example 2:

Input: s = "foo", t = "bar"
Output: false


Example 3:

Input: s = "paper", t = "title"
Output: true


Note:
You may assume both s and t have the same length.
 * */

//思路：用两个 HashMap 分别来记录原字符串和目标字符串中字符出现情况，由于 ASCII 码只有 256 个字符，
//所以可以用一个 256 大小的数组来代替 HashMap，并初始化为0，遍历原字符串，分别从源字符串和目标字符串
//取出一个字符，然后分别在两个数组中查找其值，若不相等，则返回 false，若相等，将其值更新为 i + 1，
//因为默认的值是0，所以更新值为 i + 1，这样当 i=0 时，则映射为1，如果不加1的话，那么就无法区分是否更新了
public class IsomorphicStrings_205 {
public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        
         for (int i = 0; i < s.length(); i++) {
        	 
        	 if(map1[s.charAt(i)] != map2[t.charAt(i)])return false;
        	 map1[s.charAt(i)]++;
        	 map2[t.charAt(i)]++;
		}
        return true;
    }
}
