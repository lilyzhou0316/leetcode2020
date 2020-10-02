package day35;

/*
 * There are a row of n houses, each house can be painted with one of the k colors. 
 * The cost of painting each house with a certain color is different. You have to paint 
 * all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the 
cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5

Explanation: Paint house 0 into color 0, paint house 1 into color 2. 
			 Minimum cost: 1 + 4 = 5; Or paint house 0 into color 2, paint house 1 into color 0. 
			 Minimum cost: 3 + 2 = 5. 
             
Follow up:
Could you solve it in O(nk) runtime?
 * 
 * */

//思路：在找不同颜色的最小值不是遍历所有不同颜色，而是用 min1 和 min2 来记录之前房子的最小和第二小的花费
//的颜色，如果当前房子颜色和 min1 相同，那么用 min2 对应的值计算，反之用 min1 对应的值，这种解法实际上也
//包含了求次小值的方法
public class PaintHouseII_265 {
public int minCostII(int[][] costs) {
        if(costs.length == 0 || costs[0].length == 0)return 0;
        
        int[][] dp = costs;
        int min1 = -1, min2 = -1;// min1 和 min2 来记录前一个房子的最小和第二小的花费对应的颜色
        
        for (int i = 0; i < dp.length; i++) {//遍历每个房子
        	//用两个变量保存前一个房子的最小值花色和次小值花色，因为后面会更新当前房子的最小值花色和次小值花色
        	int pre1 = min1, pre2 = min2;
        	min1 = -1;
        	min2 = -1;
			for (int j = 0; j < dp[0].length; j++) {//遍历当前房子的每种颜色，累加每种颜色的花费
				if(j != pre1) {
					//如果当前遍历到的颜色与前一个房子的最小值颜色不一样，则前一个房子可以用最小值花色（满足最小值且不同色）
					dp[i][j] += pre1 < 0 ? 0 : dp[i - 1][pre1];//注意处理值为-1的情况
				}else {
					//如果当前遍历到的颜色与前一个房子最小值的颜色一样，则前一个房子只能用次小值颜色
					dp[i][j] += pre2 < 0 ? 0 : dp[i - 1][pre2];
				}
				
				//用min1,min2记录当前房子的最小值和次小值
				if(min1 < 0 || dp[i][j] < dp[i][min1]) {
					//如果当前花费值比最小值花费值还小，则更新最小值和次小值
					min2 = min1;
					min1 = j;
				}else if(min2 < 0 || dp[i][j] < dp[i][min2]) {
					//如果当前花费值大于最小值花费但小于次小值花费，则需要更新次小值
					min2 = j;
				}
				
			}
		}
        
        //最后返回最后一个房子的最小累加值
        return dp[dp.length - 1][min1];
    }
}
