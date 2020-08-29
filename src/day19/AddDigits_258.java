package day19;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a non-negative integer num, repeatedly add all its digits until the result has only 
 * one digit.

Example:

Input: 38
Output: 2 
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
             Since 2 has only one digit, return it.
             
             
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 * */

//我的思路：两层while循环，里层将所有位数上的数字取出放入一个list，外层将list里的每个数字加起来判断是否大于等于10(n^2),超时

//思路2:递归，每次调用该方法把当前num的各个位上的数字相加一遍，直到最后数字小于10

//follow up:本题实质上就是求数根。在数学中，数根(又称位数根或数字根Digital root)是自然数的一种性质，换句话说，
//每个自然数都有一个数根。数根是将一正整数的各个位数相加（即横向相加），若加完后的值大于10的话，则继续将各位数
//进行横向相加直到其值小于十为止[1]，或是，将一数字重复做数字和，直到其值小于十为止，则所得的值为该数的数根。
//例如54817的数根为7，因为5+4+8+1+7=25，25大于10则再加一次，2+5=7，7小于十，则7为54817的数根。
//数根可以计算模运算的同余，对于非常大的数字的情况下可以节省很多时间。数字根可作为一种检验计算正确性的方法。
//例如，两数字的和的数根等于两数字分别的数根的和。另外，数根也可以用来判断数字的整除性，如果数根能被3或9整除，
//则原来的数也能被3或9整除。
//我们把 1 到 30 的树根列出来：
//原数: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
//数根: 1 2 3 4 5 6 7 8 9  1  2  3  4  5  6  7  8  9  1  2  3  4  5  6  7  8  9  1  2  3 
//会发现数根是循环出现的，对于给定的 n 有三种情况。
//n 是 0 ，数根就是 0。
//n 不是 9 的倍数，数根就是 n 对 9 取余，即 n mod 9。
//n 是 9 的倍数，数根就是 9。

public class AddDigits_258 {
public int addDigits(int num) {
	//解法1
//       if(num < 10)return num;
//       
//       int sum = 0;//用来保存当前num每位相加的结果
//       while(num != 0) {
//    	   sum += num % 10;
//    	 num = num / 10;
//       }
//       return addDigits(sum);
       
       //follow up：
       if(num < 10)return num;
       return num % 9 == 0? 9:num % 9;
    }
}
