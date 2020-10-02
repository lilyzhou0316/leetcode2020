package day35;

/*
 * Given two words word1 and word2, find the minimum number of operations required 
 * to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')


Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 * */

//思路：这里需要维护一个二维的数组 dp，其大小为 mxn，m和n分别为 word1 和 word2 的长度。dp[i][j] 表示从
//word1 的前i个字符转换到 word2 的前j个字符所需要的步骤。先给这个二维数组 dp 的第一行第一列赋值，这个很简单
//因为第一行和第一列对应的总有一个字符串是空串，于是转换步骤完全是另一个字符串的长度。跟以往的 DP 题目类似，
//难点还是在于找出状态转移方程，可以举个例子来看，比如 word1 是 "bbc"，word2 是 "abcd"，可以得到 dp 
//数组如下：
//Ø 0 a b c d
//0 0 1 2 3 4
//b 1 1 1 2 3
//b 2 2 1 2 3
//c 3 3 2 1 2
//通过观察可以发现，当 word1[i] == word2[j] 时，dp[i][j] = dp[i - 1][j - 1]，其他情况时，
//dp[i][j] 是其左，左上，上的三个值中的最小值加1，其实这里的左，上，和左上，分别对应的增加，删除，修改操作，
//具体可以参见解法一种的讲解部分，那么可以得到状态转移方程为：
//if(word1[i] == word2[j]) dp[i][j] = dp[i - 1][j - 1]
//else dp[i][j] =  min(min(dp[i - 1][j], dp[i - 1][j - 1]),dp[i][j - 1]) + 1
public class EditDistance_72 {
public int minDistance(String word1, String word2) {
	//两个字符串只要有一个为空时，则直接返回另一个字符串的长度
		if(word1.length() == 0 || word1 == null)return word2.length();
		else if(word2.length() == 0 || word2 == null)return word1.length();
		
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        dp[0][0] = 0;//两个都为空串
        
        //初始化第一列,即第二个单词为空时
        for (int i = 1; i < dp.length; i++) {
			dp[i][0] = i;
		}
        
        //初始化第一行，即第一个单词为空时
        for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = i;
		}
        
        for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				}else {
					//这里的左，上，和左上，分别对应的增加，删除，修改操作
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
				}
			}
		}
        return dp[word1.length()][word2.length()];
        
    }
}
