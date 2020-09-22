package day30;


/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

1 <= n <= 19
 * */

//思路1:dp. 从1到n的数字里任选一个数i作为当前树的根节点，则1到i-1这些数都在根节点i的左子树上，i+1到n这些数都在i的右子树上
//然后对左右子树重复上述步骤，则最终得到的一定是一个bst，但这里不用递归，而是用dp记录每个子问题的结果.定义两个函数，g(n)
//f(i,n)，g(n)表示输入数字为n时能得到的bst个数，f(i,n)表示当以数字i为根时（1<= i <= n），能得到的bst个数
//则g(n)就等于f(1,n)+f(2,n)+.....+f(n,n)
//同时，当以i为根节点时，f(i,n) = g(i - 1) * g(n - i),（可以验证，如i = 2, n = 4时， i = 3,n = 7时.....）
//所以可以得到g(n) = ∑ G(i−1)⋅G(n−i) (i=1,2,.....n)

//思路2:这道题实际上是 卡塔兰数 Catalan Numbe 的一个例子，就跟斐波那契数列一样，我们把 n = 0 时赋为1，
//因为空树也算一种二叉搜索树，那么 n = 1 时的情况可以看做是其左子树个数乘以右子树的个数，左右子树都是空树，
//所以1乘1还是1。那么 n = 2 时，由于1和2都可以为根，分别算出来，再把它们加起来即可。n = 2 的情况可由下面式子算出
//（这里的 dp[i] 表示当有i个数字能组成的 BST 的个数）：
//dp[2] =  dp[0] * dp[1]　　　(1为根的情况，则左子树一定不存在，右子树可以有一个数字)
//+ dp[1] * dp[0]　　  (2为根的情况，则左子树可以有一个数字，右子树一定不存在)
//同理可写出 n = 3 的计算方法：
//dp[3] =  dp[0] * dp[2]　　　(1为根的情况，则左子树一定不存在，右子树可以有两个数字)
//+ dp[1] * dp[1]　　  (2为根的情况，则左右子树都可以各有一个数字)
//+ dp[2] * dp[0]　　  (3为根的情况，则左子树可以有两个数字，右子树一定不存在)
//由此可以得出卡塔兰数列的递推式为： C(n+1) = 2(2n+1)/n+2 C(n)



public class UniqueBinarySearchTrees_96 {
public int numTrees(int n) {
        //解法1，dp
//	int[] dp = new int[n+1];
//	dp[0] = 1;
//	dp[1] = 1;
//	for (int i = 2; i <= n; i++) {//i代表取到输入值n
//		for (int j = 1; j <=i; j++) {//j代表当前根节点,可以从1取到i
//			dp[i] += dp[j - 1] * dp[i - j];
//		}
//	}
//	return dp[n];
	
	//解法2
	  long C = 1;
	    for (int i = 0; i < n; ++i) {
	      C = C * 2 * (2 * i + 1) / (i + 2);
	    }
	    return (int) C;
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
