package day34;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move 
 * to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total 
number of rows in the triangle.
 * */

//思路1:如果要遍历所有的路径的话，那可以是阶乘级的时间复杂度啊，OJ必灭之，趁早断了念想比较好。必须要
//优化时间复杂度啊，题目中给的例子很容易把人带偏，让人误以为贪婪算法可以解题，因为看题例子中的红色数组，
//在根数字2的下方选小的数字3，在3的下方选小的数字5，在5的下方选小的数字1，每次只要选下一层相邻的两个数字中
//较小的一个，似乎就能得到答案了。其实是不对的，贪婪算法可以带到了局部最小，但不能保证每次都带到全局最小，
//很有可能在其他的分支的底层的数字突然变的超级小，但是贪婪算法已经将其他所有分支剪掉了。所以为了保证我们
//能得到全局最小，动态规划Dynamic Programming还是不二之选啊。其实这道题和 Dungeon Game 非常的类似，
//都是用DP来求解的问题。那么其实我们可以不新建dp数组，而是直接复用triangle数组，我们希望一层一层的累加下来，
//从而使得 triangle[i][j] 是从最顶层到 (i, j) 位置的最小路径和，那么我们如何得到状态转移方程呢？其实
//也不难，因为每个结点能往下走的只有跟它相邻的两个数字，那么每个位置 (i, j) 也就只能从上层跟它相邻的两个
//位置过来，也就是 (i-1, j-1) 和 (i-1, j) 这两个位置，那么状态转移方程为：

//triangle[i][j] = min(triangle[i - 1][j - 1], triangle[i - 1][j])

//我们从第二行开始更新，注意两边的数字直接赋值上一行的边界值，那么最终我们只要在最底层找出值最小的数字，
//就是全局最小的路径和啦


//思路2:这种方法可以通过OJ，但是毕竟修改了原始数组triangle，并不是很理想的方法。在网上搜到一种更好的DP方法，
//这种方法复制了三角形最后一行，作为用来更新的一位数组。然后逐个遍历这个DP数组，对于每个数字，和它之后的元素比较
//选择较小的那个数再加上面一行（i-1,j）位置的元素的和做为新的元素，然后一层一层的向上扫描，整个过程和冒泡排序的原理差不多，最后最小的元素都冒到前面，第一个元素即为所求
public class Triangle_120 {
public int minimumTotal(List<List<Integer>> triangle) {
//解法1
//	int cur = 0;
//	for (int i = 1; i < triangle.size(); i++) {
//		for (int j = 0; j < triangle.get(i).size(); j++) {
//			if(j == 0) {
//				//如果当前数字是最左边或者最右边的数字，则它只能加上它的上一行中和它同样在最左边或者最右边的数
//				 cur = triangle.get(i).get(j);
//				cur += triangle.get(i - 1).get(j);
//				triangle.get(i).set(j, cur);
//			}else if(j == triangle.get(i).size() - 1) {
//				 cur = triangle.get(i).get(j);
//				cur += triangle.get(i - 1).get(j - 1);
//				triangle.get(i).set(j, cur);
//			}else {
//				//当前数字是位于中间的数，则可以加上它上面一行的左右两个数
//				 cur = triangle.get(i).get(j);
//				cur += Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j));
//				triangle.get(i).set(j, cur);
//			}
//		}
//	}
//	
//	Collections.sort(triangle.get(triangle.size() - 1));
//	return triangle.get(triangle.size() - 1).get(0);
	
	//解法2
	if(triangle.size() == 0 || triangle == null)
    {
        return 0;
    }
    int rows = triangle.size();
    int dp[] = new int[rows+1];
    
   
    for(int i = rows-1 ; i>=0 ; i--)
    {
        for(int j = 0 ; j<=i ; j++)
        {
        	
            dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            
        }
    }
    
   
    return dp[0];
    }

public static void main(String[] args) {
	Triangle_120 a = new Triangle_120();
	List<List<Integer>> triangle = new ArrayList<List<Integer>>();
	List<Integer> tempIntegers1 = new ArrayList<Integer>();
	List<Integer> tempIntegers2 = new ArrayList<Integer>();
	List<Integer> tempIntegers3 = new ArrayList<Integer>();
	List<Integer> tempIntegers4 = new ArrayList<Integer>();
	tempIntegers1.add(-1);
	triangle.add(tempIntegers1);
	
	
	tempIntegers2.add(2);
	tempIntegers2.add(3);
	triangle.add(tempIntegers2);
	
	
	tempIntegers3.add(1);
	tempIntegers3.add(-1);
	tempIntegers3.add(-3);
	triangle.add(tempIntegers3);
	
	
	tempIntegers4.add(5);
	tempIntegers4.add(3);
	tempIntegers4.add(-1);
	tempIntegers4.add(2);
	triangle.add(tempIntegers4);
	
	
	a.minimumTotal(triangle);
}
}
