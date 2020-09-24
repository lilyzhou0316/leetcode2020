package day32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a collection of numbers that might contain duplicates, return all possible unique
 *  permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 * */
//思路：与46基本一致，多一步跳过重复元素的操作
//这道题是之前那道 Permutations 的延伸，由于输入数组有可能出现重复数字，如果按照之前的算法运算，会有重复排列产生，
//我们要避免重复的产生，在递归函数中要判断前面一个数和当前的数是否相等，如果相等，且其对应的 visited 中的值为1，
//当前的数字才能使用（下文中会解释这样做的原因），否则需要跳过，这样就不会产生重复排列了
//由于递归的 for 都是从0开始，难免会重复遍历到数字，而全排列不能重复使用数字，意思是每个 nums 中的数字在全排列中只能
//使用一次（当然这并不妨碍 nums 中存在重复数字）。不能重复使用数字就靠 visited 数组来保证，这就是第一个 if 剪枝的意义
//所在。关键来看第二个 if 剪枝的意义，这里说当前数字和前一个数字相同，且前一个数字的 visited 值为0的时候，必须跳过。
//这里的前一个数 visited 值为0，并不代表前一个数字没有被处理过，也可能是递归结束后恢复状态时将 visited 值重置为0了，
//实际上就是这种情况
public class PermutationsII_47 {
public List<List<Integer>> permuteUnique(int[] nums) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	List<Integer> cur = new ArrayList<Integer>();
	if(nums.length == 0 || nums == null)return res;
	 
	Arrays.sort(nums);//注意一定要给nums排序了，下面才能跳过重复元素
	int[] visited = new int[nums.length];
	helper(0, nums, visited, cur, res);
	return res;
    }

public void helper(int len, int[] nums, int[] visited, List<Integer> cur, List<List<Integer>> res) {
	if(len == nums.length) {
		res.add(new ArrayList<Integer>(cur));
		return;
	}
	
	for (int i = 0; i < nums.length; i++) {
		if(visited[i] == 1)continue;
		//这里的前一个数 visited 值为0，并不代表前一个数字没有被处理过，而是递归结束后恢复状态时将 visited 值重置为0了，
		//实际上就是这种情况
		if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)continue;//跳过重复元素
		
		cur.add(nums[i]);
		visited[i] = 1;
		helper(len, nums, visited, cur, res);
		cur.remove(cur.size() - 1);
		visited[i] = 0;
	}
}
}
