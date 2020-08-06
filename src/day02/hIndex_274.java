package day02;

import java.util.Arrays;

/*
 * Given an array of citations(引用) (each citation is a non-negative integer) of a researcher, 
 * write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h 
if h of his/her N papers have at least h citations each, and the other N − h papers 
have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
             
Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * */

//思路：这道题让我们求H指数，这个质数是用来衡量研究人员的学术水平的质数，定义为一个人的学术文章有n篇分别被引用了n次，
//那么H指数就是n。而且wiki上直接给出了算法，可以按照如下方法确定某人的H指数：
//1、将其发表的所有SCI论文按被引用次数从高到低排序；2、从前往后查找排序后的列表，直到某篇论文的序号大于该论文被引次数。
//所得序号减一即为H指数。

public class hIndex_274 {
	 public int hIndex(int[] citations) {
		 int n = citations.length;
	        
	        Arrays.sort(citations);//数组从小到大排列
	        if(n == 0 || citations[0] == 0 & n==1 ) return 0;
	           if(n == 1) return 1;
	        int i= n-1;//从数组最后一个数开始，相当于对数组从大到小排列
	        while(i>= 0){
	            if(citations[i] < (n-i)) break;//如果当前序列号大于对应的引用次数，结束循环
	            i--;//否则看下一个数
	        }
	        return n-i-1;//返回当前序列号-1
	    }
}
