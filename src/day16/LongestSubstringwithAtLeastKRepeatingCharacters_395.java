package day16;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Find the length of the longest substring T of a given string (consists of lowercase 
 * letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.


Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * */

//思路：双指针+递归。递归拆分子串，分治。先统计出每个字符出现的频次，维护一对双指针，从首尾开始统计，
//从首尾往中间排除，如果出现次数小于k则不可能出现在最终子串中，排除并挪动指针，然后得到临时子串，
//依次从头遍历，一旦发现出现频次小于k的字符，以该字符为分割线，分别递归求其最大值返回。

public class LongestSubstringwithAtLeastKRepeatingCharacters_395 {

	public int longestSubstring(String s, int k) {
        int len = s.length();
        if (len == 0 || k > len) return 0;
        if (k < 2) return len;

        return count(s.toCharArray(), k, 0, len - 1);
    }

    private static int count(char[] chars, int k, int p1, int p2) {
        if (p2 - p1 + 1 < k) return 0;//两个指针间的长度小于k则肯定不满足条件
        int[] times = new int[26];  //  26个字母
        //  统计出现频次
        for (int i = p1; i <= p2; ++i) {
            ++times[chars[i] - 'a'];
        }
        //  如果该字符出现频次小于k，则不可能出现在结果子串中
        //  分别排除，然后挪动两个指针
        while (p2 - p1 + 1 >= k && times[chars[p1] - 'a'] < k) {
            ++p1;
        }
        while (p2 - p1 + 1 >= k && times[chars[p2] - 'a'] < k) {
            --p2;
        }

        if (p2 - p1 + 1 < k) return 0;
        //  得到临时子串，再递归处理
        for (int i = p1; i <= p2; ++i) {
            //  如果第i个不符合要求，切分成左右两段分别递归求得
            if (times[chars[i] - 'a'] < k) {
                return Math.max(count(chars, k, p1, i - 1), count(chars, k, i + 1, p2));
            }
        }
        return p2 - p1 + 1;//最后没找到
    }

    
}
