package day03;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/*
 * Given a list of words and two words  word1  and  word2 , return the shortest distance 
 * between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “coding”, _word2_ = “practice”
Output: 3

Input: _word1_ = "makes", _word2_ = "coding"
Output: 1

Note:
You may assume that  word1  does not equal to  word2 , and  word1  and  word2  are both 
in the list.
 * */
//思路1:用两个数组保存两个词出现的位置，然后找到最小值

//思路2：用两个pointer记录两个单词出现的位置，然后找出最小差值

//思路3:只用一个辅助变量 idx，初始化为 -1，然后遍历数组，如果遇到等于两个单词中的任意一个的单词，
//再看 idx 是否为 -1，若不为 -1，且指向的单词和当前遍历到的单词不同，更新最小值

public class shortestWordDistance_243 {
	public int shortestDistance(String[] words, String word1, String word2) {
		int res = Integer.MAX_VALUE;
		//解法1:时间复杂度不好
//		Vector<Integer> v1=new Vector<>(); //保存单词1出现的位置
//		Vector<Integer> v2=new Vector<>();  //保存单词2出现的位置
//		
//		for (int i = 0; i < words.length; i++) {
//			if (words[i] == word1) {
//				v1.add(i);
//			}else if (words[i] == word2) {
//				v2.add(i);
//			}
//		}
//		//然后求最小值
//		
//		for (int i = 0; i < v1.size(); i++) {
//			for (int j = 0; j <v2.size(); j++) {
//				res = Math.min(res,Math.abs(v2.get(j) - v1.get(i)));
//			}
//		}
		
		
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
