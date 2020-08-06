package day02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of size n, find the majority element. The majority element is the element 
 * that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3

Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 * */

//思路1:用哈希表，这种方法需要 O(n) 的时间和空间，key是元素，value是元素出现的次数，最后找到value大于n/2的元素


//思路2:用一种叫摩尔投票法 Moore Voting，需要 O(n) 的时间和 O(1) 的空间，比前一种方法更好。
//这种投票法先将第一个数字假设为过半数，然后把计数器设为1，比较下一个数和此数是否相等，若相等则计数器加一，反之减一。
//然后看此时计数器的值，若为零，则将下一个值设为候选过半数。以此类推直到遍历完整个数组，
//当前候选过半数即为该数组的过半数。不仔细弄懂摩尔投票法的精髓的话，过一阵子还是会忘记的，
//首先要明确的是这个叼炸天的方法是有前提的，就是数组中一定要有过半数的存在才能使用，下面来看本算法的思路，
//这是一种先假设候选者，然后再进行验证的算法。现将数组中的第一个数假设为过半数，然后进行统计其出现的次数，
//如果遇到同样的数，则计数器自增1，否则计数器自减1，如果计数器减到了0，则更换下一个数字为候选者。
//这是一个很巧妙的设定，也是本算法的精髓所在，为啥遇到不同的要计数器减1呢，为啥减到0了又要更换候选者呢？
//首先是有那个强大的前提存在，一定会有一个出现超过半数的数字存在，那么如果计数器减到0了话，
//说明目前不是候选者数字的个数已经跟候选者的出现个数相同了，那么这个候选者已经很 weak，不一定能出现超过半数，
//此时选择更换当前的候选者。那有可能你会有疑问，那万一后面又大量的出现了之前的候选者怎么办，不需要担心，
//如果之前的候选者在后面大量出现的话，其又会重新变为候选者，直到最终验证成为正确的过半数


//思路3:因为该数一定存在，且数组长度不为0，给数组排序后，n/2这个index上的元素一定为目标元素
public class majorityElement_169 {
public int majorityElement(int[] nums) {
	//解法1:
//	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//	int result = 0;
//	for (int i = 0; i < nums.length; i++) {//将元素放入map中并记录出现次数
//		if(map.containsKey(nums[i])) {
//			int a = map.get(nums[i]);
//			map.put(nums[i], a+1);
//		}else {
//			map.put(nums[i], 1);
//		}
//	}
//     for (int i = 0; i < nums.length; i++) {
//		int count = map.get(nums[i]);//取出每个元素的出现总次数
//		if(count > nums.length/2)result = nums[i];
//	}
//	
//     return result;
     
    //解法2:Moore Voting
	int res = 0;
	int count = 0;
	for (int num : nums) {
		if(count == 0) {//初始状态，或者当上一个候选数的count被抵消到0时，换候选数
			res = num;
			count++;
		}else if (num == res) {//当count不等于0时，看当前num是否等于候选数
			count++;
		}else {//当count不等于0且当前num不等于候选数时
			count--;
		}
		
	}
	return res;
	
     //解法3:
//     Arrays.sort(nums);
//     int idx=nums.length/2;
//     return nums[idx];
    }
}
