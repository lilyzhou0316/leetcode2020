package day03;

/*
 * Given a list of words and two words  word1  and  word2 , 
 * return the shortest distance between these two words in the list.

word1  and  word2  may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “makes”, _word2_ = “coding”
Output: 1

Input: _word1_ = "makes", _word2_ = "makes"
Output: 3

Note:
You may assume  word1  and  word2  are both in the list.

 * */

//思路：与243类似，多考虑一个word1和word2相同时的情况

public class shortestWordDistanceIII_245 {
	public int shortestDistance(String[] words, String word1, String word2) {
		int res = Integer.MAX_VALUE;
		//解法1:两个pointer,当两个词相同时多一个变量temp帮助记录上一个该单词出现的位置，
		//当两个单词不同时，与之前一样
//		int p1 = -1, p2 = -1,res = Integer.MAX_VALUE;
//		for (int i = 0; i < words.length; i++) {
//			int temp = p1;
//			if (words[i] == word1) p1 = i;
//			if (words[i] == word2) p2 = i;
//			if (p1 != -1 && p2 != -1) {
//				//两种情况
//				if (p1 == p2 && temp != -1 && temp != p1) {
//					//当两词相同时，temp会记录第一次出现的位置，而p1,p2会记录第二次出现的位置
//					res = Math.min(res, Math.abs(temp - p1));
//					
//				}else if(p1 != p2) {
//					//两词不相同时
//					res = Math.min(res, Math.abs(p2 - p1));
//				}
//			}
//		} 
		
		//解法2:两个pointer，不用多余变量，当两词相同时，用p1记录上一次出现的位置，p2记录后一次的位置
//		int p1 = words.length, p2 = - words.length;//不用-1是为了不干扰结果
//		for (int i = 0; i < words.length; i++) {
//			if (words[i] == word1) p1 = word1==word2? p2 : i;
//			if (words[i] == word2) p2 = i;
//			res = Math.min(res, Math.abs(p2 - p1));
//		}
		
		//解法3:只用一个变量，记录上一次出现过的两个词中的某一个的位置
		int p = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i] == word1 || words[i] == word2) {
				if(p != -1) {//p不为-1时一定是记录了上一次某个词出现的位置，不管两个词是否相同，
					//现在的i记录的是另一个词的位置或者相同的词出现的另一个位置
					res = Math.min(res, Math.abs(p - i));	
				}
				p = i;//p记录前一个位置
			}
		}
		
		return res;
		
	}
}
