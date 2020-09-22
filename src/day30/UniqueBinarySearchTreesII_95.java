package day30;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store 
 * values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

0 <= n <= 8
 * */

//思路1：这种建树问题一般来说都是用递归来解，这道题也不例外，划分左右子树，递归构造。这个其实是用到了大名鼎鼎的分治法
//Divide and Conquer，类似的题目还有之前的那道 Different Ways to Add Parentheses 用的方法一样，用递归来解，
//划分左右两个子数组，递归构造。刚开始时，将区间 [1, n] 当作一个整体，然后需要将其中的每个数字都当作根结点，
//其划分开了左右两个子区间，然后分别调用递归函数，会得到两个结点数组，接下来要做的就是从这两个数组中每次各取一个结点，
//当作当前根结点的左右子结点，然后将根结点加入结果 res 数组中即可
//时间复杂度：O（4^n / n^0.5）


//思路2:对1的优化，用记忆数组来优化，保存计算过的中间结果，从而避免重复计算。注意这道题的标签有一个是动态规划 
//Dynamic Programming，其实带记忆数组的递归形式就是 DP 的一种，memo[i][j] 表示在区间 [i, j] 范围内可以生成的所有
//BST 的根结点，所以 memo 必须是一个三维数组，这样在递归函数中，就可以去 memo 中查找当前的区间是否已经计算过了，是的话，
//直接返回 memo 中的数组，否则就按之前的方法去计算，最后计算好了之后要更新 memo 数组
public class UniqueBinarySearchTreesII_95 {
	public List<TreeNode> generateTrees(int n) {
		 if(n == 0)return new ArrayList<>();
		//解法1
       
      // return helper1(1,n);
		 //解法2
		 List<List<List<TreeNode>>> dp = new ArrayList<List<List<TreeNode>>>();
		 for (int i = 0; i < n; i++) {
			 dp.add(new ArrayList<List<TreeNode>>());
			 for (int j = 0; j < n; j++) {
				dp.get(i).add(new ArrayList<>());
			}
		}
		
        return helper2(1, n, dp);
    }

//public List<TreeNode> helper1(int start, int end) {
//    List<TreeNode> res = new ArrayList<>();
//	if(start > end){
//        res.add(null);
//        return res;
//    };
//    
//	for (int i = start; i <= end ; i++) {//以当前i为根节点
//		List<TreeNode> left = helper1(start, i - 1);//得到其左子树的list
//		List<TreeNode> right = helper1(i + 1, end);//得到其右子树的list
//		for (TreeNode n1 : left) {//左子树中的任意一个节点都可能成为左子树的根节点
//			for (TreeNode n2 : right) {
//				TreeNode curRoot = new TreeNode(i);
//				curRoot.left = n1;//以左子树当前节点做为左子树的根节点
//				curRoot.right = n2;//以右子树当前节点做为右子树的根节点
//				res.add(curRoot);
//			}
//		}
//	}
//	return res;
//}
	
	public List<TreeNode> helper2(int start, int end,List<List<List<TreeNode>>> dp) {
	    List<TreeNode> res = new ArrayList<>();
		if(start > end){
	        res.add(null);
	        return res;
	    };
	    if(!dp.get(start - 1).get(end - 1).isEmpty())return dp.get(start - 1).get(end - 1);
	    
		for (int i = start; i <= end ; i++) {//以当前i为根节点
			List<TreeNode> left = helper2(start, i - 1,dp);//得到其左子树的list
			List<TreeNode> right = helper2(i + 1, end, dp);//得到其右子树的list
			for (TreeNode n1 : left) {//左子树中的任意一个节点都可能成为左子树的根节点
				for (TreeNode n2 : right) {
					TreeNode curRoot = new TreeNode(i);
					curRoot.left = n1;//以左子树当前节点做为左子树的根节点
					curRoot.right = n2;//以右子树当前节点做为右子树的根节点
					dp.get(start - 1).get(end - 1).add(curRoot);
				}
			}
		}
		return dp.get(start - 1).get(end - 1);
		
	}
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
		    this.val = val;
		    this.left = left;
		    this.right = right;
		}
 	}
}
