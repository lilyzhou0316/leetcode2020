package day14;

/*
 * Convert a non-negative integer to its english words representation. Given input 
 * is guaranteed to be less than 2^31 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"


Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"


Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * */


//思路：要3个一组的进行处理，而且题目中限定了输入数字范围为0到231 - 1之间，最高只能到billion位，
//3个一组也只需处理四组即可，那么我们需要些一个处理三个一组数字的函数，我们需要把1到19的英文单词都列出来，
//放到一个数组里，还要把20,30，... 到90的英文单词列出来放到另一个数组里，然后我们需要用些技巧，
//比如一个三位数n，百位数表示为n/100，后两位数一起表示为n%100，十位数表示为n%100/10，个位数表示为n%10，
//然后我们看后两位数是否小于20，小于的话直接从数组中取出单词，如果大于等于20的话，则分别将十位和个位数字的
//单词从两个数组中取出来。然后再来处理百位上的数字，还要记得加上Hundred。主函数中调用四次这个帮助函数，
//然后中间要插入"Thousand", "Million", "Billion"到对应的位置，最后check一下末尾是否有空格，
//把空格都删掉，返回的时候检查下输入是否为0，是的话要返回'Zero'
public class IntegertoEnglishWords_273 {
	public String numberToWords(int num) {
	       if(num == 0)return "Zero";
	       
	        String[] word3 = {"","Thousand ","Million ","Billion "};
	        
	        String reString = "";
	        //将num转成str，然后从末尾开始，每三位一截取
	        String num1 = num + "";
	        int count = 0;//记录当前截取次数，好决定是否需要在末尾加thousand,million,和billion
	        while(num1.length() >= 3) {
	        	String tempString = num1.substring(num1.length() -3);
	        	num1 = num1.substring(0,num1.length() -3);
	        	count++;//第一次截取不需要加
	            if("000".equals(tempString))continue;
	        	String t2 = threeDigitsNumToWords(Integer.parseInt(tempString)) + word3[count - 1];
	        	reString = t2 + reString;	
	        }
	        
	        //出循环时如果num1还有数字,则再处理一次
	        if(num1.length() > 0) {
	        	count++;
	        	String t2 = threeDigitsNumToWords(Integer.parseInt(num1)) + word3[count - 1];
	        	reString = t2 + reString;	
	        }
	        return reString.trim();
	    }

	public String threeDigitsNumToWords(int a) {
		String reString = "";
		if(a > 999)return reString;
		 String[] words1 = {//1-19,空一个让index和值一一对应
	     		"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", 
	     		"Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen ","Twenty "
	     		};
	     String[] words2 = {//20-90
	     		"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "
	     		};
	    
	    int x = a / 100;//百位数字
	    int y = a % 100;//十位和个位,要看是否大于20
	    int z = a % 10; //个位
	     
	     if(0 < a && a <= 20)return words1[a];
	    if(a < 100)return words2[a/10] + words1[a%10];
	    if(a >= 100){
	        if(y <= 20){
	            reString = words1[x] + "Hundred " + words1[y];
	        }else{
	            reString = words1[x] + "Hundred " + words2[y/10] + words1[z];
	        }
	        
	    }
		return reString;
		
	}
}
