package day12;

import java.util.HashMap;
import java.util.Map;

import day05.increasingTripletSubsequence_334;

/*
 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection（双映射，即一对一） 
between a letter
 in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true


Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false


Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false


Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false


Notes:
You may assume pattern contains only lowercase letters, and str contains 
lowercase letters that may be separated by a single space.
 * */


//我的思路：和题205非常像，只不过这里变为一个字符对一个单词的映射. 用hashmap映射

//思路2:也可以用两个 HashMap 来完成，分别将字符和单词都映射到当前的位置加1，注意这里需要加1就是为了
//避免默认映射值0，因为 C++ 中的 HashMap 的机制是若访问一个不存在的 key 值，会默认建立一个映射值为0的映射。
//那么我们在遇到新字符和单词时，首先看 i 是否已经是 n 了，若相等了，说明此时 pattern 中的字符已经用完了，
//而 str 中还有多余的单词，这样是无法建立一对一映射的，直接返回 false。还有当两个 HashMap 的映射值
//不相同时也返回 false，否则我们同时建立单词和 pattern 字符和当前位置加1之间的映射，循环推出后还是要
//检测 i 是否和 n 相等
public class WordPattern_290 {
public boolean wordPattern(String pattern, String str) {
       
       // char[] temp1 = pattern.toCharArray();
        String[] temp2 = str.split(" ");
        if(pattern.length()!= temp2.length)return false;
        
        //解法1
       
        //int i;
        // Map<Character,String> map = new HashMap<>();
//        for ( i = 0; i < temp1.length; i++) {
//        	
//        	//先建立映射关系
//			 if(!map.containsKey(temp1[i]) && map.containsValue(temp2[i])){
//				return false;
//			}else if(map.containsKey(temp1[i]) && !map.get(temp1[i]).equals(temp2[i])) {
//				return false;
//			}else if(!map.containsKey(temp1[i]) && !map.containsValue(temp2[i])){
//				map.put(temp1[i], temp2[i]);
//			}else if(map.containsKey(temp1[i]) && map.get(temp1[i]).equals(temp2[i])) {
//				map.put(temp1[i], temp2[i]);
//			}
//        }
        //  return i == temp1.length;
        
       //优化
        Map<String,Character> map = new HashMap<>();
        for (int i = 0; i < temp2.length; i++) {
            if (!map.containsKey(temp2[i])) {
                // check if the value has been mapped to before, if so the answer is false
                if (map.containsValue(pattern.charAt(i))) return false;
                map.put(temp2[i], pattern.charAt(i));
            }
            if (map.get(temp2[i]) != pattern.charAt(i)) return false;
        }
        return true;
      
    }
public static void main(String[] args) {
	WordPattern_290 a = new WordPattern_290();
	a.wordPattern("abba", "dog cat cat dog");
}
}
