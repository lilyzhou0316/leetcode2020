package day03;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * Given an array of integers, find out whether there are two distinct indices i and j 
 * in the array such that the absolute difference between nums[i] and nums[j] is at most t 
 * and the absolute difference between i and j is at most k.

Example 1:
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1, t = 2
Output: true

Example 3:
Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 * */

/*
BRUTE FORCE APPROACH-----------------------------------------------------------------------------

The brute force approach is to account for the requirement: |i - j| <= k
We can do this by iterating over every index as i, then calculate the MAX value
of j such that the requirement is met.
j <= k + i hence maxJ <= k + i.

Now we know that for index i, we can use elements from i+1 up to maxJ to represent index j
Given indices for j, we can now evalutate the second requirement for |nums[i] - nums[j]| <= t
If that requirement is also true, we can return true.

In the worst case, k == nums.length. So every index i can be paired with any other index j.
So we would evalute each pair of i & j for both requirements.

Time: O(N^2) | Space: O(1)

OPTIMAL APPROACH-----------------------------------------------------------------------------

Can we improve the time and space? Well yes we can. What if we evaluate the requirements
differently? Let's handle the |nums[i] - nums[j]| <= t first. To handle this requirement
we can take some nums[i] and ask, 
    "Which values in the array are closest to nums[i]?"

This is a good question because the difference between 2 close elements will have the
smallest difference! If the min possible difference does not satisfy the requirement then
then no other values will satisfy it for the current nums[i], and we should move on
to the next nums[i]. But finding the closest element can take N time for each nums[i] evaluated
and this does not improve our time ;( so lets use some space!

Which data structure can store values, and lookup the nearest values to any nums[i] we give?
Well we would have to sort these values, so one data structure that sorts elements and has
a good lookup time is a TreeSet! The TreeSet can get the successor of a value with .ceiling(nums[i])
and the predecessor of a value with .floor(nums[i]) and a single lookup is log(N) time.

But how do we handle the first requirement where |i-j| <= k ???
This requirement sets a window of elements we can examine for our floor annd ceiling, so
we can make sure that our TreeSet holds at most K elements! Simply remove nums[i-k]
from the set when the size exceeds k.

Time: O(N*log(K)) | Space: O(K) but K can be equal to N so...
Time: O(N*log(N)) | Space: O(N)

*/
//思路1:暴力解法，两个pointer遍历，找出符合条件的元素，时间复杂度o(n^2),空间复杂度o(1)

//思路2:用treeset， 既可以控制让treeset里的元素不超过k+1个（sliding window），同时，
//TreeSet里如果元素具备自然顺序的特性，那么就按照元素自然顺序的特性进行排序存储。这样方便找到值最接近的元素，
//且寻找时间是log(N) 
public class containsDuplicateIII_220 {
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	  TreeSet<Integer> set = new TreeSet<Integer>();
		
		 for (int i = 0; i < nums.length; i++) {
			//先找满足|nums[i] - nums[j]|<= t的是否存在
			 
			 Integer tempInteger1 = set.ceiling(nums[i]);//ceiling是找treeset中第一个大于等于nums[i]的数
			 if (tempInteger1 != null && Math.abs((long)tempInteger1 - (long)nums[i]) <= t) {
				return true;
			}
			 Integer tempInteger2 = set.floor(nums[i]);//floor是找treeset中第一个小于等于nums[i]的数
			 if (tempInteger2 != null && Math.abs((long)tempInteger2 - (long)nums[i]) <= t) {
				return true;
			}
			 
			 //如果上述都不存在，将当前nums[i]加入set
			 set.add(nums[i]);
			 
			 //控制set里的元素个数不超过k个
			 if(set.size() > k){
				 set.remove(nums[i-k]);
			 }
		}
		 
		 return false; 
		 
		 //brute force solution:
//		 for (int i = 0; i < nums.length; i++) {
//	            int maxJ = Math.min(k + i, nums.length - 1);
//	            for (int j = i + 1; j <= maxJ; j++) {
//	                long diff = Math.abs((long) nums[i] - (long) nums[j]);
//	                if (diff <= t) return true;
//	            }
//	        }
//	        return false;
    }
}
