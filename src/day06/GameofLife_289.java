package day06;

import javafx.scene.layout.Border;

/*
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 *  is a cellular automaton devised by the British mathematician John Horton Conway 
 *  in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given 
its current state. The next state is created by applying the above rules simultaneously 
to every cell in the current state, where births and deaths occur simultaneously.

Example:
Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

Follow up:

Could you solve it in-place? Remember that the board needs to be updated at 
the same time: You cannot update some cells first and then use their updated values 
to update other cells.
In this question, we represent the board using a 2D array. In principle, 
the board is infinite, which would cause problems when the active area encroaches 
the border of the array. How would you address these problems?
 * */

//思路：因为要求原地修改（即不能复制一份原数组再修改），所以第一遍遍历数组给需要修改的细胞标记状态
//（比如-1是从活细胞变成死细胞，2是死细胞变成活细胞，这样对它们取绝对值再取2的余数后，对原数组没有影响），
//最后再遍历数组把需要修改的细胞修改为目标值
//注意，这里的边界值处理，一旦超出边界，则默认是死细胞

//四个转换条件：
//1周围少于2个1，从1变0（即-1状态）
//1周围有2个或者3个1，不变
//1周围超过3个1，变0（-1）
//0周围正好3个1，变1（2）

//8个方向：以[i,j]为基点，分别是[i,j-1,[i-1,j-1],[i-1,j],[i-1,j+1],
//[i,j+1],[i+1,j+1],[i+1,j],[i+1,j-1]
public class GameofLife_289 {
	 public void gameOfLife(int[][] board) {
		 int m = board.length;
		 int n =  board[0].length;
		 int[] steps = {0,1,-1};//以某个点为中心，走的步数（这样可以取到周围8个格子值）
		
		 //第一遍遍历，做标记
		 for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int count = 0;//统计每一个细胞周围的活细胞数量
				
				for (int k1 = 0; k1 < 3; k1++) {
					for (int k2 = 0; k2 < 3; k2++) {
						if (!(steps[k1] == 0 && steps[k2] == 0)) {//如果向某个方向走了一步的话
							//相邻格子的坐标点,相对于原位置【i,j】
							int row = i + steps[k1];
							int column = j + steps[k2];
							
							//查看相邻格子是否为1
							if((0 <= row && row < m ) && (0 <= column && column < n) && Math.abs(board[row][column]) == 1) {//相邻格子也不能超出边界
								count++;
							}
							
						}

					}
				}
				//如果相邻格子的1满足条件1，3，4中某条，则标记
				if((board[i][j] == 1 && count < 2) || (board[i][j] == 1 && count > 3))board[i][j] = -1;//活细胞变死细胞
				if(board[i][j] == 0 && count == 3)board[i][j] = 2;//死细胞变活细胞
			}
		}
		 
		 //第二遍遍历，修改值
		 for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][j] == -1) {
						board[i][j] = 0;
					}
					if (board[i][j] == 2) {
						board[i][j] = 1;
					}
				}
			}
	        
	    }
}
