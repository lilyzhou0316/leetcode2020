package day33;

/*
 * You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to 
the top?

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps


Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints:

1 <= n <= 45
 * */

//思路：dp. dp[n]表示爬n阶台阶总方法数，则dp[n] = dp[n - 1](最后一步还剩一级台阶) + dp[n-2]（最后一步还剩两级台阶）
public class ClimbingStairs_70 {
	public int climbStairs(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		return dp[n];
	}
}
