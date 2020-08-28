package day18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for 
 * example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify 
 * repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur 
more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 * */

//思路：从s中把所有长度为10的子串都截取出来放到map中，然后在s中找每个子串indexof和lastindexof值，
//如果两个值不相等，说明至少出现了两次，则符合条件，加入结果集---超时

//思路1:用位操作 Bit Manipulation 来求解，由于构成输入字符串的字符只有四种，分别是 A, C, G, T，下面来看下它们的 ASCII 码用二进制来表示：
//A: 0100 0 001 　　C: 0100 0 011 　　G: 0100 0 111 　　T: 0101 0 100
//由于目的是利用位来区分字符，当然是越少位越好，通过观察发现，每个字符的后三位都不相同，故而可以用末尾三位来区分这四个字符。
//而题目要求是 10 个字符长度的串，每个字符用三位来区分，10 个字符需要30位，在 32 位机上也 OK。为了提取出后 30 位，还需要用个
//mask，取值为 0x7ffffff，用此 mask 可取出后27位，再向左平移三位即可。算法的思想是，当取出第十个字符时，将其存在 HashMap 
//里，和该字符串出现频率映射，之后每向左移三位替换一个字符，查找新字符串在 HashMap 里出现次数，如果之前刚好出现过一次，则将当前
//字符串存入返回值的数组并将其出现次数加一，如果从未出现过，则将其映射到1。
//用题目中给的例子来分析整个过程：
//首先取出前九个字符 AAAAACCCC，根据上面的分析，用三位来表示一个字符，所以这九个字符可以用二进制表示为 
//001001001001001011011011011，然后继续遍历字符串，下一个进来的是C，则当前字符为 AAAAACCCCC，二进制表示为 
//001001001001001011011011011011，然后将其存入 HashMap 中，用二进制的好处是可以用一个 int 变量来表示任意十个字符序列，
//比起直接存入字符串大大的节省了内存空间，然后再读入下一个字符，则此时字符串为 AAAACCCCCA，还是存入其二进制的表示形式，
//以此类推，当某个序列之前已经出现过了，将其存入结果 res 中即可
public class RepeatedDNASequences_187 {
public List<String> findRepeatedDnaSequences(String s) {
	List<String> res = new ArrayList<String>();
        if(s.length() < 10)return res;
        Set<String> temp = new HashSet();
        
        for (int i = 0; i <= s.length() - 10; i++) {
        	if(!temp.add(s.substring(i,i+10)) && !res.contains(s.substring(i,i+10))) {
        		res.add(s.substring(i,i+10));
        	}else {
        		temp.add(s.substring(i,i+10));
        	}
			
		}
        return res;
    }
}
