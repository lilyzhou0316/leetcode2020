package day05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements
 *  sequence.

Your algorithm should run in O(n) complexity.

Example:
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 Therefore its length is 4.
 * */

//思路1:用set去重并查询数是否存在，如果数组中的某个数为连续元素中的一个，那么可以通过set找到这串连续数的第一个数
//然后从第一个数开始查看这串数的总个数，记录最大个数值
//时间复杂度：O(n)

//思路2:类似1，用hashmap,但没有找连续数的最小值，而是将数组的每一个数都作为最小值来找最大值

public class longestConsecutiveSequence_128 {
    public int longestConsecutive(int[] nums) {
    	
        if (nums.length == 0) return 0 ;
		
        int max = 1;//nums至少有一个数字时，最大值最小为1
        //解法1:
        Set<Integer> set = new HashSet<Integer>();
        
        for (Integer num : nums) {//去重
			set.add(num);
		}
        
        for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]-1)) {//找到一串连续数中的最小数
				continue;
			}
			int temp = nums[i];//以当前数字作为最小值开始，找连续数里的元素，直到找到最大值
			while(set.contains(temp+1)) {
				temp++;
			}
			max = Math.max(max, (temp-nums[i]+1));
		}
        
        //解法2:
//        Map<Integer,Integer> map = new HashMap<Integer, Integer>();    
//        
//        for (int num : nums) {
//			map.put(num, num);//key 记录连续数中的最小值，value记录连续数中的最大值
//		}
//        
//        for (int num : nums) {
//			int temp = num;
//			while (map.containsKey(temp+1)) {//因为没有找连续数的最小数，所以会有重复查找
//				temp++;//找最大值
//			}
//			//map.put(num, temp);
//			max = Math.max(max, temp-num+1);
//		}
        return max;
    }
}
