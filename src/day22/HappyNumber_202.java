package day22;

import java.util.HashSet;
import java.util.Set;

/*
 * Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits, and repeat the process until the number 
equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those 
numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 

Input: 19
Output: true
Explanation: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
 * */

//我的思路：特点，想要各个位数上的平方和等于1，除了1以外，n只能是10的倍数了，用一个set保存出现过的n值，一旦
//出现无限循环（已经出现过的n又出现了）则false

//思路2:其实这道题也可以不用 HashSet 来做，我们并不需要太多的额外空间，关于非快乐数有个特点，
//循环的数字中必定会有4（也就是说只要碰到4就返应该回false），
//就是利用这个性质，就可以不用set了

//思路3:这道题还有一种快慢指针的解法。 这道题环一定存在，不过有的环不符合题意，只有最后 slow 停在了1的位置，才表明是一个
//快乐数。而且这里每次慢指针走一步，快指针走两步，不是简单的指向next，而是要调用子函数计算各位上数字的平方和，当快慢指针
//相等时，跳出循环，并且判断慢指针是否为1即可
public class HappyNumber_202 {
	 public boolean isHappy(int n) {
		 //解法1:
//	       if(n == 1 )return true;
//	       
//	       Set<Integer> s = new HashSet<Integer>();//用一个set保存出现过的n值
//	       s.add(n);
//	       while(n != 1) {
//	    	   n = helper(n);
//	    	   if(n == 1)return true;
//	    	   else if(!s.add(n))return false;//s中已经有这个值出现过了，会进入循环，直接返回false
//	    	   else s.add(n);
//	       }
//	       return false;
		 
		 //解法2:
//		 if(n == 1 )return true;
//		 while(n != 1) {
//			 n = helper(n);
//			 if(n == 4)return false;
//		 }
//		 return n == 1;
		 
		 //解法3:
		 int slow = n, fast = n;
	        while (true) {
	            slow = findNext(slow);//slow走一步
	            fast = findNext(fast);//fast走两步
	            fast = findNext(fast);
	            if (slow == fast) break;//如果有环则一定会相遇
	        }
	        return slow == 1;
	}
 
	 public int helper(int x) {
		if(x < 10)return x * x;
		
		int res = 0;
		while(x >= 10) {
			int t = x % 10;
			res += t * t;
			x = x / 10;
		}
		return res + x*x;
	}
	 
	public int findNext(int n) {
	        int res = 0;
	        while (n > 0) {
	            res += (n % 10) * (n % 10);
	            n /= 10;
	        }
	        return res;
	    }
}
