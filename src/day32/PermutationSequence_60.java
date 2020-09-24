package day32;

import java.util.ArrayList;
import java.util.List;

/*
 * The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"

Example 2:

Input: n = 4, k = 9
Output: "2314"
 * */

//思路：首先我们要知道当n = 3时，其排列组合共有3! = 6种，当n = 4时，其排列组合共有4! = 24种，我们就以n = 4, k = 17的
//情况来分析，所有排列组合情况如下：
//1234，1243，1324，1342，1423，1432，2134，2143，2314，2341，2413，2431，3124，3142，3214，
//3241，3412 <--- k = 17，3421，4123，4132，4213，4231，4312，4321
//可以发现，每一位上1,2,3,4分别都出现了6次，当最高位上的数字确定了，第二高位每个数字都出现了2次，当第二高位也确定了，
//第三高位上的数字都只出现了1次，当第三高位确定了，那么第四高位上的数字也只能出现一次，下面我们来看k = 17这种情况的每位数字
//如何确定，由于k = 17是转化为数组下标为16，最高位可取1,2,3,4中的一个，每个数字出现3！= 6次
//（因为当最高位确定了，后面三位可以任意排列，所以是3！，那么最高位的数字就会重复3！次），所以k = 16的第一位数字的下标为
//16 / 6 = 2，在 "1234" 中即3被取出。
//第二位此时从1,2,4中取一个，k = 16，则此时的 k' = 16 % (3!) = 4，注意思考这里为何要取余，如果对这24个数以6个一组来分，
//那么k=16这个位置就是在第三组（k/6 = 2）中的第五个（k%6 = 4）数字。如下所示，而剩下的每个数字出现2！= 2次，所以第二数字
//的下标为4 / 2 = 2，在 "124" 中即4被取出。
//3124，3142，3214，3241，3412 <--- k' = 4，3421
//第三位此时从1,2中去一个，k' = 4，则此时的k'' = 4 % (2!) = 0，如下所示，而剩下的每个数字出现1！= 1次，所以第三个数字
//的下标为 0 / 1 = 0，在 "12" 中即1被取出。
//3412 <--- k'' = 0，3421
//第四位是从2中取一个，k'' = 0，则此时的k''' = 0 % (1!) = 0，如下所示，而剩下的每个数字出现0！= 1次，所以第四个数字
//的下标为0 / 1= 0，在 "2" 中即2被取出。
//3412 <--- k''' = 0

//那么我们就可以找出规律了
//a1 = k / (n - 1)!
//k1 = k % (n - 1)!

//a2 = k1 / (n - 2)!
//k2 = k1 % (n - 2)!
//...

//an-1 = kn-2 / 1!
//kn-1 = kn-2 % 1!

//an = kn-1 / 0!
//kn = kn-1 % 0!
public class PermutationSequence_60 {
	 public String getPermutation(int n, int k) {
	        StringBuilder str = new StringBuilder();
	        StringBuilder res = new StringBuilder();
	        for (int i = 1; i < n + 1; i++) {
				str.append(i);
			}
	        int k2 = k - 1;//k2表示下标
	       while(n >= 1) {
	    	   int index = k2 / factorial(n - 1);//得到当前位置上的数字的索引值
	    	   res.append(str.charAt(index));//将当前数字加入结果
	    	   str = str.deleteCharAt(index);//从str中删除当前数字（因为结果中数字不重复）
	    	   
	    	   //更新k值和n值
	    	   k2 = k2 % factorial(n - 1);
	    	   n = n - 1; 
	       }
	       return res.toString();
	    }
	 
	 public int factorial(int n) {
		 if(n == 0)return 1;
		 int res = 1;
		 for (int i = n; i > 0; i--) {
			res *= i;
		}
		 return res;
		
	}
}
