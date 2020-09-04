package day23;

import java.awt.Frame;

/*
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first 
 * number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most 
number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until 
a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6
 * */

//我的思路：1.暴力解法  2.找规律:1->1, 2-5 -> 2, 6-7 -> 4, 从8开始，2*偶数i即2*偶数i+1都是6，乘以奇数都是8

//思路1：第一次从左往右删除的时候，奇数都被删掉了，剩下的都是偶数。如果对所有数都除以2，那么得到一个1到 n/2 的新数列。
//下一次从右往左删除，那么返回的结果应该是调用递归的结果 lastRemaining(n / 2) 在数组1到 n/2 之间的镜像。何为镜像，
//比如 1, 2, 3, 4 这个数字，2的镜像就是3（n/2+1= 2+3）, 1的镜像是4

//思路2:如果仔细观察，可以发现从左往右删的时候，每次都是删掉第一个数字，而从右往左删的时候，则有可能删掉第一个或者
//第二个数字，而且每删一次，数字之间的距离会变为之前的两倍。这里要做的是每次记录当前数组的第一个数字，而且再通过观察
//可以看出，从右往左删时，如果剩下的数字个数是偶数个时，删掉的是第二个数字；如果是奇数个的时候，删掉的是第一个数字。
//也就是说从左往右删除，和当剩余数字为奇数时从右往左删除的效果是一样的
public class EliminationGame_390 {
public int lastRemaining(int n) {
       //解法1
     //   return n == 1 ? 1 : 2 * (1 + n / 2 - lastRemaining(n / 2)); 
	
	//解法2
	int firstNum = 1;//记录当前数组的第一个数字
	int step = 1;//记录当前相邻两个数的差值
	int remain = n;//记录当前剩余数字
	int ltor = 1;//记录当前删除方向，1是从左往右，-1是从右往左
	while(remain > 1) {//只要剩余数字还大于1个就要继续循环
		
		if(ltor == 1 || remain % 2 == 1)firstNum += step;//当前删除的都是第1，3，5，7.....个数,第一个数字改变
		//否则删除的是第2，4....个数，此时第一个数字不变
		//不管第一个数变不变，step都要变大,remain都要减少,删除方向都要改变
		step *= 2;
		remain /= 2;
		ltor = -ltor;	
	}
	return firstNum;
    }
}
