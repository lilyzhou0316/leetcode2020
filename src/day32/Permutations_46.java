package day32;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 * */

//思路：这道题是求全排列问题，给的输入数组没有重复项，这跟之前的那道 Combinations 和类似，解法基本相同，但是不同点在于
//那道不同的数字顺序只算一种，是一道典型的组合题，而此题是求全排列问题，还是用递归 DFS 来求解。这里需要用到一个 visited 
//数组来标记某个数字是否访问过，然后在 DFS 递归函数的循环应从头开始，而不是从 level 开始，这是和 Combinations 不同
//的地方，其余思路大体相同。这里再说下 level 吧，其本质是记录当前已经拼出的个数，一旦其达到了 nums 数组的长度，说明此时
//已经是一个全排列了，因为再加数字的话，就会超出。还有就是，为啥这里的 level 要从0开始遍历，因为这是求全排列，每个位置都
//可能放任意一个数字，这样会有个问题，数字有可能被重复使用，由于全排列是不能重复使用数字的，所以需要用一个 visited 数组
//来标记某个数字是否使用过
public class Permutations_46 {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		if(nums.length == 0 || nums == null)return res;
		
		int[] visited = new int[nums.length];
		helper(0, nums, visited, cur, res);
		System.out.println(res);
		return res;
	}
	public void helper(int len, int[] nums, int[] visited,List<Integer> cur, List<List<Integer>> res) {
		if(len == nums.length) {
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if(visited[i] == 1)continue;//当前数字已经访问过,则跳过
			//否则没有访问过，加入当前结果集，并标记为访问过
			cur.add(nums[i]);
			visited[i] = 1;
			helper(len + 1, nums, visited, cur, res);//当前结果集的长度加1
			//回溯
			cur.remove(cur.size() - 1);
			visited[i] = 0;
		}
	}
	
	public static void main(String[] args) {
		Permutations_46 a = new Permutations_46();
		int[] nums = {1,2,3};
		a.permute(nums);
	}
}
