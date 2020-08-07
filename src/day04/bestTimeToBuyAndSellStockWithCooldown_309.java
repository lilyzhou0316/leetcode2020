package day04;

/*
 *Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).

After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:
Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * */

//思路：dp,增加一个状态冷静期





public class bestTimeToBuyAndSellStockWithCooldown_309 {
	 public int maxProfit(int[] prices) {
	        int n = prices.length;
	        if(n<2)return 0 ;
	        //初始化dp
	        //i代表天数，j代表状态，0表示不持股（即卖出股票），1表示持股（即买入），2表示冷静期
	        int[][] dp = new int[n][3];
	        
	      //起始条件：在第 0 天，不持股的初始化值为 0，持股的初始化值为 -prices[0]（表示购买了一股），
	        //虽然不处于冷冻期，但是初始化的值可以为 0
	        dp[0][0] = 0;
	        dp[0][1] = -prices[0];
	        dp[0][2] = 0;
	        
	        //计算dp[i][j]
	        for (int i = 1; i < n; i++) {
	        	////不持股可以由这两个状态转换而来：昨天不持股，今天什么都不操作，仍然不持股；
	        	//昨天持股，今天卖了一股
				dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i] );
				//持股可以由这两个状态转换而来：昨天持股，今天什么都不操作，仍然持股；
				//昨天处在冷冻期，今天买了一股；
				dp[i][1] =  Math.max(dp[i-1][1],dp[i-1][2]-prices[i] );
				//处在冷冻期只可以由不持股转换而来，因为题目中说，刚刚把股票卖了，需要冷冻 1 天。
				dp[i][2] = dp[i-1][0];
				
			}
	        //因为持股状态下肯定收益最小，所以比较最后一天不持股和冷静期哪个收益更大
	        return Math.max(dp[n-1][0], dp[n-1][2]);
	        
	    }

}
