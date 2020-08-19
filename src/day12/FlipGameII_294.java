package day12;

/*
 * You are playing the following Flip Game with your friend: Given a string that 
 * contains only these two characters: + and -, you and your friend take turns to 
 * flip two consecutive "++" into "--". The game ends when a person can no longer make
 *  a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become 
"+--+".


Follow up:
Derive your algorithm's runtime complexity.
 * */

//思路：让我们判断先手的玩家是否能赢，可以穷举所有的情况，用回溯法来解题，思路跟上面那题类似，
//也是从第二个字母开始遍历整个字符串，如果当前字母和之前那个字母都是+，那么递归调用将这两个位置变为--的字符串，
//如果返回 false，说明当前玩家可以赢，结束循环返回 false。
//这道题不是问 “1p是否会怎么选都会赢”，而是 “如果1p每次都选特别的两个+，最终他会不会赢”。
//所以 canWin 这个函数的意思是 “在当前这种状态下，至少有一种选法，能够让他赢”。
//而 (!canWin) 的意思就变成了 “在当前这种状态下，无论怎么选，都不能赢”。所以 1p 要看的是，
//是否存在这样一种情况，无论 2p 怎么选，都不会赢。所以只要有一个 (!canWin)，1p 就可以确定他会赢。
//这道题从博弈论的角度会更好理解。每个 player 都想让自己赢，所以每轮他们不会随机选+。
//每一轮的 player 会选能够让对手输的+。如果无论如何都选不到让对手输的+，那么只能是当前的 player 输了
public class FlipGameII_294 {
	public boolean canWin(String s) {
		for (int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == '+' && s.charAt(i - 1) == '+' && !canWin(s.substring(0,i - 1)+"--"+s.substring(i+1)))
				//!canWin(s.substring(0,i - 1)+"--"+s.substring(i+1))代表在当前翻转后的情况（由第一个人进行翻转）下，
				//无论第二个人怎么选都不能赢
				
				return true;
		}
		return false;
	}
}
