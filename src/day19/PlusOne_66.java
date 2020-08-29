package day19;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a non-empty array of digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each 
element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

 

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.


Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.


Example 3:

Input: digits = [0]
Output: [1]
 

Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
 * */

//思路：将数组转成list，从最后一位开始，个位上的数字等于（x+1）%10，进位数字等于（x+1）/10，
//其余位上的数字都等于（y + 进位数字）%10
public class PlusOne_66 {
	public int[] plusOne(int[] digits) {
		
		//解法1
//		List<Integer> temp = new ArrayList<Integer>();
//		//将数组转成list
//		for (int i = 0; i < digits.length; i++) {
//			temp.add(digits[i]);
//		}
//		//从最后一位（个位）开始遍历list
//		int incre = 0;
//		for (int i = temp.size() - 1; i >=0 ; i--) {
//			if(i == temp.size() - 1) {
//				//如果是个位，则个位上的数字等于（x+1）%10，进位数字等于（x+1）/10
//				int t = (temp.get(i) + 1)%10;
//				incre = (temp.get(i) + 1)/10;
//				temp.set(i, t);
//			}else {
//				//非个位上的数字，则等于（y + 进位数字）%10
//				int t = (temp.get(i) + incre)%10;
//				incre = (temp.get(i) + incre)/10;
//				temp.set(i, t);
//			}
//		}
//		//出循环时如果inre还有数字，则需要给temp增加一位
//		if(incre != 0) {
//			temp.add(0, incre);
//		}
//		
//		int[] res = new int[temp.size()];
//		for (int i = 0; i < res.length; i++) {
//			res[i] = temp.get(i);
//		}
//		return res;
		
		//解法2，对1优化
		int incre = 0;
		for (int i = digits.length - 1; i >= 0 ; i--) {//对数组进行倒序遍历
			if(i == digits.length - 1 ) {
				//如果是个位
				incre = (digits[i] + 1)/10;
				int t =  (digits[i] + 1)%10;
				digits[i] = t;
			}else {//如果不是个位
				int t =  (digits[i] + incre)%10;
				incre = (digits[i] + incre)/10;
				digits[i] = t;

			}
		}
		//如果出循环时incre还有数字，则需要新增一位
		
		if(incre != 0) {
			int[] res = new int[digits.length+1];
			res[0] = incre;
			for (int i = 1; i < res.length; i++) {
				res[i] = digits[i-1];
			}
			return res;
		}else {
			return digits;
		}
		
		
	}
}
