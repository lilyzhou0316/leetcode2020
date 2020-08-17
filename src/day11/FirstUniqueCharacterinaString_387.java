package day11;

/*
 * Given a string, find the first non-repeating character in it and return its index. 
 * If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode"
return 2.
 

Note: You may assume the string contains only lowercase English letters.
 * */

//我的思路：遍历字符串的每一个字符，如果它不是重复字符，一定满足firstIndexOf == lastIndexOf

//思路2:用一个数组或者map记录每个字符出现的次数，然后找到第一个出现次数为1的位置
public class FirstUniqueCharacterinaString_387 {
	 public int firstUniqChar(String s) {
	        if(s.length() == 0 || s == null)return -1;
	        if(s.length() == 1)return 0;
	        
	        //解法1
//	        for (int i = 0; i < s.length(); i++) {
//				if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))return i;
//			}
//	        return -1;
	        
	        //解法2:
	        int n = s.length();
	        int[] count = new int[26];
	        for(int i=0;i<n;i++){
	            int index = s.charAt(i) - 'a';
	            count[index]++;
	        }
	        for(int i=0;i<n;i++){
	            int index = s.charAt(i) - 'a';
	           if(count[index] == 1){
	               return i;
	           }
	        }
	        return -1;
	    }
}
