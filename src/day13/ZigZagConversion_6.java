package day13;

/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number 
 * of rows like this: (you may want to display this pattern in a fixed font for better 
 * legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 * */

//思路：pattern特点是每次在每一行写一个字母，按照从第一行到最后一行，再从最后一行到第一行的顺序，
//即0-1-2-1-0这样去写，用一个数组去保存每一行，把字母按pattern写入数组中去，最后读取即可
public class ZigZagConversion_6 {
	public String convert(String s, int numRows) {
		if(numRows <= 1 || s.length() < 2)return s;
		
		int flag = -1;//用flag来表示当前的行数是需要+1 还是-1
		String[] reStrings = new String[numRows];//保存每一行字符串
		//初始化string数组为""，必须要初始化，不然默认值是null
		 for(int i = 0; i < reStrings.length; i++)reStrings[i] = "";
		int i = 0;//遍历原字符串
		int j = 0;//表示当前在第几行
		while(i < s.length()) {
			if(j == numRows - 1 || j == 0) flag = -flag;
			reStrings[j] += s.charAt(i) + "";
			i++;
			j += flag;
		}
		
		String res = "";
		for (String str: reStrings) {
			res += str;
		}
		return res;
	}
}
