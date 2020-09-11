package day27;

import java.util.Arrays;

import day27.LowestCommonAncestorofaBinaryTree_236.TreeNode;

/*
 * Given an array where elements are sorted in ascending order, convert it to a 
 * height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in
 which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],中序遍历

One possible answer is: [0,-3,9,-10,null,5], which represents the following height
 balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 
 
  Definition for a binary tree node.
  public class TreeNode {
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
 
 * */

//思路：如果将二叉搜索树按中序遍历的话，得到的就是一个有序数组了。那么反过来，我们可以得知，根节点应该是
//有序数组的中间点，从中间点分开为左右两个有序数组，在分别找出其中间点作为原中间点的左右两个子节点，
//这不就是是二分查找法的核心思想么。所以这道题考的就是二分查找法
public class ConvertSortedArraytoBinarySearchTree_108 {
public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)return null;
        int mid = nums.length / 2;
        TreeNode curNode = new TreeNode(nums[mid]);//当前节点即为当前数组的中间数
        
        int[] left = Arrays.copyOfRange(nums, 0, mid);//中间数左边的子数组
        int[] right = Arrays.copyOfRange(nums, mid + 1, nums.length);//中间数右边的子数组
        
        curNode.left = sortedArrayToBST(left);//当前节点的左子树就由左边的子数组构成
        curNode.right = sortedArrayToBST(right);
        return curNode;
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
