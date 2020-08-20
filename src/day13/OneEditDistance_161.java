package day13;

/*
 * Given two strings  s  and  t , determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into  s  to get  t                                                                                   
Delete a character from  s  to get  t 
Replace a character of  s  to get  t

条件1，2:两个字符串的长度之差等于1，长的那个字符串去掉一个字符，剩下的应该和短的字符串相同。 
 条件3:s与t长度一样，但是有一位字符不同

Example 1:

Input: _s_ = "ab", _t_ = "acb"
Output: true
Explanation: We can insert 'c' into _s_  to get  _t._


Example 2:

Input: _s_ = "cab", _t_ = "ad"
Output: false
Explanation: We cannot get _t_ from _s_ by only one step.


Example 3:

Input: _s_ = "1203", _t_ = "1213"
Output: true
Explanation: We can replace '0' with '1' to get  _t._
 * */

public class OneEditDistance_161 {
	public boolean isOneEditDistance(String s, String t) {
		if(Math.abs(s.length() - t.length()) > 1)return false;
		
		//满足条件1，2，即长度相差1
		if(s.length() > t.length())return isOneEditDistance(t, s);//让第一个参数永远是长度小的
		
		//不管是t,s长度差1还是相等，如果t中出现的字符有超过1个与出现在s中的不同，则false
		int count = 0;
		for (char c: t.toCharArray()) {
			if(!s.contains(c+""))count++;
			if(count > 1)return false;
		}
		//如果s,t中字符只有一个不同且长度差1，则去掉t中那个不同的字符，然后比较两者是否相同
		if(t.length() - s.length() == 1) {
			for (int i = 0; i < t.length(); i++) {
				if(s.charAt(i) != t.charAt(i)) {
					//当前t中字符不在s中，删除
					t = t.replace(t.charAt(i)+"", "");
					return t.equals(s);
				}
			}
		}
		return true;
		
		
	}
}
