package day33;

import java.util.Arrays;

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the
 bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:

Input: m = 3, n = 7
Output: 28


Example 2:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down


Example 3:

Input: m = 7, n = 3
Output: 28
Example 4:

Input: m = 3, n = 3
Output: 6
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.
 * */

//思路1：跟之前那道 Climbing Stairs 很类似，那道题是说可以每次能爬一格或两格，问到达顶部的所有不同爬法的个数。
//而这道题是每次可以向下走或者向右走，求到达最右下角的所有不同走法的个数。那么跟爬梯子问题一样，需要用动态规划
//Dynamic Programming 来解，可以维护一个二维数组 dp，其中 dp[i][j] 表示到当前位置(i,j)不同的走法的个数，
//然后可以得到状态转移方程为:  dp[i][j] = dp[i - 1][j]（最后一步需要向右走） + dp[i][j - 1]（最后一步需要向下走）

//思路2:其实还有另一种很数学的解法，实际机器人总共走了 m + n - 2步，其中down是m-1,right是n-1
//相当于排列组合有m+n-2空，从里面选出m-1空放down，剩下的放right，即(m + n - 2)! 除以(m - 1)! 再除以（n - 1）!
public class UniquePaths_62 {
	public int uniquePaths(int m, int n) {
		//解法1
//		int[][] dp = new int[m][n];
//		
//		 for(int[] arr : dp) {
//			 //如果只看第一行和第一列，可以发现走到最右，和走到最下，都是在前一个位置上移动一步
//			 //其它格子的值暂时赋值为1，后面会修改
//		      Arrays.fill(arr, 1);
//		    }
//		 
//		for (int i = 1; i < m; i++) {
//			for (int j = 1; j < n; j++) {
//				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//			}
//		}
//		return dp[m - 1][n - 1];
		
		//解法2
		 double[] factorial=new double[m+n-1];
         factorial[0]=1.0;
         for(int i=1;i<m+n-1;i++)
             factorial[i]=i*factorial[i-1];
         return (int)Math.round(factorial[m+n-2]/factorial[n-1]/factorial[m-1]);
	}
}
