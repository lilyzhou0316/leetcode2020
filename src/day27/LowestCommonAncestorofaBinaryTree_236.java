package day27;

import day27.LowestCommonAncestorofaBinarySearchTree_235.TreeNode;

/*
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
 * in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is 
defined between two nodes p and q as the lowest node in T that has both p and q as 
descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.


Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

 * */

//思路：与题235不同，这题里数字无序，只能在二叉树中来搜索p和q，然后从路径中找到最后一个相同的节点即为父节点，
//可以用递归来实现，在递归函数中，首先看当前结点是否为空，若为空则直接返回空，若为p或q中的任意一个，也直接返回当前结点。
//否则的话就对其左右子结点分别调用递归函数，由于这道题限制了p和q一定都在二叉树中存在，那么如果当前结点不等于p或q，
//p和q要么分别位于左右子树中，要么同时位于左子树，或者同时位于右子树，那么我们分别来讨论：
//- 若p和q分别位于左右子树中，那么对左右子结点调用递归函数，会分别返回p和q结点的位置，而当前结点正好就是p和q的
//最小共同父结点，直接返回当前结点即可，这就是题目中的例子1的情况。
//- 若p和q同时位于左子树，这里有两种情况，一种情况是 left 会返回p和q中较高的那个位置，而 right 会返回空，
//所以最终返回非空的 left 即可，这就是题目中的例子2的情况。还有一种情况是会返回p和q的最小父结点，
//就是说当前结点的左子树中的某个结点才是p和q的最小父结点，会被返回。
//- 若p和q同时位于右子树，同样这里有两种情况，一种情况是 right 会返回p和q中较高的那个位置，而 left 会返回空，
//所以最终返回非空的 right 即可，还有一种情况是会返回p和q的最小父结点，就是说当前结点的右子树中的某个结点
//才是p和q的最小父结点，会被返回
public class LowestCommonAncestorofaBinaryTree_236 {
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	//当前节点为空，或者当前节点为p,q中的一个时，直接返回
        if(root == null || p.val == root.val || q.val == root.val)return root;
        
        //当前节点不是p,q中任意一个，则分别查看左右子树
        TreeNode lefTreeNode = lowestCommonAncestor(root.left, p, q);
        TreeNode righTreeNode = lowestCommonAncestor(root.right, p, q);
        //如果p,q分别位于当前节点的左右子树上，则lefTreeNode,righTreeNode同时都不为空，且当前节点即为最近祖先节点
        if(lefTreeNode != null && righTreeNode != null)return root;
        
        //否则p,q要么都位于当前节点的左子树上，要么都位于右子树上，则lefTreeNode,righTreeNode其中有一个为空
        return lefTreeNode == null ? righTreeNode : lefTreeNode;
        
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
