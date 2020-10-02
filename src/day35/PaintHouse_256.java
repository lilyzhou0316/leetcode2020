package day35;

import java.util.Arrays;

/*
 * There is a row of n houses, where each house can be painted one of three colors: 
 * red, blue, or green. The cost of painting each house with a certain color is different.
 *  You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] 
 is the cost of painting house 1 with color green, and so on... Find the minimum cost to 
 paint all houses.

 

Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.

Example 2:

Input: costs = []
Output: 0

Example 3:

Input: costs = [[7,6,2]]
Output: 2
 

Constraints:

costs.length == n
costs[i].length == 3
0 <= n <= 100
1 <= costs[i][j] <= 20
 * */

//思路：这题跟 House Robber II 和 House Robber 很类似，不过那题不是每个房子都抢，相邻的房子不抢，而这道题
//是每个房子都刷，相邻的房子不能刷同一种颜色，而 Paint Fence 那道题主要考察有多少种刷法。这几道题很类似，
//但都不一样，需要分别区分。但是它们的解题思想都一样，需要用动态规划 Dynamic Programming 来做，这道题需要
//维护一个二维的动态数组 dp，其中 dp[i][j] 表示刷到第 i+1 房子用颜色j的最小花费，状态转移方程为:

//dp[i][j] = dp[i][j] + min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3])；

//这个也比较好理解，如果当前的房子要用红色刷，则上一个房子只能用绿色或蓝色来刷，那么要求刷到当前房子，
//且当前房子用红色刷的最小花费就等于当前房子用红色刷的钱加上刷到上一个房子用绿色和刷到上一个房子用蓝色
//中的较小值，这样当算到最后一个房子时，只要取出三个累计花费的最小值即可
public class PaintHouse_256 {
public int minCost(int[][] costs) {
        if(costs.length == 0 || costs[0].length == 0)return 0;
        
        int[][] dp = costs;
        for (int i = 1; i < dp.length; i++) {
        	//如果当前房子用红色，则之前一个房子只能用绿色或者蓝色，找出两种情况中的较小值
			dp[i][0] += Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] += Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] += Math.min(dp[i - 1][0], dp[i - 1][1]);
		}
        
        //最后返回三种颜色累计的花费中的最小值
        return Math.min(Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]), dp[dp.length - 1][2]);
        
    }
}
