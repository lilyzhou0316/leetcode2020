package day19;

import java.util.ArrayList;
import java.util.List;

/*
 * Compare two version numbers version1 and version2.比较版本号
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
从左往右比较每一级版本号，在该级上数字越大则版本号越大，如果省略则默认数字是0，同时前面的0可省略
You may assume that the version strings are non-empty and contain only digits and the . character.

The . character does not represent a decimal point and is used to separate number sequences.

For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level 
revision of the second first-level revision.

You may assume the default revision number for each level of a version number to be 0. For example, 
version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. 
Its third and fourth level revision number are both 0.

 

Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1


Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1


Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1


Example 4:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”


Example 5:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: The first version number does not have a third level revision number, which means
 its third level revision number is default to "0"
 

Note:

Version strings are composed of numeric strings separated by dots . and this numeric strings may
 have leading zeroes.
Version strings do not start or end with dots, and they will not be two consecutive dots.
 * */

//思路：将str以 . 分割成数组，比较相同位置上的数字大小，如果两个数组的长度不一致，则给短的那个补上0，使长度一致
public class CompareVersionNumbers_165 {
	 public int compareVersion(String version1, String version2) {
	       String[] s1 = version1.split("\\.");
	       String[] s2 = version2.split("\\.");
	      
	       int res = 0;
	       int len1, len2;
	       len1 = Math.min(s1.length, s2.length);
	       len2 = Math.max(s1.length, s2.length);
	     //i在两个数组的长度范围内，则直接比较两者数字大小
	       int i;
	       for ( i= 0; i < len1; i++) { 
	    	   System.out.print(s1[i]+ " ");
	    	   System.out.print(s2[i] + " ");
	    	   System.out.println();
	    	   
			if(Integer.parseInt(s1[i]) < Integer.parseInt(s2[i])) {
				return -1;
			}else if(Integer.parseInt(s1[i]) > Integer.parseInt(s2[i])) {
				return 1;
			}else{
				continue;
			}	
		
		}
	       //出循环时已经遍历完了短的数组,且之前的数字都相等
	     //i在超出了短的那个数组的长度，则比较剩余的数组的数字和0的大小
	      for ( i = len1; i < len2; i++) {
			if(s1.length > s2.length && Integer.parseInt(s1[i]) > 0)return 1;
			else if(s1.length > s2.length && Integer.parseInt(s1[i]) == 0)continue;
			else if(s2.length > s1.length && Integer.parseInt(s2[i]) > 0)return -1;
			else if(s2.length > s1.length && Integer.parseInt(s2[i]) == 0)continue;
		}
	       return 0;
	       
	    }
	 public static void main(String[] args) {
		 CompareVersionNumbers_165 a = new CompareVersionNumbers_165();
		
		 System.out.println( a.compareVersion("0.1","1.0"));
	}
}
