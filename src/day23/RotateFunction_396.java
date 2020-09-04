package day23;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, 
we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 * */

//我的思路：根据给的例子，每个f的值都是在上一次数组的基础上把最后一个元素位置移动到第一位，然后再计算对应的值

//思路2:观察f，可以看出
//F(0) = 0A + 1B + 2C +3D
//F(1) = 0D + 1A + 2B +3C
//F(2) = 0C + 1D + 2A +3B
//F(3) = 0B + 1C + 2D +3A
//从而得出
//sum = 1A + 1B + 1C + 1D
//F(1) = F(0) + sum - 4D
//F(2) = F(1) + sum - 4C
//F(3) = F(2) + sum - 4B
//即 F(i) = F(i-1) + sum - n*A[n-i], n - i即为被移动的那个元素
public class RotateFunction_396 {
public int maxRotateFunction(int[] A) {
        //解法1
	
//	int len = A.length;
//	List<Integer> t = new ArrayList<Integer>();
//	for (int i = 0; i < A.length; i++) {
//		t.add(A[i]);
//	}
//	
//	int f = 0;//f0为在位置0旋转后得到的计算结果
//	for (int i = 0; i < A.length; i++) {
//		f += i * A[i];
//	}
//	
//	int max = f;//记录最大值
//	
//	int k = 1;//每次旋转的位置，最大取len - 1
//	//每个f的值都是在上一次数组的基础上把最后一个元素位置移动到第一位
//	while(k < len) {
//		int temp = t.get(t.size() - 1);//获取最后一个元素
//		t.remove(t.size() - 1);//删除最后一个元素
//		t.add(0, temp);//把删除的元素插入头部
//
//		f= 0;
//		for (int i = 0; i < A.length; i++) {
//			f += i * t.get(i);
//		}
//		max = Math.max(max, f);
//		k++;
//	}
//	return max;
	
	//解法2
	int sum = 0;
	int len = A.length;
	int f0 = 0;
	
	for (int i = 0; i < len; i++) {
		sum += A[i];
		f0 += i * A[i];
	}
	
	int max = f0;
	int f = f0;
	for (int i = 1; i <len; i++) {
		f = f + sum - len*A[len - i];
		max = Math.max(max, f);
	}
	return max;
    }


}
