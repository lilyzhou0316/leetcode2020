package day37;

/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to 
decode it.

The answer is guaranteed to fit in a 32-bit integer.

 

Example 1:

Input: s = "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "0"
Output: 0
Explanation: There is no character that is mapped to a number starting with '0'. We cannot 
ignore a zero when we face it while decoding. So, each '0' should be part of "10" --> 'J' 
or "20" --> 'T'.
Example 4:

Input: s = "1"
Output: 1
 

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 * */

//思路1:backtrack,有点类似题93（拆分数字成合法id address），这里合法的数字格式只有1-26，不能有先导0

//思路2:这道题要求解码方法，跟之前那道 Climbing Stairs 非常的相似，但是还有一些其他的限制条件，比如说一位数
//时不能为0，两位数不能大于 26，其十位上的数也不能为0，除去这些限制条件，跟爬梯子基本没啥区别，也勉强算特殊的
//斐波那契数列，当然需要用动态规划 Dynamci Programming 来解。建立一维 dp 数组，其中 dp[i] 表示s中前i个
//字符组成的子串的解码方法的个数，长度比输入数组长多多1，并将 dp[0] 初始化为1。现在来找状态转移方程，
//dp[i] 的值跟之前的状态有着千丝万缕的联系，就拿题目中的例子2来分析吧，当 i=1 时，对应s中的字符是 s[0]='2'，
//只有一种拆分方法，就是2，注意 s[0] 一定不能为0，这样的话无法拆分。当 i=2 时，对应s中的字符是 s[1]='2'，
//由于 s[1] 不为0，那么其可以被单独拆分出来，就可以在之前 dp[i-1] 的每种情况下都加上一个单独的2，这样 dp[i]
//至少可以有跟 dp[i-1] 一样多的拆分情况，接下来还要看其能否跟前一个数字拼起来，若拼起来的两位数小于等于26，
//并且大于等于 10（因为两位数的高位不能是0），那么就可以在之前 dp[i-2] 的每种情况下都加上这个二位数，
//所以最终 dp[i] = dp[i-1] + dp[i-2]，是不是发现跟斐波那契数列的性质吻合了。所以0是个很特殊的存在，
//若当前位置是0，则一定无法单独拆分出来，即不能加上 dp[i-1]，就只能看否跟前一个数字组成大于等于 10 且
//小于等于 26 的数，能的话可以加上 dp[i-2]，否则就只能保持为0了。具体的操作步骤是，在遍历的过程中，
//对每个数字首先判断其是否为0，若是则将 dp[i] 赋为0，若不是，赋上 dp[i-1] 的值，然后看数组前一位是否存在，
//如果存在且满足前一位是1，或者和当前位一起组成的两位数不大于 26，则当前 dp[i] 值加上 dp[i - 2]。
//最终返回 dp 数组的最后一个值即可
public class DecodeWays_91 {
public int numDecodings(String s) {
        if(s.charAt(0) - '0' == 0)return 0;//以0开头的都不符合要求
        
        int[] dp = new int[s.length() + 1];//dp[i] 表示s中前i个字符组成的子串的解码方法的个数
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
			if(s.charAt(i - 1) - '0' == 0) {
				//当前数字为0，则给dp[i]赋值为0（它不能单独拆分）
				dp[i] = 0;
			}else {
				//否则，当前数字可以单独拆分出来
				dp[i] = dp[i - 1];
			}
			
			//不管当前数字能不能单独拆开，都要看它是否能和前一位数组成1-26之间的数字,注意这里前一位数不能为0
			if(s.charAt(i - 2) - '0' == 1 || (s.charAt(i - 2) - '0' == 2 && s.charAt(i - 1) - '0' < 7)) {
				dp[i] += dp[i - 2];
			}
		}
        return dp[dp.length - 1];
    }

}
