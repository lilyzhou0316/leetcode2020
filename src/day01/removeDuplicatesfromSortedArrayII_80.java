package day01;
/*
Given a sorted array nums, remove the duplicates in-place 
such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, 
you must do this by modifying the input array in-place with O(1) extra memory.

Example:
Given nums = [1,1,1,2,2,3],
Your function should return length = 5, 
with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
 * */

//这道题是之前那道 Remove Duplicates from Sorted Array 的拓展，这里允许最多重复的次数是两次，
//那么可以用一个变量 count 来记录还允许有几次重复，count 初始化为1，如果出现过一次重复，则 count 递减1，
//那么下次再出现重复，快指针直接前进一步，如果这时候不是重复的，
//则 count 恢复1，由于整个数组是有序的，所以一旦出现不重复的数，则一定比这个数大，此数之后不会再有重复项。

public class removeDuplicatesfromSortedArrayII_80 {
	public int removeDuplicatesfromSortedArrayII(int[] arr) {
		int i = 0;//慢
		int j = 1;//快
		int count = 1;
		while(j<arr.length) {
			//分两种情况：
			if (arr[i] == arr[j] && count == 0) {
				//1.如果arr[i] == arr[j] && count = 0,快指针直接前进一步(因为已经没有重复次数了
				//此时需要快指针去找第一个不相等的元素)
				j++;
			} else {
				//2.1如果arr[i] == arr[j] 且 count != 0,则还有重复次数，此时i,j指向的元素又正好相等，因此count-1
				if (arr[i] == arr[j]) {
					count--;
				} else {//2.2如果i,j不相等，则count = 1(重新设置count)
                    count = 1;
				}
				//以上两种情况都需要让慢指针先前进一位，并把快指针的值赋给慢指针,然后再让快指针前进一位
				arr[++i] = arr[j++];
			}
		}
		return arr.length == 0? 0 : i+1;
		
	}

}
