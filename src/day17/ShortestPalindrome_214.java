package day17;


/*
 * Given a string s, you are allowed to convert it to a palindrome by adding 
 * characters in front of it. Find and return the shortest palindrome you can find
 *  by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"


Example 2:

Input: "abcd"
Output: "dcbabcd"
 * */

//思路1：双指针+recursion.这里使用双指针来找出字符串s的最长回文前缀的大概范围，这里所谓的最长回文前缀
//是指从开头开始到某个位置为止是回文串，比如 "abbac" 这个子串，可以知道前四个字符组成的回文串 "abba" 
//就是最长回文前缀。方法是指针i和j分别指向s串的开头和末尾，若 s[i] 和 s[j] 相等，则i自增1，j自减1，
//否则只有j自减1。需要注意的是，这样遍历一遍后，得到的范围 [0, i) 中的子串并不一定就是最大回文前缀，
//也可能还需要添加字符，举个例子来说，对于 "adcba"，在 for 循环执行之后，i=2，可以发现前面的 "ad" 
//并不是最长回文前缀，其本身甚至不是回文串，需要再次调用递归函数来填充使其变为回文串，但可以确定的是
//可以添加最少的字符数让其变为回文串。而且可以确定的是后面剩余的部分肯定不属于回文前缀，此时提取出剩下的字符，
//翻转一下加到最前面，而对范围 [0, i) 内的子串再次递归调用本函数，这样，在子函数最终会组成最短的回文串，
//从而使得整个的回文串就是最短的

//解法2:找从第一个字符开始的最长回文子串，用两个指针分别指向对应的位置比较，
//然后用 end 指向符合条件的回文子串的最后一位的位置，则需要翻转的非回文子串是从end+1开始
class ShortestPalindrome_214 {
public String shortestPalindrome(String s) {
	    if(s.length() < 2)return s;
	    //解法1
//        int l = 0;
//        for (int r = s.length() - 1; r >= 0; r--) {
//			if(s.charAt(l) == s.charAt(r))l++;
//		}
//        if(l == s.length())return s;//s本身就是回文串，则不需要添加任何字符
//        String temp = s.substring(l);//s不是回文，截取从l往后的非回文部分
//        String reverseString = new StringBuilder().append(temp).reverse().toString();//翻转非回文部分
//        //因为[0,l）部分可能也不是回文，所以需要递归一下使它最终一定是回文串（单个字符）
//        return reverseString + shortestPalindrome(s.substring(0,l)) + temp;
	    
	    //解法2
	    int i = 0;
	    int end = s.length() - 1;
	    int j = end;
	    while(i < j) {
	    	if(s.charAt(i) == s.charAt(j)) {
	    		i++;
	    		j--;
	    	}else {//如果不相等，说明当前子串不是回文子串，重新开始找
	    		i = 0;
	    		end--;
	    		j = end;
	    	}
	    }
	    //出循环时，end一定是指向一个回文子串的末尾
	    String temp = new StringBuilder().append(s.substring(end+1)).reverse().toString();
	    return temp + s;
    }
}
