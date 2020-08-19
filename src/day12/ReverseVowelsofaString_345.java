package day12;

/*
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"


Example 2:

Input: "leetcode"
Output: "leotcede"

Note:
The vowels does not include the letter "y".
 * */
//我的思路：双指针
public class ReverseVowelsofaString_345 {
public String reverseVowels(String s) {
        if(s.length() == 0 || s == null)return "";
        if(s.length() == 1)return s;
        
        String vowels = "aeiouAEIOU";
        char[] temp = s.toCharArray();
        int l = 0, r = temp.length - 1;
        //解法1
//        while(l < r) {
//        	if(vowels.contains(temp[l]+"") && (vowels.contains(temp[r]+""))){
//        		char c = temp[l];
//        		temp[l] = temp[r];
//        		temp[r] = c;
//        		l++;
//        		r--;
//        	}else if(vowels.contains(temp[l]+"")) {
//        		r--;
//        	}else if(vowels.contains(temp[r]+"")){
//        		l++;
//        	}else {
//        		l++;
//        		r--;
//        	}
//        }
       
        //解法2:对1优化
      while(l < r) {
    	if(isVowel(temp[l]) && isVowel(temp[r])){
    		char c = temp[l];
    		temp[l] = temp[r];
    		temp[r] = c;
    		l++;
    		r--;
    	}else if(isVowel(temp[l])) {
    		r--;
    	}else if(isVowel(temp[r])){
    		l++;
    	}else {
    		l++;
    		r--;
    	}
    }
        return String.valueOf(temp);
    }

public boolean isVowel(char x) {
	if(x == 'a' || x == 'e' || x == 'i' ||x == 'o' ||x == 'u' 
			||x == 'A' ||x == 'E' ||x == 'I' ||x == 'O' ||x == 'U')return true;
	return false;
}
}
