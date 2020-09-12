package day27;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import day27.BinarySearchTreeIterator_173.TreeNode;

/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest 
 * element in it.

 

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1


Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3


Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth 
smallest frequently? How would you optimize the kthSmallest routine?

 

Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * */

//我的思路：非递归的中序遍历，用一个变量记录当前遍历到第几小的节点，当节点个数等于k时，当前的节点即为所求

//思路2:再来看一种分治法的思路，由于 BST 的性质，可以快速定位出第k小的元素是在左子树还是右子树，首先计算出左子树的结点
//个数总和 cnt，如果k小于等于左子树结点总和 cnt，说明第k小的元素在左子树中，直接对左子结点调用递归即可。如果k大于
//cnt+1，说明目标值在右子树中，对右子结点调用递归函数，注意此时的k应为 k-cnt-1，应为已经减少了 cnt+1 个结点。
//如果k正好等于 cnt+1，说明当前结点即为所求，返回当前结点值即可

// Follow up 中说假设该 BST 被修改的很频繁，而且查找第k小元素的操作也很频繁，问我们如何优化。其实最好的方法还是像
//上面的解法那样利用分治法来快速定位目标所在的位置，但是每个递归都遍历左子树所有结点来计算个数的操作并不高效，
//所以应该修改原树结点的结构，使其保存包括当前结点和其左右子树所有结点的个数，这样就可以快速得到任何左子树结点总数
//来快速定位目标值了。定义了新结点结构体，然后就要生成新树，还是用递归的方法生成新树，注意生成的结点的 count 
//值要累加其左右子结点的 count 值。然后在求第k小元素的函数中，先生成新的树，然后调用递归函数。在递归函数中，
//不能直接访问左子结点的 count 值，因为左子节结点不一定存在，所以要先判断，如果左子结点存在的话，那么跟上面解法的操作相同。
//如果不存在的话，当此时k为1的时候，直接返回当前结点值，否则就对右子结点调用递归函数，k自减1
public class KthSmallestElementinaBST_230 {
public int kthSmallest(TreeNode root, int k) {
	 if(root == null)return 0;
	 int result = 0;
        //解法1        
//        int count = 0;
       
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = root;
//        
//        while(!stack.isEmpty() || cur != null) {
//        	if(cur != null) {
//        		stack.add(cur);
//        		cur = cur.left;//先把所有的左子节点加入栈
//        	}else {
//        		cur = stack.pop();//取出最左的子节点
//        		count++;//记录当前节点为第几小的节点
//        		if(count == k) {//如果加入后结果集长度正好等于k，说明当前加入的节点即为所求
//        			result = cur.val;
//        			break;
//        		}
//        		
//        		cur = cur.right;//如果当前节点有右节点，则加入栈
//        	}
//        }
//        
//        return result;
	
	//解法2
//	int left = countNodes(root.left);//计算左子树的总节点个数
//	if( k <= left) {
//		//当k小于等于左子树总节点个数时，说明要找的节点在左子树上，继续递归左子树
//		return kthSmallest(root.left, k);
//	}else if(k > left + 1) {
//		//当k大于左子树总节点个数+1时（加上了当前根节点），说明要找的节点在右子树上，继续递归右子树
//		return kthSmallest(root.right, k - left - 1);//k值需要更新，因为要把之前的左子树和根节点排除掉
//	}else if(k == left + 1) {
//		//如果k 等于 左子树总节点个数+1 则说明当前根节点即为所求
//		result =  root.val;
//	}
//	return result;
	 
	 
	 //follow up解法
	 MyTreeNode cur = buildTree(root);
	 return helper(cur, k);
    }

//解法2辅助函数
public int countNodes(TreeNode t) {
	if(t== null)return 0;
	return 1 + countNodes(t.left) + countNodes(t.right);
}

//follow up辅助函数,根据传入的treenode建一个新的树
public MyTreeNode buildTree(TreeNode t) {
	if(t== null)return null;
	MyTreeNode cur = new MyTreeNode(t.val);
	cur.left = buildTree(t.left);
	cur.right = buildTree(t.right);
	//计算当前节点的总节点数
	if(cur.left != null) cur.count += cur.left.count;
	if(cur.right != null)cur.count += cur.right.count;
	return cur;
}

//follow up辅助函数，根据传入的MyTreeNode找到第k个节点
public int helper(MyTreeNode t, int k) {
	int result = 0;
	if(t.left != null) {
		int cnt = t.left.count;//当前左子树不为空，则得到左子树的总节点个数
		if(k <= cnt) {
			//k在左子树上,继续往左子树递归
			return helper(t.left, k);
		}else if(k > cnt + 1) {
			//k在右子树上，继续往右子树递归
			return helper(t.right, k - cnt - 1);
		}else if(k == cnt + 1) {
			//当前根节点即为所求
			result = t.val;
		}
	}else {
		//当前左子树为空
		if(k == 1)result = t.val;
		return helper(t.right, k - 1);
		
	}
	return result;
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

class MyTreeNode {
    int val;
    int count;//follow up辅助变量
    MyTreeNode left;
    MyTreeNode right;
    MyTreeNode() {}
    MyTreeNode(int val) { this.val = val; }
    MyTreeNode(int val, MyTreeNode left, MyTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.count = 1;
    }  
}
}
