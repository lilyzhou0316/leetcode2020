package day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/*
 * Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
 * */



//思路1:三指针。用一个list保存结果，把1加入list,然后分别用三个指针表示list里在该指针之前的数与2，3，5相乘的结果是否已经加入到了list里,
//找到三个指针指向的数和指针代表的数的三个乘积其中的最小值，加入list，重复这个步骤直到list的长度等于n

//思路2:也可以使用最小堆来做，首先放进去一个1，然后从1遍历到n-1，每次取出堆顶元素（即取了n-1个数，那么出循环时，
//堆顶即第n小的数，比如n=4，则取出了前3个数，最后堆顶剩下的就是第4个目标数），为了确保没有重复数字，进行一次 while 
//循环，将此时和堆顶元素相同的都取出来，然后分别将这个取出的数字乘以 2，3，5，并分别加入最小堆。
//这样最终 for 循环退出后，堆顶元素就是所求的第n个丑陋数
public class UglyNumberII_264 {
public int nthUglyNumber(int n) {
	if(n == 1)return 1;
	
	//解法1
//	List<Integer> res = new ArrayList<Integer>();
//	res.add(1);
//	int i2 = 0, i3 = 0, i5 =0;//i代表乘以2的结果
//	
//	while(res.size() < n) {
//		//m分别代表当前指针与2，3，5分别相乘的结果
//		int m2 = res.get(i2) * 2;
//		int m3 = res.get(i3) * 3;
//		int m5 = res.get(i5) * 5;
//		//找到三个数的最小值
//		int m = Math.min(m2, Math.min(m3, m5));
//		if(m == m2)i2++;//如果是乘以2的指针的结果最小，则该指针后移一位
//		if(m == m3)i3++;
//		if(m == m5)i5++;
//		res.add(m);//将最小值加入结果集
//	}
//	
//	return res.get(res.size() - 1);//结果集的最后一个数即为所求
	
	//解法2
	Queue<Long> queue = new PriorityQueue<>();//数字从小到大排列
	queue.add((long)1);
	for (int i = 1; i < n; i++) {
		long temp = queue.poll();//取出第一个元素
		while(!queue.isEmpty() && temp == queue.peek()) {
			//如果此时堆顶元素与取出元素相等，则删除重复元素
			temp = queue.poll();
		}
		//把当前取出元素分别乘以2，3，5再加入堆
		queue.add(temp * 2);
		queue.add(temp * 3);
		queue.add(temp * 5);
	}
	//出循环时，堆顶元素即为所求
       int res = new Long(queue.peek()).intValue();
	return res;
}


}
