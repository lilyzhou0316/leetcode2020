package day13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given a string which contains only lowercase letters, remove duplicate letters 
 * so that every letter appears once and only once. You must make sure your result 
 * is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"


Example 2:

Input: "cbacdcbc"
Output: "acdb"

Note: This question is the same as 1081: 
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * */

//思路：先建立一个哈希表来统计每个字母出现的次数，还需要一个visited数字来纪录每个字母是否被访问过，
//我们遍历整个字符串，对于遍历到的字符，先在哈希表中将其值减一，然后看visited中是否被访问过，
//若访问过则继续循环，说明该字母已经出现在结果中并且位置已经安排妥当。如果没访问过，
//我们和结果中最后一个字母比较，如果该字母的ASCII码小并且结果中的最后一个字母在哈希表中的值
//不为0(说明后面还会出现这个字母)，那么我们此时就要在结果中删去最后一个字母且将其标记为未访问，
//然后加上当前遍历到的字母，并且将其标记为已访问，以此类推直至遍历完整个字符串s，
//此时结果里的字符串即为所求。这里有个小技巧，我们一开始给结果字符串res中放个"0"，就是为了在第一次比较时方便，
//如果为空就没法和res中的最后一个字符比较了，而‘0’的ASCII码要小于任意一个字母的，所以不会有问题。
//最后我们返回结果时再去掉开头那个‘0’即可
public class RemoveDuplicateLetters_316 {
public String removeDuplicateLetters(String s) {
        if(s.length() <= 1)return s;
        
        StringBuilder res = new StringBuilder();
        res.append("0");//记录结果，为了在第一次比较时不为空，初始化为“0”（因为任意字母的ascii值都大于0），最后删除开头的0即可
        int[] visited = new int[26];//用于标记当前字母是否已经在结果里出现了
        
        //记录原字符串中每个字母出现的次数
        int []frequency = new int[26];
        for(char ch:s.toCharArray())
            frequency[ch-'a']++;
        
        //遍历原字符串
        for (char ch:s.toCharArray()) {
        	//让当前字母的次数减1
        	frequency[ch - 'a']--;
			//如果当前字母已经访问过（已经在结果中出现过），则跳过
			if(visited[ch - 'a'] == 1)continue;
			
			//如果当前字母是否比结果中的最后一个字母的自然顺序小，如果是，且最后一个字母在map中
	        //的剩余次数大于0， 则将当前字母替换掉结果里的最后一个字母，并将map里的次数减1，最后把当前字母标记为
	        //已访问，最后一个字母还原为未访问
			while(ch < res.charAt(res.length() - 1) && frequency[res.charAt(res.length() - 1) - 'a'] > 0) {
				visited[res.charAt(res.length() - 1) - 'a'] = 0;
				res.deleteCharAt(res.length() - 1);
				
			}
			
			res.append(ch);
			visited[ch - 'a'] = 1;
		}
        
        //删除开头的0
        return res.toString().substring(1);
        
		}
        
    }

