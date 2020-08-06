package day01;

/*
 Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, 
there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:

1 <= nums.length <= 2 * 10^4
It's guaranteed that nums[i] fits in a 32 bit-signed integer.
k >= 0
 * */


//思路1:有种类似翻转字符的方法，思路是先把前 n-k 个数字翻转一下，再把后k个数字翻转一下，最后再把整个数组翻转一下

//思路2:通过不停的交换某两个数字的位置来实现旋转（ swap(nums[i + start], nums[n - k + i + start]);）

public class rotateArray_189 {
	public void rotateArray(int[] nums, int k) {
		//解法1:
//		if(nums == null || nums.length < 2){
//	        return;
//	    }
//	    
//	    if(k > nums.length){
//	        k %= nums.length;
//	    }
//	    
//	    int left = 0;
//	    int mid = nums.length - 1 - k;
//	    int right = nums.length - 1;
//	    reverse(nums, left, mid);//先翻转
//	    reverse(nums, mid + 1, right);//再翻转剩下的
//	    reverse(nums, left, right);//最后翻转整个数组
//	    
//	}
//
//
//	public void reverse(int[] arr, int L, int R){
//	    
//	    while(L <= R){
//	        swap(arr, L++, R--);            
//	    }
//	}
//
//	public void swap(int[] arr, int i, int j){
//	    int tmp = arr[i];
//	    arr[i] = arr[j];
//	    arr[j] = tmp;
//	}
		//解法2:
		
	}	
}


