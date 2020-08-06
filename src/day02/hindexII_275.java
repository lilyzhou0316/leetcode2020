package day02;

import java.util.Arrays;

/*
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) 
 * of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h 
if h of his/her N papers have at least h citations each, and the other N − h papers 
have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
             
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?
 * */

//思路：这题是之前那道 H-Index 的拓展，输入数组是有序的，让我们在 O(log n) 的时间内完成计算，看到这个时间复杂度，
//而且数组又是有序的，应该有很敏锐的意识应该用二分查找法，目标值 target 会随着 mid 值的变化而变化，
//这里的 right 的初始值和 while 循环条件是否加等号是需要注意的问题，一般来说，是把 right 初始化为数组的长度，
//然后循环条件中不加等号，但是这种 right 的初始化对于这种目标值不固定的情况下不好使，需要初始化为长度减1
//那么此时循环条件中是否要加等号，这个其实很玄学，在 Find Peak Element 中，right 也是初始化为数组长度减1，
//但是循环条件却不能加等号。这道题却一定需要加等号，否则会跪在 [0] 这个 test case，有些时候固有的规律并不好使，
//可能只能代一些 corner case 来进行检验，比如 [], [0], [1,2] 这种最简便的例子。
//基于上面的分析，我们最先初始化 left 和 right 为0和 len-1，然后取中间值 mid，
//比较 citations[mid] 和 len-mid 做比较，如果前者大，则 right 移到 mid 之前，
//反之 left 移到 mid 之后，循环条件是 left<=right，最后返回 len-left 即可
public class hindexII_275 {
	 public int hIndex(int[] citations) {
		 int n = citations.length;
	        
	        if(n == 0 || citations[0] == 0 & n==1 ) return 0;
	           if(n == 1) return 1;
	           
	         int low = 0, high = n-1;
	        
	         while (low<=high) {
	        	 int mid = (low+high)/2;
				if (citations[mid] < (n-mid)) {
					low = mid+1;
				}else if(citations[mid] > (n-mid)) {
					high = mid -1;
				}else {
					return n - mid;
				}
				
			}
	         return n - low;
	           
//	        int i= n-1;//从数组最后一个数开始，相当于对数组从大到小排列
//	        while(i>= 0){
//	            if(citations[i] < (n-i)) break;//如果当前序列号大于对应的引用次数，结束循环
//	            i--;//否则看下一个数
//	        }
	        //return n-i-1;//返回当前序列号-1
	        
	    }

}
