package day17;

import java.util.Stack;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true


Example 2:

Input: "()[]{}"
Output: true


Example 3:

Input: "(]"
Output: false


Example 4:

Input: "([)]"
Output: false


Example 5:

Input: "{[]}"
Output: true
 * */


//思路：stack装左括号，一旦遇到右括号则看栈顶的括号是否匹配，如果不匹配则false，匹配继续读取
public class ValidParentheses_20 {
	public boolean isValid(String s) {
		if(s.length() == 0)return true;
		if(s.length() == 1)return false;
		
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if(c == '(' || c == '{' || c == '[') {
				stack.push(c);
			}else if(c == ')'){
				if(stack.isEmpty() || stack.peek() != '(')return false;
				else stack.pop();
			}else if(c == '}'){
				if(stack.isEmpty() || stack.peek() != '{')return false;
				else stack.pop();
			}else if(c == ']'){
				if(stack.isEmpty() || stack.peek() != '[')return false;
				else stack.pop();
		}
		}
		return stack.size() == 0? true:false;
		
	}
}
