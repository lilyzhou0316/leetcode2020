package day12;

import java.util.Arrays;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/*
 * Given a string s1, we may represent it as a binary tree by partitioning it to 
 * two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a 
scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", 
it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.


Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true


Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
 * */


//思路1:递归的解法。 简单的说，就是 s1 和 s2 是 scramble 的话，那么必然存在一个在 s1 上的长度 l1，
//将 s1 分成 s11 和 s12 两段，同样有 s21 和 s22，那么要么 s11 和 s21 是 scramble 的并且 s12 和 
//s22 是 scramble 的；要么 s11 和 s22 是 scramble 的并且 s12 和 s21 是 scramble 的。 
//就拿题目中的例子 rgeat 和 great 来说，rgeat 可分成 rg 和 eat 两段， great 可分成 gr 和 eat 两段，
//rg 和 gr 是 scrambled 的， eat 和 eat 当然是 scrambled

//思路2:根据以往的经验来说，根字符串有关的题十有八九可以用 DP 来做，那么难点就在于如何找出状态转移方程。
//这其实是一道三维动态规划的题目，使用一个三维数组 dp[i][j][n]，其中i是 s1 的起始字符，j是 s2 的起始字符，
//而n是当前的字符串长度，dp[i][j][len] 表示的是以i和j分别为 s1 和 s2 起点的长度为 len 的字符串是不是
//互为 scramble。有了 dp 数组接下来看看状态转移方程，也就是怎么根据历史信息来得到 dp[i][j][len]。
//判断这个是不是满足，首先是把当前 s1[i...i+len-1] 字符串劈一刀分成两部分，然后分两种情况：
//第一种是左边和 s2[j...j+len-1] 左边部分是不是 scramble，以及右边和 s2[j...j+len-1] 右边部分是不是
//scramble；
//第二种情况是左边和 s2[j...j+len-1] 右边部分是不是 scramble，以及右边和 s2[j...j+len-1]
//左边部分是不是 scramble。如果以上两种情况有一种成立，说明 s1[i...i+len-1] 和 s2[j...j+len-1] 
//是 scramble 的。而对于判断这些左右部分是不是 scramble 是有历史信息的，因为长度小于n的所有情况都在前面
//求解过了（也就是长度是最外层循环）。上面说的是劈一刀的情况，对于 s1[i...i+len-1] 有 len-1 种劈法，
//在这些劈法中只要有一种成立，那么两个串就是 scramble 的。总结起来状态转移方程是：
//dp[i][j][len] = (dp[i][j][k] && dp[i+k][j+k][len-k] || dp[i][j+len-k][k] && dp[i+k][j][len-k])

//对于所有 1<=k<len，也就是对于所有 len-1 种劈法的结果求或运算。因为信息都是计算过的，
//对于每种劈法只需要常量操作即可完成，因此求解递推式是需要 O(len)（因为 len-1 种劈法）。
//如此总时间复杂度因为是三维动态规划，需要三层循环，加上每一步需要线行时间求解递推式，
//所以是 O(n^4)。虽然已经比较高了，但是至少不是指数量级的，动态规划还是有很大优势的，空间复杂度是 O(n^3)


public class ScrambleString_87{
public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2))return true;
        //检查两个str是否是anagram
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }

//        //解法1
//        for (int i = 1; i < s1.length(); ++i) {
//        	//将s1,s2任意划分成两部分,如果长度相等的对应的两部分都是是scramble的，则true
//        	 if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
//                 return true;
//             if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
//                 return true;
//
//        }
//        return false;
        
        //解法2
        int n = s1.length();
        
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
      //dp[i][j][len] 表示从字符串 S 中 i 开始,长度为 len 的字符串,是否能变换为从字符串 T 中 j 开始
      //长度为 len 的字符串，true为能
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 初始化单个字符的情况，对于长度是 1 的子串，只有相等才能变过去
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        // 枚举长度 2～n
        for (int len = 2; len <= n; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];

    }
}
