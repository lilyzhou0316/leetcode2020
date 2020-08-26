package day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Given a string s, return all the palindromic permutations (without duplicates) of it. 
 * Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]


Example 2:

Input: "abc"
Output: []


Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach 
from: Permutations II or Next Permutation.
 * */

//思路：用哈希表来记录所有字符的出现个数，然后找出出现奇数次数的字符加入 mid 中，如果有两个或两个以上的奇数
//个数的字符，则返回空集，对于每个字符，不管其奇偶，都将其个数除以2的个数的字符加入t中，这样做的原因是如果
//是偶数个，将其一半加入t中，如果是奇数，如果有1个，除以2是0，不会有字符加入t，如果是3个，除以2是1，
//取一个加入t。等获得了t之后，t是就是前半段字符，对其做全排列，每得到一个全排列，加上 mid 和该全排列的逆序列
//就是一种所求的回文字符串，这样就可以得到所有的回文全排列了。在全排序的子函数中有一点需要注意的是，如果直接
//用数组来保存结果时，并且t中如果有重复字符的话可能会出现重复项，比如 t = "baa" 的话，那么最终生成的结果
//会有重复项，不信可以自己尝试一下。这里简单的说明一下，当 start=0，i=1 时，交换后得到 aba，在之后当
//start=1，i=2 时，交换后可以得到 aab。但是在之后回到第一层当baa后，当 start=0，i=2 时，交换后又得到了
//aab，重复就产生了。那么其实最简单当去重复的方法就是将结果 res 定义成 HashSet，利用其去重复的特性，
//可以保证得到的是没有重复的
public class PalindromePermutationII_267 {
	public List<String> generatePalindromes(String s) {
		List<String> reStrings = new ArrayList<String>();
		if(s.length() == 0 || s.length() == 1)return reStrings;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(char a: s.toCharArray()) {
			if(map.containsKey(a)) {
				int temp = map.get(a);
				map.put(a, temp + 1);
			}else {
				map.put(a, 1);
			}
		}
		
		
		StringBuilder str = new StringBuilder();
		String midString = "";//记录如果s为奇数长度时位于中间的字符
		for(Entry<Character, Integer> entry: map.entrySet()) {
			if(entry.getValue() % 2 ==1) {
				midString += entry.getKey();//把出现次数为奇数的字符加入
			}
			if(midString.length() > 1)return reStrings;	
		}
		
		//出循环说明原字符串可以组成回文
		//对于每个字符，不管其奇偶，都将其个数除以2的个数的字符加入str中,这样就取到了回文字符的一半
		for (char a : map.keySet()) {
				int temp = map.get(a) / 2;
				while(temp-- > 0)str.append(a);
		}
		
		Set<String> outSet = new HashSet<String>();
		//再对这一半的回文字符进行排列
		permutation(str.toString(), map, midString, "", outSet);
		
		for (String string : outSet) {
			reStrings.add(string);
		}
		return reStrings;
	}
	
	public void permutation(String s, Map<Character, Integer> m,String mid, String res, Set<String> out) {
		if(res.length() >= s.length()) {
			String reverseString = "";
			for(int i = res.length() - 1; i>=0 ; i--) {
				reverseString += res.charAt(i);
			}
			out.add(res + mid + reverseString );
			return;
		}
		for(Entry<Character, Integer> entry: m.entrySet()) {
			if(entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
				permutation(s, m, mid, res + entry.getKey(), out);
				entry.setValue(entry.getValue() + 1);
			}
		}
	}
		
	
}
