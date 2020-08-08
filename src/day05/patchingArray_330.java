package day05;

/*
 * Given a sorted positive integer array nums and an integer n, add/patch elements 
 * to the array such that any number in range [1, n] inclusive can be formed by the 
 * sum of some elements in the array. Return the minimum number of patches required.
 * 
 * Example 1:
 * Input: nums = [1,3], n = 6
   Output: 1 
   Explanation:
   Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
   Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
   Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
   So we only need 1 patch.
   
   Example 2:
   Input: nums = [1,5,10], n = 20
   Output: 2
   Explanation: The two patches can be [2, 4]. 
   
   Example 3:
   Input: nums = [1,2,2], n = 5
   Output: 0

 * */

//思路1:贪心算法，定义一个变量miss，用来表示[0,n]之间最小的不能表示的值，初始化为1（n=0时返回0）。
//此时我们能表示的范围是[0, miss)，表示此时我们能表示0到miss-1的所有数，如果此时的num <= miss，
//那么我们可以把我们能表示数的范围扩大到[0, miss+num)，如果num>miss，那么此时我们需要添加一个数，
//这里我们不能加上一个大于miss的数，因为这样的话miss永远都求不到了（比如[1，3)，我们能取到1，2，如果加上4，
//那能取到1，2，4，5，6，但是取不到3），所以我们加上miss它本身，此时取值范围变成[1，2*miss)
//以此类推直至miss>n，我们可以得到结果。
//时间复杂度 : O(m + \log n),在每次迭代中，我们或者增加 i ，或者将 miss 加倍。 i增加的总数为 m，
//miss 加倍的总数为 logn。

public class patchingArray_330 {
    public int minPatches(int[] nums, int n) {
        //解法1:
    	if(n == 0)return 0;
    	
    	long miss = 1;//miss代表当前的最小的不能表示的数,注意miss的取值可能超过Integer.Max_Value
    	int i = 0;//用i遍历数组
    	int len = 0;//len记录加入的数字的个数
    	while (miss <= n) {
    		//把数组里的数取出来加入[1，miss)
			if (i < nums.length && nums[i] <= miss ) {//如果nums[i] <= miss,直接扩大取值范围,不需要增加数
				miss += nums[i];
				i++;//遍历下一个数
			}else{//如果数组里的数已经取完还没有到达n，或者当前取到的数大于miss
				miss += miss;//则需要增加miss本身（因为它是符合条件的能扩大到目前最大取值范围的数）
				len++;//数组里需要增加的数+1
			}
		}
    	//出循环时，miss > n
    	return len;
    	
    }
}
