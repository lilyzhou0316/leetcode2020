package day20;

import java.util.Arrays;

/*
 * Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * */

//思路：prime num就是只能被1和本身整除的数，所以如果一个数能被其它数整除就不是，那么从1开始遍历到一个数的平方根
//如果都不能整除，则说明是prime num
//高效实现 countPrimes
//高效解决这个问题的核心思路是和上面的常规思路反着来：
//首先从 2 开始，我们知道 2 是一个素数，那么 2 × 2 = 4, 3 × 2 = 6, 4 × 2 = 8... 都不可能是素数了。
//然后我们发现 3 也是素数，那么 3 × 2 = 6, 3 × 3 = 9, 3 × 4 = 12... 也都不可能是素数了。


//中心思想：埃拉托斯特尼筛法的具体内容就是：
//要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的倍数剔除，剩下的就是素数。
//同时考虑到大于2的偶数都不是素数，所以可以进一步优化成：

//要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的奇数倍剔除，剩下的奇数就是素数。

//时间复杂度: O(N * loglogN)
public class CountPrimes_204 {
public int countPrimes(int n) {
	//解法1
//	boolean[] isPrim = new boolean[n];//用来保存当前数字是否是素数
//    Arrays.fill(isPrim, true);//先初始化都为true
//    
//    //由于因子的对称性，我们外层的 for 循环只需要遍历到 sqrt(n)
//    for (int i = 2; i * i < n; i++) 
//        if (isPrim[i]) //从2开始，如果当前数是素数，则它的倍数一定不是素数
//            for (int j = i * i; j < n; j += i) 
//            	//j代表当前素数i的倍数，从i*i开始是因为2到i-1的数与i的乘积在之前已经遍历过了
//                isPrim[j] = false;
//    
//    int count = 0;
//    for (int i = 2; i < n; i++)
//        if (isPrim[i]) count++;
//    
//    return count;

		//解法2
	 int result = 0, sqrt_n = (int) Math.sqrt(n);
     boolean[] b = new boolean[n];   // 初始化默认值都为 false，为质数标记
     if (2 < n) result++; // 如果大于 2 则一定拥有 2 这个质数
     for (int i = 3; i < n; i += 2) {  // 从 3 开始遍历，且只遍历奇数
         if (!b[i]) {  // 是质数
             if (i <= sqrt_n)//不大于根号n
                 for (int j = i; i * j < n; j += 2)
                     b[i * j] = true;    // 将当前质数的奇数倍都设置成非质数标记 true
             result++;   // 质数个数 +1
         }
     }
     return result;

    }

}
