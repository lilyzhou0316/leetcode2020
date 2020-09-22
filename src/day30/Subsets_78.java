package day30;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/*
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 * */

//思路1：非递归，对于题目中给的例子 [1,2,3] 来说，最开始是空集，那么我们现在要处理1，就在空集上加1，为 [1]，
//现在我们有两个自己 [] 和 [1]，下面我们来处理2，我们在之前的子集基础上，每个都加个2，可以分别得到 [2]，[1, 2]，
//那么现在所有的子集合为 [], [1], [2], [1, 2]，同理处理3的情况可得 [3], [1, 3], [2, 3], [1, 2, 3], 再加上之前
//的子集就是所有的子集合了
//Time complexity: O(N * 2^N)

//思路2：递归的解法，相当于一种深度优先搜索，由于原集合每一个数字只有两种状态，要么存在，要么不存在，那么在构造子集时就有
//选择和不选择两种情况，所以可以构造一棵二叉树，左子树表示选择该层处理的节点，右子树表示不选择，最终的叶节点就是所有子集合，
//树的结构如下：
//                    []        
//             /               \        
//            /                 \     
//           /                   \
//         [1]                   []
//     /           \           /     \
//    /             \         /       \        
//    [1 2]         [1]       [2]      []
//  /       \      /   \     /   \    /  \
//[1 2 3]  [1 2] [1 3] [1] [2 3] [2] [3]  [] 
public class Subsets_78 {
	public List<List<Integer>> subsets(int[] nums) {
		 List<List<Integer>> res = new ArrayList<>();
			if(nums.length == 0 || nums == null)return res;
			List<Integer> curSubset = new ArrayList<Integer>();
			
			
			//解法1
//			res.add(new ArrayList<Integer>());//空集为任何集合的子集，所以先加入空集
//			for (int i = 0; i < nums.length; i++) {
//				List<List<Integer>>  temp1 = new ArrayList<List<Integer>>();//用一个临时变量保存新子集
//				
//					for (List<Integer> list : res) {
//						//这里需要用到copy，而不是直接等于，否则后面会修改res里的原list
//						List<Integer> temp2 = new ArrayList<Integer>(list);
//						//取出当前结果集中的所有子集，给每个子集加上当前的数字组成新的子集
//						temp2.add(nums[i]);
//						temp1.add(temp2);
//					}
//					//再把新的子集加入结果集
//					for (List<Integer> list : temp1) {
//						res.add(list);
//					}		
//				
//			}
			
			//解法2:递归，
			helper(nums, 0, res, curSubset);
			return res;
	}
	
	public void helper(int[] nums, int start, List<List<Integer>> res, List<Integer> curSubset) {
		if(start == nums.length){
            res.add(new ArrayList<Integer>(curSubset));
            return;
        }
		
		    helper(nums, start + 1, res, curSubset);//先把空集加入
			curSubset.add(nums[start]);//取当前元素
			helper(nums, start + 1, res, curSubset);//处理当前元素的下一个元素
			curSubset.remove(new Integer(nums[start]));//不取当前元素
		
	}
	
	public static void main(String[] args) {
		Subsets_78 a = new Subsets_78();
		int[] nums = {1,2,3};
		a.subsets(nums);
	}
}
