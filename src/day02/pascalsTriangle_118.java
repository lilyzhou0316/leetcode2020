package day02;

import java.awt.List;
import java.util.ArrayList;

/*
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 5

Output:
[
     [1],
     [1,1],
     [1,2,1],
     [1,3,3,1],
     [1,4,6,4,1]
]
 * */


//思路:下一组数组的index为i的元素等于上一组数组的index为i-1和i这两个元素的和，
//如果下一数组的i等于0或者length-1，则i对应的元素值为1


public class pascalsTriangle_118 {
public List<List<Integer>> generate(int numRows) {
	
	List<List<Integer>> result=new ArrayList<>();
	if (numRows == 0) {
		return result;
	}
	ArrayList<Integer> firstRowList = new ArrayList<>();
	firstRowList.add(1);
	result.add(firstRowList);
	
	if (numRows == 1) {
		return result;
	}
	
	ArrayList<Integer> secondRowList = new ArrayList<>();
	secondRowList.add(1);
	secondRowList.add(1);
	result.add(secondRowList);
	
	if (numRows == 2) {
		return result;
	}
	
	int count = 2;
	int i = 1;
	while (count < numRows) {
		List<Integer> tempArrayList = result.get(i);//取出第二个list[1,1]
		ArrayList<Integer> temp = new ArrayList<Integer>();//创建第三个list
		temp.add(1);
		for (int j = 0; j < tempArrayList.size()-1; j++) {
			temp.add(tempArrayList.get(j)+tempArrayList.get(j+1));
		}
		temp.add(1);
		result.add(temp);
		count++;
		i++;
	}
        return result;
    }

}
