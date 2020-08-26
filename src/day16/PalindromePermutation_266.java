package day16;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string, determine if a permutation(排列) of the string could form a palindrome.

Example 1:

Input: "code"
Output: false


Example 2:

Input: "aab"
Output: true


Example 3:

Input: "carerac"
Output: true


Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. 
How about character which occurs odd number of times?
 * */

//我的思路：要使排列形成回文字符，则出现的奇数个的字母的数量最多只能有一个（中间位），
//其它位置上的字母出现的次数一定为偶数
//用个map来记录每个字母出现的次数
public class PalindromePermutation_266 {
	public boolean canPermutePalindrome(String s) {
		if(s.length() == 0 || s.length() == 1)return true;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char a : s.toCharArray()) {
			//记录s中每个字符出现的次数
			if(map.containsKey(a)) {
				int temp = map.get(a);
				map.put(a, temp + 1);
			}else {
				map.put(a, 1);
			}
		}
		
		int count = 0;//统计次数为奇数个的字符的数量
		for (Integer vInteger : map.values()) {
			if(vInteger % 2 == 1)count++;
			if(count > 1)return false;
		}
		
		return true;
	}
}
