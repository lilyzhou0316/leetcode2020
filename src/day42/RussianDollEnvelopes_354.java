package day42;

import java.util.Arrays;
import java.util.Comparator;

/*
 * You have a number of envelopes with widths and heights given as a pair of integers 
 * (w, h). One envelope can fit into another if and only if both the width and height of 
 * one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 
3 ([2,3] => [5,4] => [6,7]).
 * */
public class RussianDollEnvelopes_354 {
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes.length == 0 || envelopes == null)return 0;
		if(envelopes.length == 1)return 1;
		
		//思路：300升级版，需要写一个比较器让所有envelopes按照宽度从小到大排序，碰到宽度相同时，长度大的在前
		Arrays.sort(envelopes, new Comparator<int[]>() {//每次传入的是一个[5,4] array
			public int compare(int[] a1, int[] a2) {
				if(a1[0] == a2[0])return a2[1] - a1[1];//w相同时，h大的在前，因为如果最大的h都不满足条件，则不用再看后面的
				else return a1[0] - a2[0];//w不相同时，w小的在前
			}
		});
		
		//1.dp
//		int[] dp = new int[envelopes.length];//dp[i]代表以当前信封i为最大信封时，能套信封的最多个数
//		int max = 1;
//		Arrays.fill(dp, 1);
//		
//		for (int i = 1; i < envelopes.length; i++) {
//			for (int j = 0; j < i; j++) {
//				if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
//					dp[i] = Math.max(dp[i], dp[j] + 1);
//				}
//			}
//			max = Math.max(max, dp[i]);
//		}
//		return max;
		//2.二分法插入替换,这里只比较h的大小，因为前面已经对w排序了
		
		int[][] sorted = new int[envelopes.length][];
		sorted[0] = envelopes[0];
		int end = 0;
		
		for (int i = 1; i < envelopes.length; i++) {
			if(envelopes[i][1] > sorted[end][1]) {
				//当前信封h大于排序后的数组里的最后一个信封时
				end++;
				sorted[end] =envelopes[i];
			}else {
				//h小于等于时
				int l = 0, r = sorted.length;
				while(l < r) {
					int mid = l + (r - l) / 2;
					if(sorted[mid][1] >= envelopes[i][1])r = mid;
					else l = mid + 1;
				}
				//出循环时，找到替换位置r
				sorted[r][1] = envelopes[i][1];	
			}
		}
		return end + 1;
		
	}
}
