package day27;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import day27.ConvertSortedListtoBinarySearchTree_109.TreeNode;

/*
 * Implement an iterator over a binary search tree (BST). Your iterator will be 
 * initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:
图见截图173

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where
 h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at 
least a next smallest number in the BST when next() is called.
 * */

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

//我的思路：要求next 和 hasnext方法的时间复杂度都是O(1),则把给的bst里的所有节点按中序遍历
//放到一个list里，然后用next方法每次删除并返回list第一个元素，hasnext方法判断list是否为空

//思路2：也可以用非递归的中序遍历方法做，让空间复杂度达到O(h)
public class BinarySearchTreeIterator_173 {
	class BSTIterator {
//		List<Integer> list = new ArrayList<Integer>();
//		
//	    public BSTIterator(TreeNode root) {
//	    	if(root != null)
//	    	inorder(root, list);
//	    }
//	    
//	    /** @return the next smallest number */
//	    public int next() {
//	    	
//	        return list.remove(0);
//	    	
//	    }
//	    
//	    /** @return whether we have a next smallest number */
//	    public boolean hasNext() {
//	        return !list.isEmpty();
//	    }
//	    
//	    public void inorder(TreeNode t, List<Integer> res) {
//			if(t.left != null)inorder(t.left,res);
//			res.add(t.val);
//			if(t.right != null)inorder(t.right, res);
//		}
		
		//解法2
		Stack<TreeNode> stack = new Stack<>();
		
		public BSTIterator(TreeNode root) {
			while(root != null) {
				stack.add(root);
				root = root.left;//先把所有左子节点加入（个数即为高度）
			}
		}
		
		 /** @return the next smallest number */
	    public int next() {
	    	TreeNode tempNode = stack.pop();//取出最左子节点
	    	int res = tempNode.val;
	    	if(tempNode.right != null) {
	    		//如果当前节点的右子节点存在（左子节点已经取过了）
	    		//则往右移，把当前节点当成根节点重复加入所有左节点
	    		tempNode = tempNode.right;
	    		while(tempNode != null) {
	    			stack.add(tempNode);//并加入右子节点
	    			//如果此时右子节点还有左子节点则加入左子节点
	    			tempNode = tempNode.left;
	    		}
	    	}
	    	return res;
	    }
	    
		
	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        return !stack.isEmpty();
	    }
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
