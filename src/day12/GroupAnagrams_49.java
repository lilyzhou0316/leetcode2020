package day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

All inputs will be in lowercase.
The order of your output does not matter.
 * */

//我的思路：取出每第一个词，与它后面的词比较看包含的字母是否相等（先排序再比较），相等加入结果集，
//并从原数组里删除那些相等的单词，然后重复此步骤

//思路2：将每个单词加入map，如果当前单词排序后等于map的key排序后的string， 则将它加入对应的key的value

//思路3:用一个大小为 26 的 int 数组来统计每个单词中字符出现的次数，然后将 int 数组转为一个唯一的字符串（相当于把一个原字符串重新排序了），
//然后再跟字符串数组进行映射（找key）

public class GroupAnagrams_49 {
	 public List<List<String>> groupAnagrams(String[] strs) {
		 //解法1
		 List<List<String>> resList = new ArrayList<List<String>>();
		 if(strs.length < 2 )return resList;
		 
		 Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		 for (int i = 0; i < strs.length; i++) {
			 char[] c1 = strs[i].toCharArray();
			 Arrays.sort(c1);
			 String s1 = new String(c1);
			
			if(!map.containsKey(s1)) {
				 ArrayList<String> temp2 = new ArrayList<String>();	
				 temp2.add(strs[i]);
				map.put(s1, temp2);
			}else{
				ArrayList<String> temp2 = map.get(s1);
				temp2.add(strs[i]);
				map.put(s1, temp2);
			}
		}
		for (List<String> list : map.values()) {
			resList.add(list);
		}
		return resList;
		 
		 
		 
	 }
}
