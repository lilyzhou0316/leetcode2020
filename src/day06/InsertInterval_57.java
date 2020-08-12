package day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a set of non-overlapping intervals, insert a new interval into the intervals 
 * (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

NOTE: input types have been changed on April 15, 2019. Please reset to default 
code definition to get new method signature.
 * */

//思路1:和56题一样的做法

//思路2:解法1优化
//将 newInterval 之前开始的区间添加到输出。
//添加 newInterval 到输出，若 newInterval 与输出中的最后一个区间重合则合并他们。
//一个个添加区间到输出，若有重叠部分则合并他们。
//

public class InsertInterval_57 {
	 public int[][] insert(int[][] intervals, int[] newInterval) {
		 if(intervals.length == 0 || intervals == null) {
	          int[][] res = new int[1][];
	          res[0] = newInterval;
				 return res;
			 }
		 
	        List<int[]> merged = new ArrayList<int[]>();//保存最终结果
	      //解法1
//	        List<int[]> temp = new ArrayList<int[]>();
//	        //将intervals所有元素加入
//	        for (int i = 0; i < intervals.length; i++) {
//					temp.add(intervals[i]);
//			}
//        //将newInterval加入
//	        	temp.add(newInterval);
//	        Collections.sort(temp, (n1,n2) -> n1[0] - n2[0]);
//	        
//	        merged.add(temp.get(0));//将第一个区间加入结果集
//	        //遍历temp
//	        for (int i = 1; i < temp.size(); i++) {
//	        	//如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合,直接加入
//				if(temp.get(i)[0] > merged.get(merged.size() - 1)[1]) {
//					merged.add(temp.get(i));
//				}else {
//					//否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
//					merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], temp.get(i)[1]);
//				}
//			}
	        
	        //解法2
	        int i = 0;
	        for (; i < intervals.length; i++) {
					if (intervals[i][0] < newInterval[0]) {
						//将 newInterval 之前开始的区间添加到输出。
						merged.add(intervals[i]);
					}else {
						break;
					}
			}
	        
	      //添加 newInterval 到输出，若 newInterval 与输出中的最后一个区间重合则合并他们。否则直接加入
	        if (merged.isEmpty() || merged.get(merged.size()-1)[1] < newInterval[0] ) {
	        	merged.add(newInterval);
			}else {//需要合并
				merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], newInterval[1]);
			}
	        
	        //将原数组中剩下的区间一个个加入，如有需要合并的则合并
	        for (; i < intervals.length; i++) {
	        	if (merged.get(merged.size()-1)[1] < intervals[i][0]) {//不需要合并
		        	merged.add(intervals[i]);
				}else {//需要合并
					merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], intervals[i][1]);
				}
		}
	        
	        return merged.toArray(new int[0][]);
	       
	    }
}
