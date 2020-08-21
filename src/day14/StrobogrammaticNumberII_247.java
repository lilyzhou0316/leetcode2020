package day14;

import java.util.ArrayList;
import java.util.List;

/*
 * A strobogrammatic number is a number that looks the same when rotated 
 * 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 * */

//思路：用递归来做。
//n = 0:   none
//n = 1:   0, 1, 8； 
//n = 2:   11, 69, 88, 96；
//n = 3:   101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986
//注意观察 n=0 和 n=2，可以发现后者是在前者的基础上，每个数字的左右增加了 [1 1], [6 9], [8 8], [9 6]，
//看 n=1 和 n=3 更加明显，在0的左右增加 [1 1]，变成了 101, 在0的左右增加 [6 9]，变成了 609,
//在0的左右增加 [8 8]，变成了 808, 在0的左右增加 [9 6]，变成了 906, 
//然后在分别在1和8的左右两边加那四组数，实际上是从 m=0 层开始，一层一层往上加的，需要注意的是当加到了n层的
//时候，左右两边不能加 [0 0]，因为0不能出现在两位数及多位数的开头，在中间递归的过程中，
//需要有在数字左右两边各加上0的那种情况
public class StrobogrammaticNumberII_247 {
	public List<String> findStrobogrammatic(int n) {
		return find(n,n);
		
	}
	
	public List<String> find(int m,int n) {
		List<String> reStrings = new ArrayList<String>();
		reStrings.add("");
		if(m == 0)return reStrings;
		if(m == 1) {
			reStrings.add("0");
			reStrings.add("1");
			reStrings.add("8");
			return reStrings;
		}
		List<String> tempString = find(m - 2,n);
		List<String> t2 = new ArrayList<String>();
		
			for (String string : tempString) {
				if(m != n) t2.add("0" + string + "0") ;//当前需要返回的递归层数不是最外层
				t2.add("1" + string + "1") ;
				t2.add("6" + string + "9") ;
				t2.add("8" + string + "8") ;
				t2.add("9" + string + "6") ;
			}
			return t2;
		
	}
}
