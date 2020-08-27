package day17;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s, partition s such that every substring of the partition is a
 *  palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 * */

//思路：这又是一道需要用DFS来解的题目，既然题目要求找到所有可能拆分成回文数的情况，那么肯定是所有的情况都
//要遍历到，对于每一个子字符串都要分别判断一次是不是回文数，那么肯定有一个判断回文数的子函数，还需要一个DFS
//函数用来递归，再加上原本的这个函数，总共需要三个函数来求解。我们将已经检测好的回文子串放到字符串数组out中，
//当s遍历完了之后，将out加入结果res中。那么在递归函数中我们必须要知道当前遍历到的位置，用变量start来表示，
//所以在递归函数中，如果start等于字符串s的长度，说明已经遍历完成，将out加入结果res中，并返回。否则就从
//start处开始遍历，由于不知道该如何切割，所以我们要遍历所有的切割情况，即一个字符，两个字符，三个字符，等等。
//首先判断取出的子串是否是回文串，调用一个判定回文串的子函数即可，这个子函数传入了子串的起始和终止的范围，
//若子串是回文串，那么我们将其加入out，并且调用递归函数，此时start传入 i+1，之后还要恢复out的状态。
//那么，对原字符串的所有子字符串的访问顺序是什么呢，如果原字符串是 abcd, 那么访问顺序为: 
//a -> b -> c -> d -> cd -> bc -> bcd-> ab -> abc -> abcd, 这是对于没有两个或两个以上子回文串的情况。
//那么假如原字符串是 aabc，那么访问顺序为：a -> a -> b -> c -> bc -> ab -> abc -> aa -> b -> c ->
//bc -> aab -> aabc，中间当检测到aa时候，发现是回文串，那么对于剩下的bc当做一个新串来检测，于是有 
//b -> c -> bc，这样扫描了所有情况，即可得出最终答案
public class PalindromePartitioning_131 {
	public List<List<String>> partition(String s) {
		 List<List<String>> res = new ArrayList<List<String>>();
		 if(s.length() == 0 || s == null)return res;
		 
		 
		 List<String> t = new ArrayList<String>();
		 helper(s,0,t,res);
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
	 
	 public void helper(String str, int start, List<String> temp, List<List<String>> res) {
		if(start == str.length()) {//str已经遍历完了
			res.add(new ArrayList<>(temp));
			return;
		}
		//start为遍历的起始位置
		for (int i = start; i < str.length(); i++) {
			if(isPalindrome(str, start, i)){
               //检查当前子串是否是回文
               temp.add(str.substring(start,i+1));//如果是，则加入结果集
			helper(str, i+1, temp, res);//再看剩下的子串是否是回文
			temp.remove(temp.size() - 1);//每次最后一个元素都重复了,需要删除
           }
			
		}
		
	}
}
