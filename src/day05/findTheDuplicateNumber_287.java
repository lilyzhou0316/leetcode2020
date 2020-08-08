package day05;

/*
 * Given an array nums containing n + 1 integers where each integer is between 1 and
 *  n (inclusive), prove that at least one duplicate number must exist. Assume that there
 *   is only one duplicate number, find the duplicate one.
 *   
 *   Example 1:
 *   
 *   Input: [1,3,4,2,2]
     Output: 2
     
     Example 2:
     
     Input: [3,1,3,4,2]
     Output: 3
     
     Note:

1.You must not modify the array (assume the array is read only).
2.You must use only constant, O(1) extra space.
3.Your runtime complexity should be less than O(n^2).
4.There is only one duplicate number in the array, but it could be repeated more
 than once.1.
 * */

//思路1:暴力解法，两层遍历

//思路2:题目要求不改原数组，同时又不能复制原数组（use only constant, O(1) extra space），
//第一想法，双指针，但实际是利用的快慢指针，解析见下链接
//https://leetcode-cn.com/problems/find-the-duplicate-number/solution/qian-duan-ling-hun-hua-shi-tu-jie-kuai-man-zhi-z-3/

//思路3:二分法查找，以 [2, 4, 5, 2, 3, 1, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，
//根据题目意思，每个数都在 1 和 7 之间。
//例如：区间 [1, 7]的中位数是 4，遍历整个数组，如果整个数组里小于等于 4 的整数的个数严格大于 4 的时候，
//就可以说明重复的数存在于区间 [1, 4]里。

//
public class findTheDuplicateNumber_287 {
	 public int findDuplicate(int[] nums) {
	        //解法3:二分法
//		 int n = nums.length;
//		 int left = 1;//数组的最小值
//		 int right = n-1;//数组可能取到的最大值
//		 
//		 while (left < right) {//只有当left = right时，说明找到了重复数
//			int mid = (left+right)/2;
//			
//			//遍历数组，找出小于等于mid的数的个数
//			int count = 0;
//			for (int num : nums) {
//				if (num <= mid) {
//					count++;
//				}
//			}
//			
//			if (count > mid) {//重复的数存在于区间 [1, mid]里。
//				right = mid;
//			}else {//重复的数存在于区间 [mid+1, right]里。
//				left = mid+1;
//			}	
//		}
//		 //出循环时，left = right
//		 return left;
		 
		 //解法2:快慢指针
		 int fast = 0, slow = 0;
	        while(true){
	            fast = nums[nums[fast]];
	            slow = nums[slow];
	            if(fast == slow)//一个快指针，一个慢指针，进入到环里一定会相遇
	                break;
	        }
	        int finder = 0;//第三个指针，为慢指针
	        while(true){//让第三个指针和前面的慢指针，一个从头开始，一个从快慢指针相遇的地方开始，走走走
	        	//它们相遇时即为环的入口处（即重复数字处），证明见链接
	            finder = nums[finder];
	            slow = nums[slow];
	            if(slow == finder)
	                break;        
	        }
	        return slow;
	    }
}
