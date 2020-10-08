package day36;

import java.util.Arrays;

/*
 * The demons（恶魔） had captured the princess (P) and imprisoned her in the bottom-right 
 * corner of a dungeon（地牢）. The dungeon consists of M x N rooms laid out 
 * in a 2D grid. Our valiant（英勇） knight (K) was initially positioned in the top-left room and must 
 * fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his
 health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon 
entering these rooms; other rooms are either empty (0's) or contain magic orbs（宝珠） that increase 
the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only 
rightward or downward in each step.

Write a function to determine the knight's minimum initial health so that he is able to 
rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 
7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the 
bottom-right room where the princess is imprisoned.
 * */

//思路:建立一个二维数组 dp，其中 dp[i][j] 用来表示从当前位置 (i, j) 出发的最小起始血量，最先处理的是公主所在
//的房间的起始生命值，然后慢慢向第一个房间扩散，不断的得到各个位置的最优的生命值。逆向推正是本题的精髓所在啊，
//仔细想想也是，如果从起始位置开始遍历，我们并不知道初始时应该初始化的血量，但是到达公主房间后，我们知道血量
//至少不能小于1，如果公主房间还需要掉血的话，那么掉血后剩1才能保证起始位置的血量最小。那么下面来推导状态转移方程，
//首先考虑每个位置的血量是由什么决定的，骑士会挂主要是因为去了下一个房间时，掉血量大于本身的血值，而能去的房间
//只有右边和下边，所以当前位置的血量是由右边和下边房间的可生存血量决定的，进一步来说，应该是由较小的可生存血量
//决定的，因为较我们需要起始血量尽可能的少，因为我们是逆着往回推，骑士逆向进入房间后 PK 后所剩的血量就是骑士正向
//进入房间时 pk 前的起始血量。所以用当前房间的右边和下边房间中骑士的较小血量减去当前房间的数字，
//如果是负数或着0，说明当前房间是正数，这样骑士进入当前房间后的生命值是1就行了，因为不会减血。而如果差是正数的话，
//当前房间的血量可能是正数也可能是负数，但是骑士进入当前房间后的生命值就一定要是这个差值。所以我们的状态转移
//方程是 dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])。为了更好的处理边界情况，
//我们的二维 dp 数组比原数组的行数列数均多1个，先都初始化为整型数最大值 INT_MAX，由于我们知道到达公主房间后，
//骑士火拼完的血量至少为1，那么此时公主房间的右边和下边房间里的数字我们就都设置为1，这样到达公主房间的生存血量
//就是1减去公主房间的数字和1相比较，取较大值，就没有问题了

//思路2:可以对空间进行优化，使用一个一维的 dp 数组，并且不停的覆盖原有的值
public class DungeonGame_174 {
public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        //解法1
//        int[][] dp = new int[m + 1][n + 1];
//        //把dp初始化为整数最大值
//        for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp[0].length; j++) {
//				dp[i][j] = Integer.MAX_VALUE;
//			}
//		}
        //因为骑士的起始血量最小值为1，所以把公主房间右边和下面的房间的值初始化为1
       // dp[m][n - 1] = dp[m - 1][n] = 1;
        
        //从公主房间开始，往回推每个dp[i][j]的值
        //for (int i = m - 1; i >= 0; i--) {
			//for (int j = n - 1; j >= 0; j--) {
				//先看当前房间的右边和下面房间谁的可生存血量更小
				//然后用该值减去当前房间的血量值，与1（任意房间所需最小生存血量值）比较，取较大值
				//dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
		//	}
	//	}
        //return dp[0][0];
        
        //解法2
        int[] dp = new int[n + 1];//用一维数组保存每一行的值，直到更新到第一行
        
        //其它值初始化为整型最大值
        Arrays.fill(dp, Integer.MAX_VALUE);
        //把公主房间初始化为1
        dp[n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
        	for (int j = n - 1; j >= 0; j--) {
			dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
		}
		}
        return dp[0];
    }
}
