package day30;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import javafx.util.Pair;

/*
 * Given a binary tree, return the  vertical order  traversal of its nodes' values. 
 * (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 * */

//思路：看题目中给的第一个例子，3和 15 属于同一列，3在前，第二个例子中，3,5,2 在同一列，3在前，5和2紧随其后，
//那么隐约的可以感觉到好像是一种层序遍历的前后顺序，如何来确定列的顺序呢，这里可以把根节点给个序号0，然后开始层序遍历，
//拿出当前节点，凡是它的左子节点则序号减1，右子节点序号加1，这样可以通过序号来把相同列的节点值放到一起，用一个 TreeMap 
//来建立序号和其对应的节点值的映射，用 TreeMap 的另一个好处是其自动排序功能可以让列从左到右，由于层序遍历需要用到 
//queue，此时 queue 里不能只存节点，而是要存序号和节点组成的 pair 对儿，这样每次取出就可以操作序号，
//而且排入队中的节点也赋上其正确的序号
//时间复杂度O(N) ，空间复杂度O(N)
public class BinaryTreeVerticalOrderTraversal_314 {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null)return res;
		//解法1
//		Map<Integer, List<Integer>> map = new TreeMap<>();//用map保存有相同序号的节点,treemap会给key自动排序
//		List<Pair<Integer, TreeNode>> queue = new ArrayList<>();//进行层序遍历,将根节点的序号设为0
//		queue.add(new Pair<Integer, TreeNode>(0, root));
//		while(!queue.isEmpty()) {
//			Pair<Integer, TreeNode> temp = queue.remove(0);
//			if(temp.getValue().left != null)
//				//如果当前节点的左子节点不为空，则加入它的左子节点，且设置序号为当前节点序号-1
//				queue.add(new Pair<Integer, TreeNode>(temp.getKey() - 1, temp.getValue().left));
//			if(temp.getValue().right != null)
//				//如果当前节点的右子节点不为空，则加入它的右子节点，且设置序号为当前节点序号+1
//				queue.add(new Pair<Integer, TreeNode>(temp.getKey() + 1, temp.getValue().right));
//			
//			//把当前节点加入map
//			if(map.containsKey(temp.getKey())) {
//				//如果当前节点的序号已经出现过，则直接加入已经存在的list
//				List<Integer> tempList = map.get(temp.getKey());
//				tempList.add(temp.getValue().val);
//			}else {
//				//否则新建list
//				List<Integer> newlist = new ArrayList<Integer>();
//				newlist.add(temp.getValue().val);
//				map.put(temp.getKey(),newlist);
//			}
//		}
//		//最后给序号排序按从小到大的顺序取出对应的list放入结果集
//	
//		for (List<Integer> list : map.values()) {
//			res.add(list);
//		}
		
		//解法2:对1优化，添加过程中记录节点序号出现的最大和最小值，则最终就按照从最小值到最大值从map中取list
		 Map<Integer, ArrayList> columnTable = new HashMap();
		    // Pair of node and its column offset
		    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
		    int column = 0;
		    queue.offer(new Pair(root, column));

		    int minColumn = 0, maxColumn = 0;

		    while (!queue.isEmpty()) {
		      Pair<TreeNode, Integer> p = queue.poll();
		      root = p.getKey();
		      column = p.getValue();

		      if (root != null) {
		        if (!columnTable.containsKey(column)) {
		          columnTable.put(column, new ArrayList<Integer>());
		        }
		        columnTable.get(column).add(root.val);
		        minColumn = Math.min(minColumn, column);
		        maxColumn = Math.max(maxColumn, column);

		        queue.offer(new Pair(root.left, column - 1));
		        queue.offer(new Pair(root.right, column + 1));
		      }
		    }

		    for(int i = minColumn; i < maxColumn + 1; ++i) {
		      res.add(columnTable.get(i));
		    }

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
