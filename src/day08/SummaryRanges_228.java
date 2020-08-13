package day08;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.


Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 * */

//我的思路：用list模拟栈，因为原数组有序且无重复，将第一个元素加入栈，如果当前元素正好等于栈顶元素+1，直接入栈
//如果不等，说明不连续了，则取出所有栈内元素组成一个区间加入结果集

//思路2:两个指针。只需遍历一遍数组即可，每次检查下一个数是不是递增的，如果是，则继续往下遍历，如果不是了，
//我们还要判断此时是一个数还是一个序列，一个数直接存入结果，序列的话要存入首尾数字和箭头“->"。
//我们需要两个变量i和j，其中i是连续序列起始数字的位置，j是连续数列的长度，当j为1时，说明只有一个数字，
//若大于1，则是一个连续序列
public class SummaryRanges_228 {
public List<String> summaryRanges(int[] nums) {
	 if(nums.length == 0 || nums == null)return new ArrayList<String>();
	 List<String> res = new ArrayList<String>();
	 
        //解法1:栈
//	List<Integer> stack = new ArrayList<Integer>();
//	for (int i = 0; i < nums.length; i++) {
//		if(stack.isEmpty() || stack.get(stack.size()-1) + 1 == nums[i]) {
//			//如果当前元素正好等于栈顶元素+1或者栈为空，直接入栈
//			stack.add(nums[i]);
//		}else {//当前元素不等于栈顶元素+1,取出之前的元素加入结果集，然后清空栈，将当前元素加入栈
//			//1.栈里有多于1个元素时，取出栈中第一个元素和最后一个元素，作为结果加入结果集,然后清空栈
//			if (stack.size()>1) {
//				String tempString = stack.get(0) + "->" + stack.get(stack.size()-1);
//				res.add(tempString);
//				stack.clear();
//			} else {//2.栈里只有一个元素时，直接加入结果集，然后清空栈
//				String tempString = stack.get(0) + "";
//				res.add(tempString);
//				stack.clear();
//			}
//			stack.add(nums[i]);
//		}
//	}
//	//出循环时，栈里可能还有元素
//	if (stack.size()>1) {
//		String tempString = stack.get(0) + "->" + stack.get(stack.size()-1);
//		res.add(tempString);
//		stack.clear();
//	} else {
//		String tempString = stack.get(0) + "";
//		res.add(tempString);
//		stack.clear();
//	}
	 
	//解法2:双指针
		 int cur = 0;
		 int next;//两个指针，一个指向当前元素，一个指向它的下一个元素，看是否是连续递增的
		 while(cur < nums.length - 1) {
			next = cur+1;
			int temp = nums[cur];
			while (next < nums.length && nums[next] == ++temp ) {
				next++;	
			}
			//出循环说明不连续了，检查cur和next之间是否只有一个元素
			if (next - cur -1 == 0) {
				res.add(nums[cur] + "");
				cur++;
			}else if(next - cur -1 > 0) {
				res.add(nums[cur] + "->" + nums[next- 1]);
				cur = next;
			}	
		}
	     //出循环时，cur可能指向了最后一个元素
	        if(cur == nums.length - 1)res.add(nums[cur] + "");
	        
	return res;
    }
}
