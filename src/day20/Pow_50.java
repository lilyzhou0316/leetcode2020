package day20;


/*
 * Implement pow(x, n), which calculates x raised to the power n (i.e. x^n).

 

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000


Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100


Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 

Constraints:

-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= x^n <= 10^4
 * */

//思路1:快速幂+递归。因为x^64可以通过x - x^2 - x^4 - x^8 - x^16 - x^32 - x^64得到，同理x^73也可以这样
//得到，只是要多乘一个x，所以x^n只需要考虑n的奇偶性，然后递归即可，base case为n = 0 时，x^n = 1
//时间和空间复杂度都是log（n）
public class Pow_50 {
	//解法1
	 public double myPow(double x, int n) {
		//考虑n取到边界情况
		 long N = n;//如果n取-2^31，则当计算结果时，1/x^n这里n会超出int范围
		 return N >= 0? helper(x, N):1.0 / helper(x, -N);
	 }
	 
	 public double helper(double x, long n) {
		if(n == 0)return 1.0;
		
		double temp = helper(x, n / 2);
		return n % 2 == 1?temp * temp * x : temp * temp;
	}
}
