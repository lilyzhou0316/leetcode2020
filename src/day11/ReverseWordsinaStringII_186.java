package day11;

import java.util.Arrays;
import java.util.Collections;

/*
 * Given an input string __ , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]


Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.

Follow up: Could you do it  in-place  without allocating extra space?
 * */

//我的思路：和题151基本一样，把数组先变成string数组

//思路2:先把每个单词翻转一遍，再把整个字符串翻转一遍，或者也可以调换个顺序，先翻转整个字符串，再翻转每个单词
public class ReverseWordsinaStringII_186 {
	public void reverseWords(String[] str) {
		//解法1
//		String tempString = "";
//		for (Character c : str) {
//			tempString += c;
//		}
//		String[] strings = tempString.split("\\s+");
//		Collections.reverse(Arrays.asList(strings));
//		tempString = String.join(" ", strings);
//		str = tempString.toCharArray();
		
		//解法2
		int start = 0;
		for (int i = 0; i <= str.length; i++) {
			if(i == str.length || str[i] == " ") {
				//先翻转单词,碰到空格，或者i超出范围了，说明i之前的位置能组成一个单词
				reverse(str, start, i - 1);
				start = i + 1;
			}
		}
		//再翻转整个数组
		reverse(str, 0, str.length - 1);
		
	}
	
	public void reverse(String[] arr,int start, int end) {
		while(start < end) {
			String temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}
}
