package day10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
 * One envelope can fit into another if and only if both the width and height of one 
 * envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 
3 ([2,3] => [5,4] => [6,7]).
 * */

//我的思路：类似题300的dp暴力解法，先给数组排序（先按w从小到大，然后按h从大到小），然后遍历数组，对当前元素i,查看它前面的所有元素，满足
//dp[i]= max(dp[i],dp[j]+1)

//思路2:类似题300的思路2，，先给数组排序（先按w从小到大，然后按h从大到小），然后对h同样找tail[i],表示当前i+1个元素都是上升子序列，同时结尾是当前的最小值
public class RussianDollEnvelopes_354 {
	 public int maxEnvelopes(int[][] envelopes) {
	       int n = envelopes.length;
	       if(n <= 1)return n;
	       int max = 1;
	       // sort on increasing in first dimension and decreasing in second
	        Arrays.sort(envelopes, new Comparator<int[]>() {
	            public int compare(int[] arr1, int[] arr2) {
	                if (arr1[0] == arr2[0]) {//如果w相等，再按h排序
	                    return arr2[1] - arr1[1];
	                } else {//先按w排序
	                    return arr1[0] - arr2[0];
	                }
	           }
	        });
	        
	        //解法1：排序+dp
//	        int[] dp = new int[n];
//	        Arrays.fill(dp, 1);
// for (int i = 1; i < envelopes.length; i++) {
//	for (int j = 0; j < i; j++) {
//		if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
//			dp[i] = Math.max(dp[i], dp[j]+1);
//		}
//	}
//	max = Math.max(max, dp[i]);
//}
//	      
	       
	        //解法2:
	        int[][] tail = new int[n][];
	        tail[0] = envelopes[0];
	        int end = 0;//指向tail数组的最后一个位置
	        //从第二个元素开始
	        for (int i = 1; i < tail.length; i++) {
				if(envelopes[i][1] > tail[end][1]) {
					//如果当前元素的h大于tail的最后一个元素的h，则直接加入
					end++;
					tail[end] = envelopes[i];
				}else {
					//说明当前元素的h小于等于tail的最后一个元素的h
					 // 使用二分查找法，在有序数组 tail 中
		            // 找到第 1 个大于等于 envelopes[i][1] 的元素，尝试让那个元素更小
					int l = 0, r = end;
					while(l < r) {
						int mid = l + (r- l)/2;
						if(tail[mid][1] < envelopes[i][1])l = mid + 1;
						else r = mid;
					}
					tail[l][1] = envelopes[i][1];
				}
			}
	       return ++end;
	     
	    }
	 
//	 public static void main(String[] args) {
//		 RussianDollEnvelopes_354 a = new RussianDollEnvelopes_354();
//		 int[][] b = {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},
//				 {6,360},{7,380}};
//		 
//				int max = a.maxEnvelopes(b);
//	}
}
