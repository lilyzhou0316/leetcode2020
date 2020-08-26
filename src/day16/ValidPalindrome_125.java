package day16;


/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric 
 * characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true


Example 2:

Input: "race a car"
Output: false
 

Constraints:

s consists only of printable ASCII characters.
 * */

//我的思路：双指针，一个头一个尾遍历，看两指针上的字母是否相等，忽略非字母的字符(注意，数字也算字母)
public class ValidPalindrome_125 {
  public boolean isPalindrome(String s) {
        if(s.length() == 0 || s.length() == 1)return true;
        
        s = s.toLowerCase();//将s里所有大写字母转成小写
        int left = 0, right = s.length() - 1;
        while(left < right) {
        	while(left < s.length() && !isAlpha(s.charAt(left))) {
        		//left指针指向的不是一个字母
        		left++;
        	}
        	if(left == s.length())return true;
        	//right指针指向的不是一个字母
        	while(!isAlpha(s.charAt(right)))right--;
        	
        	if(s.charAt(left) != s.charAt(right))return false;
        	else {
        		left++;
        		right--;
        	}
        }
        return true;
    }
  
  public boolean isAlpha(char a) {
	if('a' <= a && a <= 'z')return true;
	if('A' <= a && a<= 'Z')return true;
	if('0' <= a && a <= '9')return true;
	return false;
}
}
