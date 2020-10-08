package day36;

/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing 
 * only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

 * */

//思路1:建立一个二维 dp 数组，其中 dp[i][j] 表示到达 (i, j) 位置所能组成的最大正方形的边长。我们首先来考虑
//边界情况，也就是当i或j为0的情况，那么在首行或者首列中，必定有一个方向长度为1，那么就无法组成长度超过1的正方形，
//最多能组成长度为1的正方形，条件是当前位置为1。边界条件处理完了，再来看一般情况的递推公式怎么办，对于任意一点
//dp[i][j]，由于该点是正方形的右下角，所以该点的右边，下边，右下边都不用考虑，关心的就是左边，上边，和左上边。
//这三个位置的dp值 suppose 都应该算好的，还有就是要知道一点，只有当前 (i, j) 位置值为1，dp[i][j] 
//才有可能大于0，否则 dp[i][j] 一定为0。当 (i, j) 位置为1，此时要看 dp[i-1][j-1], dp[i][j-1]，和 
//dp[i-1][j] 这三个位置，我们找其中最小的值，并加上1，就是 dp[i][j] 的当前值了，这个并不难想，
//毕竟不能有0存在，所以只能取交集，最后再用 dp[i][j] 的值来更新结果 res 的值即可

//思路2:优化，用一个一维数组就可以解决，为了处理边界情况，padding 了一位，所以 dp 的长度是 m+1，然后还
//需要一个变量 pre 来记录上一个层的 dp 值，我们更新的顺序是行优先，就是先往下遍历，用一个临时变量t保存当前 
//dp 值，然后看如果当前位置为1，则更新 dp[i] 为 dp[i], dp[i-1], 和 pre 三者之间的最小值，再加上1，
//来更新结果 res，如果当前位置为0，则重置当前 dp 值为0，因为只有一维数组，每个位置会被重复使用
public class MaximalSquare_221 {
	 public int maximalSquare(char[][] matrix) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        
	        //解法1
	      //dp[i][j] 表示到达 (i, j) 位置所能组成的最大正方形的边长,即(i,j)为当前正方形的右下角
//	        int[][] dp = new int[m][n];
//	        int res = 0;
//	        
//	        for (int i = 0; i < m; i++) {
//				for (int j = 0; j < n; j++) {
//                    //初始化第一行第一列
//	        //如果当前位置值为1，则最大正方形边长也为1
//                    if(i == 0 || j == 0)dp[i][j] = matrix[i][j] - '0';
//					else if(matrix[i][j] - '0' == 1) {
//                        //从位置（1，1）开始，计算每个位置的可能最大正方形边长
//						//如果当前位置值为1,才有可能组成正方形
//						//查看当前位置的左，上及左上三个点的dp值，取其最小值然后加1即为当前位置的dp值
//						dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
//					}
//					res = Math.max(res, dp[i][j]);
//				}
//			}
//	        return res * res;
	        
	        //解法2
	        int[] dp = new int[m + 1];
	        int res = 0, pre = 0;//pre保存前面一列同行位置的值，即当前位置的左边位置的值（因为这里更新的是列）
	        for (int j = 0; j < n; ++j) {
	            for (int i = 1; i <= m; ++i) {
	                int temp = dp[i];
	                if (matrix[i - 1][j] == '1') {
	                	 //dp[i]保存当前位置的左上位置的值
                        //dp[i - 1]保存当前位置的上面位置的值
                        //pre保存当前位置的左边位置的值
	                    dp[i] = Math.min(dp[i], Math.min(dp[i - 1], pre)) + 1;
	                    res = Math.max(res, dp[i]);
	                } else {
	                    dp[i] = 0;
	                }
	                pre = temp;
	            }
	        }
	        return res * res;
	    }
}
