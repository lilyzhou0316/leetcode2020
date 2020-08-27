package day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Determine whether an integer is a palindrome. An integer is a palindrome when it 
 * reads the same backward as forward.

Example 1:

Input: 121
Output: true


Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. 
Therefore it is not a palindrome.


Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


Follow up:

Coud you solve it without converting the integer to a string?
 * */

//思路：转成string然后从中心开始往两边读看是否相等

//follow up：如果有负号，直接false，否则取出每一位上的数放到一个list里，如果该list翻转后与原list相等，则true
public class PalindromeNumber_09 {
	public boolean isPalindrome(int x) {
		if(x < 0)return false;
		if(0 <= x && x <= 9)return true;
		
		//解法1
		//至少两位数
//		String tString = x + "";
//		int l = tString.length() % 2 == 1? tString.length() / 2 : tString.length() / 2 - 1;
//		int r = tString.length() / 2;
//		while(l >= 0 && r <= tString.length() - 1) {
//			if(tString.charAt(l) != tString.charAt(r))return false;
//			else {
//				l--;
//				r++;
//			}
//		}
//		return true;
		
		//解法2

//     
//      String s = String.valueOf(x);
//      //回文数就是正着反着读一摸一样
//      String reverse = new StringBuilder().append(s).reverse().toString();
//     
//      if(reverse.equals(s))return true;
//      return false;
		
		//follow up:
		List<Integer> temp = new ArrayList<Integer>();
		while(x  >= 10) {//2位数及以上
			temp.add(x % 10);
			x = x / 10;
		}
		temp.add(x);
		List<Integer> temp2 = new ArrayList<Integer>();
		for(Integer nInteger : temp) {
			temp2.add(nInteger);
		}
		Collections.reverse(temp);
		
		if(temp.equals(temp2))return true;
		return false;

	}
}
