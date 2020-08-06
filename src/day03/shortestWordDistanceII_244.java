package day03;

/*
 * Design a class which receives a list of words in the constructor, 
 * and implements a method that takes two words  word1  and  word2  and
 *  return the shortest distance between these two words in the list. 
 *  Your method will be called  repeatedly  many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “coding”, _word2_ = “practice”
Output: 3

Input: _word1_ = "makes", _word2_ = "coding"
Output: 1

Note:
You may assume that  word1  does not equal to  word2 , and  word1  and  word2  are both in the list.

 
 * */

//思路：解法与243一样，只是注意数组由构造器传入

public class shortestWordDistanceII_244 {
	String[] words;
	public shortestWordDistanceII_244(String[] words) {
		this.words = words;
	}
	public int shortestDistance(String word1, String word2) {
		int res = Integer.MAX_VALUE;
		//解法2:
//		int p1 = -1, p2 = -1;
//		for (int i = 0; i < words.length; i++) {
//			if (words[i] == word1) p1 = i;
//			if (words[i] == word2) p2 = i;
//			if (p1 != -1 && p2 != -1) {
//				res = Math.min(res, Math.abs(p1-p2));
//			}
//		}
		
		//解法3:
		int p = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i] == word1 || words[i] == word2) {
				if (p != -1 && words[p] != words[i]) {//如果p记录的词不是当前找到的单词，则重新计算res
					res = Math.min(res, Math.abs(p-i));
				}
				p = i;//计算完后更新p记录的位置
			}
			
		}
		
		return res;
		
	}
}
