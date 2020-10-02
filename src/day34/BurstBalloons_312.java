package day34;

import day04.jumpGame2_45;

/*
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it 
 * represented by array nums. You are asked to burst all the balloons. If the you burst 
 * balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right 
 * are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * */

//思路：类似题375. 对于像这种求极值问题，一般都要考虑用动态规划 Dynamic Programming 来做，维护一个二维动态数组 dp，
//其中 dp[i][j] 表示打爆区间 [i,j] 中的所有气球能得到的最多金币。题目中说明了边界情况，当气球周围没有气球
//的时候，旁边的数字按1算，这样可以在原数组两边各填充一个1，方便于计算。这道题的最难点就是找状态转移方程，
//还是从定义式来看，假如区间只有一个数，比如 dp[i][i]，那么计算起来就很简单，直接乘以周围两个数字即可更新。
//如果区间里有两个数字，就要算两次了，先打破第一个再打破了第二个，或者先打破第二个再打破第一个，比较两种情况，
//其中较大值就是该区间的 dp 值。假如区间有三个数呢，比如 dp[1][3]，怎么更新呢？如果先打破第一个，剩下两个
//怎么办呢，难道还要分别再遍历算一下吗？这样跟暴力搜索的方法有啥区别呢，还要 dp 数组有啥意思。所谓的状态转移，
//就是假设已知了其他状态，来推导现在的状态，现在是想知道 dp[1][3] 的值，那么如果先打破了气球1，剩下了
//气球2和3，若之前已经计算了 dp[2][3] 的话，就可以使用其来更新 dp[1][3] 了，就是打破气球1的得分加上 
//dp[2][3]。那假如先打破气球2呢，只要之前计算了 dp[1][1] 和 dp[3][3]，那么三者加起来就可以更新 
//dp[1][3]。同理，先打破气球3，就用其得分加上 dp[1][2] 来更新 dp[1][3]
//那么对于有很多数的区间 [i, j]，如何来更新呢？现在是想知道 dp[i][j] 的值，这个区间可能比较大，但是如果
//知道了所有的小区间的 dp 值，然后聚沙成塔，逐步的就能推出大区间的 dp 值了。还是要遍历这个区间内的每个气球，
//就用k来遍历吧，k在区间 [i, j] 中，假如第k个气球最后被打爆，那么此时区间 [i, j] 被分成了三部分，
//[i, k-1]，[k]，和 [k+1, j]，只要之前更新过了 [i, k-1] 和 [k+1, j] 这两个子区间的 dp 值，可以直接用 
//dp[i][k-1] 和 dp[k+1][j]，那么最后被打爆的第k个气球的得分该怎么算呢，你可能会下意识的说，就乘以周围两个
//气球被 nums[k-1] * nums[k] * nums[k+1]，但其实这样是错误的，为啥呢？dp[i][k-1] 的意义是什么呢，
//是打爆区间 [i, k-1] 内所有的气球后的最大得分，此时第 k-1 个气球已经不能用了，同理，第 k+1 个气球也不能
//用了，相当于区间 [i, j] 中除了第k个气球，其他的已经爆了，那么周围的气球只能是第 i-1 个，和第 j+1 个了，
//所以得分应为 nums[i-1] * nums[k] * nums[j+1]，分析到这里，状态转移方程应该已经跃然纸上了吧，如下所示：

//dp[i][j] = max(dp[i][j], nums[i - 1] * nums[k] * nums[j + 1] + dp[i][k - 1] + dp[k + 1][j])                
//( i ≤ k ≤ j )

//有了状态转移方程了，就可以写代码，下面就遇到本题的第二大难点了，区间的遍历顺序。一般来说，遍历所有子区间的
//顺序都是i从0到n，然后j从i到n，然后得到的 [i, j] 就是子区间。但是这道题用这种遍历顺序就不对，在前面的分析
//中已经说了，这里需要先更新完所有的小区间，然后才能去更新大区间，而用这种一般的遍历子区间的顺序，会在更新
//完所有小区间之前就更新了大区间，从而不一定能算出正确的dp值，比如拿题目中的那个例子 [3, 1, 5, 8] 来说，
//一般的遍历顺序是：

//[3] -> [3, 1] -> [3, 1, 5] -> [3, 1, 5, 8] -> [1] -> [1, 5] -> [1, 5, 8] -> [5] 
//-> [5, 8] -> [8] 

//显然不是我们需要的遍历顺序，正确的顺序应该是先遍历完所有长度为1的区间，再是长度为2的区间，再依次累加长度，
//直到最后才遍历整个区间：

//[3] -> [1] -> [5] -> [8] -> [3, 1] -> [1, 5] -> [5, 8] -> [3, 1, 5] -> [1, 5, 8] 
//-> [3, 1, 5, 8]

//这里其实只是更新了 dp 数组的右上三角区域，最终要返回的值存在 dp[1][n] 中，其中n是两端添加1之前
//数组 nums 的个数

//时间复杂度：O（n^3）
public class BurstBalloons_312 {
public int maxCoins(int[] nums) {
        int n = nums.length;
        //给原数组左右各加上一个1
        int[] newNums  = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
        	if(i == 0 || i == n + 1) {
        		newNums[i] = 1;
        	}else {
        		newNums[i] = nums[i - 1];
        	}	
		}
        
        int[][] dp = new int[n + 2][n + 2];
        
        //用len控制当前要计算的小区间的长度，从1开始
        for (int len = 1; len <= n; len++) {
			for (int i = 1; i <= n - len + 1; i++) {//小区间的左index
				int j = i + len - 1;//小区间的右index
				for (int k = i; k <= j; k++) {
					//用k遍历小区间每个数字，从i到j
					//newNums[i - 1] * newNums[k] * newNums[j + 1]为扎破index为k的气球得到的金币数
					//dp[i][k - 1],dp[k + 1][j]分别为k的左右子区间能得到的最大值
					dp[i][j] = Math.max(dp[i][j], newNums[i - 1] * newNums[k] * newNums[j + 1] + 
							dp[i][k - 1] + dp[k + 1][j]);
				}
			}
		}
        return dp[1][n];//dp[1][n]代表区间1-n能得到的金币最大值
        
    }
}
