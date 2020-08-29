package day19;

/*
 * Given two integers dividend and divisor, divide two integers without using multiplication, 
 * division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. 
For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.


Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit 
signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your
 function returns 231 − 1 when the division result overflows.
 * */

//思路：商，是问被除数里能有几个除数。a/b , 用b翻n番 去试探是否能达到a，比如，7除以2， 2不翻番为2，没达到，2翻一番为4，
//没达到；2翻两番为8，超过了，于是商就是2加上（7-4）/2（递归出现了）。左移n，就是翻n番
//溢出，可以用long接管int来解决

public class DivideTwoIntegers_29 {
	public int divide(int dividend, int divisor) {
        if(divisor == 1) return dividend;
        if(divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        long a = dividend;
        long b = divisor;

        long ans;
        if(a >0 && b > 0)
            ans = div(a,b);
        else if(a <0 && b < 0)
            ans = div(-a, -b);
        else     //一正一负
            ans = -div(Math.abs(a), Math.abs(b));
        
        return (int) ans;

    }

    public long div(long a, long b) {
        
    	// 如果小于，就无法从a中减去任何b
        if(a < b) return 0;

        int i = 0;//记录左移次数，通过1<<i得到b被减去的次数
        while( a >= (b<<i) ){//<<一位即*2，>>一位即/2
            i++;
        }
        //出循环时，左移次数比实际多1次
        i--;

        return (1<<i) + div(a - (b<<i) , b);//1<<i表示从a中减去了多少个b，比如左移1位，减去了2个，左移2位，
        //减去了4个，因此还要计算剩余值还能不能再减去b
        
    }
}
