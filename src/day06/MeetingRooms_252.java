package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import day01.removeDuplicatesfromSortedArray_26;

/*
 * Given an array of meeting time intervals consisting of start and end times
 *  [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *  
 *  

Example 1:
Input: [[0,30],[5,10],[15,20]]
Output: false

Example 2:
Input: [[7,10],[2,4]]
Output: true

NOTE: input types have been changed on April 15, 2019.
 Please reset to default code definition to get new method signature
 * */

//思路1:和56/57很像，找有没有可以合并的区间，如果有则返回false,否则返回true

public class MeetingRooms_252 {
    public boolean canAttendMeetings(int[][] intervals) {
	List<int[]> mergedList = new ArrayList<int[]>();
	boolean flag = true;//记录结果，如果发生合并则为false
	//先给原数组排序，按照start从大到小
	Arrays.sort(intervals, (n1,n2) -> n1[0] - n2[0]);
	//把第一个区间加入merged
	mergedList.add(intervals[0]);
	//遍历原数组，看是否会发生合并
	for (int i = 1; i < intervals.length; i++) {
		if (intervals[i][0] > mergedList.get(mergedList.size()-1)[1]) {
			mergedList.add(intervals[i]);
		}else {
			flag = false;
		}
	}
	
	return flag;
}
}
