package day17;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s, partition s such that every substring of the partition is 
 * a palindrome

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.


Example 2:

Input: s = "a"
Output: 0


Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
 * */

//思路：dp.维护一个一维的dp数组，其中dp[i]表示子串 [0, i] 范围内的最小分割数，那么我们最终要返回的就是 
//dp[n-1] 了.如何更新dp[i]呢，前面说过了其表示子串 [0, i] 范围内的最小分割数。那么这个区间的每个位置都
//可以尝试分割开来，所以就用一个变量j来从0遍历到i，这样就可以把区间 [0, i] 分为两部分，
//[0, j-1] 和 [j, i]，那么suppose我们已经知道区间 [0, j-1] 的最小分割数 dp[j-1]，
//因为我们是从前往后更新的，而 j 小于等于 i，所以 dp[j-1] 肯定在 dp[i] 之前就已经算出来了。
//这样我们就只需要判断区间 [j, i] 内的子串是否为回文串了，是的话，dp[i] 就可以用 1 + dp[j-1] 来更新了
public class PalindromePartitioningII_132 {
public int minCut(String s) {
       int[] dp = new int[s.length()];
       for(int i=0;i<s.length();i++) {
    	   dp[i] = i;//初始化值，对于0-i子串来说，最多能分割i次
    	   for (int j = 0; j <= i; j++) {
			if(isPalindrom(s, j, i)) {
				 dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
			}
		}
       }
       return dp[s.length() - 1];
}

public boolean isPalindrom(String s, int l, int r) {
	while(l < r) {
		if(s.charAt(l) != s.charAt(r))return false;
		else {
			l++;
			r--;
		}
	}
	return true;
}


}
