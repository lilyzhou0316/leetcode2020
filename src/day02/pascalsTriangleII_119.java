package day02;

import java.awt.List;
import java.util.ArrayList;

/*
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * 
 * Example:
 * Input: 3
   Output: [1,3,3,1]
 * 
 * 
 * Follow up:
 * Could you optimize your algorithm to use only O(k) extra space?
 * */

//思路与118一致

public class pascalsTriangleII_119 {
	 public List<Integer> getRow(int rowIndex) {
		 List<List<Integer>> result=new ArrayList<>();
			
			ArrayList<Integer> firstRowList = new ArrayList<>();
			firstRowList.add(1);
			result.add(firstRowList);
			
			if (rowIndex == 0) {
				return result.get(rowIndex); 
			}
			
			ArrayList<Integer> secondRowList = new ArrayList<>();
			secondRowList.add(1);
			secondRowList.add(1);
			result.add(secondRowList);
			
			if (rowIndex == 1) {
				return result.get(rowIndex); 
			}
			
			int count = 1;
			int i = 1;
			while (count < rowIndex+1) {
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
		    return result.get(rowIndex); 
	    }

}
