package day23;


/*
 * Given a non-negative integer n, count all numbers with unique digits(每个位数上的数字不相同), 
 * x, where 0 ≤ x < 10^n.

Example:

Input: 2
Output: 91 
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, 
             excluding 11,22,33,44,55,66,77,88,99
 

Constraints:

0 <= n <= 8
 * */

//思路:找规律，如果是一位数，则0-9共10个不重复数字， 如果是两位数，则十位可以取1-9中的任意数（9种可能），
//个位可以取0-9中除了十位选的那个数字外的所有数字（9种可能），即共9*9+10=91个数字，同理可推断后面的情况
//n等于几，即最多可以取到几位数
public class CountNumberswithUniqueDigits_357 {
public int countNumbersWithUniqueDigits(int n) {
        if(n == 0)return 1;
        if(n == 1)return 10;
        
        int res = 0;
        if(n > 1) {
        	res = countNumbersWithUniqueDigits(n-1) + helper(n);
        }
        return res;
    }

public int helper(int x) {
	
	int res = 1;
	for (int i = 1; i <= x; i++) {
		if(i == 1)res *= 9;
		else res *= 10 - i + 1;
}
	return res;
}
}
