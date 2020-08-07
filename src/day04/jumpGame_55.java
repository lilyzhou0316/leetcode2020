package day04;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index 
 * of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
 which makes it impossible to reach the last index.
 
 Constraints:
 1 <= nums.length <= 3 * 10^4
 0 <= nums[i][j] <= 10^5
 * */

//思路1:动态规划（存在型）,石头过河类题目.要去到第i个石头，则要看是否存在前一个位置j能不能跳到i且跳的步数<=nums[j]
//时间复杂度o(n^2)

//思路2:优化思路1，动态规划。维护一个一维数组 dp，其中 dp[i] 表示达到i位置时剩余的跳力，
//若到达某个位置时跳力为负了，说明无法到达该位置。到达当前位置的剩余跳力跟什么有关呢，
//其实是跟上一个位置的剩余跳力（dp 值）和上一个位置新的跳力（nums 数组中的值）有关，
//这里新的跳力就是原数组中每个位置的数字，因为其代表了以当前位置为起点能到达的最远位置。
//所以当前位置的剩余跳力（dp 值）和当前位置新的跳力中的较大那个数决定了当前能到的最远距离，
//而下一个位置的剩余跳力（dp 值）就等于当前的这个较大值减去1，因为需要花一个跳力到达下一个位置，
//所以就有状态转移方程了：dp[i] = max(dp[i - 1], nums[i - 1]) - 1，如果当某一个时刻 dp 数组的值为负了，
//说明无法抵达当前位置，则直接返回 false，最后循环结束后直接返回 true  即可

//思路3：贪婪算法（greedy）。因为这里并不是很关心每一个位置上的剩余步数，而只希望知道能否到达末尾，
//也就是说我们只对最远能到达的位置感兴趣，所以维护一个变量 reach，表示最远能到达的位置，初始化为0。
//遍历数组中每一个数字，如果当前坐标大于 reach 或者 reach 已经抵达最后一个位置则跳出循环，
//否则就更新 reach 的值为其和 i + nums[i] 中的较大值，其中 i + nums[i] 表示当前位置能到达的最大位置

public class jumpGame_55 {
	 public boolean canJump(int[] nums) {
		 int n = nums.length;
		 if(n == 1)return true;
	        if(nums[0] == 0)return false;
	       //解法1:基本动态规划（存在型）
//	       int n = nums.length;//总共多少个石头
//			 boolean[] f = new boolean[n];//f[i]代表是否能到达位置i，true为能
//			 //初始化,第一块石头不需要跳，直接能到达
//			 f[0] = true;
//			 //开始计算f(1),f(2)...直到f(n-1)
//			 for (int i = 1; i < n; i++) {
//				 f[i] = false;//先假设所有的石头都不能跳到j位置
//				 for (int j = 0; j <i; j++) {//j为i之前的某块石头
//					if (f[j] && i-j<=nums[j]) {//如果存在j满足条件则f(i) = true
//						f[i] = true;
//					}
//				}	
//			}
//			 return f[n-1];//最后求f(n-1)，即是否能到达最后一块石头
	        
	        //解法2:动态规划优化
//	        int[] dp = new int[n];//dp[i]记录当前位置的剩余跳力
//	        dp[0] = 0;//起始位置的剩余跳力为0
//	        for (int i = 1; i < dp.length; i++) {
//	        	//第i个位置上的剩余跳力，是前一个位置的剩余跳力和前一个位置的最大跳力中的较大值，
//	        	//减1是因为从前一个位置跳到当前位置还需要一个跳力
//				dp[i] = Math.max(dp[i-1], nums[i-1]) - 1;
//				if (dp[i] < 0) {//一旦出现dp[i]<0说明不能到达当前位置
//					return false;
//				}
//			}
//	        //如果能遍历完所有位置，说明可以到达最后一个位置
//	        return true;
	        
	        //解法3:greedy
	        int reach = 0;//变量代表前一个位置上能到达的最远位置
	        for (int i = 0; i < n; i++) {
				if (i > reach || reach > n-1) {
					//如果i > reach，说明从前一个位置无法到达当前位置i，
					//如果reach > n-1，说明已经能到达最后一个位置了
					break;
				}
				reach = Math.max(reach, i+nums[i]);//i+nums[i]代表当前位置上能到达的最远距离的index
			}
	        return reach > n-1;
	        }

}
