package day22;

import java.util.Arrays;

/*
 * There are n bulbs（灯泡） that are initially off. You first turn on all the bulbs. Then, you turn off 
 * every second bulb. On the third round, you toggle every third bulb (turning on if it's off or 
 * turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only 
 * toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Input: 3
Output: 1 
Explanation: 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
 * */

//我的思路：双层遍历，外层控制当前轮数，内层控制灯泡的位置,超时

//思路2:对于第n个灯泡，只有当次数是n的因子的之后，才能改变灯泡的状态，即n能被当前次数整除，比如当n为36时，
//它的因数有(1,36), (2,18), (3,12), (4,9), (6,6), 可以看到前四个括号里成对出现的因数各不相同，
//括号中前面的数(第i轮)改变了灯泡状态，后面的数（第j轮）又变回去了，等于灯泡的状态没有发生变化，只有最后那个(6,6)，
//在次数6的时候改变了一次状态，没有对应其它的状态能将其变回去了，所以灯泡就一直是点亮状态的。所以所有平方数都有这么
//一个相等的因数对，即位置位于平方数（如1，4，9....）的灯泡都将会是点亮的状态。
//那么问题就简化为了求1到n之间完全平方数的个数，我们可以用force brute来比较从1开始的完全平方数和n的大小


//思路3:还有一种方法更简单，我们直接对n开方，取它的值的整数部分，这个整型数的平方最接近于n，即为n包含的所有完全平方数的个数
public class BulbSwitcher_319 {
public int bulbSwitch(int n) {
        
       //解法1:
//        int res = 1;
//        while(res * res <= n)res++;//位置位于平方数（如1，4，9....）的灯泡都将会是点亮的状态。
//        return --res;
        
        
        //解法2
        return (int)Math.sqrt(n);
    }
}
