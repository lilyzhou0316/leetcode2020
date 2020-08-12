package day06;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

/*
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 *  Create the maximum number of length k <= m + n from digits of the two. 
 *  The relative order of the digits from the same array must be preserved. 
 *  相对位置保持不变，即数字先后顺序不变即可
 *  Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:
Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]

Example 2:
Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]

Example 3:
Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
 * */

//思路:由于k的大小不定，所以有三种可能：
//第一种是当k为0时，两个数组中都不取数。
//第二种是当k小于等于m+n时，这种情况下，有可能只从一个数组中取数，或者两个都取一些。
//第三种情况是k > m+n，则两个数组中的所有数加起来也不够，返回空集

//对于第二种情况，思路是：假设从数组1中取k1个数，从数组2中取k2个数，那么k1+k2 = k
//1.首先先找出数组1，和数组2取k1,k2个数时的各自的最大子数组（相对位置不变）：
//从数组中按顺序取数，放入一个栈中，如果当前数大于栈顶数，则删除栈顶数，比较下一个栈顶数，重复直到当前数小于等于栈顶数或者栈为空时
//把当前数压入栈中

//2.将找到的两个子数组k1,k2合并，找到最大合集：从k1，k2中一位一位取数比较，谁的数大，谁放在新数组前面的位置，然后剩下的继续比较

//具体解释见截图
public class CreateMaximumNumber_321 {

    /**
     * 最大数来源于 nums1长度为s的子序列 和num2长度为k - s的子序列
     * 反证法可得 最大数来源于 nums1长度为s的最大子序列 和num2长度为k - s的最大子序列
     * 按最大值合并两个子序列， 即为结果
     * 时间复杂度 ： O(k * max(n, k) )
     */
   public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[k];
        if(k == 0 || m + n < k) return ans;
        for(int k1 = 0; k1 <= k; k1++){//k1为从数组1中取数的个数
             int k2 = k - k1;
            if(k1 > m || k2 > n) continue;//当k1,k2超出取值范围时，跳过
   
            int[] seq1 = maxSubSequence(nums1,k1);    //子序列1
            int[] seq2 = maxSubSequence(nums2,k2);  //子序列2
            int[] temp = merge(seq1,seq2);           //归并
            if(compare(temp,ans)){                   //比较逻辑大小
                for(int j=0;j<k;j++){
                    ans[j] = temp[j];
                }
            }
        }
        return ans;
    }

    //求数组中k个顺序不变的最大子序列
    private int[] maxSubSequence(int[] nums, int k){
        int l = nums.length;
        if(l<=k) return nums;
        
        //代表最多可以丢弃几个数
        int drop = l-k;

        int[] ans = new int[k];
        if(k==0) return ans;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<l;i++){
            while(!stack.empty() && nums[i]>stack.peek() && drop-->0){
                stack.pop();
            }
            stack.push(nums[i]);
            
        }
        //裁剪大小
        while(stack.size()>k) stack.pop();

        for(int i=k-1;i>=0;i--){
            ans[i] = stack.pop();
        }
        return ans;
    }

    //归并数组
    //这里遇到了好多坑，一开始是想着按照归并排序那种方式归并的，结果发现在遇到连续几位数字相同的情况下时会出现问题
    private int[] merge(int[] nums1, int[] nums2){
        int l1 = nums1.length;
        int l2 = nums2.length;
        if(l1==0) return nums2;
        if(l2==0) return nums1;
        int ans[] = new int[l1+l2];
        int i1 = 0;
        int i2 = 0;
        for(int i=0;i<l1+l2;i++){
            if(compare(Arrays.copyOfRange(nums1, i1, l1),Arrays.copyOfRange(nums2, i2, l2))){
                ans[i] = nums1[i1++];
            }
            else{
                ans[i] = nums2[i2++];
            }
        }
        return ans;
    }

    //比较数组的逻辑大小，如果数组长短不一样并且前n个数字完全一样，则认为长度大的那个数组大
    //返回值：若nums1>nums2，返回true
    private boolean compare(int[] nums1, int[] nums2){
        int n = Math.min(nums1.length,nums2.length);
        for(int i=0;i<n;i++){
            if(nums1[i]>nums2[i]) return true;
            else if(nums1[i]<nums2[i]) return false;
            else continue;
        }
        return nums1.length>nums2.length;
    }
 
 
}
