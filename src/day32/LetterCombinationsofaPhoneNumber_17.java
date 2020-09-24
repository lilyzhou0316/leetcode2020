package day32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that 
 * the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 
does not map to any letters.
2--abc
3--def
4--ghi
5--jkl
6--mno
7--pqrs
8--tuv
9--wxyz

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 * */
public class LetterCombinationsofaPhoneNumber_17 {
	 List<String> result = new ArrayList<String>();
     
	    Map<String, String> map = new HashMap<>();
		    
		    public List<String> letterCombinations(String digits) {
		    	 map.put("2","abc");
			     map.put("3","def");
			     map.put("4","ghi");
			     map.put("5","jkl");
			     map.put("6","mno");
			     map.put("7","pqrs");
			     map.put("8","tuv");
			     map.put("9","wxyz");
		        //recursion 
		        
		        if (digits.length() == 0 || digits == null) return result;

		        //call the recursion method
		        //initial combination as ""
		        recursion("", digits);
		        return result;
		    }
		    
		    public void recursion(String combination, String digits){
		        //base case
		        if(digits.length() == 0){//当所有数字都被取出后
		            result.add(combination);
		        }else{//永远取第一个数字
		           String digit = digits.substring(0,1);
		            //找到对应该数字的string
		            String letters = map.get(digit);
		            //iterate all chars in letters
		            for(int i=0; i<letters.length();i++){
	                    //把每个字符保存到当前结果中，并将当前取的数字从digits中删除，从而能获取下一个数字
		                char letter = letters.charAt(i);
		                //append the letter to combination string and proceed the next digit
		                recursion(combination+letter, digits.substring(1));
		            }
		            
		        }
		    }
	
}
