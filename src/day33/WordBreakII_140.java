package day33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 *  add spaces in s to construct a sentence where each word is a valid dictionary word. Return all 
 *  such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 * */
//我的思路：类似题93，282，291，拆词，每次拆的词都在wordDict查找下看是否包含，如果不包含则继续，如果包含则加入当前结果str，
//最后遍历s结束则停止

//思路：先扫一遍wordDict数组，看有没有单词可以当s的开头，那么我们可以发现cat和cats都可以，比如我们先选了cat，那么此时s
//就变成了 "sanddog"，我们再在数组里找单词，发现了sand可以，最后剩一个dog，也在数组中，于是一个结果就出来了。然后回到开头
//选cats的话，那么此时s就变成了 "anddog"，我们再在数组里找单词，发现了and可以，最后剩一个dog，也在数组中，于是另一个结果
//也就出来了。那么这个查询的方法很适合用递归来实现，因为s改变后，查询的机制并不变，很适合调用递归函数。再者，我们要明确的是，
//如果不用记忆数组做减少重复计算的优化，那么递归方法跟brute force没什么区别，大概率无法通过OJ。所以我们要避免重复计算，
//如何避免呢，还是看上面的分析，如果当s变成 "sanddog"的时候，那么此时我们知道其可以拆分成sand和dog，当某个时候如果我们
//又遇到了这个 "sanddog"的时候，我们难道还需要再调用递归算一遍吗，当然不希望啦，所以我们要将这个中间结果保存起来，由于我们
//必须要同时保存s和其所有的拆分的字符串，那么可以使用一个HashMap，来建立二者之间的映射，那么在递归函数中，我们首先检测
//当前s是否已经有映射，有的话直接返回即可，如果s为空了，我们如何处理呢，题目中说了给定的s不会为空，但是我们递归函数处理时
//s是会变空的，这时候我们是直接返回空集吗，这里有个小trick，我们其实放一个空字符串返回，为啥要这么做呢？我们观察题目中的
//Output，发现单词之间是有空格，而最后一个单词后面没有空格，所以这个空字符串就起到了标记当前单词是最后一个，那么我们就不要
//再加空格了。接着往下看，我们遍历wordDict数组，如果某个单词是s字符串中的开头单词的话，我们对后面部分调用递归函数，将结果
//保存到rem中，然后遍历里面的所有字符串，和当前的单词拼接起来，这里就用到了我们前面说的trick。for循环结束后，记得返回
//结果res之前建立其和s之间的映射，方便下次使用
public class WordBreakII_140 {
	 public List<String> wordBreak(String s, List<String> wordDict) {
			
			Map<String, List<String>> map = new HashMap<>();//用来保存中间结果
			Set<String> set = new HashSet<String>();//用来快速查找当前单词是否在字典里
			for (String word : wordDict) {
				set.add(word);
			}
			return helper(s, wordDict,map,set);
			
		    }

		public List<String> helper(String s, List<String> wordDict,Map<String, List<String>> map, Set<String> set) {
		    if(s.length() == 0) {
		    	//递归base case
		        return Arrays.asList(new String[]{""});
		    }
		    
		    //如果之前已经保存过当前字符串对应的结果集，则直接返回
			if(map.containsKey(s))return map.get(s);
			
			List<String> res = new ArrayList<String>();
			
			//否则开始拆分当前字符串
			for (String word : set) {
				if(s.startsWith(word)) {//当前字符串以字典里的某个单词开始
				//则继续递归往下拆，直到拆完
				List<String> temp = helper(s.substring(word.length()), wordDict, map, set);
				
				for (String str : temp) {
					if(str.length() == 0){
		                //当前单词为最后一个单词
		                res.add(word);
		                    
		            }else{
		                res.add(word + " " + str);
		            }
				}
				
				}
				
			}
			//最后将当前拆分的结果加入map
			map.put(s, res);
			return res;
			}
}

