package day02;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]

Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
 * 
 * */

//思路1:用哈希表，这种方法需要 O(n) 的时间和空间(空间不满足)，key是元素，value是元素出现的次数，
//最后找到value大于n/3的元素

//思路2:这道题让我们求出现次数大于 n/3 的数字，而且限定了时间和空间复杂度，那么就不能排序，也不能使用 HashMap，
//这么苛刻的限制条件只有一种方法能解了，那就是摩尔投票法 Moore Voting，这种方法在之前那道题 Majority Element 
//中也使用了。题目中给了一条很重要的提示，让先考虑可能会有多少个这样的数字，经过举了很多例子分析得出，
//任意一个数组出现次数大于 n/3 的数最多有两个，因为如果有超过两个，也就是至少三个数字满足“出现的次数大于 n/3”，
//那么就意味着数组里总共有超过 3*(n/3) = n 个数字，这与已知的数组大小矛盾，所以，只可能有两个或者更少）。
//那么有了这个信息，使用投票法的核心是找出两个候选数进行投票，需要两遍遍历，第一遍历找出两个候选数，
//第二遍遍历重新投票验证这两个候选数是否为符合题意的数即可，选候选数方法和前面那篇 Majority Element 一样，
//由于之前那题题目中限定了一定会有大多数存在，故而省略了验证候选众数的步骤，这道题却没有这种限定，
//即满足要求的大多数可能不存在，所以要有验证
public class majorityElementII_229 {
	 public List<Integer> majorityElement(int[] nums) {
		//解法1:
//			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//			List<Integer> result = new ArrayList<Integer>();
//			for (int i = 0; i < nums.length; i++) {//将元素放入map中并记录出现次数
//				if(map.containsKey(nums[i])) {
//					int a = map.get(nums[i]);
//					map.put(nums[i], a+1);
//				}else {
//					map.put(nums[i], 1);
//				}
//			}
//		     for (int i = 0; i < nums.length; i++) {
//				int count = map.get(nums[i]);//取出每个元素的出现总次数
//				if(count > nums.length/3 && !result.contains(nums[i]))result.add(nums[i]);
//			}
//			
//		     return result;
		 
		 //解法2:
		 int num1 = 0, num2 = 0, count1 = 0, count2 = 0, n = nums.length;
		 List<Integer> result = new ArrayList<Integer>();
		 for (int num:nums) {//这里找到两个目标数的过程和169一样
			 if (num1== num) { //Order is important. Because if number is equal to second and firstcount is zero then first and second will be duplicates. 
	                count1++;
	            } else if (num2 == num) {
	                count2++;
	            } else if (count1 == 0) {
	                num1 = num;
	                count1++;
	            } else if(count2 == 0) {
	            	num2 = num;
	                count2++;
	            } else {
	            	count1--;
	            	count2--;
	            }
		}
		 
		 //验证这两个数是否为要找的数，即重新遍历一遍数组，计算num1和num2出现的次数，看是否大于n/3
		 count1 = 0;
		 count2 = 0;
		 for (int num:nums) {
			if (num == num1) {
				count1++;
			}
			if (num == num2) {
				count2++;
			}
		}
		 
		 if(count1 > n/3 &&!result.contains(num1))result.add(num1);
		 if(count2 > n/3 &&!result.contains(num2))result.add(num2);
		 return result;
	    }

}
