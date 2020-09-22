package day30;

/*
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a 
 * non-null node, we record the node's value. If it is a null node, we record using a sentinel 
 * value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", 
where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal 
serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing
 null pointer.

You may assume that the input format is always valid, for example it could never contain two 
consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true


Example 2:

Input: "1,#"
Output: false

Example 3:

Input: "9,#,#,1"
Output: false
 * */

//思路1：通过举例可以发现符合条件的str有如下两个规律：
//1. 数字的个数总是比#号少一个
//2. 最后一个一定是#号
//同时，如果根节点是#，则后面后面不能再有节点，而且不能有三个连续的#号出现。
//用一个变量记录#和数字的个数，遇到数字+1， 遇到#-1，如果不算最后一个#号（此时数字个数和#个数相同），
//则需要满足每次遍历到#变量一定不会小于0，一旦小于0返回false,且遍历到倒数第二个字符时变量正好等于0
//如果最后一个字符不是#，也直接返回false

//思路2:用一个变量capacity来记录能容忍"#"的个数，然后给preorder末尾加一个逗号，这样可以处理末尾的"#"。遍历preorder
//字符串，如果遇到了非逗号的字符，直接跳过，否则的话capacity自减1，如果此时capacity小于0了，直接返回false。此时再判断
//逗号前面的字符是否为"#"，如果不是的话，capacity自增2。这种设计非常巧妙，如果逗号前面是"#"，我们capacity自减1没问题，
//因为容忍了一个"#"；如果前面是数字，那么先自减的1，可以看作是初始化的1被减了，然后再自增2，因为每多一个数字，
//可以多容忍1个"#"，最后还是要判断capacity是否为0，跟上面的解法一样，我们要补齐"#"的个数，少了也是不对的

public class VerifyPreorderSerializationofaBinaryTree_331 {
public boolean isValidSerialization(String preorder) {
	
	//解法1
	//if(preorder.charAt(preorder.length() - 1) != '#')return false;//最后一个字符不为#	
//        String[] str = preorder.split(",");
//        int count = 0;//记录#个数
//       for (int i = 0; i < str.length - 1; i++) {//遍历到倒数第二个字符
//		if(!"#".equals(str[i]))count++;//遇到数字加1
//		else {
//			count--;//遇到#减1
//			if(count < 0)return false;
//		}
//	}
//       if(count == 0)return true;//遍历完了，count应该正好为0
//       else return false;
       
	
	//解法2
	preorder += ",";
	int capacity = 1;//初始容忍#个数为1
	for (int i = 0; i < preorder.length(); i++) {
		if(preorder.charAt(i) != ',')continue;//跳过非逗号的字符
		//一旦碰见逗号字符，查看它之前的是什么字符，先假设它是#，给capacity自减1,如果此时capacity小于0直接返回false
		if(--capacity < 0)return false;
		//然后再验证它是不是#，如果是数字，则把上一步减的1加回来，再增加1（1个数字对应一个#）
		if(preorder.charAt(i - 1) != '#')capacity += 2;
	}
	return capacity == 0;
    }
}
