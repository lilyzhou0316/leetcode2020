package day28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



/*
 * Serialization is the process of converting a data structure or object into a sequence of bits 
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection 
 * link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on h
ow your serialization/deserialization algorithm should work. You just need to ensure that a 
binary tree can be serialized to a string and this string can be deserialized to the original 
tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Clarification: The above format is the same as how LeetCode serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with
 different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and 
deserialize algorithms should be stateless.
 * */

//我的思路：题目要求将二叉树转str， 再从str还原成二叉树，前者可以用层级遍历，注意处理null节点，后者用队列处理
public class SerializeandDeserializeBinaryTree_297 {
	public class Codec {

	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	    	//层级遍历保存各节点值到str
	        StringBuilder res = new StringBuilder();
	        if(root == null)return res.toString();
	        Queue<TreeNode> q = new LinkedList<TreeNode>();//这里必须用链表，因为链表才能插入null节点
	        q.add(root);
	        
	        while(!q.isEmpty()) {
	        	TreeNode temp = q.poll();//取出队列中头节点
	        	if(temp != null) {
	        		res.append(temp.val + ",");
	        		q.add(temp.left);
	        		q.add(temp.right);
	        	}else {
	        		res.append("null" + ",");
	        	} 	
	    }
	        return res.toString();
	    }
	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	    	if(data == null || data.length() == 0)return null;
	       //将给的str去掉最后一个逗号后以,分割成数组
	    	String[] cur = data.substring(0,data.length() - 1).split(",");
	    	 Queue<TreeNode> q = new LinkedList<TreeNode>();//这里必须用链表，因为链表才能插入null节点
	    	TreeNode root = new TreeNode(Integer.parseInt(cur[0]));//以数组第一个数字作为根节点
	    	q.add(root);//保存所有根节点
	    	
	    	int next = 1;//指针，指向当前节点左右子节点
	    	while(!q.isEmpty()) {
	    		TreeNode curRoot = q.poll();//取出当前根节点
	    		if(!"null".equals(cur[next])) {
		    		//如果当前节点的左子节点不为空
		    		curRoot.left = new TreeNode(Integer.parseInt(cur[next]));
		    		q.add(curRoot.left);
		    		
		    }
		    	//为空则跳过当前节点左子节点
		    	next++;//指向下一个节点（右节点）
		    	if(!"null".equals(cur[next])) {
		    		//如果当前节点的右子节点不为空
		    		curRoot.right = new TreeNode(Integer.parseInt(cur[next]));
		    		q.add(curRoot.right);
		    		
		    }
		    	//为空则跳过右子节点
		    	//指向下一个根节点
		    	next++;
	    	}
	    	return root;
	}

	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.deserialize(codec.serialize(root));
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
