package day35;

/*
 * Given a m x n grid filled with non-negative numbers, find a path from top left to 
 * bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * */

//思路：这道题跟之前那道 Dungeon Game 没有什么太大的区别，都需要用动态规划 Dynamic Programming 来做，
//这应该算是 DP 问题中比较简单的一类，我们维护一个二维的 dp 数组，其中 dp[i][j] 表示到达当前位置的最小路径和。
//接下来找状态转移方程，因为到达当前位置 (i, j)  只有两种情况，要么从上方 (i-1, j) 过来，要么从左边 
//(i, j-1) 过来，我们选择 dp 值较小的那个路径，即比较 dp[i-1][j] 和 dp[i][j-1]，将其中的较小值加上
//当前的数字 grid[i][j]，就是当前位置的 dp 值了。但是有些特殊情况要提前赋值，比如起点位置，直接赋值为 
//grid[0][0]，还有就是第一行和第一列，其中第一行的位置只能从左边过来，第一列的位置从能从上面过来，
//所以这两行要提前初始化好，然后再从 (1, 1) 的位置开始更新到右下角即可
public class MinimumPathSum_64 {
public int minPathSum(int[][] grid) {
	 
	        //dp[i][j]表示移动到位置(i,j)时最小路径和的值
	        int[][] dp = new int[grid.length][grid[0].length];
	        dp[0][0] = grid[0][0];//初始化起始位置值
	        
	        //初始化第一列
	        for (int i = 1; i < dp.length; i++) {
				dp[i][0] = grid[i][0] + dp[i - 1][0];//下面的格子只能从上一个格子往下移动得到
			}
	        //初始化第一行
	        for (int i = 1; i < dp[0].length; i++) {
				dp[0][i] = grid[0][i] + dp[0][i - 1];//右边的格子只能从左边一个格子右移得到
			}
	        
	        //从位置（1，1）开始，走到右下角
	        for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {
					dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
	        
	        return dp[dp.length - 1][dp[0].length - 1];
	    }
    }

