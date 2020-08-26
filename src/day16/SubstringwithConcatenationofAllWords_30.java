package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * You are given a string, s, and a list of words, words, that are all of the same length.
 *  Find all starting indices of substring(s) in s that is a concatenation of each word in 
 *  words exactly once and without any intervening characters.

 

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.


Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
 * */


//思路：sliding window变形，字符串s的长度n为 18，words 数组中有两个单词 (cnt=2)，每个单词的长度 len 
//均为3，那么遍历的顺序为 0，3，6，8，12，15，然后偏移一个字符 1，4，7，9，13，16，然后再偏移一个字符
//2，5，8，10，14，17，这样就可以把所有情况都遍历到，还是先用一个 HashMap m1 来记录 words 里的所有词，
//然后从0开始遍历，用 left 来记录左边界的位置，count 表示当前已经匹配的单词的个数。然后一个单词一个单词
//的遍历，如果当前遍历的到的单词t在 m1 中存在，那么将其加入另一个 HashMap m2 中，如果在 m2 中个数小于等于
//m1 中的个数，那么 count 自增1，如果大于了，则需要做一些处理，比如下面这种情况：s = barfoofoo, 
//words = {bar, foo, abc}，给 words 中新加了一个 abc ，目的是为了遍历到 barfoo 不会停止，当遍历到
//第二 foo 的时候,  m2[foo]=2, 而此时 m1[foo]=1，这时候已经不连续了，所以要移动左边界 left 的位置，
//先把第一个词 t1=bar 取出来，然后将 m2[t1] 自减1，如果此时 m2[t1]<m1[t1] 了，说明一个匹配没了，
//那么对应的 count 也要自减1，然后左边界加上个 len，这样就可以了。如果某个时刻 count 和 cnt 相等了，
//说明成功匹配了一个位置，将当前左边界 left 存入结果 res 中，此时去掉最左边的一个词，同时 count 自减1，
//左边界右移 len，继续匹配。如果匹配到一个不在 m1 中的词，说明跟前面已经断开了，重置 m2，count 为0，
//左边界 left 移到 j+len
public class SubstringwithConcatenationofAllWords_30 {
public List<Integer> findSubstring(String s, String[] words) {
	List<Integer> res = new ArrayList<Integer>();
    if(s.length() == 0 || words.length == 0)return res;
    int totalLen = words[0].length()*words.length;
    if(s.length() < totalLen)return res;
    
    //记录words中每个单词出现的次数
    Map<String, Integer> map1 = new HashMap<String, Integer>();
    for(String str : words) {
    	if(map1.containsKey(str)) {
    		int temp = map1.get(str);
    		map1.put(str, temp + 1);
    	}else {
    		map1.put(str, 1);
    	}
    }
    
    int n = s.length(), cnt = words.length, len = words[0].length();
   
    for (int i = 0; i < len; ++i) {//这层循环是为了让以每一个字符开头的子串都会遍历到
        int left = i,//左边界
        		count = 0;//记录当前子串中匹配的单词个数
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        for (int j = i; j <= n - len; j += len) {//j为右边界，每次按一个单词的长度遍历
            String t = s.substring(j, j + len);//每次截取一个单词
            if (map1.containsKey(t)) {//如果当前单词是words里出现的单词
            	//给m2里对应单词的出现次数加1
            	if(map2.containsKey(t)) {
            		 int temp1 = map2.get(t);
                     map2.put(t, temp1 + 1);
            	}else {
            		map2.put(t, 1);
            	}
               
            	//如果次数加1后，m2里的该单词出现次数不超过m1里的，则匹配上了，count加1
                if (map2.get(t) <= map1.get(t)) {
                    ++count;
                } else {//否则，没有匹配上，当前单词在子串中出现的次数太多了
                    while (map2.get(t) > map1.get(t)) {
                    	//把当前左边界上的单词从子串中移出去，直到当前单词的出现次数符合条件
                        String t1 = s.substring(left, left + len);
                        int temp2 = map2.get(t1);
                        map2.put(t1, temp2 - 1);
                        //如果移出去的单词是之前的匹配单词之一，则count要减1
                        if (map2.get(t1) < map1.get(t1)) --count;
                        left += len;//移动左边界
                    }
                }
                if (count == cnt) {//如果匹配单词数量达到words的长度，说明都匹配上了
                    res.add(left);//记录当前左边界
                    //移除左边界上的单词
                    int temp3 = map2.get(s.substring(left, left + len));
                    map2.put(s.substring(left, left + len), temp3 - 1);
                    --count;
                    left += len;
                }
            } else {
                map2.clear();
                count = 0;
                left = j + len;
            }
        }
    }
    return res;
    }
}
