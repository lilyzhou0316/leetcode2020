package day14;

import java.util.HashMap;
import java.util.Map;

/*
 * A strobogrammatic(频闪的) number is a number that looks the same when rotated 
 * 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is 
represented as a string.

Example 1:

Input:  "69"
Output: true


Example 2:

Input:  "88"
Output: true


Example 3:

Input:  "962"
Output: false
 * */

//我的思路：能满足条件的数字只有：1，6，8，9，0，记录每个数字旋转180度后的结果，
//将旋转后的整体数字(先反转原数字顺序，再对每个数字进行180旋转)和原数字进行比较即可

//思路2:这道题其实可以看做求回文数的一种特殊情况，还是用双指针来检测，如果首尾指针对应的数字其中一个旋转后等于另一个未旋转后的
//值，则首尾指针分别往中间走一步，一直到两者相遇
public class StrobogrammaticNumber_246 {
	public boolean isStrobogrammatic(String num) {
		//能满足条件的数字只有：1，6，8，9，0，map记录每个数字旋转180度后的结果
		Map<Character, Integer> map = new HashMap<>();
		map.put('1',1);
		map.put('0',0);
		map.put('6',9);
		map.put('8',8);
		map.put('9',6);
		
		//解法1
		//如果num里有包含非0，1，6，8，9的数字，直接返回false
//		for (int i = 0; i < num.length(); i++) {
//			if(!map.containsKey(num.charAt(i)))return false;
//		}
//		//如果num里都是0，1，6，8，9这几个数字，则先对num反转，再对数字进行旋转
//		StringBuilder temString = new StringBuilder();
//		for (int i = num.length() - 1; i >= 0; i--) {
//			temString.append(map.get(num.charAt(i)));
//		}
//		return temString.toString().equals(num);
		
		//解法2:
		int l = 0, r = num.length() - 1;
		while(l <= r) {
			//如果不是0，1，6，8，9中的数字则直接返回false
			if(!map.containsKey(num.charAt(l)) || !map.containsKey(num.charAt(r)))return false;
			if(!(map.get(num.charAt(l))+"").equals( num.charAt(r)+"")) {
				return false;
			}else {
				l++;
				r--;
			}
		}
		return true;
	}
}
