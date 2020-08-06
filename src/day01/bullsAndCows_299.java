package day01;
/*
 You are playing the following Bulls and Cows game with your friend: 
 You write down a number and ask your friend to guess what the number is. 
 Each time your friend makes a guess, you provide a hint that indicates how many digits 
 in said guess match your secret number exactly in both digit and position (called "bulls") 
 and how many digits match the secret number but locate in the wrong position (called "cows"). 
 Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.



Example 2:
Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * */

//思路1:将两个字符串转成char[]，然后逐一比较得到A的数，然后再将找到的A从char[]里剔除，再找guess里的string是否再secret里

//思路2:其实可以用一次循环就搞定的，在处理不是bulls的位置时，我们看如果secret当前位置数字的映射值小于0，
//则表示其在guess中出现过（即在guess中出现的数字对应的次数都-1），cows自增1，然后映射值加1，如果guess当前位置的数字的映射值大于0，
//则表示其在secret中出现过（即在中secret出现的数字对应的次数都+1）），cows自增1，然后映射值减1，
public class bullsAndCows_299 {
    public String getHint(String secret, String guess) {
    	//解法1:
//    	int bulls= 0;//记录A
//    	int cows = 0;//记录B
//    	int[] nums = {0,0,0,0,0,0,0,0,0,0};//记录0-9数字在secret中出现的次数
//    	char[] s = secret.toCharArray();
//    	char[] g = guess.toCharArray();
//    	for (int i = 0; i < secret.length(); i++) {
//			if(s[i] == g[i]) {
//				bulls++;
//			}else {
//				int temp1 = Integer.parseInt(s[i]+"");
//				nums[temp1]++;
//			}
//		}
//    	for (int i = 0; i < g.length; i++) {
//    		int temp2 = Integer.parseInt(g[i]+"");
//			if(g[i] != s[i] && nums[temp2] !=0 ) {//不是bulls同时又是secret里出现过的数字
//				cows++;
//				nums[temp2]--;
//			}
//		}
//    	
//        return bulls+"A"+cows+"B";
    	
    	//解法2:
    	int bulls= 0;//记录A
    	int cows = 0;//记录B
    	int[] nums = {0,0,0,0,0,0,0,0,0,0};//记录0-9数字在secret中出现的次数
    	char[] s = secret.toCharArray();
    	char[] g = guess.toCharArray();
    	for (int i = 0; i < secret.length(); i++) {
			if(s[i] == g[i]) {
				bulls++;
			}else {
				int temp1 = Integer.parseInt(s[i]+"");
				nums[temp1]++;
				int temp2 = Integer.parseInt(g[i]+"");
				nums[temp2]--;
				if(nums[temp1]<=0)cows++;
				if(nums[temp2]>=0)cows++;
				
			}
		}

     return bulls+"A"+cows+"B";
    }

}
