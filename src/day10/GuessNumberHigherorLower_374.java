package day10;

/*
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 
 
Example :

Input: n = 10, pick = 6
Output: 6
 * */

//我的思路：二分法查找

public class GuessNumberHigherorLower_374 {
	/** 
	 * Forward declaration of guess API.
	 * @param  num   your guess
	 * @return 	     -1 if num(实际数) is lower than the guess number（猜的数）
	 *			      1 if num is higher than the guess number
	 *               otherwise return 0
	 * int guess(int num);
	 */
public int guessNumber(int n) {
	 
        int l = 1, r = n;
        while(l < r) {
        	int mid = l + (r - l)/2;
        	if(guess(mid) == 0)return mid;
        	if(guess(mid) == 1){
        		l = mid + 1;
        	}else if(guess(mid) == -1){
        		r = mid - 1;
        	}
        }
        return l;
    }

}
