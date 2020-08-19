package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*
 * Given a string, we can "shift" each of its letter to its successive letter,
 *  for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings 
that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each  inner  list's elements must follow the 
lexicographic（词典的） order.
 * */

//我的思路：map映射，将每个词加入到map中，如果当前词和key的shifting规律不一致，则直接加入map形成新key，
//否则加入相同shifting规律的词的value

//思路2:上面那个方法的不高效之处在于对于每个遍历到的字符串，都要和 HashMap 中所有的关键字都比较一次，而其实
//我们可以更加巧妙的利用偏移字符串的特点，那就是字符串的每个字母和首字符的相对距离都是相等的，比如 abc 
//和 efg 互为偏移，对于 abc 来说，b和a的距离是1，c和a的距离是2，对于 efg 来说，f和e的距离是1，g和e的距离
//是2。再来看一个例子，az 和 yx，z和a的距离是 25，x和y的距离也是 25 (直接相减是 -1，这就是要加 26 
//然后取余的原因)，那么这样的话，所有互为偏移的字符串都有个 unique 的距离差，根据这个来建立映射就可以很好
//的进行单词分组了

public class GroupShiftedStrings_249 {
	//解法1
//	public List<List<String>> groupStrings(String[] strings) {
//		List<List<String>> resList = new ArrayList<List<String>>();
//		if(strings.length == 0)return resList;
//		
//		if(strings.length == 1) {
//			List<String> tempList = new ArrayList<String>();
//			tempList.add(strings[0]);
//			resList.add(tempList);
//			return resList;
//		}
//		//这里用TreeSet<String>装有相同shifting规律的string，这样既可以去重，又能自动排序
//		Map<String,TreeSet<String>> map = new HashMap<>();
//		for (int i = 0; i < strings.length; i++) {
//			boolean flag = false;
//			for (String str : map.keySet()) {
//				//遍历map的key，如果当前元素的shifting规律和key一致，则加入对应key的value
//				if(isShifting(str, strings[i])) {
//					TreeSet<String> temp = map.get(str);
//					temp.add(strings[i]);
//					map.put(str, temp);
//					flag = true;
//				}
//			}
//			//出第二层循环如果没有找到shifting规律一致的key，则直接加入map
//			if(!flag) {
//				TreeSet<String> temp = new TreeSet<String>();
//				temp.add(strings[i]);
//				map.put(strings[i], temp);
//			}
//		}
//		//最后取出map的所有value值
//		for (TreeSet<String> list : map.values()) {
//			List<String> tempList = new ArrayList<String>();
//			for (String string : list) {
//				tempList.add(string);
//			}
//			resList.add(tempList);
//		}
//		return resList;
//	}
//	
//	public boolean isShifting(String s1, String s2) {
//		//判断两个stringshifting规律是否一致
//		if(s1.length() != s2.length())return false;
//		
//		for (int i = 1; i <s1.length(); i++) {
//			if((s1.charAt(i) - s1.charAt(i - 1)) != (s2.charAt(i) - s2.charAt(i - 1)))return false;
//		}
//		return true;
//		
//	}
	
	//解法2
	public List<List<String>> groupStrings(String[] strings) {
		//用map的key记录每个str的每个字母与首字母的距离差，如果两个str的距离差一样，则满足条件
		List<List<String>> resList = new ArrayList<List<String>>();
		if(strings.length == 0)return resList;
		
		if(strings.length == 1) {
			List<String> tempList = new ArrayList<String>();
			tempList.add(strings[0]);
			resList.add(tempList);
			return resList;
          }
		Map<String,TreeSet<String>> map = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			String t = "";
			for (char c : strings[i].toCharArray()) {
				//记录每个str的每个字母与首字母的距离差
				t += c - strings[i].charAt(0) + ",";
			}
			if(!map.containsKey(t)) {
				//如果map里没有这个距离差，则直接加入map
				TreeSet<String> tempSet = new TreeSet<String>();
				tempSet.add(strings[i]);
				map.put(t, tempSet);
			}else {
				//如果map里已经有这个距离差了，则加入对应的value
				TreeSet<String> tempSet = map.get(t);
				tempSet.add(strings[i]);
				map.put(t, tempSet);
			}
		}
		//最后取出map的所有value值
		for (TreeSet<String> list : map.values()) {
			List<String> tempList = new ArrayList<String>();
			for (String string : list) {
				tempList.add(string);
			}
			resList.add(tempList);
		}
		return resList;
	}
}
