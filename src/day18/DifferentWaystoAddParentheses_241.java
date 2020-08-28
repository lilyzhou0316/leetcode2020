package day18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string of numbers and operators, return all possible results from computing 
 * all the different possible ways to group numbers and operators. The valid operators
 *  are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2


Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 * */

//思路：先从最简单的输入开始，若 input 是空串，那就返回一个空数组。若 input 是一个数字的话，
//那么括号加与不加其实都没啥区别，因为不存在计算，但是需要将字符串转为整型数，因为返回的是一个整型数组。
//当然，input 是一个单独的运算符这种情况是不存在的，因为前面说了这道题默认输入的合法的。下面来看若 input 
//是数字和运算符的时候，比如 "1+1" 这种情况，那么加不加括号也没有任何影响，因为只有一个计算，结果一定是2。
//再复杂一点的话，比如题目中的例子1，input 是 "2-1-1" 时，就有两种情况了，(2-1)-1 和 2-(1-1)，
//由于括号的不同，得到的结果也不同，但如果我们把括号里的东西当作一个黑箱的话，那么其就变为 ()-1  和 2-()，
//其最终的结果跟括号内可能得到的值是息息相关的，那么再 general 一点，实际上就可以变成 () ? () 这种形式，
//两个括号内分别是各自的表达式，最终会分别计算得到两个整型数组，中间的问号表示运算符，可以是加，减，或乘。
//那么问题就变成了从两个数组中任意选两个数字进行运算，瞬间变成我们会做的题目了有木有？而这种左右两个括号代表
//的黑盒子就交给递归去计算，像这种分成左右两坨的 pattern 就是大名鼎鼎的分治法 Divide and Conquer 了
//这道题，我们不用新建递归函数，就用其本身来递归就行，先建立一个结果 res 数组，然后遍历 input 中的字符，
//根据上面的分析，我们希望在每个运算符的地方，将 input 分成左右两部分，从而扔到递归中去计算，从而可以得到
//两个整型数组 left 和 right，分别表示作用两部分各自添加不同的括号所能得到的所有不同的值，此时我们只要
//分别从两个数组中取数字进行当前的运算符计算，然后把结果存到 res 中即可。当然，若最终结果 res 中还是空的，
//那么只有一种情况，input 本身就是一个数字，直接转为整型存入结果 res 中即可

//对上述方法的优化：使用 HashMap 来保存已经计算过的情况，这样可以减少重复计算，从而提升运算速度，以空间换时间
public class DifferentWaystoAddParentheses_241 {
	 //优化,使用 HashMap 来保存已经计算过的情况，这样可以减少重复计算，从而提升运算速度，以空间换时间
	Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	 public List<Integer> diffWaysToCompute(String input) {
		 if(map.containsKey(input))return map.get(input);
	        List<Integer> resIntegers = new ArrayList<Integer>();
	       
	        
	        for (int i = 0; i < input.length(); i++) {
				//遍历input每个字符，如果碰到‘+’，‘-’或者‘*’，则以i为分界点将input分成左右两部分进行分治递归
	        	if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
	        		List<Integer> leftIntegers = diffWaysToCompute(input.substring(0,i));
	        		List<Integer> righIntegers = diffWaysToCompute(input.substring(i+1));
	        		
	        		//然后遍历上面两个list，从中取出每个数和另一组中的每个数进行运算
	        		for (int j = 0; j < leftIntegers.size(); j++) {
						for (int j2 = 0; j2 < righIntegers.size(); j2++) {
							if(input.charAt(i) == '+')resIntegers.add(leftIntegers.get(j)+righIntegers.get(j2)) ;
							else if(input.charAt(i) == '-')resIntegers.add(leftIntegers.get(j)-righIntegers.get(j2)) ;
							else resIntegers.add(leftIntegers.get(j)*righIntegers.get(j2)) ;
						}
					}
	        	}
			}
	        if(resIntegers.isEmpty())resIntegers.add(Integer.parseInt(input));
	        map.put(input, resIntegers);
	        return resIntegers;
	    }
}
