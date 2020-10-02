package day34;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a positive integer n, find the least number of perfect square numbers (for 
 * example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 * */

//思路：根据四平方和定理，任意一个正整数均可表示为4个整数的平方和，其实是可以表示为4个以内的平方数之和，
//那么就是说返回结果只有 1,2,3 或4其中的一个（用来表示的整数的个数），首先我们将数字化简一下，由于一个数如果
//含有因子4，那么我们可以把4都去掉，并不影响结果，比如2（1+1）和8（4+4）,3（1+1+1）和12（4+4+4）等等，
//返回的结果都相同。还有一个可以化简的地方就是，如果一个数除以8余7（1+1+1+4）的话，那么肯定是由
//4个完全平方数组成。那么做完两步后，一个很大的数有可能就会变得很小了，大大减少了运算时间，下面我们就来尝试的
//将其拆为两个平方数之和，如果拆成功了那么就会返回1或2，因为其中一个平方数可能为0. 
//(注：由于输入的n是正整数，所以不存在两个平方数均为0的情况)。

//思路2:dp, 用dp[i]表示数字i最少能由几个数的平方和组成，从0开始计算，初始值为0
public class PerfectSquares_279 {
public int numSquares(int n) {
        //解法1，利用四平方和定理
//	while(n % 4 == 0) n = n / 4;//去掉所有的因子4
//	if(n % 8 == 7)return 4;
//	
//	for (int i = 0; i * i <= n; i++) {
//		int num = (int)Math.sqrt(n - i * i);
//		if(num * num + i * i == n) {
//			//因为n大于0， 所以num和i两者不可能同时为0
//			if(num == 0 || i == 0)return 1;
//			else return 2;
//		}
//	}
//	//因为四平方和定理，答案只可能是1，2，3，4这几种情况
//	return 3;
	
	//解法2
	List<Integer> dp = new ArrayList<Integer>();
	dp.add(0);//初始化第一个值为0,一直计算到n
	while (dp.size() <= n ) {
		int cur = dp.size();
		int value = Integer.MAX_VALUE;//记录当前数字需要的最小个数
		for (int i = 1; i * i <= cur; i++) {
			//dp.get(cur - i*i)代表从当前目标值cur里减去i*i后差值对应的需要的最小个数，在此基础上再加1（即加上数字i它的平方和）
			value = Math.min(value, dp.get(cur - i*i) + 1);
		}
		dp.add(value);
	}
	return dp.get(n);
    }
}
