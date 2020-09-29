package day33;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear 
 * twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 * */
public class FindAllDuplicatesinanArray_442 {
	public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int index = -1;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            index = nums[i] % n;
            nums[index] += n;//the number i which appears 2 or more times, nums[i] will >= 2*n
        }
        
        for(int i = 0; i < n; i++){
            if(nums[i] > 2 * n){//因为数值从1到n，当值正好为n时，出现一次即变成2n，所以最小要大于2n
                if(i == 0){//特殊情况，当数组里的数都等于数组长度时
                    res.add(n);
                }else{
                    res.add(i);
                }
                
            }
        }
        return res;
    }
}
