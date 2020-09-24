package day32;

import java.util.ArrayList;
import java.util.List;

/*
 * Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2",
 "2r1", "3d", "w3", "4"]

 * */

//思路1：数一下题目中给的例子的所有情况的个数，是16个，而word有4个字母，刚好是2的4次方，这是巧合吗，当然不是，如果把0到15
//的二进制写出来，每一个可以对应一种情况，如下所示：

//0000 word
//0001 wor1
//0010 wo1d
//0011 wo2
//0100 w1rd
//0101 w1r1
//0110 w2d
//0111 w3
//1000 1ord
//1001 1or1
//1010 1o1d
//1011 1o2
//1100 2rd
//1101 2r1
//1110 3d
//1111 4
//那么我们就可以观察出规律，凡是0的地方都是原来的字母，单独的1还是1，如果是若干个1连在一起的话，就要求出1的个数，
//用这个数字来替换对应的字母，既然规律找出来了，那么代码就很好写了

//思路2:recursion。
public class GeneralizedAbbreviation_320 {
	
	//解法1
//	public List<String> generateAbbreviations(String word) {
//        List<String> ans = new ArrayList<>();
//        //1 << word.length()，即让x从0取到2^(word.length()) - 1，即上面列举的情况
//        for (int x = 0; x < (1 << word.length()); ++x) // loop through all possible x
//            ans.add(abbr(word, x));
//        return ans;
//    }
//
//    // build the abbreviation for word from number x
//	//比如，当x = 0010时，根据每个位上的值确定当前位应该是数字还是字母，如果值是0则是字母，如果值是1则是数字，如果是连续的1则累加
//    private String abbr(String word, int x) {
//        StringBuilder builder = new StringBuilder();//构建当前x对应的结果
//        
//        int k = 0, n = word.length(); // k is the count of consecutive ones in x，k记录连续的1的个数
//        for (int i = 0; i < n; ++i, x >>= 1) {
//        	//通过x >>= 1和x & 1操作，让x的每一位都会按顺序移动到最后一位上去，然后得到它的值
//        	//如当前x = 0010，先通过x & 1得到末位位0，则当前显示字母，然后下一轮循环通过x >>= 1，使x变成0001，重复
//            if ((x & 1) == 0) { // bit is zero, we keep word.charAt(i)，当前位显示字母
//                if (k != 0) { // we have abbreviated k characters，看前面是否有1，如果有先记录到目前为止的连续的1的个数
//                    builder.append(k);
//                    k = 0; // reset the counter k，记录完后，重设k
//                }
//                builder.append(word.charAt(i));//然后记录当前字母
//            }
//            else // bit is one, increase k，如果当前位置需要显示数字，则k加1
//                ++k;
//        }
//        //如果出循环时，k不为0，说明最后一个连续的1的个数还没有记录，记录下
//        if (k != 0) builder.append(k); //don't forget to append the last k if non zero
//        return builder.toString();
//    }
	
	//解法2，递归
	public List<String> generateAbbreviations(String word) {
		List<String> res = new ArrayList<String>();
		
		recursion(word, 0, 0, "", res);
		return res;
	}
	public void recursion(String word, int position, int count, String output,List<String> res) {
		if(position == word.length()) {
			if(count != 0) {
				//加入最后的连续的1的个数
				output += count;
			}
			res.add(new String(output));
			return;
		}
		//递归分两种情况，1.当前遍历到的为数字，则count加1，position加1继续遍历
		recursion(word, position + 1, count + 1, output, res);
		//2.当前遍历到的为字母，则先加入不为0的count，再加入当前字母，并将count重设为0
		recursion(word, position + 1, 0, output + (count == 0 ? "" : count) + word.charAt(position), res);
	}
}
