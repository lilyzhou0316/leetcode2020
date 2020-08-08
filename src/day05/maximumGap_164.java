package day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an unsorted array, find the maximum difference between the successive elements 
 * in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:
Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
             
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

Note:

You may assume all elements in the array are non-negative integers and fit in the 
32-bit signed integer range.
Try to solve it in linear time/space.
 * */

//题目要求是要线性的时间和空间，那么只能用桶排序或者基排序，Arrays.sort()的时间复杂度 O(nlogn)，不符合要求。
//思路1:桶排序。中心思想，给n个桶，每个桶的interval(取值区间差，即能放的最大值 - 最小值)一样，
//让数组中的数字按桶的取值区间存放，然后计算每个相邻桶的最大值最小值之差（后一个的最小值 - 前一个的最大值），
//但这样做一定需要一个前提，因为我们只计算了相邻箱子的差值，没有计算箱子内数字的情况，
//所以我们需要保证每个箱子里边的数字一定不会产生大于上面情况的 gap。这样只要让所有桶里出现正好一个空桶即可
//具体见截图

//思路2:基数排序，中心思想是把数组中的数字一个个取出先按个位数字的大小从小到达排列，然后把排好的数字按顺序取出，
//再把刚刚取出的数字按十位数字的大小从小到大排列，然后再取出，......重复此过程直到按照最高位数字大小排序后取出
//即为自然排序的从小到大
//时间复杂度O(kn)，k 是最大数字的位数，当 k 远小于 n 的时候，时间复杂度可以近似看成 O(n)

public class maximumGap_164 {
    public int maximumGap(int[] nums) {
    	if(nums.length < 2)return 0;
    	
    	//解法1:基数排序
//    	List<ArrayList<Integer>> lists = new ArrayList<>();//用来放0-9对应的数
//        for (int i = 0; i < 10; i++) {
//            lists.add(new ArrayList<>());
//        }
//        int n = nums.length;
//        int max = nums[0];
//        //找出最大的数字，根据最大数确定需要排序到哪个位上（个位，十位，百位。。。。）
//        for (int i = 1; i < n; i++) {
//            if (max < nums[i]) {
//                max = nums[i];
//            }
//        }
//        int m = max;
//        int exp = 1;//控制当前是按照哪个位排序的
//        //一位一位的进行
//        while (max > 0) {
//            //将之前的元素清空
//            for (int i = 0; i < 10; i++) {
//                lists.set(i, new ArrayList<>());
//            }
//            //将数字放入对应的位置
//            for (int i = 0; i < n; i++) {
//            	//初始按每个数字的个位数大小排序，如果个位相同，按照在数组中的先后顺序排序
//            	// lists.get()取到个位数字对应的arrylist数组
//                lists.get(nums[i] / exp % 10).add(nums[i]);
//            }
//    		
//            //将数字依次拿出来
//            int index = 0;
//            for (int i = 0; i < 10; i++) {//总共0-9 10个arraylist数组
//                for (int j = 0; j < lists.get(i).size(); j++) {
//                    nums[index] = lists.get(i).get(j);
//                    index++;
//                }
//
//            }
//            
//            //每当一个位数上的排序排完后，前进到下一位
//            max /= 10;
//            exp *= 10;
//        }
//        
//        //出循环后，原数组已经按自然排序排好了，此时再找相邻元素的最大差值
//
//        int maxGap = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i + 1] - nums[i] > maxGap) {
//                maxGap = nums[i + 1] - nums[i];
//            }
//        }
//        return maxGap; 
    	
    	//解法2:桶排序
        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        //找出最大值、最小值
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        if(max - min == 0) {
            return 0;
        }
    	
        //算出每个箱子的范围
        //因为要保证有一个空桶，然后数组中的最大最小值是不参与interval的计算的，所以实际有n-2个数
        //鸽巢原理的变形，有 n - 2 个数字，如果箱子数多于 n - 2 ，那么一定会出现空箱子。
        //总范围是 max - min，那么 interval = (max - min) / 箱子数，为了使得 interval 尽量大，
        //箱子数取最小即可，也就是 n - 1。

        int interval = (int) Math.ceil((double)(max - min) / (n - 1));
        
        //记录每个箱子里数字的最小值和最大值，其它值不需要关心，同时剔除原数组本身的最大最小值
        int[] bucketMin = new int[n - 1];
        int[] bucketMax = new int[n - 1];
        
        //最小值初始为 Integer.MAX_VALUE
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        //最小值初始化为 -1，因为题目告诉我们所有数字是非负数
        Arrays.fill(bucketMax, -1);

        //考虑每个数字去向，并记录每个箱子里的最大值和最小值
        for (int i = 0; i < nums.length; i++) {
            //当前数字所在箱子编号
            int index = (nums[i] - min) / interval;
            //最大数和最小数不需要考虑
            if(nums[i] == min || nums[i] == max) {
                continue;
            }
            //更新当前数字所在箱子的最小值和最大值
            bucketMin[index] = Math.min(nums[i], bucketMin[index]);
            bucketMax[index] = Math.max(nums[i], bucketMax[index]);
        }
    	 
        int maxGap = 0;
        //min 看做第 -1 个箱子的最大值
        int previousMax = min;
        //从第 0 个箱子开始计算每个箱子之间的差值
        for (int i = 0; i < n - 1; i++) {
            //最大值是 -1（初始化值） 说明箱子中没有数字，为空箱子
            if (bucketMax[i] == -1) {
                continue;
            }
            
            //当前箱子的最小值减去前一个箱子的最大值
            maxGap = Math.max(bucketMin[i] - previousMax, maxGap);
            previousMax = bucketMax[i];
        }
        //出循环时，还没有考虑max
        //把max看做最后一个箱子的最小值
        maxGap = Math.max(max - previousMax, maxGap);
        return maxGap;

    }
}
