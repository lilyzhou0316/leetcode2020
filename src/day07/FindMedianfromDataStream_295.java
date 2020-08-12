package day07;

import java.util.PriorityQueue;

/*
 * Median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

Follow up:
If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, 
how would you optimize it?
 * */

//思路1:优先队列。一种最容易想到的思路是，数据流新进来一个数，都把它与已经读出来的数进行一次排序，这样中位数就
//可以很容易得到。这样做“不好”的地方是：排序的时间复杂度为 O(NlogN)，但事实上，我们对除了中位数以外
//的其它位置的元素并不关心。我们只关心在中间的那两个数（或者一个数），其它数没有必要进行 “比较” 和 “交换” 
//的操作。我们可以利用堆的性质，每次将数字分成两部分，如果总元素个数是奇数，则左边堆（元素个数比右边堆多1个）
//的最大值即为所求，如果总元素个数是偶数，则左右堆元素个数相等，找到左堆的最大值和右堆的最小值，它们相加除以2即为所求
//而其它元素无需排序，这样就可以以O(logN) 的复杂度每次都从堆中取出最值。


//时间复杂度：O(logN)，优先队列的出队入队操作都是对数级别的，数据在两个堆中间来回操作是常数级别的，
//综上时间复杂度是 O(logN) 级别的。
//空间复杂度：O(N)，使用了三个辅助空间，其中两个堆的空间复杂度是O(N/2)，
//一个表示数据流元素个数的计数器 total，占用空间 O(1)，综上空间复杂度为 O(N)。




public class FindMedianfromDataStream_295 {
	int total ;//记录两个堆一共有多少个元素
	PriorityQueue<Integer> maxHeap ;//左边堆，取它最大值
	PriorityQueue<Integer> minHeap ;//右边堆，取它最小值
    /** initialize your data structure here. */
    public FindMedianfromDataStream_295() {
    	total = 0;
        maxHeap =  new PriorityQueue<Integer>((x,y) -> y-x);//找最大值
        minHeap = new PriorityQueue<Integer>();//找最小值
    }
    
    public void addNum(int num) {
        total++;//每加进来一个数，总数加1
        maxHeap.add(num);//左边堆每加入一个新来的数，则把最大值给右边堆，
        //以保持两个堆的元素个数始终相等，同时又保证了左边堆的所有元素始终小于右边堆
        minHeap.add(maxHeap.poll());
        //如果当前总个数是奇数个，则左边堆要比右边堆多一个元素，即把右边堆的最小值给左边堆
        if (total % 2 == 1) {
			maxHeap.add(minHeap.poll());
		}
    }
    
    public double findMedian() {
    	//如果总个数是奇数，找左边堆的最大值
        if (total % 2 == 1) {
			return (double)maxHeap.peek();
		}else {
			return (double)(maxHeap.peek()+minHeap.peek())/2;
		}
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
