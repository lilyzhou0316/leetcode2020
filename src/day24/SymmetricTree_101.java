package day24;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import day24.SameTree_100.TreeNode;

/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
 

Follow up: Solve it both recursively and iteratively.
 * */

//思路1：类似题100， 遍历二叉树，从level1开始，如果当前两个节点n1,n2相等， 则看n1的左节点是否等于n2的右节点，
//n1的右节点是否等于n2的左节点，满足条件继续遍历

//思路2:迭代。借助两个队列queue来实现，我们首先判空，如果root为空，直接返回true。否则将root的左右两个子结点分别装入
//两个队列，然后开始循环，循环条件是两个队列都不为空。在while循环中，我们首先分别将两个队列中的队首元素取出来，
//如果两个都是空结点，那么直接跳过，因为我们还没有比较完，有可能某个结点没有左子结点，但是右子结点仍然存在，所以这里
//只能continue。然后再看，如果有一个为空，另一个不为空，那么此时对称性已经被破坏了，不用再比下去了，直接返回false。
//若两个结点都存在，但是其结点值不同，这也破坏了对称性，返回false。否则的话将node1的左子结点和右子结点排入队列1，
//注意这里要将node2的右子结点和左子结点排入队列2，注意顺序的对应问题。最后循环结束后直接返回true，这里不必再去check
//两个队列是否同时为空，因为循环结束后只可能是两个队列均为空的情况，其他情况比如一空一不空的直接在循环内部就返回false了
public class SymmetricTree_101 {
	//解法1
//public boolean isSymmetric(TreeNode root) {
//        if(root == null)return true;
//        return helper(root.left, root.right);
//    }
//
//public boolean helper(TreeNode n1, TreeNode n2) {
//	if(n1 == null && n2 == null) return true;
//	if((n1 == null && n2 != null) || (n1 != null && n2 == null) || (n1.val != n2.val))
//		return false;//n1,n2其中一个为空，或者n1,n2不相等
//	
//	//n1,n2相等，则看n1的左节点值是否等于n2的右节点值，n1的右节点值是否等于n2的左节点值
//	return helper(n1.left, n2.right) && helper(n1.right, n2.left);
//}
	
	//解法2
	public boolean isSymmetric(TreeNode root) {
		if(root == null)return true;
		List<TreeNode> s1 = new ArrayList<>();//用两个队列来分别存放当前节点的左右节点值
		List<TreeNode> s2 = new ArrayList<>();
		s1.add(root.left);
		s2.add(root.right);
		while(!s1.isEmpty() && !s2.isEmpty()) {
			//当两个栈不为空时，取出第一个元素
			TreeNode t1 = s1.remove(0);
			TreeNode t2 = s2.remove(0);
			
			//比较两者的值
			if(t1 == null && t2 == null)continue;//都为空时跳过，看下一个节点
			if((t1 == null && t2 != null) || (t1 != null && t2 == null) || (t1.val != t2.val))
				return false;//两个节点其中一个为空时，或者两者值不等
			
			//两节点值相等，则把第一个节点的左右节点加入队列1，把第二个节点的右左节点加入队列2
			s1.add(t1.left);
			s1.add(t1.right);
			s2.add(t2.right);
			s2.add(t2.left);
			
		}
		//出循环说明所有节点都遍历过且符合条件
		return true;
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
