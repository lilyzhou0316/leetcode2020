package day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/*
 * Given a list of unique words, find all pairs of distinct indices (i, j) in 
 * the given list, so that the concatenation of the two words, i.e. words[i] + words[j]
 *  is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]


Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
 * */


//思路：用到哈希表来建立每个单词和其位置的映射，然后需要一个set来保存出现过的单词的长度，算法的思想是，
//遍历单词集，对于遍历到的单词，我们对其翻转一下，然后在哈希表查找翻转后的字符串是否存在，注意不能和原字符串
//的坐标位置相同，因为有可能一个单词翻转后和原单词相等，现在我们只是处理了bat和tab的情况，还存在abcd和cba，
//dcb和abcd这些情况需要考虑，这就是我们为啥需要用set，由于set是自动排序的，我们可以找到当前单词长度在set
//中的iterator，然后从开头开始遍历set，遍历比当前单词小的长度，比如abcdd翻转后为ddcba，我们发现set中有
//长度为3的单词，然后我们dd是否为回文串，若是，再看cba是否存在于哈希表，若存在，则说明abcdd和cba是回文对，
//存入结果中，对于dcb和aabcd这类的情况也是同样处理，我们要在set里找的字符串要在遍历到的字符串的左边和右边
//分别尝试，看是否是回文对，这样遍历完单词集，就能得到所有的回文对
public class PalindromePairs_336 {
	 public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> res = new ArrayList<List<Integer>>();
		        if(words.length == 0 || words == null)return res;
		        
		        Map<String, Integer> map = new HashMap<String, Integer>();
		        Set<Integer> set  = new TreeSet<>();//会自动排序
		        for (int i = 0; i < words.length; i++) {
					//因为无重复单词，所以直接放入map即可
		        	map.put(words[i], i);
		        	set.add(words[i].length());
				}
		        
		        for (int i = 0; i < words.length; i++) {
		        	//把当前单词翻转
					String temp = new StringBuilder().append(words[i]).reverse().toString();
					//1.如果翻转后的单词出现在map里，且为不同index的单词，则符合条件
					if(map.containsKey(temp) && map.get(temp) != i) {//即bat和tab的情况
						List<Integer> t = new ArrayList<Integer>();
						t.add(i);
						t.add(map.get(temp));
						res.add(t);
						
					}
					//2.不是第一种情况，则看翻转后的单词的某一部分是否为回文，且剩下的部分在map里出现了，则也满足条件
					for (Integer n : set) {
		                
		                
						//当前单词从0-长度n是回文，且剩下部分翻转后在map里出现了
						if(n < words[i].length() && isPalindrome(words[i], 0, words[i].length() - n -1) 
								&& map.containsKey(new StringBuilder().append(words[i].substring(words[i].length() - n)).reverse().toString())) {
							List<Integer> t = new ArrayList<Integer>();
							
							t.add(map.get(new StringBuilder().append(words[i].substring(words[i].length() - n)).reverse().toString()));
		                    t.add(i);
							res.add(t);
							
						}
						//当前单词从n-末尾是回文，且0-n-1部分在map里出现了
						if(n < words[i].length() && isPalindrome(words[i], n, words[i].length()-1) 
								&& map.containsKey(new StringBuilder().append(words[i].substring(0,n)).reverse().toString()) ){
							List<Integer> t = new ArrayList<Integer>();
							t.add(i);
							t.add(map.get(new StringBuilder().append(words[i].substring(0,n)).reverse().toString()));
							res.add(t);
							
						}
						
					}
						
				}
		    	return res;
		        
		    }

		public boolean isPalindrome(String s, int l, int r) {
			while(l < r) {
				if(s.charAt(l) != s.charAt(r)) {
					return false;
				}else {
					l++;
					r--;
				}
			}
			return true;
			
		}
}
