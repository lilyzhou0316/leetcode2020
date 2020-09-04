package day23;

/*
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize 
 * the product of those integers. Return the maximum product you can get.

Example 1:

Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.


Example 2:

Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.


Note: You may assume that n is not less than 2 and not larger than 58.
 * */

//思路1：最简单粗暴的方法自然是检查所有情况了，当前的拆分方法需要用到之前的拆分值，这种重现关系就很适合动态规划 
//Dynamic Programming 来做，我们使用一个一维数组 dp，其中 dp[i] 表示数字i拆分为至少两个正整数之和的最大乘积，
//数组大小为 n+1，值均初始化为1，因为正整数的乘积不会小于1。可以从3开始遍历，因为n是从2开始的，而2只能拆分为两个1，
//乘积还是1。i从3遍历到n，对于每个i，需要遍历所有小于i的数字，因为这些都是潜在的拆分情况，对于任意小于i的数字j，
//首先计算拆分为两个数字的乘积，即j乘以 i-j，然后是拆分为多个数字的情况，这里就要用到 dp[i-j] 了，
//这个值表示数字 i-j 任意拆分可得到的最大乘积，再乘以j就是数字i可拆分得到的乘积，取二者的较大值来更新 dp[i]，
//最后返回 dp[n] 即可

//思路2:找规律，从3到10对其进行拆分可以发现
//数字3可以拆分成 2+1 或 1+1+1，显然第一种拆分方法乘积大为2。
//数字4拆成 2+2，乘积最大，为4。
//数字5拆成 3+2，乘积最大，为6。
//数字6拆成 3+3，乘积最大，为9。
//数字7拆为 3+4，乘积最大，为 12。
//数字8拆为 3+3+2，乘积最大，为 18。
//数字9拆为 3+3+3，乘积最大，为 27。
//数字10拆为 3+3+4，乘积最大，为 36。
//可以看出从5开始，数字都需要先拆出所有的3，一直拆到剩下一个数为2或者4，因为剩4就不用再拆了，拆成两个2和不拆没有意义，
//而且4不能拆出一个3剩一个1，这样会比拆成 2+2 的乘积小。这样我们就可以写代码了，先预处理n为2和3的情况，
//然后先将结果 res 初始化为1，然后当n大于4开始循环，结果 res 自乘3，n自减3，根据之前的分析，当跳出循环时，
//n只能是2,3,4，再乘以 res 返回
public class IntegerBreak_343 {
public int integerBreak(int n) {
        //解法1
//	int[] dp = new int[n+1];//dp[i]代表数字i的最大乘积，dp[n]即为所求，所以长度为n+1
//	
//	dp[2] = 1;//初始值，n从2开始
//	for (int i = 3; i < dp.length; i++) {
//		for (int j = 1; j < i; j++) {//j是需要从i里减去的数字(即i被拆分为j和i - j)，从1开始，到i-1
//			dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i - j]));
//			
//		}
//	}
//	return dp[n];
	
	//解法2
	if(n == 2)return 1;
	if(n == 3)return 2;
	if(n == 4)return 4;
	
	int res = 1;
	while(n > 4) {
		res *= 3;
		n = n - 3;
	}
	return res * n;
	
	
    }
}
