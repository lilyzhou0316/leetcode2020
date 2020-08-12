package day07;

import java.util.ArrayDeque;
import java.util.TreeMap;

/*
 * Given an array nums, there is a sliding window of size k which is moving from the very 
 * left of the array to the very right. You can only see the k numbers in the window.
 *  Each time the sliding window moves right by one position. Return the max sliding window.
 *  找每次sliding window里的最大值

Follow up:
Could you solve it in linear time?o(n)

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 
 Constraints:
 
 1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
 * */

//我的思路：用list放k个元素，一旦超过范围就从最前面删除1个，每次新增一个的时候找出当前
//sliding window里的最大值（可以先用二分法给当前list排序）

//优化思路：我们希望窗口内的数字是有序的，但是每次给新窗口排序又太费时了，所以最好能有
//一种类似二叉搜索树的结构（treemap），可以在 lgn 的时间复杂度内完成插入和删除操作

//思路1:暴力法，最简单直接的方法是遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1 个滑动窗口，
//每个有 k 个元素，于是算法的时间复杂度为 O(N k)，表现较差。



//思路2:可以使用优先队列来做，即最大堆，最大数字放队首，不过此时我们里面放一个 pair 对儿，
//由数字和其所在位置组成的，这样我们就可以知道每个数字的位置了，而不用再进行搜索了。
//在遍历每个数字时，进行 while 循环，假如最大的数字此时不在窗口（队列）中了，就要移除队首元素，
//判断方法就是将队首元素的 pair 对儿中的second值（位置坐标）跟 i-k 对比，小于等于就移除。
//然后将当前数字和其位置组成 pair 对儿加入优先队列中。
//此时看若 i >= k-1，说明窗口大小正好是k，就将最大值加入结果 res 中即可

//思路3:题目中的 Follow up 要求我们代码的时间复杂度为 O(n)。提示我们要用双向队列 deque 来解题，
//双向队列，该数据结构可以从两端以常数时间压入/弹出元素。存储双向队列的索引比存储元素更方便，
//因为两者都能在数组解析中使用。
//算法：1.处理前 k 个元素，初始化双向队列。
//2.遍历整个数组。在每一步 :
               //清理双向队列 :只保留当前滑动窗口中有的元素的索引。移除比当前元素小的所有元素，它们不可能是最大的。
//3.将当前元素添加到双向队列中。
//4.将 deque[0] 添加到输出中。
//5.返回输出数组。
//时间复杂度：O(N)，每个元素被处理两次- 其索引被添加到双向队列中和被双向队列删除。
//空间复杂度：O(N)，输出数组使用了O(N−k+1) 空间，双向队列使用了 O(k)

public class SlidingWindowMaximum_239 {
//	 public int[] maxSlidingWindow(int[] nums, int k) {
//	        //解法1
//		 int n = nums.length;
//		 if (n == 0 || nums == null) {
//			return new int[0];
//		}
//		 
//		 
//		 int[] res = new int[n - k +1];//保存结果集
//		 TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();//用treemap来当窗口
//		 
//		 for (int i = 0; i < n; i++) {
//			// for i < k, just put the entries to the tree because there are not enough entries
//			tree.put(nums[i], i);
//			if (i >= k -1) {//i= k - 1时，达到窗口目标元素个数，
//				//先找最大值(即treemap里的lastkey--highest key)加入到结果集中
//				res[i - k +1] = tree.lastKey();
//				//然后删除最早加入treemap中的元素，为下一个元素腾位置
//				//但此时要考虑到treemap中是否有重复元素
//				//因为当前元素索引为i，所以我们需要删除的元素的索引为i-k+1
//				//此时我们需要验证通过nums[i-k+1]作为key在treemap中找到的对应的value值（即索引值）
//				//是否等于i-k+1，如果相等说明没有重复元素，如果不等，说明后来的重复元素覆盖了之前的值
//				//此时我们就不需要删除了（因为已经被覆盖）
//				if (tree.get(nums[i-k+1]) == i-k+1) {
//					tree.remove(nums[i-k+1]);
//				}
//				
//			}
//			
//			
//		}
//		 return res;
//	    }
	
	
	//解法2:双向队列
	ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
	  int [] nums;

	  public void clean_deque(int i, int k) {
	    // remove indexes of elements not from sliding window
		  //即超出范围时移除最早加入的元素
	    if (!deq.isEmpty() && deq.getFirst() == i - k)
	      deq.removeFirst();

	    // remove from deq indexes of all elements 
	    // which are smaller than current element nums[i]
	    //移除那些比当前元素值要小的元素，因为它们肯定不是我们要找的值
	    while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])   
	    	deq.removeLast();
	  }

	  public int[] maxSlidingWindow(int[] nums, int k) {
	    int n = nums.length;
	    if (n * k == 0) return new int[0];
	    if (k == 1) return nums;

	    // init deque and output
	    this.nums = nums;
	    for (int i = 0; i < k; i++) {//将前k个元素加入队列
	      clean_deque(i, k);
	      deq.addLast(i);
	    }
	    int [] output = new int[n - k + 1];
	    output[0] = nums[deq.getFirst()];//将第一个最大值加入到结果集

	    // build output
	    for (int i  = k; i < n; i++) {//将剩下的元素加入队列
	      clean_deque(i, k);
	      deq.addLast(i);
	      output[i - k + 1] = nums[deq.getFirst()];
	    }
	    return output;
	  }

}
