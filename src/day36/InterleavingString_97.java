package day36;

/*
 * Given s1, s2, and s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true
 

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lower-case English letters.
 * */

//思路：只要是遇到字符串的子序列或是匹配问题直接就上动态规划 Dynamic Programming，其他的都不要考虑。
//一般来说字符串匹配问题都是更新一个二维 dp 数组，核心就在于找出状态转移方程。那么我们还是从题目中给的
//例子出发吧，手动写出二维数组 dp 如下：
//  Ø d b b c a
//Ø T F F F F F
//a T F F F F F
//a T T T T T F
//b F T T F T F
//c F F T T T T
//c F F F T F T

//首先，这道题的大前提是字符串 s1 和 s2 的长度和必须等于 s3 的长度，如果不等于，肯定返回 false。那么当
//s1 和 s2 是空串的时候，s3 必然是空串，则返回 true。所以直接给 dp[0][0] 赋值 true，然后若 s1 和 s2 
//其中的一个为空串的话，那么另一个肯定和 s3 的长度相等，则按位比较，若相同且上一个位置为 True，赋 True，
//其余情况都赋 False，这样的二维数组 dp 的边缘就初始化好了。下面只需要找出状态转移方程来更新整个数组即可，
//我们发现，在任意非边缘位置 dp[i][j] 时，它的左边或上边有可能为 True 或是 False，两边都可以更新过来，
//只要有一条路通着，那么这个点就可能为 True。那么我们得分别来看，如果左边的为 True，那么我们去除当前
//对应的 s2 中的字符串 s2[j - 1] 和 s3 中对应的位置的字符相比（计算对应位置时还要考虑已匹配的s1中的
//字符），为 s3[j - 1 + i], 如果相等，则赋 True，反之赋 False。 而上边为 True 的情况也类似，所以可以
//求出状态转移方程为：

//dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i - 1 + j]) || 
//(dp[i][j - 1] && s2[j - 1] == s3[j - 1 + i]);

//其中 dp[i][j] 表示的是 s2 的前 i 个字符和 s1 的前 j 个字符是否匹配 s3 的前 i+j 个字符，
//根据以上分析，可写出代码
public class InterleavingString_97 {
public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() == 0 || s1 == null)return s2.equals(s3);
        else if(s2.length() == 0 || s2 == null)return s1.equals(s3);
        
        if(s1.length() + s2.length() != s3.length())return false;
        
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];//dp[i][j] 表示的是 s2 的前 i 个字符和 s1 的前 j 个字符是否匹配 s3 的前 i+j 个字符
        
        dp[0][0] = true;//两者都为空串时，为true
        
        //初始化第一行第一列
        
        for (int i = 1; i < dp.length; i++) {
        	//当s2为空，s1不为空时,检查s1和s3是否一一对应并查看前一个位置的情况
			dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
		}
        for (int i = 1; i < dp[0].length; i++) {
        	//当s1为空，s2不为空时，检查s2和s3是否一一对应并查看前一个位置的情况
			dp[0][i] = s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1];
		}
        
        //从（1，1）位置开始
        for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				//检查当前位置的左边或者上边
				//如果左边的为 True(s1为true)，那么我们去除当前对应的 s2 中的字符串 s2[j - 1] 和 s3 中对应
				//的位置的字符相比（计算对应位置时还要考虑已匹配的s1中的字符），为 s3[j - 1 + i], 
				//如果相等，则赋 True，反之赋 False
				//而上边为 True 的情况也类似
				
				dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(j + i - 1)) || 
						(dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j + i - 1));
			}
		}
       return dp[s1.length()][s2.length()]; 
    }
}
