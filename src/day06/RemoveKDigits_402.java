package day06;

import java.util.Stack;

/*
 * Given a non-negative integer num represented as a string, remove k digits from the number
 *  so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.


Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 
which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. 
Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 * */

//思路：因为123a456和123b456只要比较ab大小就知道谁大，所以比较一个数大小就是看某一个数字不同的位上的大小
//因此用一个栈一个个压入数组里的数字，如果当前数字比栈顶数字（它的前一位数字）小，栈不为空且还可以再删除数字时（k!=0），
//则重复删除栈顶数字，直到k=0，然后当前数字入栈，最后取出所有栈中数字,保留n-k个即可
//n为数组长度

public class RemoveKDigits_402 {
    public String removeKdigits(String num, int k) {
    	 //解法1:利用栈原理
//        if(k >= num.length())return "0";//要删除的位数和数字位数一样，即全部删除
//        if (k == 0)return num;//一位都不能删除时，返回原字符串 
//      int k1 = k;//保存k值用于最后截取
//       String stack = "";//把字符串当作一个栈用
//       for (int i = 0; i < num.length(); i++) {
//			while(k != 0 && stack.length() != 0 && stack.charAt(stack.length()-1) > num.charAt(i)) {//只要上述条件成立，则删除数字还没有结束
//				stack = stack.substring(0,stack.length()-1);
//				k--;
//			}
//				stack += num.charAt(i);
//		}
//
//       //最后输出前n-k位,首位为0的跳过;
//      stack = stack.substring(0, num.length() - k1);
//       while (stack.charAt(0) == '0' && stack.length()>1) {
//			stack = stack.substring(1);
//		}
//       
//       return stack;
    	
    	//解法1优化
        if(k==0)return num;
    
    if(num.length()==k)return "0";
    
    StringBuilder str = new StringBuilder();//用stringbuilder比直接截取子字符串快
    for(char c:num.toCharArray()){
        while(k>0 && str.length()!=0 && c<str.charAt(str.length()-1)){
            k--;
            str.deleteCharAt(str.length()-1);
        }
        
        //当要加入的字符为0，且会成为字符串的首字符时，跳过它，不加入到字符串
        str.append((c == '0' && str.length() == 0)?"":c);
    }
    
    while(k>0){//遍历完后，k可能还>0,此时删除最后k个字符串即可（因为一定是小的数字在前面）
        k--;
        str.deleteCharAt(str.length()-1);
    }
    
    return (str.length() == 0)? "0":str.toString();
       
    }
    
//    public static void main(String[] args) {
//    	RemoveKDigits_402 r1 = new RemoveKDigits_402();
//    	
//    	System.out.println(r1.removeKdigits("1432219", 3));
//    	
//	}
}
