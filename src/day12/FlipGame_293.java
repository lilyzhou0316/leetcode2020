package day12;

import java.util.ArrayList;
import java.util.List;

/*
 * You are playing the following Flip(翻转) Game with your friend: Given a string that 
 * contains only these two characters: + and -, you and your friend take turns to 
 * flip two consecutive "++" into "--". The game ends when a person can no longer make
 *  a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
 

If there is no valid move, return an empty list [].
 * */

//我的思路：暴力解法，从第二个元素开始，看它和它前面一个元素，如果都为+号，则变为-号并加入结果集

public class FlipGame_293 {
	public List<String> generatePossibleNextMoves(String s) {
		List<String> reStrings = new ArrayList<String>();
		if(s.length() < 2 || s == null)return reStrings;
		
		for (int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
				reStrings.add(s.substring(0,i - 1) + "--" + s.substring(i + 1));
			}
		}
		return reStrings;
	}
}
