package day23;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an integer n, return 1 to n in lexicographical order(词典顺序，即一个一个char进行比较).

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as 
large as 5,000,000.
 * */

//思路：按个位数遍历，在遍历下一个个位数之前，先遍历十位数，十位数的高位为之前的个位数，只要这个多位数并没有超过n，
//就可以一直往后遍历，如果超过了，我们除以10（即取到了之前遍历到的个位数），然后再加1，如果加1后末尾形成了很多0，
//那么我们要用个while循环把0都去掉，然后继续运算
public class LexicographicalNumbers_386 {
public List<Integer> lexicalOrder(int n) {
       List<Integer> res = new ArrayList<Integer>();
       
       int curDigitNum = 1;//从第一个数字为1开始,只能取到1-9
       for (int i = 0; i < n; i++) {
		res.add(curDigitNum);
		if(curDigitNum * 10 <= n) {//如果当前数字增加位数后还小于n，则开始加入以当前数字为开头数的多位数
			curDigitNum *= 10;	
		}else {
			if(curDigitNum >= n)curDigitNum /= 10;//以当前数字为开头的多位数已经遍历完了，则取下一个数字作为当前数字
			curDigitNum++;
			while(curDigitNum % 10 == 0)curDigitNum /= 10;//因为只能取到1-9，所以如果当前数字末尾有多个0，去掉多余的0 
		}
	}
       return res;
       
    }
}
