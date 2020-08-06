package day02;

/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i 
to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the 
clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.



Example 1:
Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.


Example 2:
Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
 * */

//思路1：根据给定的两个数组，找到第一个gas[i]比cost[i]大的index作为起点，然后开始进行计算看是否能往后走，
//如果到某个点汽油不够了，则说明从起点到这个点中间的任何一个点都不能作为起点（因为都要经过那个不够的点），
//则把起点设为下一个点（也就是这个起点的前一个点作为新起点），继续遍历。当遍历完整个环时，当前保存的起点即为所求。


//思路2:我们也可以从后往前遍历，用一个变量mx来记录出现过的剩余油量的最大值，total记录当前剩余油量的值，
//start还是记录起点的位置。当total大于mx的时候，说明当前位置可以作为起点，更新start，并且更新mx。为啥呢？
//因为我们每次total加上的都是当前位置的油量减去消耗，如果这个差值大于0的话，说明当前位置可以当作起点，
//因为从当前位置到末尾都不会出现油量不够的情况，而一旦差值小于0的话，说明当前位置如果是起点的话，油量就不够，
//无法走完全程，所以我们不更新起点位置start。最后结束后我们还是看totoa是否大于等于0，如果其小于0的话，
//说明没有任何一个起点能走完全程，因为总油量都不够
public class gasStation_134 {
	 public int canCompleteCircuit(int[] gas, int[] cost) {
		 //解法1:
//		 int start = 0;//起点
//		 int total=0;//记录走完整个一圈剩余的汽油，只有它大于0才满足,跟从哪个点开始没关系
//		 int sum = 0;//记录从起点开始往下走，到它下一个点的时候新加的汽油+剩余的汽油是否够走到下一个点
//		 for (int i = 0; i < gas.length; i++) {
//			total += gas[i] - cost[i];
//			sum += gas[i] - cost[i];
//			if (sum < 0) {//不够走到下一个点了，说明从start到i做起点都不对，看i的下一个点
//				start = i+1;
//				sum = 0;
//			}
//		}
//		 //找到起点位置，并且总耗油量要大于等于0才能完成任务
//		 return (total < 0) ? -1:start;
		 
		 //解法2:
		 int max = -1;//出现过的最大剩余油量
		 int total=0;//记录走完整个一圈剩余的汽油，只有它大于0才满足,跟从哪个点开始没关系
		 int start = 0;//记录起点位置
		 for (int i =  gas.length-1; i >= 0; i--) {
			total += gas[i] - cost[i];
			if (total > max) {
				max = total;
				start = i;
			}
		}
		 //找到起点位置，并且总耗油量要大于等于0才能完成任务
		 return (total < 0) ? -1:start;
	        
	    }
	 

}
