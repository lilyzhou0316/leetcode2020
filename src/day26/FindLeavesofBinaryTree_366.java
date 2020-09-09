package day26;

import java.util.List;

/*
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove
 *  all leaves, repeat until the tree is empty.

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2 
        
2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         
 * */
public class FindLeavesofBinaryTree_366 {
	public List<List<Integer>> findLeaves(TreeNode root) {}
}
