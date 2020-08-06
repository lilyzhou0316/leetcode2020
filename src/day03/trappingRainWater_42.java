package day03;

import java.util.Stack;

/*
 * Given n non-negative integers representing an elevation(海拔，标高) map where the width of each bar is 1,
 *  compute how much water it is able to trap after raining.
 *  
 *  图见链接https://leetcode.com/problems/trapping-rain-water/description/
 *  
 *  Example:
 *  Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 * */
//解题中心思想：只看每一个索引i对应的方块，该方块能盛多少水是由它左边的最高高度和右边的最高高度中的较低值决定的（类似水桶短板），
//见图片，其能盛的水等于min(leftmax,rightmax)-i，所以关键是找到以当前i为基准它的左右最大高度，并选择其中的最小值

//思路1:建议看第三个图，用两个数组分别保存做最大值和右最大值
//用一个数组保存对于i元素来说，左右最大值中的最小值，
//基于动态规划 Dynamic Programming 的，维护一个一维的 dp 数组，这个 DP 算法需要遍历两遍数组，
//第一遍在 dp[i] 中存入i位置左边的最大值，然后开始第二遍遍历数组，第二次遍历时找右边最大值，
//然后和左边最大值比较取其中的较小值，然后跟当前值 A[i] 相比，如果大于当前值，则将差值存入结果
//时间复杂度 O(n)空间复杂度O(n).


//思路2:双指针,再看一种只需要遍历一次即可的解法，这个算法需要 left 和 right 两个指针分别指向数组的首尾位置，
//并用两个变量保存左边最大值和右边最大值，然后找到其中的最小值，计算当前i能盛的水量，
//以此类推直至 left 和 right 指针重合
//时间复杂度 O(n)空间复杂度O(1).

//思路3:对上面的解法进行进一步优化，使其更加简洁

//思路4:其中心思想是计算每个凹字型盛水量。这种解法是用 stack 来做的，遍历高度，如果此时栈为空，或者当前高度小于等于栈顶高度，则把当前高度的坐标压入栈，
//注意这里不直接把高度压入栈，而是把坐标压入栈，这样方便在后来算水平距离。当遇到比栈顶高度大的时候，
//就说明有可能会有坑存在，可以装雨水。此时如果取出栈顶索引后，栈里不能为空，如果为空，那么不能形成坑（少了左边的墙），直接跳过，
//如果不为空的话，那么此时把栈顶元素取出来作坑，新的栈顶元素就是左边界，当前高度是右边界，只要取二者较小值，
//减去坑的高度，长度就是右边界坐标减去左边界坐标再减1，二者相乘就是盛水量啦
//时间复杂度 O(n)空间复杂度O(n).


//思路5:暴力破解，见图片
//时间复杂度 o(n^2)空间复杂度O(1).

public class trappingRainWater_42 {
	 public int trap(int[] height) {
		 //解法1
//		 int res = 0, mx = 0, n = height.length;
//	        int[] dp = new int[n];
//	        for (int i = 0; i < n; ++i) {//将i位置左边的最大值存入dp[i]
//	            dp[i] = mx;
//	            mx = Math.max(mx, height[i]);
//	        }
//	        mx = 0;
//	        for (int i = n - 1; i >= 0; --i) {//从尾到头遍历数组，找i右边最大值并与左边最大值比较，取其中的较小值
//	            dp[i] = Math.min(dp[i], mx);
//	            mx = Math.max(mx, height[i]);
//	            if (dp[i] - height[i] > 0) res += dp[i] - height[i];//比较当前值和dp[i]，如果大于当前值，则将差值存入结果
//	        }
//	        return res;
		 
		 //解法2:
//		 int n = height.length;
//		 int res = 0, l = 0, r = n - 1;
//		 int leftmax = 0, rightmax = 0;
//		 while (l<r) {//找左右最大值
//			leftmax = Math.max(leftmax, height[l]);
//			rightmax =  Math.max(rightmax, height[r]);
//			if (leftmax < rightmax) {//比较左右最大值哪个更小
//				res += leftmax - height[l];//计算当前位置上的盛水量
//				l++;
//			}else {
//				res += rightmax - height[r];
//				r--;
//			}
//		}
//	        
//	        return res;
		 
		 //解法3:
//		 int l = 0, r = height.length - 1, level = 0, res = 0;
//	        while (l < r) {
//	            int lower = height[(height[l] < height[r]) ? l++ : r--];
//	            level = Math.max(level, lower);
//	            res += level - lower;
//	        }
//	        return res;
		 
		 //解法4:
		 Stack<Integer> s = new Stack<Integer>();
	        int i = 0, n = height.length, res = 0;
	        while (i < n) {
	            if (s.isEmpty() || height[i] <= height[s.peek()]) {
	                s.push(i++);
	            } else {
	                int t = s.pop();//取出栈顶元素作坑
	                if (s.isEmpty()) continue;//缺少左边界，直接跳过
	                res += (Math.min(height[i], height[s.peek()]) - height[t]) * (i - s.peek() - 1);
	            }
	        }
	        return res;
	 }
}
