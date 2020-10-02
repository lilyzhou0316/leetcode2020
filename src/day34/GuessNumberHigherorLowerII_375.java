package day34;

/*
 * We are playing the Guessing Game. The game will work as follows:

I pick a number between 1 and n.
You guess a number.
If you guess the right number, you win the game.
If you guess the wrong number, then I will tell you whether the number I picked is higher 
or lower, and you will continue guessing.
Every time you guess a wrong number x, you will pay x dollars. If you run out of money, 
you lose the game.
Given a particular n, return the minimum amount of money you need to guarantee a win 
regardless of what number I pick.
Example 1:
图见leetcode网站


Input: n = 10
Output: 16
Explanation: The winning strategy is as follows:
- The range is [1,10]. Guess 7.
    - If this is my number, your total is $0. Otherwise, you pay $7.
    - If my number is higher, the range is [8,10]. Guess 9.
        - If this is my number, your total is $7. Otherwise, you pay $9.
        - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
        - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
    - If my number is lower, the range is [1,6]. Guess 3.
        - If this is my number, your total is $7. Otherwise, you pay $3.
        - If my number is higher, the range is [4,6]. Guess 5.
            - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
            - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
            - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
        - If my number is lower, the range is [1,2]. Guess 1.
            - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
            - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to 
guarantee a win.


Example 2:

Input: n = 1
Output: 0
Explanation: There is only one possible number, so you can guess 1 and not have to pay 
anything.


Example 3:

Input: n = 2
Output: 1
Explanation: There are two possible numbers, 1 and 2.
- Guess 1.
    - If this is my number, your total is $0. Otherwise, you pay $1.
    - If my number is higher, it must be 2. Guess 2. Your total is $1.
The worst case is that you pay $1.
 

Constraints:

1 <= n <= 200
 * */

//思路：如果只有一个数字，那么不用猜，cost 为0。如果有两个数字，比如1和2，猜1，即使不对，cost 也比猜2要低。
//如果有三个数字 1，2，3，那么就先猜2，根据对方的反馈，就可以确定正确的数字，所以 cost 最低为2。
//如果有四个数字 1，2，3，4，那么情况就有点复杂了，策略是用k来遍历所有的数字，然后再根据k分成的左右两个区间，
//取其中的较大 cost 加上k。
//当k为1时，左区间为空，所以 cost 为0，而右区间 2，3，4，根据之前的分析应该取3，所以整个 cost 就是 1+3=4。

//当k为2时，左区间为1，cost 为0，右区间为 3，4，cost 为3，整个 cost 就是 2+3=5。

//当k为3时，左区间为 1，2，cost 为1，右区间为4，cost 为0，整个 cost 就是 3+1=4。

//当k为4时，左区间 1，2，3，cost 为2，右区间为空，cost 为0，整个 cost 就是 4+2=6。

//综上k的所有情况，此时应该取整体 cost 最小的，即4，为最后的答案，这就是极小化极大算法
//同时，还需要一个变量来记录整个过程中的最小总花费
//Time complexity : O(n!). 超时
//优化，用一个dp[i][j]数组来保存已经计算过的结果,dp[i][j]表示从i到j范围的最小总花费
public class GuessNumberHigherorLowerII_375 {
	 public int getMoneyAmount(int n) {
		 int[][] dp = new int[n + 1][n + 1];
	        return helper(1, n, dp);
	    }
	 public int helper(int low, int high,int[][] dp) {
		 if(low >= high)return 0;
		 if(low + 1 == high)return low;//如果当前区间只有两个数字（low,high），则返回较小的那个数，即low
		 if(dp[low][high] != 0)return dp[low][high];//如果之前已经计算过当前区间的值，直接返回
		 
		int minTotal = Integer.MAX_VALUE;//记录整个过程中的最小总花费
		
		int mid = (low + high) / 2;
		for (int i = high - 1; i >= mid; i -= 2) {
			//以每一个数字作为当前guess, 然后分别看其左区间和右区间谁更大，取更大值加上当前花费,即为当前数字下的最小值
			//然后和整个过程中的最小总花费比较，取更小值
			int curRes = i + Math.max(helper(low, i - 1,dp), helper(i + 1, high,dp));
			minTotal = Math.min(curRes, minTotal);	
		}
		dp[low][high] = minTotal;
		return minTotal;
		
	}
}
