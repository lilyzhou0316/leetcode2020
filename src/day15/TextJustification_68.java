package day15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Given an array of words and a width maxWidth, format the text such that each line has 
 * exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can 
in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth 
characters.

Extra spaces between words should be distributed as evenly as possible. If the number of 
spaces on a line do not divide evenly between words, the empty slots on the left will be 
assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted 
between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.

每行字符加上空格数 == maxwidth
每行单词数尽可能多，空格在单词之间分布的尽可能平均，如果不能，则让左边的空格多于右边
单词顺序不变
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
[4,2,2,7,2,4,14]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]


Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only 
             one word.
             
             
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 * */

//思路：用一个变量str暂时保存当前结果，按顺序往str里加入单词，并记录每个单词的长度，一旦加入一个单词，
//且它前面str里已经有单词了，则加入一个空格，统计长度，一旦下一个单词加入后长度超过最大长度，则舍弃当前单词
//然后调整str里的空格
public class TextJustification_68 {
	 public List<String> fullJustify(String[] words, int maxWidth) {
	       
	        int i = 0;//遍历words
	        List<String> res = new ArrayList<>();
	        
	       while( i < words.length) {
	    	   int j = i, len = 0;//len表示不含空格的所有单词的长度
	            while (j < words.length && len + words[j].length() + j - i <= maxWidth) {
	            	//如果加入当前单词及空格数（1）不会超过最大长度，则将当前单词长度加入计算
	                len += words[j++].length();
	            }
	            StringBuilder out = new StringBuilder();
	            int space = maxWidth - len;//计算当前行总共需要的空格数
	            for (int k = i; k < j; ++k) {//从i到j是当前行需要的单词
	                out.append(words[k]);
	                if (space > 0) {//如果当前行需要插入空格
	                    int tmp;
	                    if (j == words.length) { 
	                    	//如果当前行为最后一行
	                        if (j - k == 1) tmp = space;
	                        //如果当前加入的单词是最后一个单词，则需要加入的空格数即为当前行总共需要的空格数
	                        else tmp = 1;//否则只需要加入1个空格（最后一行单词之间只允许有一个空格）
	                    } else {
	                    	//当前行不是最后一行
	                        if (j - 1 > k) {//当前单词不是这行的最后一个单词
	                            if (space % (j - 1 - k) == 0) tmp = space / (j - k - 1);
	                            else tmp = space / (j - k - 1) + 1;
	                        } else tmp = space;
	                    }
	                    int tmp2 = tmp;
	                   while(tmp > 0) {
	                	   out.append(" ");
	                	   tmp--;
	                   }
	                    space -= tmp2;
	                }
	            }
	            res.add(out.toString());
	            i = j;
			}
	        return res;
	    }
}
