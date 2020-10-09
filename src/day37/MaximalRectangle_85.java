package day37;

/*
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle 
 * containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],
				 ["1","0","1","1","1"],
				 ["1","1","1","1","1"],
				 ["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = []
Output: 0
Example 3:

Input: matrix = [["0"]]
Output: 0
Example 4:

Input: matrix = [["1"]]
Output: 1
Example 5:

Input: matrix = [["0","0"]]
Output: 0
 

Constraints:

rows == matrix.length
cols == matrix.length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
 * */

//思路：这里我们统计每一行的连续1的个数，使用一个数组 h_max（统计宽度）, 其中 h_max[i][j] 表示第i行，第j个位置水平方向
//连续1的个数，若 matrix[i][j] 为0，那对应的 h_max[i][j] 也一定为0。统计的过程跟建立累加和数组很类似，
//唯一不同的是遇到0了要将 h_max 置0。这个统计好了之后，只需要再次遍历每个位置，首先每个位置的 h_max 值都先用
//来更新结果 res，因为高度为1也可以看作是矩形，然后我们向上方遍历(找高度)，上方 (i-1, j) 位置也会有 h_max 值，但是
//用二者之间的较小值才能构成矩形，用新的矩形面积来更新结果 res，这样一直向上遍历，直到遇到0，或者是越界的时候
//停止，这样就可以找出所有的矩形了
public class MaximalRectangle_85 {
public int maximalRectangle(char[][] matrix) {
	if (matrix.length == 0|| matrix[0].length == 0) return 0;
	
    int res = 0, m = matrix.length, n = matrix[0].length;
    
    int[][] h_max = new int[m][n];//统计每行最大宽度
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == '0') continue;//如果本身值为0，则宽度设置为0
            if (j > 0) h_max[i][j] = h_max[i][j - 1] + 1;//如果本身值为1，则累加
            else h_max[i][0] = 1;
        }
    }
    
    //计算高度
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (h_max[i][j] == 0) continue;//如果当前坐标宽度为0，则最大矩形面积为0
            int mn = h_max[i][j];//否则，找到当前出现过的最大宽度（最大宽度*1为当前不看高度时的最大面积）
            res = Math.max(res, mn);
            //对于这个最大宽度的坐标，找它的可能高度值（看它上面的坐标点是否不为0）
            for (int k = i - 1; k >= 0 && h_max[k][j] != 0; --k) {
                mn = Math.min(mn, h_max[k][j]);//取最小宽度，乘以当前高度
                res = Math.max(res, mn * (i - k + 1));//i - k + 1即为当前高度
            }
        }
    }
    return res;
    }
}
