package day33;

/*
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the 
 * total number of unlock patterns of the Android lock screen, which consist of minimum of m keys 
 * and maximum n keys.

 

Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the 
other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

 

Example:

Input: m = 1, n = 1
Output: 9

 * */

//思路：这道题说的是安卓机子的解锁方法，有9个数字键，如果密码的长度范围在 [m, n] 之间，问所有的解锁模式共有
//多少种，注意题目中给出的一些非法的滑动模式。那么先来看一下哪些是非法的，首先1不能直接到3，必须经过2，同理的
//有4到6，7到9，1到7，2到8，3到9，还有就是对角线必须经过5，例如1到9，3到7等。建立一个二维数组 jumps，
//用来记录两个数字键之间是否有中间键，然后再用一个一位数组 visited 来记录某个键是否被访问过，然后用递归来解，
//先对1调用递归函数，在递归函数中，遍历1到9每个数字的 next，然后找他们之间是否有 jump 数字，如果 next 
//没被访问过，并且 jump 为0，或者 jump 之前被访问过，则对 next 调用递归函数找下一个符合条件数字。
//数字1的模式个数算出来后，由于 1,3,7,9 是对称的，所以乘4即可，然后再对数字2调用递归函数，2,4,6,8 也是
//对称的，再乘4，最后单独对5调用一次，然后把所有的加起来就是最终结果了
public class AndroidUnlockPatterns_351 {
public int numberOfPatterns(int m, int n) {
        int res = 0;
        int[] visited = new int[10];//记录当前数字是否有被访问过，1为访问过，0为未访问过
        int[][] jumps = new int[10][10];//记录有中间数的连线
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;
        //分别计算1，2，5对应的结果
        res += helper(visited, jumps, 1, 1, 0, m, n) * 4;
        res += helper(visited, jumps, 2, 1, 0, m, n) * 4;
        res += helper(visited, jumps, 5, 1, 0, m, n);
       
        return res;
    }

public int helper(int[] visited, int[][] jumps, int cur, int len, int res, int m, int n) {
	if(len > n)return res;
	if(len <= n && len >= m)res++;//当前路径数字个数满足在m,n之间，则总的路径数加1
	
	visited[cur] = 1;//标记当前数字为访问过
	for (int next = 1; next <= 9; next++) {//寻找下一个符合条件的数字
		if(visited[next] == 0 && (jumps[cur][next] == 0 || visited[jumps[cur][next]] == 1)) {
			//如果下一个数字是没访问过的，且当前数字和下一个数字之间没有中间数，或者是这个中间数之前被访问过了，则符合要求，继续递归
			res = helper(visited, jumps, next, len + 1, res, m, n);
		}
	}
	
	//找完当前数字对应的路径数，需要重设其访问状态
	visited[cur] = 0;
	return res;
}
}
