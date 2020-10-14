package day41;

/*
 * You are a product manager and currently leading a team to develop a new product. 
 * Unfortunately, the latest version of your product fails the quality check. Since each 
 * version is developed based on the previous version, all the versions after a bad version 
 * are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which returns whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of 
calls to the API.

 

Example 1:

Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.


Example 2:

Input: n = 1, bad = 1
Output: 1
 

Constraints:

1 <= bad <= n <= 231 - 1
 * */

/* The isBadVersion API is defined in the parent class VersionControl.
boolean isBadVersion(int version); */
public class FirstBadVersion_278 {
	 public int firstBadVersion(int n) {
		 //思路：二分法查找目标值
		 int l = 1;
		 int r = n;
		 while(l <= r) {
			 int mid = l + (r - l) / 2;
			 if(isBadVersion(mid)) {
				 //当前mid对应的版本及其之后的版本都是坏的，但它之前可能还有坏的版本，需要检查前半部分
				 r = mid - 1;
			 }else {
				 //当前mid对应的版本是好的，检查它之后的版本
				 l = mid + 1;
			 }
		 }
		 return r + 1;
	 }
}
