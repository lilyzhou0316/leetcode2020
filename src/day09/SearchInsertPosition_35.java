package day09;

/*
 * Given a sorted array and a target value, return the index if the target is found.
 *  If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2


Example 2:

Input: [1,3,5,6], 2
Output: 1  l = r = 1


Example 3:

Input: [1,3,5,6], 7
Output: 4 l = r =3


Example 4:

Input: [1,3,5,6], 0
Output: 0 l = r =0
 * */

//我的思路：二分法查找（因为数组有序无重复）

//暴力解法：遍历数组，找到等于目标数的位置即返回，如果找到第一个大于目标数的值（前面没有等于的情况），则它的索引即为插入点，
public class SearchInsertPosition_35 {
public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int mid;
        while (l < r) {
        	mid = l + (r - l)/2;
			if (nums[mid] == target) {
				return mid;
			}else if(nums[mid] < target) {
				l = mid + 1;
			}else {
				r = mid;
			}	
		}
        
        //出循环时，一定没找到,如果目标值大于数组最大值，则需插入最后(增加一位)，否则插入l所在位置
        if (target > nums[nums.length - 1]) {
			return nums.length;
		}
       return l;
    }
}
