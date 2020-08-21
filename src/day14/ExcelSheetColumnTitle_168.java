package day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a positive integer, return its corresponding column title as appear in an 
 * Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
    
    
Example 1:

Input: 1
Output: "A"


Example 2:

Input: 28
Output: "AB"


Example 3:

Input: 701
Output: "ZY"
 * */

//我的思路：给的数字，先整除26（一直除到结果小于26），得到的结果对应1-26里的字母（注意，一旦碰到余数为0的，整除结果-1），再对26取余，得到的结果对应1-26里的字母，
//两个结果拼接即可
//ascii编码中A是65

//优化：可以对上面对方法进行下优化，合并if和else，写的更简洁一些。从上面的讲解中我们得知，会造成这种不便的
//原因是能被26整除的数字，无法得到字符Z。那么我们用一个小trick，比如对于26来说，我们先让n自减1，变成25，
//然后再对26取余，得到25，此时再加上字符A，就可以得到字符Z了。叼就叼在这对其他的不能整除26的数也是成立的，
//完美解决问题
public class ExcelSheetColumnTitle_168 {
public String convertToTitle(int n) {
	//解法1
     //if(n <= 26)return (char)(n+64)+"";
//	
//	List<Character> res = new ArrayList<Character>();
//        while(n > 26) {
//        	int temp = n % 26;
//        	if(temp == 0) {
//        	res.add('Z');	
//        	n = (n / 26) - 1;
//        	}else {
//        		res.add((char)(temp + 64));
//                n = n / 26;
//        	}
//        }
//       res.add( (char)(n + 64));
//       String str = "";
//       //倒着取出list里的每个字符
//       for (int i = res.size()-1; i >= 0; i--) {
//		str += res.get(i);
//	}
//  return str;
	
	//解法2:优化
	String tempString = "";
	while(n > 0) {
		tempString +=(char)  --n % 26 + 'A';
		n = n / 26;
	}
  String str = "";
  //倒着取出list里的每个字符
  for (int i = tempString.length() - 1; i >= 0; i--) {
	str += tempString.charAt(i);
}
return str;
       
    }
}
