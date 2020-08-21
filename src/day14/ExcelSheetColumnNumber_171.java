package day14;

/*
 * Given a column title as appear in an Excel sheet, return its corresponding 
 * column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
    
Example 1:

Input: "A"
Output: 1


Example 2:

Input: "AB"
Output: 28


Example 3:

Input: "ZY"
Output: 701
 

Constraints:

1 <= s.length <= 7
s consists only of uppercase English letters.
s is between "A" and "FXSHRXW".
 * */

//我的思路：与题168正好相反，从最后一位字符开始读，每一位字符对应的值应该是 26^(len - 1 -i)*(对应字符 - 'A'+1)
public class ExcelSheetColumnNumber_171 {
public int titleToNumber(String s) {
	int res = 0;
        for (int i = 0; i < s.length(); i++) {
        	
			res += Math.pow(26, (s.length() - 1 -i))*(s.charAt(i) - 'A' + 1);
		}
        return res;
    }
}
