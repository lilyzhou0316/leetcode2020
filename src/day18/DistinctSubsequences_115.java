package day18;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting 
some (can be none) of the characters without disturbing the relative positions of the remaining 
characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It's guaranteed the answer fits on a 32-bit signed integer.

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
 * */

//暴力解法：以s每个字符为起点，找到t，看能找到几次

//思路1:看到有关字符串的子序列或者配准类的问题，首先应该考虑的就是用动态规划Dynamic Programming来求解

//思路2:递归之分治。S 中的每个字母就是两种可能选他或者不选他。我们用递归的常规思路，将大问题化成小问题，也就是分治的思想。
//分两种情况：
//1.s[0] == t[0],则又可分为
//   1)选择s[0]时，只需要看s[1,s.len - 1中能得到多少个t[1,t.len - 1],设为n1;
//   2)不选择s[0]时，则需要看s[1,s.len - 1中能得到多少个t[0,t.len - 1],设为n2;
//  则总个数为n1 + n2

//2.s[0] ！= t[0]
//则只需要看s[1,s.len - 1中能得到多少个t[0,t.len - 1],设为n3;
//总个数为n3

//递归出口是：当s为空串时，从s中选t的可能数为0； 当t为空串时，从s中选t的可能是1.
//最后为了节省时间，把每次递归的结果保存在map中，在求之前，先看map中有没有，有的话直接拿出来就可以了。

//思路3：递归之回溯。回溯的思想就是朝着一个方向找到一个解，然后再回到之前的状态，改变当前状态，继续尝试得到新的解。
//可以类比于二叉树的DFS，一路走到底，然后回到之前的节点继续递归。对于这道题，和二叉树的DFS很像了，每次有两个可选的状态，
//选择S串的当前字母和不选择当前字母。
//当S串的当前字母和T串的当前字母相等，我们就可以选择S的当前字母，进入递归。递归出来以后，继续尝试不选择S的当前字母，进入递归。

public class DistinctSubsequences_115 {
	
	//解法：递归分治
//public int numDistinct(String s, String t) {
//        Map<String, Integer> map = new HashMap<String, Integer>();
//       
//        return helper(s,0,t,0,map);
//        
//    }
//
//public int helper(String s, int s_start,String t,int t_start, Map<String, Integer> map) {
//	 //T 是空串，选法就是 1 种
//    if (t_start == t.length()) { 
//        return 1;
//    }
//    //S 是空串，选法是 0 种
//    if (s_start == s.length()) {
//        return 0;
//    }
//    String key = s_start + "@" + t_start;
//    //先判断之前有没有求过这个解
//    if (map.containsKey(key)) {
//		return map.get(key); 
//	}
//    int count = 0;
//    //当前字母相等
//    if (s.charAt(s_start) == t.charAt(t_start)) {
//        //从 S 选择当前的字母，此时 S 跳过这个字母, T 也跳过一个字母。
//        count = helper(s, s_start + 1, t, t_start + 1, map)
//        //S 不选当前的字母，此时 S 跳过这个字母，T 不跳过字母。
//              + helper(s, s_start + 1, t, t_start, map);
//     
//    }else{ //当前字母不相等 
//        //S 只能不选当前的字母，此时 S 跳过这个字母， T 不跳过字母。
//        count =helper(s, s_start + 1, t, t_start, map);
//    }
//    //将当前解放到 map 中
//    map.put(key, count);
//    return count; 
//
//}
	
	//递归回溯
	int count = 0;
	public int numDistinct(String s, String t) { 
	    HashMap<String, Integer> map = new HashMap<>();
	    numDistinctHelper(s, 0, t, 0, map);
	    return count;
	}

	private void numDistinctHelper(String s, int s_start, String t, int t_start, 
				HashMap<String, Integer> map) {
	    if (t_start == t.length()) {
	        count++; 
	        return;
	    }
	    if (s_start == s.length()) {
	        return;
	    }
	    String key = s_start + "@" + t_start;
	    if (map.containsKey(key)) {
	        count += map.get(key);
	        return; 
	    }
	    int count_pre = count;
	    //当前字母相等，s_start 后移一个，t_start 后移一个
	    if (s.charAt(s_start) == t.charAt(t_start)) {
	        numDistinctHelper(s, s_start + 1, t, t_start + 1, map);
	    }
	    //出来以后，继续尝试不选择当前字母，s_start 后移一个，t_start 不后移
	    numDistinctHelper(s, s_start + 1, t, t_start, map);
	    
	    //将增量存起来
	    int count_increment = count - count_pre;
	    map.put(key, count_increment); 
	}

}
