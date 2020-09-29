package day33;

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the 
bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2

Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
 * */

//思路：类似题62， 但要注意处理有障碍物的格子。首先考虑起始位置是否有障碍物，如果有，则机器人无法移动，返回0，
//然后先处理第一行和第一列，如果当前格子值为0（即没有障碍物），且它的前一个格子值为1时，
//则将其值设为1（即题62，初始化第一行第一列值），反之，设为0
//最后考虑往中间走，仍然满足题62的动态方程：dp[i][j] = dp[i][j-1] + dp[i-1][j],但是如果碰到障碍物（值为1），
//则直接将该格子值改为0,只有当前格子值不为1时，才使用动态方程
public class UniquePathsII_63 {
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        
        //起始格子有障碍物，直接返回0
        if(obstacleGrid[0][0] == 1)return 0;
        
        //初始化第一行第一列值
        obstacleGrid[0][0] = 1;
        
        //如果当前格子值为0（即没有障碍物），且它的前一个格子值为1时，
      //则将其值设为1（即题62，初始化第一行第一列值），反之，设为0
        for (int i = 1; i < row; i++) {
			if(obstacleGrid[i][0] != 1 && obstacleGrid[i - 1][0] == 1)obstacleGrid[i][0] = 1;
			else obstacleGrid[i][0] = 0;
		}
        
        for (int i = 1; i <column; i++) {
        	if(obstacleGrid[0][i] != 1 && obstacleGrid[0][i - 1] == 1)obstacleGrid[0][i] = 1;
        	else obstacleGrid[0][i] = 0;
		}
        
        //开始遍历其它格子
        for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				if(obstacleGrid[i][j] == 1) {
					//碰到障碍物
					obstacleGrid[i][j] = 0;
				}else {
					obstacleGrid[i][j] = obstacleGrid[i- 1][j] + obstacleGrid[i][j - 1];
				}
			}
		}
        return obstacleGrid[row - 1][column - 1];
    }
}
