package day21;

/*
 * Given an array of integers that is already sorted in ascending order, find two numbers such 
 * that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element 
twice.

Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * */

//思路：给的是已经排好序的数组，一般先考虑二分法查找
public class TwoSumII_Inputarrayissorted_167 {
public int[] twoSum(int[] numbers, int target) {
//        for (int i = 0; i < numbers.length - 1; i++) {
//			int l = i+1;
//			int r = numbers.length - 1;
//			while(l <= r) {
//				int mid = l + (r - l) / 2;
//				if(numbers[i] + numbers[mid] == target)return new int[] {i+1, mid+1};
//				else if(numbers[i] + numbers[mid] > target)r = mid - 1;
//				else l = mid + 1;
//			}
//		}
//        return null;
	
	 //解法2
    int left = 0;
    int right = numbers.length - 1;
    
    while(left < right){
        
        if(numbers[left] + numbers[right] == target) return new int[]{left+1,right+1};
        else if(numbers[left] + numbers[right] > target) right -- ;
        else left++;
        
    }
    
    return new int[2];
    }
}
