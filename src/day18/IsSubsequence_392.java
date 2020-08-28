package day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string s and a string t, check if s is subsequence of t.

A subsequence of a string is a new string which is formed from the original string by 
deleting some (can be none) of the characters without disturbing the relative positions 
of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to 
check one by one to see if T has its subsequence. In this scenario, how would you change 
your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

 

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true


Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:

0 <= s.length <= 100
0 <= t.length <= 10^4
Both strings consists only of lowercase characters.
 * */

//我的思路：双指针，分别指向s和t，如果指向的字符相等则同时后移，否则后移指向t的指针，最后看指向s的指针是否等于s.len

//题目中的 Follow up 说如果有大量的字符串s，问我们如何进行优化。那么既然字符串t始终保持不变，我们就可以在t上做
//一些文章。子序列虽然不需要是连着的子串，但是字符之间的顺序是需要的，那么我们可以建立字符串t中的每个字符跟其位置
//直接的映射，由于t中可能会出现重复字符，所以把相同的字符出现的所有位置按顺序加到一个数组中，所以就是用
//HashMap 来建立每个字符和其位置数组之间的映射。然后遍历字符串s中的每个字符，对于每个遍历到的字符c，
//我们到 HashMap 中的对应的字符数组中去搜索，由于位置数组是有序的，我们使用二分搜索来加快搜索速度，
//这里需要注意的是，由于子序列是有顺序要求的，所以需要一个变量 pre 来记录当前匹配到t字符串中的位置，
//对于当前s串中的字符c，即便在t串中存在，但是若其在位置 pre 之前，也是不能匹配的。所以我们可以使用 
//二分查找来找到第一个大于 pre 的位置，若不存在，直接返回 false，否则将 pre 更新为二分查找的结果并继续循环即可
public class IsSubsequence_392 {
public boolean isSubsequence(String s, String t) {
        if(s.length() > t.length())return false;
        
        //解法1
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()) {
        	if(s.charAt(i) == t.charAt(j)) {
        		i++;
        		j++;
        	}else {
        		j++;
        	}
        }
        
        return i == s.length();
        
        //follow up解法1
        
//        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
//      //用map保存每个出现在t里的字符的位置
//        for (int i = 0; i < t.length(); i++) {
//        	List<Integer> temp = map.containsKey(t.charAt(i))?map.get(t.charAt(i)): new ArrayList<Integer>();
//        	temp.add(i);
//        	map.put(t.charAt(i), temp);
//		}
//        
//        int pre = -1;//记录s中遍历到的当前字符的前一个字符的位置（在t中）
//        for (int i = 0; i < s.length(); i++) {
//        	//在s中字符不在t中，直接返回false
//			if(!map.containsKey(s.charAt(i)))return false;
//			else {//在s中字符在t中
//				//拿出该字符在t中出现位置的数组，用二分法查找第一个大于前一个字符位置的值(pre)
//				List<Integer> temp = map.get(s.charAt(i));
//				int l = 0, r = temp.size() - 1;
//				while(l < r) {
//					
//				}
//				
//			}
//		}
        
        
        //follow up解法2
     // 预处理
//        t = " " + t; // 开头加一个空字符作为匹配入口
//        int n = t.length();
//        int[][] dp = new int[n][26]; // 记录t中每个字符后面的所有字符的对应位置
//        for (char ch = 0; ch < 26; ch++) {
//            int p = -1;
//            for (int i = n - 1; i >= 0; i--) { // 从后往前记录dp
//                dp[i][ch] = p;
//                if (t.charAt(i) == ch + 'a') p = i;
//            }
//        }
//        // 匹配
//        int i = 0;
//        for (char ch : s.toCharArray()) { // 跳跃遍历
//            i = dp[i][ch - 'a'];
//            if (i == -1) return false;
//        }
//        return true;

    }


}
