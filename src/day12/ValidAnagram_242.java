package day12;

import java.util.Arrays;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/*
 * Given two strings s and t , write a function to determine if t is an anagram(同字母异序词)
 *  of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true


Example 2:

Input: s = "rat", t = "car"
Output: false


Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your
 solution to such case?
 * */

//我的思路：与题205基本一直，看两个string里出现的字母次数是否一样，用两个数组表示两个string里出现的字母的次数
//对Follow up： 将数组范围扩大至256

//思路2:对两个string排序，然后比较是否相等
public class ValidAnagram_242 {
public boolean isAnagram(String s, String t) {
	 if(s.length() != t.length())return false;
     //解法1:
//     int[] arr1 = new int[26];
//     int[] arr2 = new int[26];
//     
//     for (int i = 0; i < s.length(); i++) {
//     	arr1[s.charAt(i) - 'a']++;
//			arr2[t.charAt(i) - 'a']++;
//		}
//     
//     for (int i = 0; i < arr1.length; i++) {
//			if(arr1[i] != arr2[i])return false;
//		}
//     return true;
     
     //解法2
	 char[] temp1 = s.toCharArray();
	 char[] temp2 = t.toCharArray();
	 Arrays.sort(temp1);
	 Arrays.sort(temp2);
	 return (new String(temp1).equals(new String(temp2)));
    }
}
