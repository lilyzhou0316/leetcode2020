package day34;

/*
 * You are given coins of different denominations(面值) and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that 
 * amount. If that amount of money cannot be made up by any combination of the coins, 
 * return -1.
 * 

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
Example 4:

Input: coins = [1], amount = 1
Output: 1
Example 5:

Input: coins = [1], amount = 2
Output: 2
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 231 - 1
 * */

//思路：动态规划 Dynamic Programming 来做，好处是保留了一些中间状态的计算值，可以避免大量的重复计算。
//我们维护一个一维动态数组 dp，其中 dp[i] 表示钱数为i时的最小硬币数的找零，注意由于数组是从0开始的，
//所以要多申请一位，数组大小为 amount+1，这样最终结果就可以保存在 dp[amount] 中了。初始化 dp[0] = 0，
//因为目标值若为0时，就不需要硬币了。其他值可以初始化是 amount+1，为啥呢？因为最小的硬币是1，所以 amount 
//最多需要 amount 个硬币，amount+1 也就相当于当前的最大值了，注意这里不能用整型最大值来初始化，因为在
//后面的状态转移方程有加1的操作，有可能会溢出，除非你先减个1，这样还不如直接用 amount+1 舒服呢。好，接下来
//就是要找状态转移方程了，没思路？不要紧！回归例子1，假设我取了一个值为5的硬币，那么由于目标值是 11，所以
//是不是假如我们知道 dp[6]，那么就知道了组成 11 的 dp 值了？所以更新 dp[i] 的方法就是遍历每个硬币，
//如果遍历到的硬币值小于i值（比如不能用值为5的硬币去更新 dp[3]）时，用 dp[i - coins[j]] + 1 来更新
//dp[i]，所以状态转移方程为：

//dp[i] = min(dp[i], dp[i - coins[j]] + 1);
//其中 coins[j] 为第j个硬币，而 i - coins[j] 为钱数i减去其中一个硬币的值，剩余的钱数在 dp 数组中找到值，
//然后加1和当前 dp 数组中的值做比较，取较小的那个更新 dp 数组
public class CoinChange_322 {
public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];//dp[i]表示目标值为i时所需的最小硬币个数，i从0开始取值
        
        dp[0] = 0;//目标值为0时不需要硬币
        for (int i = 1; i < dp.length; i++) {
			dp[i] = amount + 1;//初始化其它值，因为硬币最小数值为1，所以需要的最大硬币数为amount，
			//可以把 amount + 1作为最大值来比较
		}
        
        for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				//对于当前目标值i来说，如果i - coins[j] >= 0，说明当前遍历到的硬币面值可以用来组成目标数
				if(i >= coins[j]) {
					//dp[i - coins[j]] + 1为[i - coins[j]差值所需要的最少硬币个数加上1个硬币（当前面值的硬币）
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
        //最后处理无法组成目标值的情况
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
