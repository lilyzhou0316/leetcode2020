package day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/*
 * Given a non-empty string str and an integer k , rearrange the string such that 
 * the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange 
the string, return an empty string "".

Example 1:

str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
相同字母的index相差3，即中间需要2个不同字母隔开


Example 2:

str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.


Example 3:

str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
 * */

//思路：这道题的解法用到了哈希表，堆，和贪婪算法。需要一个哈希表来建立字符和其出现次数之间的映射，
//然后需要一个堆来保存这每一堆映射，按照出现次数来排序。然后如果堆不为空我们就开始循环，
//我们找出k和str长度之间的较小值，然后从0遍历到这个较小值，对于每个遍历到的值，如果此时堆为空了，
//说明此位置没法填入字符了，返回空字符串，否则我们从堆顶取出一对映射，然后把字母加入结果res中，
//此时映射的个数减1，如果减1后的个数仍大于0，则我们将此映射加入临时集合v中，同时str的个数len减1，
//遍历完一次，我们把临时集合中的映射对由加入堆中
public class RearrangeStringkDistanceApart_358 {
	public String rearrangeString(String str, int k) {
		if (k == 0) return str;
		
        String res = "";
        int len = str.length();
        Map<Character, Integer> m = new HashMap<>();//保存每个字母出现的次数
        
        //遍历str将每个字母出现的次数放入map
        for (int i = 0; i < len; i++) {
			if(m.containsKey(str.charAt(i))) {
				int temp = m.get(str.charAt(i));
				m.put(str.charAt(i), temp + 1);
			}else {
				m.put(str.charAt(i), 1);
			}
		};
		//将键值对放入堆中，堆顶为出现次数最小的字母(自定义比较器)
		
		PriorityQueue<Map.Entry<Character,Integer>> q = new PriorityQueue<>(new Comparator<Map.Entry<Character,Integer>>() {
	        @Override
	        public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character,Integer> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }
	    });
		
        for(Entry<Character,Integer> entry : m.entrySet()){
        	q.add(entry);
        }
        
        while (!q.isEmpty()) {
            //vector<pair<int, int>> v;
        	List<Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character,Integer>>();
            int cnt = Math.min(k, len);
            for (int i = 0; i < cnt; ++i) {
                if (q.isEmpty()) return "";
                //取出堆顶元素
                Entry<Character,Integer> entry = q.poll();
                //将堆顶entry的字母加入res
                 res += entry.getKey();
                 //将其出现次数减1
                 entry.setValue(entry.getValue() - 1);
                 //如果此时这个字母的出现次数还大于0，说明是重复元素，加入list
                if (entry.getValue() > 0) list.add(entry);
                --len;
            }
            for (Entry<Character,Integer> a : list) q.add(a);
        }
        return res;
	}
}
