package day13;

import java.util.ArrayList;
import java.util.List;

/*
 * Design an algorithm to encode a list of strings to a string. The encoded string 
 * is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}


Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}
 

So Machine 1 does:

string encoded_string = encode(strs);
 

and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
 

strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. 
Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode 
algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should 
implement your own encode/decode algorithm.
 * */

//思路：题目中并没有限制加码的方法，那么只要能成功的把有码变成无码就行了，具体变换方法自己可以设计。
//由于需要把一个字符串集变成一个字符串，然后把这个字符串再还原成原来的字符串集，所以可以加上某个特殊字符用以
//分割原字符串数组中的每个字符串，同时必须还要加上长度的信息，加码方法是长度 + "/" + 字符串，
//比如对于 "a","ab","abc"，就变成 "1/a2/ab3/abc"，那么解码的时候就有规律可寻，先寻找 "/"，
//然后之前的就是要取出的字符个数，从 "/" 后取出相应个数即可，以此类推直至没有 "/"了，
//这样就得到高清无码的字符串集了

public class EncodeandDecodeStrings_271 {
	  // Encodes a list of strings to a single string.
	public String encode(String[] strs) {
		String tempString = "";
		for (String string : strs) {
			tempString += string.length() + "/" + string;
		}
		return tempString;
	}
	// Decodes a single string to a list of strings.
	public String[] decode(String s) {
		List<String> res = new ArrayList<String>();
		int i = 0;
		while( i < s.length()) {
			int j = s.indexOf("/");
			int len = Integer.parseInt(s.substring(i, j - i));
			res.add(s.substring(j + 1, j + 1 + len));
			i = j + 1 + len;
		}
		String[] reStrings = new String[res.size()];
		for (int j = 0; j < res.size(); j++) {
			reStrings[j] = res.get(j); 
		}
		return reStrings;
	}
}
