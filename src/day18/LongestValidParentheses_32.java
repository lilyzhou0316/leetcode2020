package day18;

import java.util.Stack;

/*
 * Given a string containing just the characters '(' and ')', find the length of the
 *  longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"


Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 * */

//思路1：stack.需要定义个 start 变量来记录合法括号串的起始位置，遍历字符串，如果遇到左括号，
//则将当前下标压入栈，如果遇到右括号，如果当前栈为空，则将下一个坐标位置记录到 start，如果栈不为空，
//则将栈顶元素取出，此时若栈为空，则更新结果和 i - start + 1 中的较大值，否则更新结果和 i - st.top() 
//中的较大值

//思路2:dp.

//思路3:有效括号的特性，就是长度一定是偶数，并且在任何位置左括号的数量都是大于等于右括号的数量的，
//如果在任何位置右括号大于左括号的数量，那么说明这个组成的括号是无效的。
public class LongestValidParentheses_32 {
public int longestValidParentheses(String s) {
        if(s.length() < 2)return 0;
//       解法1
//        int max = 0;//记录最大连续出现括号数
//        int start = 0;
//        Stack<Integer> stack = new Stack<>();//将每个左括号的index入栈
//        for (int i = 0; i < s.length(); i++) {
//			if(s.charAt(i) == '(')stack.push(i);
//			else {
//				if(stack.isEmpty())start = i + 1;//当前为右括号，且栈里没有能配对的左括号，那么需要调整start位置
//				else {
//					stack.pop();//当前为右括号，取出栈顶元素与它配对
//					if(stack.isEmpty())max = Math.max(max, i - start + 1);//如果取完后栈为空，说明从i-start都是符合要求的
//					else max = Math.max(max, i - stack.peek());//如果取完后栈不为空，说明从i到栈顶index之前的长度是符合要求的
//				}
//			}
//		}
//        return max;
        
        //解法2
        int left = 0, right = 0, max = 0;
        //从前往后右括号大于左括号则无效，让left和right都归0
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            //left大于right先不管
            if (left == right) {//左右括号数相等时，统计目前的最大括号数
                max = Math.max(max, 2 * right);
            } else if (right > left) {
                //right大于left重新记
                left = right = 0;
            }
        }
        left = right = 0;
        //从后往前左括号大于右括号则无效，让left和right都归0
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;

    }
}
