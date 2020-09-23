package day31;

import java.util.ArrayList;
import java.util.List;

/*
 * Given two integers n and k, return all possible combinations of k numbers 
 * out of 1 ... n.

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n
 * */

//思路2：backtrack, 类似题78，90
public class Combinations_77 {
	
	//解法1
//	public List<List<Integer>> combine(int n, int k) {
//	    // init first combination
//	    LinkedList<Integer> nums = new LinkedList<Integer>();
//	    for(int i = 1; i < k + 1; ++i)
//	      nums.add(i);
//	    nums.add(n + 1);
//
//	    List<List<Integer>> output = new ArrayList<List<Integer>>();
//	    int j = 0;
//	    while (j < k) {
//	      // add current combination
//	      output.add(new LinkedList(nums.subList(0, k)));
//	      // increase first nums[j] by one
//	      // if nums[j] + 1 != nums[j + 1]
//	      j = 0;
//	      while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
//	        nums.set(j, j++ + 1);
//	      nums.set(j, nums.get(j) + 1);
//	    }
//	    return output;
//	  }
	
	
	//解法2
public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
       
        helper(1, new ArrayList<Integer>(), k, n, res);
        return res;
        
    }
public void helper(int start, List<Integer> cur, int k, int n, List<List<Integer>> res) {
	if(k == 0) {
		res.add(new ArrayList<Integer>(cur));
		return;
	}
	//i是当前数字，cur是当前结果集
	for (int j = start; j < n + 1; j++) {
		cur.add(j);
		helper(j + 1, cur, k - 1, n, res);
		cur.remove(cur.size() - 1);
	}
}
}
