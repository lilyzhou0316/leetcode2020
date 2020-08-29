package day19;


/*
 * Given two non-negative integers num1 and num2 represented as strings, return the product 
 * of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * */

//思路1:普通竖式,遍历 num2 每一位与 num1 进行相乘，将每一步的结果进行累加。注意，num2 除了第一位的，
//其他位与 num1 运算的结果需要补0，这里需要用到计算字符串数字累加（415. 字符串相加，类似题67）
//时间复杂度m*n, 空间复杂度m+n

//思路2:优化竖式，见截图演示说明。该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。
//具体规律如下：乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N
//num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。

public class MultiplyStrings_43 {
//	 public String multiply(String num1, String num2) {
//	        if (num1.equals("0") || num2.equals("0")) {
//	            return "0";
//	        }
//	        // 保存计算结果
//	        String res = "0";
//	        
//	        // num2 逐位与 num1 相乘
//	        for (int i = num2.length() - 1; i >= 0; i--) {
//	            int carry = 0;
//	            // 保存 num2 第i位数字与 num1 相乘的结果
//	            StringBuilder temp = new StringBuilder();
//	            // 补 0 
//	            for (int j = 0; j < num2.length() - 1 - i; j++) {
//	                temp.append(0);
//	            }
//	            int n2 = num2.charAt(i) - '0';
//	            
//	            // num2 的第 i 位数字 n2 与 num1 相乘
//	            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
//	                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
//	                int product = (n1 * n2 + carry) % 10;
//	                temp.append(product);
//	                carry = (n1 * n2 + carry) / 10;
//	            }
//	            // 将当前结果与新计算的结果求和作为新的结果
//	            res = addStrings(res, temp.reverse().toString());
//	        }
//	        return res;
//	    }
//
//	    /**
//	     * 对两个字符串数字进行相加，返回字符串形式的和
//	     */
//	    public String addStrings(String num1, String num2) {
//	        StringBuilder builder = new StringBuilder();
//	        int carry = 0;
//	        for (int i = num1.length() - 1, j = num2.length() - 1;
//	             i >= 0 || j >= 0 || carry != 0;
//	             i--, j--) {
//	            int x = i < 0 ? 0 : num1.charAt(i) - '0';
//	            int y = j < 0 ? 0 : num2.charAt(j) - '0';
//	            int sum = (x + y + carry) % 10;
//	            builder.append(sum);
//	            carry = (x + y + carry) / 10;
//	        }
//	        return builder.reverse().toString();
//	    }
	
	//优化
		public String multiply(String num1, String num2) {
		
	    if (num1.equals("0") || num2.equals("0")) {
	    return "0";
	}
	    int[] res = new int[num1.length() + num2.length()];//乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N

	    // num2 逐位与 num1 相乘
	    for (int i = num2.length() - 1; i >= 0; i--) {
	    	int n2 = num2.charAt(i) - '0';
	    	for (int j =num1.length() - 1; j >= 0; j--) {
	    		int n1 = num1.charAt(j) - '0';
	    		int pro = n1 * n2 +res[i+j+1];
	    		//让res的最后两位记录当前两个数字相乘并加上carry后的值
	    		//i+j+1记录个位
	    		//i+j记录十位
	    		res[i+j+1] = pro % 10;
	    		res[i+j] += pro / 10;//注意，这里要加上十位上原来的数字
			}
	    }
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < res.length; i++) {
	        if (i == 0 && res[i] == 0) continue;
	        result.append(res[i]);
	    }
	    return result.toString();

	}
}