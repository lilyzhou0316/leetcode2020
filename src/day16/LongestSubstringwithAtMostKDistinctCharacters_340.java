package day16;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string, find the length of the longest substring T that contains at most 
 * k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
 * *
 */
 
//思路：类似题159，把2换成k即可
public class LongestSubstringwithAtMostKDistinctCharacters_340 {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if(s.length() <= 2)return s.length();
		
//		int max = 0;
//		StringBuilder str = new StringBuilder();
//		int count = 0;//记录子串里有几个不同字母
//		int i = 0;
//		while(i<s.length()) {
//			if(str.length() == 0 || (count < k && str.indexOf(s.charAt(i)+"") < 0)) {
//				//str为空，或者str不包含当前字符且count<2时，直接加入str,并让count加1
//				str.append(s.charAt(i));
//				count++;
//				max = Math.max(max, str.length());
//				i++;
//			}else if(count <= k && str.indexOf(s.charAt(i)+"") >= 0) {
//				//如果str已经有当前字符了，且count <= 2，则直接加入
//				str.append(s.charAt(i));
//				max = Math.max(max, str.length());
//				i++;
//			}else {
//				//其它情况，则需要从左边开始删除str中的字符
//				str.deleteCharAt(0);
//			}
//		}
//		return max;
		
		//解法2，用map映射每个字符及出现次数，一旦map里的键值对超过k个，则需要从map里按顺序删除字母
		int res = 0, left = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
        	//记录当前字符的映射
            if(map.containsKey(s.charAt(i))) {
            	int t = map.get(s.charAt(i));
            	map.put(s.charAt(i), t + 1);
            }else {
            	map.put(s.charAt(i), 1);
            }
            //当map中的键值对超过k时
            while (map.size() > k) {
            	//从left开始删除字符
            	int t = map.get(s.charAt(left));
            	map.put(s.charAt(left), t - 1);
                if (map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                ++left;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
}
	}
