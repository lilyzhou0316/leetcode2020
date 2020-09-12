package day28;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import day12.ValidAnagram_242;
import day28.ClosestBinarySearchTreeValue_270.TreeNode;

/*
 * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
   
   
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
  

Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 * */

//思路1:中序取出所有节点到一个list，然后把所有节点值放另一个list，给第二个list排序，然后把值赋给第一个list里的节点

//follow up:这道题的真正符合要求的解法应该用的 Morris 遍历（即threaded inorder,让没有左右子节点的节点
//其右子节点指向按排序大小的它的后一位），这是一种非递归且不使用栈，空间复杂度为 O(1) 的遍历方法，
//在其基础上做些修改，加入 first, second 和 parent 指针，
//来比较当前节点值和中序遍历的前一节点值的大小
public class RecoverBinarySearchTree_99 {
public void recoverTree(TreeNode root) {
	if(root == null)return;
        //解法1
//	List<TreeNode> list1 = new ArrayList<>();
//	List<Integer> list2 = new ArrayList<Integer>();
//	inorder(root, list1, list2);
//	
//	Collections.sort(list2);
//	
//	for (int i = 0; i < list1.size(); i++) {
//		list1.get(i).val = list2.get(i);
//	}
	
	//解法2
	TreeNode temp = null;//temp永远指向正好比当前节点的小一点的节点（即按大小顺序排序时在当前节点值左边1位的节点）
	TreeNode first = null, second = null;//记录两个位置错误的节点
	TreeNode pre = null;//指向当前节点的前一个节点位置（如果正常则永远比当前节点小）
	while(root!=null){
		if(root.left!=null){//如果还有比当前节点值更小的值，则通过循环让root指向当前的最小值，并建立连接
			
			temp = root.left;
			while(temp.right != null && temp.right != root)//找到按大小顺序排序时在当前节点值左边1位的节点
				temp = temp.right;
			
			if(temp.right != null){//进入这里说明temp的右子节点指向了当前节点
				// the threading already exists

				//System.out.println(root.val);//输出当前最小值
				if(pre != null && pre.val > root.val){
					//如果前一个节点值比当前节点还大，则发生位置错误，记录该节点
			    	if(first == null){
			    		first = pre;
			    		second = root;
			    		}else{
			    			second = root;
			    			}
			     }
			    pre = root;
			
			    temp.right = null;//断开threading
				root = root.right;//右移，找到下一个当前节点，不为空则又进入循环
			}else{//进入这里说明temp和当前节点之间还没有建立联系
				
				temp.right = root;// construct the threading
				root = root.left;//让当前节点指向更小的数
			}
		}else{//如果当前节点的值为当前最小值
			
			//System.out.println(root.val);//则直接输出
			
			if(pre != null && pre.val > root.val){
		    	if(first == null){first = pre;second = root;}
		    	else{second = root;}
		    }
			pre = root;
			root = root.right;//并让当前节点右移
		}
	}
	
    }

public void inorder(TreeNode t, List<TreeNode> list1,List<Integer> list2) {
	if(t.left != null)inorder(t.left, list1,list2);
	list1.add(t);
	list2.add(t.val);
	if(t.right != null)inorder(t.right, list1,list2);
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
