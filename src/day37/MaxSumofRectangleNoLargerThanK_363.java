package day37;

import java.util.TreeSet;

/*
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in 
 * the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],
				 [0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 * */
public class MaxSumofRectangleNoLargerThanK_363 {
	 public int maxSumSubmatrix(int[][] matrix, int k) {
		 for(int i = 0; i < matrix.length; i++){
	            for(int j = 1; j < matrix[0].length; j++){
	                matrix[i][j] += matrix[i][j-1];
	            }
	        }
	        int ans = Integer.MIN_VALUE;
	        for(int c1 = 0; c1 < matrix[0].length; c1++){
	            for(int c2 = c1; c2 < matrix[0].length; c2++){
	                
	                TreeSet<Integer>set = new TreeSet<>();
	                int sum = 0;
	                set.add(0);
	                
	                for(int r = 0; r < matrix.length; r++){
	                    sum += (c1 == 0)? matrix[r][c2]: matrix[r][c2] - matrix[r][c1-1];
	                    Integer find = set.ceiling(sum - k);
	                        
	                    if(find != null)ans = Math.max(ans, sum - (int)find);
	                    set.add(sum);
	                }
	            }
	        }
	        return ans; 
	    }
}
