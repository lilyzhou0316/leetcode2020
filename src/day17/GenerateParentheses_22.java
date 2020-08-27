package day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given n pairs of parentheses, write a function to generate all combinations
 *  of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 * */

//思路：递归。base case为n = 1，（），从n=2开始，g(n)是在g(n-1)的基础上，把g(n-1)里的每一个str
//根据长度一一拆开，中间加上一个()
public class GenerateParentheses_22 {
	 public List<String> generateParenthesis(int n) {
		 List<String> res = new ArrayList<String>();
		 
		if(n == 1) {
			res.add("()");
			return res;
		}
		
		//n > 1
			//遍历n-1里的所有元素，给所有元素加上3种情况，然后去重，再加入res，返回
			List<String> temp = generateParenthesis(n - 1);
			Set<String> set = new HashSet<String>();
			for (String s : temp) {
				for(int i=0;i<s.length();i++){
	                set.add(s.substring(0,i+1)+"()"+s.substring(i+1,s.length()));   
	            }  
			}
			for (String s : set) {
				res.add(s);
			}	
		
		return res;
	 }
}
