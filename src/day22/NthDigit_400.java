package day22;


/*
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).

Example 1:

Input:
3

Output:
3


Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is
 part of the number 10.
 * */

//思路：首先来分析自然数序列和其位数的关系，前九个数都是1位的，然后10到99总共90个数字都是两位的，100到999这900个数都是
//三位的，那么这就很有规律了，我们可以定义个变量cnt，初始化为9，然后每次循环扩大10倍，再用一个变量len记录当前循环区间数字
//的位数，另外再需要一个变量start用来记录当前循环区间的第一个数字，我们n每次循环都减去len*cnt (区间总位数)，当n落到
//某一个确定的区间里了，那么(n-1)/len就是目标数字在该区间里的坐标，加上start就是得到了目标数字，然后我们将目标数字
//start转为字符串，(n-1)%len就是所要求的目标位，最后别忘了考虑int溢出问题，把所有变量都申请为长整型的好了
public class NthDigit_400 {
public int findNthDigit(int n) {
	long len = 1;//记录当前数字区间的位数，如1-9是1位，10-99是2位，100-999是3位.....
	long cnt = 9;//根据当前数字位数记录当前数字区间总共有多少个数，如1-9共9个，10-99共90个，100-999共900个
	long start = 1;//记录当前数字区间的第一个数字，即1，10，100.....
        long n1 = n;
        
        //计算n应该落在哪个数字区间
        while(n1 > len * cnt) {
        	n1 = n1 - len*cnt;
        	len++;//当前数字区间的位数加1
        	cnt = cnt * 10;//当前数字区间的数字个数
        	start = start * 10;
        }
        //出循环说明n落在当前数字区间
       
        String t = start + (n1 - 1)/len + "";//计算得出对应的数字,并转换位str
       int index = new Long((n1-1)%len).intValue();
        return Integer.parseInt(t.charAt(index)+"");
        
    }
}
