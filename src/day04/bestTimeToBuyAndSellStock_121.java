package day04;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell 
one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
             
Example 2:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * */

//思路1:暴力求解，遍历数组求每个元素和其它元素的差，找到最大的为正的差值

//思路2:用一个变量记录数组中的最小值，然后每次计算当前值和这个最小值之间的差值最为利润，然后每次选较大的利润来更新
public class bestTimeToBuyAndSellStock_121 {
	 public int maxProfit(int[] prices) {
	        int minNum = Integer.MAX_VALUE;
	        int profit = 0;
	        for (int i = 0; i < prices.length; i++) {
				if (minNum > prices[i]) {
					minNum = prices[i];
				}
				if (profit < prices[i] - minNum) {
					profit = prices[i] - minNum;
				}
			}

	        return profit;
	    }

}
