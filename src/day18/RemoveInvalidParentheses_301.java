package day18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 *  Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]


Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]


Example 3:

Input: ")("
Output: [""]
 * */

//思路1:可以用 BFS 来解，把给定字符串排入队中，然后取出检测其是否合法，若合法直接返回，不合法的话，对其进行遍历，
//对于遇到的左右括号的字符，去掉括号字符生成一个新的字符串，如果这个字符串之前没有遇到过，将其排入队中，用 HashSet 
//记录一个字符串是否出现过。对队列中的每个元素都进行相同的操作，直到队列为空还没找到合法的字符串的话，那就返回空集
public class RemoveInvalidParentheses_301 {
public List<String> removeInvalidParentheses(String s) {
        //
	List<String> res = new ArrayList<String>();
	Set<String> set = new HashSet<String>();//用来给结果去重
	boolean found = false;//记录是否找到目标
	List<String> q = new ArrayList<String>();
	q.add(s);//从s开始，把每一个去掉了某个括号的且没有处理过的字符串加入队列
	while(!q.isEmpty()) {
		
		String t = q.remove(0);
		if(isValid(t)) {//如果从队列中取出的元素符合要求，则加入结果集
			res.add(t);
			
			found = true;
		}
		if(found == true)continue;//注意，这里一旦结果集有符合条件的string了，则found一直保持true，
		//因此只会检测队列里已经存在的元素而不会再新增元素了
		
		//从队列中取出的元素不符合要求，则遍历该元素，并删除一个左（右）括号后加入队列
		 for (int i = 0; i < t.length(); ++i) {
             if (t.charAt(i) != '(' && t.charAt(i) != ')') continue;//非括号，跳过
             String str = t.substring(0, i) + t.substring(i + 1);
             if (!set.contains(str)) {//如果去掉了某个括号后的这个字符串没有出现过，则加入队列
                 q.add(str);
                set.add(str);
             }
         }
		
	}
	return res;
    }

public boolean isValid(String s) {
	Stack<Character> stack = new Stack<Character>();
	
	for (Character c : s.toCharArray()) {
		if(c == '(')stack.push(c);
		else if(c == ')'){
			if(stack.isEmpty())return false;
			else stack.pop();
		}
	}
	return stack.isEmpty();
	
}


}
