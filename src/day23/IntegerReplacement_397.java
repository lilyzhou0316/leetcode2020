package day23;

/*
 * Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1


Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 * */

//思路：递归
public class IntegerReplacement_397 {
public int integerReplacement(int n) {
        if(n == 1)return 0;
        if (n == Integer.MAX_VALUE)//考虑溢出情况
            return 32;
        
        if(n % 2 == 0)return 1 + integerReplacement(n / 2);//n为偶数时，直接除以2，次数+1
        else {//n为奇数时
        	//先给n加1或者减1，然后再除以2，操作次数加2
        	return 2 + Math.min(integerReplacement((n + 1) / 2), integerReplacement((n - 1) / 2));
        	
        }
    }
}
