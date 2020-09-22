package day30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 * Given a collection of integers that might contain duplicates, nums, 
 * return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 * */

//思路：比题78多了一步去重，其它类似
public class SubsetsII_90 {
public List<List<Integer>> subsetsWithDup(int[] nums) {
       
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if(nums.length == 0 || nums == null)return res;
	List<Integer> curSubset = new ArrayList<Integer>();
	
	//解法1，非递归
		
//		res.add(curSubset);
//		for (int num : nums) {
//			List<List<Integer>> temp1= new ArrayList<List<Integer>>();
//			//取出当前结果集里的子集，给所有子集加上当前数字，去重后，加入结果集
//			for (List<Integer> list : res) {
//				List<Integer> temp2 = new ArrayList<Integer>(list);
//				temp2.add(num);
//	            Collections.sort(temp2);//排序去重
//				if(!temp1.contains(temp2))temp1.add(temp2);
//			}
//			for (List<Integer> list : temp1) { 
//	              //去重
//				if(!res.contains(list))res.add(list);
//			}
//		}
	
	//解法2，递归
	helper(nums, 0, curSubset, res);
		return res; 
    }

public void helper(int[] nums, int start,List<Integer> curSubset,List<List<Integer>> res ) {
	if(start == nums.length) {
		Collections.sort(curSubset);
		if(!res.contains(curSubset))res.add(new ArrayList<Integer>(curSubset));
		return;
	}
	
	helper(nums, start + 1, curSubset, res);
	curSubset.add(nums[start]);//取当前元素
	helper(nums, start + 1, curSubset, res);
	curSubset.remove(new Integer(nums[start]));//不取当前元素
}
}
