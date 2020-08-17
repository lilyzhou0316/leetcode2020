package day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an arbitrary ransom note string and another string containing letters from 
 * all the magazines, write a function that will return true if the ransom note can be 
 * constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

 

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false


Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false


Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

You may assume that both strings contain only lowercase letters.
 * */

//我的思路：遍历ransomNote的每个字符，看它是否存在于magazine中，且一旦遍历完一个就要从magazine中删除一个（如果存在）

//思路2:用哈希表统计magazine中每个字符出现的次数，然后遍历ransomNote，看map里是否包含当前字符

//思路3:用数组统计ransomNote和magazine中每个字符出现的次数，然后看ransomNote里出现的字符的次数和magazine里的是否一样
public class RansomNote_383 {
	 public boolean canConstruct(String ransomNote, String magazine) {
	        if(ransomNote.length() > magazine.length())return false;
	        if(magazine.length() == 0 && ransomNote.length() == 0)return true;
	        
	        //解法1
//	        List<Character> temp = new ArrayList<>();
//	       for (int i = 0; i < magazine.length(); i++) {
//			temp.add(magazine.charAt(i));
//		}
//	       
//	       int i;
//	        for ( i = 0; i < ransomNote.length(); i++) {
//				if(temp.indexOf(ransomNote.charAt(i)) == -1) {
//					//一旦ransomNote中有一个字符magazine中没有的话，就返回false
//					return false;
//				}else {
//					//magazine中有当前ransomNote中的字符
//					//删除magazine中的这个字符
//					temp.remove(temp.indexOf(ransomNote.charAt(i)));
//				}
//			}
//	        if(i == ransomNote.length())return true;
//	        return false;
	        
	        //解法2:
//	        
//	        Map<Character, Integer> map2 = new HashMap<Character, Integer>();
//	        //统计magazine字符串中每个字符出现的次数
//	       
//	        for (int i = 0; i < magazine.length(); i++) {
//				if (map2.containsKey(magazine.charAt(i))) {
//					int time = map2.get(magazine.charAt(i));
//					map2.put(magazine.charAt(i), time+1);
//				}else {
//					map2.put(magazine.charAt(i), 1);
//				}
//			}
//	        System.out.println(map2);
//	        for (int i = 0; i < ransomNote.length(); i++) {
//				if(map2.containsKey(ransomNote.charAt(i))) {
//					//当map中包含当前字符时，检查出现次数是否为0
//					int time = map2.get(ransomNote.charAt(i));
//					if(time > 0)map2.put(ransomNote.charAt(i), time-1);
//					else return false;
//				}else {
//					//当map中不包含当前字符
//					return false;
//				}
//			}
//	        return true;
	        
	        //解法3
	        int[] count1 = new int[26];
	        int[] count2 = new int[26];
	        for (int i = 0; i < ransomNote.length(); i++) {
				count1[ransomNote.charAt(i) - 'a']++;
			}
	        
	        for (int i = 0; i < magazine.length(); i++) {
				count2[magazine.charAt(i) - 'a']++;
			}
	        
	        for (int i = 0; i < ransomNote.length(); i++) {
				if(count1[ransomNote.charAt(i) - 'a'] > count2[ransomNote.charAt(i) - 'a'])return false;
			}
	        
	        return true;
	    }
}
