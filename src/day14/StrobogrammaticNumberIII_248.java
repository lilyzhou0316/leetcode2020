package day14;

import java.util.ArrayList;
import java.util.List;

/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees 
 * (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in 
the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the  low and  high  
numbers are represented as string.
 * */

//我的思路：按照题247的思路，先判断low和high分别是几位数，然后用递归找出所有符合位数的可能list，从list里再
//筛选出符合条件的（会超时）

//思路：基于第二道的基础上，不保存所有的结果，而是在递归中直接计数（加一个判断是否在区间内），根据之前的分析，需要初始化 n=0 和 n=1 
//的情况，然后在其基础上进行递归，递归的长度 len 从 low 到 high 之间遍历，然后看当前单词长度有没有达到 len，
//如果达到了，首先要去掉开头是0的多位数，然后去掉长度和 low 相同但小于 low 的数，和长度和 high 相同但大于 high 的数，
//然后结果自增1，然后分别给当前单词左右加上那五对对称数，继续递归调用
public class StrobogrammaticNumberIII_248 {
	public int strobogrammaticInRange(String low, String high) {
		int res = 0;
        for (int i = low.length(); i <= high.length(); ++i) {
            find(low, high, "", i, res);
            find(low, high, "0", i, res);
            find(low, high, "1", i, res);
            find(low, high, "8", i, res);
        }
        return res;
	}
	
	public void find(String low, String high, String path, int len, int res) {
		if (path.length() >= len) {
            if (path.length() != len || (len != 1 && path.charAt(0) == '0')) return;
            if ((len == low.length() && path.compareTo(low) < 0) || (len == high.length() && path.compareTo(high) > 0)) {
                return;
            }
            ++res;
        }
        find(low, high, "0" + path + "0", len, res);
        find(low, high, "1" + path + "1", len, res);
        find(low, high, "6" + path + "9", len, res);
        find(low, high, "8" + path + "8", len, res);
        find(low, high, "9" + path + "6", len, res);
	}
}
