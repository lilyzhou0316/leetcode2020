package day29;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 * */

//思路1：根据展开后形成的链表的顺序分析出是使用先序遍历，可以用递归和迭代两种方式（迭代省略）

//思路2:寻找前驱节点（morris遍历，空间复杂度是 O(1)），前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的
//左子节点为空，则该节点不需要进行展开操作。如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，
//该节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。因此，
//问题转化成寻找当前节点的前驱节点。
//具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给
//前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，
//继续处理链表中的下一个节点，直到所有节点都处理结束。



public class FlattenBinaryTreetoLinkedList_114 {

public void flatten(TreeNode root) {
        //解法1，递归,时间复杂度：O(n),空间复杂度：O(n)
//	if(root == null)return;
//	List<TreeNode> res = new ArrayList<>();
//	res = preOrder(root, res);
//	TreeNode cur = res.get(0);
//	TreeNode head = cur;
//	for (int i = 1; i < res.size(); i++) {
//		cur.left = null;
//		cur.right = res.get(i);
//		cur = cur.right;
//	}

	
	//解法2，类似morris前序遍历,找前驱节点
	TreeNode cur = root;
	while(cur != null) {
		if(cur.left != null) {
			//如果当前节点的左子树不为空，则找到左子树上的最右子节点
			TreeNode next = cur.left;//保存当前左子节点
			TreeNode pre = next;
			while(pre.right != null) {
				pre = pre.right;
			}
			//让此时的最右节点pre的右子节点指向当前节点的右子节点
			pre.right = cur.right;
			//让当前节点的右子节点变成左子节点，左子节点为空
			cur.left = null;
			cur.right = next;
		}
		//当前节点处理完成，右移往下一个节点
		cur = cur.right;
	}
    }
public List<TreeNode> preOrder(TreeNode t, List<TreeNode> res) {
	res.add(t);
	if(t.left != null)preOrder(t.left, res);
	if(t.right != null)preOrder(t.right, res);
	return res;
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
