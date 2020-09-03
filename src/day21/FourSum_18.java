package day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d 
 * in nums such that a + b + c + d = target? Find all unique quadruplets in the array which 
 * gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 * */

//思路：类似3sum,只不过这里要遍历双层，再用二分法,注意跳过重复元素,并每次判断当前i,j下的最大最小值
public class FourSum_18 {
public List<List<Integer>> fourSum(int[] nums, int target) {
	 List<List<Integer>> res = new ArrayList<List<Integer>>();
     if(nums.length < 4)return res;
      Arrays.sort(nums);
      
      int length = nums.length;
      
      for (int i = 0; i < length-3; i++) {
      	if(i > 0 && nums[i] == nums[i - 1])continue;//去重
      	
      	/*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
          int min1=nums[i]+nums[i+1]+nums[i+2]+nums[i+3];
          if(min1>target){
              break;
          }
          /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
          int max1=nums[i]+nums[length-1]+nums[length-2]+nums[length-3];
          if(max1<target){
              continue;
          }
          
			for (int j = i + 1; j < length - 2; j++) {
				int l = j + 1;
				int r = length - 1;
				if(j > i + 1 && nums[j] == nums[j - 1])continue;//去重
				
				/*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
              int min=nums[i]+nums[j]+nums[l]+nums[l+1];
              if(min>target){
                  break;
              }
              /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
              int max=nums[i]+nums[j]+nums[r]+nums[r-1];
              if(max<target){
                  continue;
              }

				while(l < r) {
					int temp = nums[i] + nums[j] + nums[l] + nums[r];
					if(temp == target) {
						res.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
						while(l < r && nums[l] == nums[++l]);
						while(l < r && nums[r] == nums[--r]);
					}else if(temp  < target) {
						//总和太小，l右移
						while(l < r && nums[l] == nums[++l]);
					}else {
						//总和太大，r左移
						while(l < r && nums[r] == nums[--r]);
					}
				}
			}
		}
      return res;
    }
}
