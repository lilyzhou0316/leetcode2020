package day11;

/*
 * Write a function that reverses a string. The input string is given as an array 
 * of characters char[].

Do not allocate extra space for another array, you must do this by modifying 
the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]


Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 * */
//我的思路：遍历数组，将第一个和末尾字符交换，将第二个和倒数第二个交换。。。。。重复此步骤直到中间点

//思路2:双指针
public class ReverseString_344 {
public void reverseString(char[] s) {
	//解法1
//        for (int i = 0; i < s.length/2; i++) {
//			swap(s, i, s.length -1 -i);
//		}
	
	//解法2
	int l = 0, r = s.length - 1;
	while(l < r) {
		swap(s, l, r);
		l++;
		r--;
	}
    }

public void swap(char[] arr, int i, int j) {
	char temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}
}
