package day34;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 *  determine if s can be segmented into a space-separated sequence of one or more 
 *  dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 * */

//思路1:递归，先把字典中的所有单词都存入 HashSet 中，这样我们就有了常数时间级的查找速度，然后开始给
//字符串分段了，先看第一个字母是否在字典中，如果不在的话，好办，说明这种分法肯定是错的。问题是在的话，
//后面的那部分怎么处理，难道还用 for 循环？咱也不知道还要分多少段，怎么用 for 循环。对于这种不知道怎么处理
//的情况，一个万能的做法是丢给递归函数，让其去递归求解，这里我们 suppose 递归函数会返回我们一个正确的值，
//如果返回的是 true 的话，表明我们现在分成的两段都在字典中，我们直接返回 true 即可，因为只要找出一种情况
//就行了。这种调用递归函数的方法就是 brute force 的解法，我们遍历了所有的情况，优点是写法简洁，思路清晰，
//缺点是存在大量的重复计算，时间复杂度不符合要求。所以我们需要进行优化，使用记忆数组 memo 来保存所有已经计算
//过的结果，再下次遇到的时候，直接从 cache 中取，而不是再次计算一遍。这种使用记忆数组 memo 的递归写法，
//和使用 dp 数组的迭代写法，乃解题的两大神器，凡事能用 dp 解的题，一般也有用记忆数组的递归解法。
//这里我们的记忆数组 memo[i] 定义为范围为 [i, n] 的子字符串是否可以拆分，初始化为 -1，表示没有计算过，
//如果可以拆分，则赋值为1，反之为0。在之前讲 brute force 解法时，博主提到的是讲分成两段的后半段的调用
//递归函数，我们也可以不取出子字符串，而是用一个 start 变量，来标记分段的位置，这样递归函数中只需要从 
//start 的位置往后遍历即可，在递归函数更新记忆数组 memo 即可

//思路2:动态规划 Dynamic Programming。博主曾经说玩子数组或者子字符串且求极值的题，基本就是 DP 没差了。
//DP 解法的两大难点，定义 dp 数组跟找出状态转移方程，先来看 dp 数组的定义，这里我们就用一个一维的 dp 数组，
//其中 dp[i] 表示范围 [0, i) 内的子串是否可以拆分，注意这里 dp 数组的长度比s串的长度大1，是因为我们要 
//handle 空串的情况，我们初始化 dp[0] 为 true，然后开始遍历。注意这里我们需要两个 for 循环来遍历，
//因为此时已经没有递归函数了，所以我们必须要遍历所有的子串，我们用j把 [0, i) 范围内的子串分为了两部分，
//[0, j) 和 [j, i)，其中范围 [0, j) 就是 dp[j]，范围 [j, i) 就是 s.substr(j, i)，其中 dp[j] 
//是之前的状态，我们已经算出来了，可以直接取，只需要在字典中查找 s.substr(j, i) 是否存在了，如果二者均为 
//true，将 dp[i] 赋为 true，并且 break 掉，此时就不需要再用j去分 [0, i) 范围了，因为 [0, i) 
//范围已经可以拆分了。最终我们返回 dp 数组的最后一个值，就是整个数组是否可以拆分的布尔值了
public class WordBreak_139 {
	
	//解法1
//public boolean wordBreak(String s, List<String> wordDict) {
//	Set<String> set = new HashSet<String>();
//	//把字典中的所有单词都存入 HashSet 中，这样我们就有了常数时间级的查找速度
//	for (String str : wordDict) {
//		set.add(str);
//	}
//	
//	Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();//map用来保存从i开始到末尾已经检查过的子串的结果
//	
//        return helper(0, s, wordDict, set, map);
//    }
//
//public boolean helper(int start,String s,  List<String> wordDict, Set<String> set, Map<Integer,Boolean> map) {
//	if(start >= s.length())return true;//遍历完了str
//	
//	if(map.containsKey(start))return map.get(start);//如果之前已经遍历过对应的str
//	
//	for (int i = start + 1; i <= s.length(); i++) {
//		if(set.contains(s.substring(start, i)) && helper(i, s, wordDict, set, map)) {
//			//以当前start为起点以i为分割点给str分割，两部分都在字典里,符合条件,更新map，返回true
//			map.put(start, true);
//			return map.get(start);
//		}
//	}
//	//否则不符合条件，更新map返回false
//	map.put(start, false);
//	return map.get(start);
//}
	
	//解法2
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<String>();
		for (String str : wordDict) {
			set.add(str);
		}
		
		boolean[] dp = new boolean[s.length() + 1];//dp[i]代表从0-i的子串是否可以按要求拆分
		dp[0] = true;//空串返回true
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				if(dp[j] && set.contains(s.substring(j, i))) {
					dp[i] = true;
					break;//只需要找到一个符合条件的就行
				
			}
		}
	}
		return dp[s.length()];

}
	}
