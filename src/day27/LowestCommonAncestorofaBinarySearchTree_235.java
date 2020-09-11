package day27;

import java.util.ArrayList;
import java.util.List;

import day27.ValidateBinarySearchTree_98.TreeNode;

/*
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes 
 * in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
between two nodes p and q as the lowest node in T that has both p and q as descendants
 (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.


Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant 
of itself according to the LCA definition.

Constraints:

All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.
 * */

//我的思路：找出p,q各自的所有祖先节点（通过找路径），然后找最小的共同祖先节点

//思路1:这道题我们可以用递归来求解，我们首先来看题目中给的例子，由于二叉搜索树的特点是左<根<右，所以根节点的值
//一直都是中间值，大于左子树的所有节点值，小于右子树的所有节点值，那么我们可以做如下的判断，如果根节点的值大于p和q之间的
//较大值，说明p和q都在左子树中，那么此时我们就进入根节点的左子节点继续递归，如果根节点小于p和q之间的较小值，
//说明p和q都在右子树中，那么此时我们就进入根节点的右子节点继续递归
//如果都不是，则说明当前根节点就是最小共同父节点，（一个在左子树，一个在右子树，根节点是唯一公共祖先）
//直接返回即可
public class LowestCommonAncestorofaBinarySearchTree_235 {
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	if(root == null)return root;
    TreeNode targeTreeNode = new TreeNode();
     if(root.val > Math.max(p.val, q.val)) {
     	//p,q都在左子树上,继续往左子树递归
     	return lowestCommonAncestor(root.left, p, q);
     }else if(root.val < Math.min(p.val, q.val)){
         //p,q都在右子树上,继续往右子树递归
         return lowestCommonAncestor(root.right, p, q);
     }
    else {
     	//p,q都在右子树上，或者一边一个
     	targeTreeNode.val = root.val;
     }
     return targeTreeNode;

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
