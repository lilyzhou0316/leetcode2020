package day33;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to 
 * add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 

Constraints:

0 <= num.length <= 10
num only contain digits.
 * */

//思路：首先要像题93一样，把数字拆开（这里只有一个限制条件，就是两位及以上数字没有先导0），然后把拆开的数字中间插入计算符号

//这道题给了我们一个只由数字组成的字符串，让我们再其中添加+,-或号来形成一个表达式，该表达式的计算和为给定了target值，让我
//们找出所有符合要求的表达式来。看了题目中的例子1和2，很容易让人误以为是必须拆成个位数字，其实不是的，比如例子3中的 "105",
//5能返回"10-5"，说明连着的数字也可以。如果非要在过往的题中找一道相似的题，我觉得跟 Combination Sum II 很类似。不过这道
//题要更复杂麻烦一些。还是用递归来解题，我们需要两个变量diff和curNum，一个用来记录将要变化的值，另一个是当前运算后的值，
//而且它们都需要用 long 型的，因为字符串转为int型很容易溢出，所以我们用长整型。对于加和减，diff就是即将要加上的数和即将要
//减去的数的负值，而对于乘来说稍有些复杂（因为乘法优先级高于加法和减法），此时的diff应该是上一次的变化的diff乘以即将要乘上的数，有点不好理解，那我们来举
//个例子，比如 2+3*2，即将要运算到乘以2的时候，上次循环的 curNum = 5, diff = 3, 而如果我们要算这个乘2的时候，新的变化
//值diff应为 3*2=6，而我们要把之前+3操作的结果去掉，再加上新的diff，即 (5-3)+6=8，即为新表达式 2+3*2 的值
public class ExpressionAddOperators_282 {
public List<String> addOperators(String num, int target) {
	List<String> res = new ArrayList<String>();
	if(num.length() == 0 || num == null)return res;
	helper(num, target, 0, 0, "", res);
	return res;
    }

public void helper(String num, int target, long curNum, long diff, String output, List<String> res) {
	if(num.length() == 0 && curNum == target) {
		//如果当前计算结果等于目标值，且num遍历完了,则将当前output加入结果集
		res.add(new String(output));
		return;
	}
	
	for (int i = 1; i <= num.length(); i++) {
		//当前能截取的子串长度从1到整个num长度
		String cur = num.substring(0,i);
		//如果当前截取的为2位数及以上且有先导0，则直接跳过此轮递归
		if(cur.length() > 1 && cur.charAt(0) == '0')return;
		long temp = Long.parseLong(cur);
		if(output.length() > 0) {
			//如果output中已经有数字了，则对当前数字和之前结果进行+，-，*的运算
			
			helper(num.substring(i), target, curNum + temp, temp, output + "+" + cur, res);
			helper(num.substring(i), target, curNum - temp, -temp, output + "-" + cur, res);
			helper(num.substring(i), target, (curNum - diff) + diff * temp, diff * temp, output + "*" + cur, res);
		}else {
			//如果output为空，则直接把当前数字加入
			
			helper(num.substring(i), target, temp, temp, output+cur, res);
		}
	}
}
}
