package day06;

/*
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be
 O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 * */

//思路：因为时间要求是 O(log (m+n))，所以用二分法查找，根据中位数的定义，在统计中，中位数被用来：
//将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。所以如果将n1,n2两个数组
//任意划分成左右两部分，只要保证leftA+leftB的元素个数 = rightA+rightB（n1+n2为偶）,
//或者leftA+leftB的元素个数 = rightA+rightB+1（n1+n2为奇数），
//且max(leftA+leftB) <= min(rightA+rightB)
//则新数组的中位数就很好找了，如果新数组长度为奇数，则中位数 = max（leftA+leftB）
//如果新数组长度为偶数，则中位数 = (max（leftA+leftB）+ min(rightA+rightB))/2.0
//要确保这两个条件，只需要保证：
//i + j = n1 + n2 - i- j（当 n1+n2为偶数）或 i + j = n1 + n2 - i - j + 1（当n1+n2为奇数）。
//等号左侧为leftA+leftB的元素个数，等号右侧为 rightA+rightB的元素个数。
//将 i 和 j 全部移到等号左侧，我们就可以得到 i+j = (n1 + n2 +1)/2。这里的分数结果只保留整数部分(所以奇偶结果一样)。



public class medianOfTwoSortedArrays_4 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		int n1 = nums1.length, n2 = nums2.length;
		//始终让元素较少的那个数组作为第一个参数传入
	   if(n1 > n2)return findMedianSortedArrays( nums2,  nums1);
	   
	   int left = 0, right  = n1;//以短数组作为依据（这样可以根据i计算j）遍历，找到符合条件的i,j
	   while (left <= right) {
		  int i = left + (right - left)/2;//i表示数组n1在左侧part的元素个数，j表示数组n2在左侧part的元素个数
		  int j = (n1 + n2 +1)/2 - i;//让i+j = (n1 + n2 +1)/2始终成立,条件1
		
		//找出数组1，2的左侧最大值和右侧最小值
		//如果数组n1在左侧part的元素个数为0，则不存在数组1的左侧最大值，否则数组1左侧最大值为nums1[i-1]（有序的）
		  int maxLeftA = i == 0? Integer.MIN_VALUE : nums1[i-1];
		//同理
		  int maxLeftB = j == 0? Integer.MIN_VALUE: nums2[j-1];
		//如果数组n1在左侧part的元素个数为n1,即在右侧的元素个数为0个，则不存在数组1的右侧最小值
		  int minRightA = i == n1? Integer.MAX_VALUE : nums1[i];
		//同理
		  int minRightB = j == n2? Integer.MAX_VALUE : nums2[j];
		  
		  
		//比较四个元素的大小关系，如果满足条件2:max(leftA+leftB) <= min(rightA+rightB)，说明i,j找对了
	        if (maxLeftA <= minRightB && maxLeftB <= minRightA) {//上面条件2的变形
	        	//此时再考虑总长度的奇偶
	          // total length is even
	          if ((n1 + n2) % 2 == 0) {
	            return (double) (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
	          } else {
	            // total length is odd
	            return (double) Math.max(maxLeftA, maxLeftB);
	          }
	        } else if (maxLeftA > minRightB) {
	            //如果短数组左边最大值比数组2右边最小值还大，说明找的i点的值太大了，需要找个小点的值
	          right = i - 1;
	        } else {//当i满足条件后看j
	            //maxLeftB > minRightA的情况，说明短数组的分界值i点太小了，需要大一点的值
	          left = i + 1;
	        }
	      }
	      return 0.0;
	}

}
