package day19;

/*
 * Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"


Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
 * */

//我的思路：把短的那个当作第一个参数，然后给它前面补0，再让两者相加，碰到2进位
public class AddBinary_67 {
	 public String addBinary(String a, String b) {
		 //解法1
//	        if(a.length() > b.length())return addBinary(b, a);//把短的那个当作第一个参数
//	        
//	        StringBuilder s1 = new StringBuilder(a);
//	        StringBuilder s2 = new StringBuilder(b);
//	        while(s1.length() < s2.length()) {//给短的补0，直到两者的长度相等
//	        	s1.insert(0, 0);
//	        }
//	       
//	        int incre = 0;
//	        StringBuilder res = new StringBuilder();
//	        for (int i = s1.length() - 1; i >= 0 ; i--) {//倒序遍历两个str取出每个数字相加
//				int t = Integer.parseInt(s1.charAt(i)+"")+Integer.parseInt(s2.charAt(i)+"") + incre;
//				
//				incre = t / 2;
//				res.insert(0, t % 2);
//				
//			}
	        
	        //优化
	        int incre = 0;
	        StringBuilder res = new StringBuilder();
	        int i = a.length() - 1;
	        int j = b.length() - 1;
	        while(i >=0 || j >= 0) {//当a,b其中有一个还没有遍历完时
	        	int n1 = i >= 0? a.charAt(i) - '0' : 0;//如果a没有遍历完，则直接取当前数字，否则取0
	        	int n2 = j >= 0? b.charAt(j) - '0' : 0;//同上
	        	int sum = (n1 + n2 + incre);
	        	incre = sum / 2;
	        	res.insert(0, sum % 2);
	        	i--;
	        	j--;
	        }
	        if(incre != 0)res.insert(0, incre);
	        return res.toString();
	    }
	 
	
}
