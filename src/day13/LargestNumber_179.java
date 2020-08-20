package day13;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Given a list of non negative integers, arrange them such that they form the 
 * largest number.

Example 1:

Input: [10,2]
Output: "210"

Example 2:

Input: [3,30,34,5,9]
Output: "9534330"

Note: The result may be very large, so you need to return a string instead of an integer.
 * */

//思路：自定义排序方法，对于两个数字a和b来说，如果将其都转为字符串，如果 ab > ba，则a排在前面，比如9和34，
//由于 934>349，所以9排在前面，再比如说 30 和3，由于 303<330，所以3排在 30 的前面。按照这种规则对原数组
//进行排序后，将每个数字转化为字符串再连接起来就是最终结果
public class LargestNumber_179 {
public String largestNumber(int[] nums) {
        if(nums.length == 0 || nums == null) return "";
        if(nums.length == 1)return nums[0]+"";
        
        //自定义比较器
        Comparator<String> comp = new Comparator<String>() {
        	 @Override
 		    public int compare(String a, String b){
        		return (b + a).compareTo(a + b);//谁大放前面
        	 }
		};
		
		//把int数组转换成string数组
		String[] str = new String[nums.length];
		for (int j = 0; j < str.length; j++) {
			str[j] = nums[j]+"";
		} 
        //根据自定义排序方法对str数组排序
        Arrays.sort(str, comp);
        //String reString = "";
     // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        //因为排序后，第一位是最大数字，如果它是0说明后面的数字都是0
     		if(str[0].charAt(0) == '0')
     			return "0";
        //最后按顺序取出每个数字组合到一起即为所求
//        for (String string : str) {
//			reString += string;
//		}
//        return reString;
     		
     		StringBuilder res = new StringBuilder();
     		for (String string : str) {
				res.append(string);
			}
        return res.toString();
    }
}
