package day36;

/*
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the 
same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
 * */
public class PaintFence_276 {
public int numWays(int n, int k) {
	if (n == 0 || k == 0) {
        return 0;
    }
	//dp[i][0]表示第i块木板涂色与之前一块不同时的最多方法，dp[i][1]表示第i块木板涂色与之前一块相同时的最多方法
    int[][] dp = new int[n][2];
    dp[0][0] = k; // diff = 0，第一块木板，没有与它同色的木板，所以最多有k中颜色可以涂
    dp[0][1] = 0; // same = 1，第一块木板，没有与它同色的木板，所以同色情况下的方法为0
    for (int i = 1; i < n; i++) {
    	//当前木板与前一块木板涂色不同，则当前块可选择的颜色一定是k - 1（与之前一块不同即可）
    	//而之前一块的涂色可能则有dp[i-1][1] + dp[i-1][0]这么多种
        dp[i][0] = (dp[i-1][1] + dp[i-1][0])*(k-1);
        dp[i][1] = dp[i-1][0];//当前木板与前一块木板要涂色相同，则前一块一定与再之前一块颜色不同，所以当前木板涂色方法与之前一块一样
    }
    return dp[n-1][0] + dp[n-1][1];
    }
}
