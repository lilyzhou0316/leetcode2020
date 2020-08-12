package day07;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array nums and a target value k , find the maximum length of a subarray 
 * subarray即要连续的元素
 * that sums to k. If there isn't one, return 0 instead.

Example 1:

Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:

Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O( n ) time?
 * */

//思路：用hashmap保存前i-1个数的累加和值和对应的索引i
public class MaximumSizeSubarraySumEqualsk_325 {
    public int maximumLength(int[] nums, int k) {
	if(nums.length == 0 || nums == null)return 0;
	
	int sum = 0;//记录累加和
	int lenMax = 0;//记录当前满足条件的最长子序列的长度
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	for (int i = 0; i < nums.length; i++) {//遍历数组
		sum += nums[i];//计算累加和
		if(sum == k) {//1.如果当前累加值正好等于k,则找到了目标长度（因为是从第一个元素开始累加的，所以长度一定是最长的）
			lenMax = i+1;
		}else if(map.containsKey(sum - k)) {//2.如果当前累加值 - 之前某个位置j上的累加值 = k，
			//说明目标长度 = i - j与lenMax里的较大值
			lenMax = Math.max(lenMax, i - map.get(sum - k));
		}
		if (!map.containsKey(sum)) {//如果当前累加和没有出现过，加入map
			map.put(sum, i);
		}
	}
	return lenMax;
}
}
