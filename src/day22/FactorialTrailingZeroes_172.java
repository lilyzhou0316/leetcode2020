package day22;


/*
 * Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Note: Your solution should be in logarithmic time complexity.
 * */

//我的思路：递归求斐波那契数列，然后把数转str， 再从尾部遍历看有几个0,超时

//思路：是让求一个数的阶乘末尾0的个数，也就是要找乘数中 10 的个数，而 10 可分解为2和5，而n!中，2的数量又远大于5的数量
//（比如5！一个5，三个2，10！2个5，8个2....），那么此题即便为找出5的个数（5的数量决定了能凑成几个10）。

//思路2:递归
public class FactorialTrailingZeroes_172 {
public int trailingZeroes(int n) {
	//解法1
//        int res = 0;
//        while(n > 0) {
//        	res += n / 5;
//        	n = n / 5;
//        }
//        return res;
	
	//解法2
	return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
