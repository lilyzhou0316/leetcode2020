package day04;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Example 1:
Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
             
Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
             
             
Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * */

//思路1:动态规划：
//dp[i][j] 代表利润，i代表第几天，j代表交易状态（0，未交易，1，买入一次，2卖出1次，3买入2次，4卖出2次）
//2. DP公式
//- dp[i][0] = dp[i-1][0]  第i天未交易，说明之前都没有交易过，因为一旦之前发生交易状态会变1-4
//- dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]); 
//第i天买入过一次，两种情况：1是前一天买入一次，今天啥都没做，2是前一天未交易，今天买入一次
//- dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
//第i天卖出过一次，两种情况：1是前一天卖出一次，2是前一天买入，今天卖出一次
//- dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
//第i天买入过两次，两种情况：1是前一天买入了两次，2是前一天卖出一次，今天买入一次
//- dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
//第i天卖出两次，两种情况：1是前一天卖出了两次，2是前一天买入两次，今天卖出一次

//初始值
//dp[0][0] = 0; 第一天未交易，利润为0
//dp[0][1] = -prices[0]; 第一天买入一次，利润减少


//结果输出
//用户收益为卖出一次，或两次后的最大利润

public class bestTimeToBuyAndSellStock3_123 {
	 public int maxProfit(int[] prices) {
		//解法1:动态规划
         //先考虑特殊情况
		 int n = prices.length;
		 if (n < 2) {
			return 0;
		}
		 
		 //初始化dp数组
		 //5代表j的5个状态
		 int[][] dp = new int[n][5];
		 
     //考虑第一天的5个状态
		 dp[0][0] = 0;
		 dp[0][1] = -prices[0];
      dp[0][2] = 0;//既买入有卖出各一次
      dp[0][3] = -prices[0];//以相同的价格买入卖出再买入
      dp[0][4] = 0;//既买入有卖出各两次
		 
		 //计算dp[i][j]
		 for (int i = 1; i < n; i++) {
			dp[i][0] =  dp[i-1][0];
			dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]); 
			dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
			dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
			dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
		}
		 return Math.max(dp[n-1][2], dp[n-1][4]);
	    }
}
