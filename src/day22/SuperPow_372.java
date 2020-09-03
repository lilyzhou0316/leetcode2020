package day22;

import java.util.LinkedList;

/*
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large 
 * positive integer given in the form of an array.

Example 1:

Input: a = 2, b = [3]
Output: 8


Example 2:

Input: a = 2, b = [1,0]
Output: 1024
 * */

//思路：暴力解法，直接取出b值，然后用a^b对1337取余数，会超时

//重点：1.常见模运算 （a * b）% k = (a % k)(b % k) % k
//2.将指数运算转换为递归：
//a^[1,5,6,4] = a^4 * a^[1,5,6,0]
//            = a^4 * (a^[1,5,6])^10
//            = a^4 * (a^6 * (a ^ [1,5,0]))^10
//            = a^4 * (a^6 * (a ^ [1,5])^10)^10
public class SuperPow_372 {
	int mod = 1337;

    public int superPow(int a, int[] b) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int num : b) {
            list.add(num);
        }
        return superPow(a, list);
    }

    private int superPow(int a, LinkedList<Integer> list) {
        if (list.isEmpty()) {
            return 1;
        }
        Integer integer = list.peekLast();//取出当前list的最后一位数字
        list.pollLast();
        int part1 = mypow(a, integer);//相当于计算a^4的部分
        int part2 = mypow(superPow(a, list), 10);//相当于计算(a^[1,5,6])^10
        return (part1 * part2) % mod;//最后计算整个结果的mod
    }

    //计算某个数的n次方对mod取模结果
    private int mypow(int a, int k) {
        a = a % mod;
        int result = 1;

        for (int i = 0; i < k; i++) {//如(a^2)%mod = (a%mod)(a%mod)%mod = （a%mod）^k % mod
            result = result * a % mod;//result 保存（a%mod）^k结果
        }
        return result % mod;
    }

}
