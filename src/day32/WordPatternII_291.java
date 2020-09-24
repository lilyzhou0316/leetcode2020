package day32;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and 
a non-empty substring in str.

 

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
 

Constraints:

You may assume both pattern and str contains only lowercase letters.
 * */

//思路：这道题是之前那道 题290 Word Pattern 的拓展，之前那道题词语之间都有空格隔开，这样可以一个单词一个单词的读入，然后
//来判断是否符合给定的特征，而这道题没有空格了，那么难度就大大的增加了，因为我们不知道对应的单词是什么，所以得自行分开，
//可以用回溯法来生成每一种情况来判断，这里还是需要用 HashMap 来建立模式字符和单词之间的映射，还需要用变量p和r来记录当前
//递归到的模式字符（pattern里的字符）和单词串（str）的位置，在递归函数中，如果p和r分别等于模式字符串和单词字符串的长度，说明此时匹配成功结束了，
//返回 ture，反之如果一个达到了而另一个没有，说明匹配失败了，返回 false。如果都不满足上述条件的话，取出当前位置的模式字符，
//然后从单词串的r位置开始往后遍历，每次取出一个单词，如果模式字符已经存在 HashMap 中，而且对应的单词和取出的单词也相等，
//那么再次调用递归函数在下一个位置，如果返回 true，那么就返回 true。反之如果该模式字符不在 HashMap 中，要看有没有别的
//模式字符已经映射了当前取出的单词，如果没有的话，建立新的映射，并且调用递归函数，注意如果递归函数返回 false 了，要在 
//HashMap 中删去这个映射
public class WordPatternII_291 {

	 public boolean wordPatternMatch(String pattern, String str) {
	        return helper(pattern, str, new HashMap<>(), new HashSet<>());
	        
	    }
	    
	    private boolean helper(String pattern, String str, Map<Character, String> map, Set<String> taken) {
	        if (pattern.isEmpty() || str.isEmpty()) {
	            //检查是否有空字符串
	            return pattern.isEmpty() && str.isEmpty();
	            
	        }
	        
	        char curPattern = pattern.charAt(0);//每次都取第一个字符，每次取完后删除
	        
	        //如果当前字符在map中，则检查它对应的子串和当前str的开头子串是否一致
	        if (map.containsKey(curPattern)) {
	            if (!str.startsWith(map.get(curPattern))) {
	                //不一致，返回false
	                return false;
	            }
	            
	            //一致，继续检查后面的，且把已经检查过的字符串从pattern 和 str里删除
	            return helper(pattern.substring(1), str.substring(map.get(curPattern).length()), map, taken);
	        }
	        
	        //如果当前字符不在map中,则给它截取对应的字符串
	        StringBuilder patternVal = new StringBuilder();
	        for (int i = 0; i < str.length(); i++) {
	            patternVal.append(str.charAt(i));
	            String patternValStr = patternVal.toString();
	            if (taken.contains(patternValStr)) {
	                //如果截取的字符串已经用过了，则选择下一个
	                continue;
	            }
	            //如果还没有用过，则标记为使用了
	            taken.add(patternValStr);
	            //并放入map中
	            map.put(curPattern, patternValStr);
	            //对pattern 和 str后面的继续进行检查看是否符合条件
	            if (helper(pattern.substring(1), str.substring(patternValStr.length()), map, taken)) {
	                return true;
	            }
	            //如果不满足条件，则删除当前加入的，进行回溯
	            taken.remove(patternValStr);
	            map.remove(curPattern);
	        }
	        
	        return false;
	    }



}
