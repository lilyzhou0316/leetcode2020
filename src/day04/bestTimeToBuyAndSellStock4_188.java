package day04;

/*
 * Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time
 (ie, you must sell the stock before you buy again).

Example 1:
Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * */

//思路：先考虑k的次数问题，如果k>prices.length/2，则变成了无限交易次数的问题（即题122解法）
//如果k<=prices.length/2，则变成了限制交易次数的问题，即题188解法,只是将j变成变量而不是常量
public class bestTimeToBuyAndSellStock4_188 {
public int maxProfit(int k, int[] prices) {
	 int n = prices.length;
     if (n < 2) {
			return 0 ;
		}
     
      if (k < 1 ) { return 0; }

     // k 超过了上限，也就变成了 无限次交易问题
      if (k > prices.length / 2) {
          return maxProfitOfII(prices);
      }
     
     //初始化dp数组，j的状态为0（未交易），1（买入一次），2（卖出一次），....2k（卖出k次）
     int[][] dp = new int[n][k*2+1];
     
     //初始化第一天的情况
     for (int j = 0; j < k*2+1; j++) {
			if(j % 2 == 0)dp[0][j] = 0;
			if(j % 2 == 1)dp[0][j] = -prices[0];
		}
     //初始化未交易的情况
     for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}
     
     //计算dp[i][j]
     int max= 0;
     for (int i = 1; i < n; i++) {
     	for (int j = 1; j < k*2+1; j++) {
     		if(j % 2 == 0) {
         		dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+prices[i]);
         	}
 			if(j % 2 == 1) {
 				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]-prices[i]);
 			}
 			if(max < dp[i][j]) max = dp[i][j];
			}
		}
     
    return max; 
 }
 
 // 解决无限次交易的方法
  public int maxProfitOfII(int[] prices) {
     int res = 0;
     for (int i = 1; i < prices.length; i++) {
         if (prices[i] > prices[i - 1]) {
             res += prices[i] - prices[i - 1];
         }
     }
     return res;
    }
}
