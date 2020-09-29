package day33;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s containing only digits, return all possible valid IP addresses that can be 
 * obtained from s. You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated 
by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid 
\IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses. 

 

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "1111"
Output: ["1.1.1.1"]
Example 4:

Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]
Example 5:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:

0 <= s.length <= 3000
s consists of digits only.
 * */

//思路：有点类似题291 word pattern2,题目明确指出输入字符串只含有数字，所以当某段是三位时，我们要判断其是否越界
//（>255)，还有一点很重要的是，当只有一位时，0可以成某一段，如果有两位或三位时，像 00， 01， 001， 011， 000等
//都是不合法的，所以我们还是需要有一个判定函数来判断某个字符串是否合法。这道题其实也可以看做是字符串的分段问题，
//在输入字符串中加入三个点，将字符串分为四段，每一段必须合法，求所有可能的情况。根据目前刷了这么多题，得出了两个经验，
//一是只要遇到字符串的子序列或配准问题首先考虑动态规划DP，二是只要遇到需要求出所有可能情况首先考虑用递归 。这道题并非是
//求字符串的子序列或配准问题，更符合第二种情况，所以我们要用递归来解。我们用k来表示当前还需要分的段数，如果k = 0，
//则表示三个点已经加入完成，四段已经形成，若这时字符串刚好为空，则将当前分好的结果保存。若k != 0, 则对于每一段，
//我们分别用一位，两位，三位来尝试，分别判断其合不合法，如果合法，则调用递归继续分剩下的字符串，最终和求出所有合法组合
public class RestoreIPAddresses_93 {
public List<String> restoreIpAddresses(String s) {
	List<String> res = new ArrayList<String>();
	if(s.length() < 4 || s == null)return res;
	List<String> cur = new ArrayList<String>();
	helper(s, cur, res);
	return res;
    }
public void helper(String s,List<String> cur,  List<String> res) {
	if(cur.size() == 4 ) {
        if(s.length() == 0){
           //如果当前s已经遍历完，且4段都分好了,则将当前结果都加上.然后加入结果集
        	String curString = cur.get(0) + "." + cur.get(1) + "." + cur.get(2) + "." + cur.get(3) ;  
		res.add(curString);
		return; 
        }
		
	}else{
        for (int i = 1; i <= 3; i++) {//限制每次截取的子串长度不超过3
			if(s.length() >= i && isValid(s.substring(0,i))) {
				//如果s长度够截取，且截取的子串符合条件
				String temp = s.substring(0,i);
	            
	            cur.add(temp);
				helper(s.substring(i), cur ,  res);
				//backtrack
	            cur.remove(cur.size() - 1); 
				}
			
		}	
	}
    }
	


public boolean isValid(String str) {
	//如果传入字符串长度大于3或者为0， 或者长度为2，3时却有先导0,则直接返回false
		if(str.length() == 0 || str.length() > 3 || (str.length() > 1 && str.charAt(0) == '0'))return false;
	int res = Integer.parseInt(str);//如果其它条件都满足，则看值有没有超出范围
	return res <= 255 && res >=0 ;
}
}
