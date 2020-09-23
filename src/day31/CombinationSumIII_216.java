package day31;

import java.util.ArrayList;
import java.util.List;

/*
 * Find all valid combinations of k numbers that sum up to n such that the 
 * following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain 
the same combination twice, and the combinations may be returned in any order.

 

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations. [1,2,1] is not valid because 1 is 
used twice.
Example 4:

Input: k = 3, n = 2
Output: []
Explanation: There are no valid combinations.
Example 5:

Input: k = 9, n = 45
Output: [[1,2,3,4,5,6,7,8,9]]
Explanation:
1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
​​​​​​​There are no other valid combinations.
 

Constraints:

2 <= k <= 9
1 <= n <= 60
 * */

//思路：与题39，40类似，只不过数字不重复，只能用一次，且要考虑k和n的关系
public class CombinationSumIII_216 {
public List<List<Integer>> combinationSum3(int k, int n) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> output = new ArrayList<Integer>();
	
	if(k >= n) {
		return  res;
	}
	helper(res, output, 1, k, n);
	return res;
	
    }

public void helper(List<List<Integer>> res,List<Integer> output, int start, int k, int n) {
	if(n < 0)return;
	if(output.size() >= k && n > 0)return;
	if(output.size() == k && n == 0) {
		res.add(new ArrayList<Integer>(output));
		return;
	}
	
	for (int i = start; i < 10; i++) {
		output.add(i);
		helper(res, output, i + 1, k, n - i);
		output.remove(output.size() - 1);
	}
}
}
