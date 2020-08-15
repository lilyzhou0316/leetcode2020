package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

 
Note:

There may be more than one LIS combination, it is only necessary for you to return the
 length.
Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 * */



//思路1:动态规划 Dynamic Programming 的解法，这种解法的时间复杂度为 O(n2)，类似 brute force 的解法，
//维护一个一维 dp 数组，其中 dp[i] 表示以 nums[i] 为结尾的最长递增子串的长度，对于每一个 nums[i]，
//从第一个数再搜索到i，如果发现某个数小于 nums[i]，更新 dp[i]， dp[i] = max(dp[i], dp[j] + 1)，
//即比较当前 dp[i] 的值和那个小于 num[i] 的数的 dp 值加1的大小，就这样不断的更新 dp 数组，
//到最后 dp 数组中最大的值就是我们要返回的 LIS 的长度

//思路2:我的思路的修正，用一个数组tail[i]表示找到当前i元素时，长度为 i + 1 的所有上升子序列的结尾的最小值。
//如果当前元素大于tail[i]，则直接加入数组末尾，如果当前元素小于tail[i]，则从头遍历tail数组，找到第一个大于
//当前元素的数，将该数替换为当前元素（如果数组中已经存在当前元素，则什么都不做）
//遍历完数组后，tail[i]数组长度即为所求
//具体解释见链接  https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
public class LongestIncreasingSubsequence_300 {
public int lengthOfLIS(int[] nums) {
	if(nums.length == 0 || nums == null)return 0;
	if(nums.length == 1)return 1;
	
	//解法1
	//int max = 0;
//	int[] dp = new int[nums.length];// dp[i] 表示以 nums[i] 为结尾的最长递增子串的长度
//	Arrays.fill(dp, 1);//注意，让dp[i]初始值为1
//	
//	
//	for (int i = 1; i < dp.length; i++) {
//		for (int j = 0; j < i; j++) {//找nums[i]之前所有比它小的数
//			if(nums[i] > nums[j]) {
//				dp[i] = Math.max(dp[i], dp[j] + 1);
//				
//			}
//		}
//        max = Math.max(max, dp[i]);
//	}
//	return max;
	
	//解法2:
	// tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
    int[] tail = new int[nums.length];
    // 遍历第 1 个数，直接放在有序数组 tail 的开头
    tail[0] = nums[0];
    // end 表示有序数组 tail 的最后一个已经赋值元素的索引
    int end = 0;

    //从第二个数开始遍历原数组
    for (int i = 1; i < nums.length; i++) {
        // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
        if (nums[i] > tail[end]) {
            // 直接添加在那个元素的后面，所以 end 先加 1
            end++;
            tail[end] = nums[i];
        } else {
            // 使用二分查找法，在有序数组 tail 中
            // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
            int left = 0;
            int right = end;
            while (left < right) {
                // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                int mid = left + (right - left) / 2;
                //int mid = left + ((right - left) >>> 1);
                if (tail[mid] < nums[i]) {
                    // 中位数肯定不是要找的数，把它写在分支的前面
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            //出循环时，left即为目标index
            tail[left] = nums[i];
        }
       
    }
    // 此时 end 是有序数组 tail 最后一个元素的索引
    // 题目要求返回的是长度，因此 +1 后返回
    end++;
    return end;

    }
}
