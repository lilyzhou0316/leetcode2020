package day07;

import java.util.TreeMap;

/*
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., 
 * summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., 
then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]

Follow up:

What if there are lots of merges and the number of disjoint intervals are small
 compared to the data stream's size?
 * */

//思路：此题是715题的简化版，715题加入的是区间，本题加入的是一个个点。在查找加入位置时可以通过二分查找或者
//TreeMap来实现，在进行区间合并时如果使用数组列表的话时间复杂度为O(n), 而用链表的话时间复杂度为O(1)。
//因此使用TreeMap达到O(lgn)的搜索速度，使用链表达到O(1)的区间合并速度，TreeMap的key采用区间的右端点。


public class DataStreamasDisjointIntervals_352 {
	  /** Initialize your data structure here. */
	private class Node {

	    int left, right;
	    Node next;

	    public Node(int left, int right, Node next) {
	      this.left = left;
	      this.right = right;
	      this.next = next;
	    }
	  }

	  private TreeMap<Integer, Node> treeMap;

	  private Node dummy;

	  private int count;

	  /**
	   * Initialize your data structure here.
	   */
	  public DataStreamasDisjointIntervals_352() {
	    treeMap = new TreeMap<>();
	    dummy = new Node(-2, -2, null);
	    treeMap.put(-2, dummy);
	    count = 0;
	  }

	  public void addNum(int val) {
	    Node pre = treeMap.lowerEntry(val).getValue();
	    Node cur = pre.next;
	    if (cur != null && cur.left <= val) {
	      return;
	    }
	    boolean isNull = cur == null;
	    if (isNull) {
	      cur = new Node(val + 2, val + 2, null);
	    }
	    if (pre.right + 1 == val) {
	      if (val + 1 == cur.left) {
	        treeMap.remove(pre.right);
	        treeMap.remove(cur.right);
	        pre.right = cur.right;
	        pre.next = cur.next;
	        treeMap.put(cur.right, pre);
	        count--;
	      } else {
	        treeMap.remove(pre.right);
	        pre.right++;
	        treeMap.put(pre.right, pre);
	      }
	    } else if (val + 1 == cur.left) {
	      cur.left--;
	    } else {
	      Node node = new Node(val, val, isNull ? null: cur);
	      treeMap.put(val, node);
	      count++;
	      pre.next = node;
	    }
	  }

	  public int[][] getIntervals() {
	    int[][] res = new int[count][2];
	    Node node = dummy.next;
	    int id = 0;
	    while (node != null) {
	      res[id++] = new int[]{node.left, node.right};
	      node = node.next;
	    }
	    return res;
	  }
	}

	/**
	 * Your SummaryRanges object will be instantiated and called as such: SummaryRanges obj = new
	 * SummaryRanges(); obj.addNum(val); int[][] param_2 = obj.getIntervals();
	 */

	
