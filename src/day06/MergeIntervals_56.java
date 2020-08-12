package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. 
Please reset to default code definition to get new method signature.

Constraints:
intervals[i][0] <= intervals[i][1]

 * */

//思路:我们用数组 merged 存储最终的答案。
//首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，
//并按顺序依次考虑之后的每个区间：
//如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
//否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。


public class MergeIntervals_56 {
	 public int[][] merge(int[][] intervals) {
		 if (intervals.length == 0 || intervals == null) {
			return new int[0][];
		}
		 List<int[]> merged = new ArrayList<int[]>();//保存最终结果
		 
		// Arrays.sort可以传入比较器，这里是让区间的起点更小的排前面
		 Arrays.sort(intervals, (n1,n2) -> n1[0] - n2[0]);
		  merged.add(intervals[0]) ;//将第一个区间加入 merged 数组
		  
		  //从第二个区间开始，遍历数组
		  for (int i = 1; i < intervals.length; i++) {
			//如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合,直接加入
			  if (intervals[i][0] > merged.get(merged.size() - 1)[1]) {
				merged.add(intervals[i]);
			}else {
				//否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
				 merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1],intervals[i][1] );
			}
		}
		  return merged.toArray(new int[0][]);
		 
	 }
	 
}
