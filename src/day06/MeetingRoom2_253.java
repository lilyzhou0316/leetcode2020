package day06;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Given an array of meeting time intervals consisting of start and end times 
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:
Input: [[7,10],[2,4]]
Output: 1

NOTE: input types have been changed on April 15, 2019.
 Please reset to default code definition to get new method signature.
 * */

//Solution: Sort the array based on start-time of the interval. Then, use the min-heap 
//based on min end time. For every interval remove the top element of the priority queue 
//if the end time of the top <= start time of the new interval. Add the new interval to 
//the queue. The max size of the priority queue attained during this process will be 
//the answer.

//中心思想：找某一个时间段里，出现交集区间的最大个数即为最少需要的房间

public class MeetingRoom2_253 {
	public int minMeetingRooms(int[][] intervals) {
		//先让原数组按起始时间从小到大排列
		Arrays.sort(intervals, (n1,n2) -> n1[0] - n2[0]);
		//然后把已经排序的数组元素一个个加入到队列中,按照结束时间从小到大
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((n1,n2) -> n1[1] - n2[1]);
		int max = 0;//记录出现的最大交集区间的个数
		for (int[] interval : intervals) {
			while(!queue.isEmpty() && interval[0] > queue.peek()[1]) {
				//如果当前区间和队列里的区间没有交集，则取出队列里的区间,直到出现交集，或者队列为空为止
				queue.poll();
			}
			queue.add(interval);
			max = Math.max(max, queue.size());
		}
		return max;
	}
}
