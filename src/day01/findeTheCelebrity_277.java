package day01;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them,
there may exist one celebrity. The definition of a celebrity is that
 all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. 
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?"
 to get information of whether A knows B. You need to find out the celebrity
  (or verify there is not one) by asking as few questions as possible (in the asymptotic sense渐进感).

You are given a helper function bool knows(a, b)which tells you whether A knows B. 
Implement a function int findCelebrity(n). 
There will be exactly one celebrity if he/she is in the party. 
Return the celebrity's label if there is a celebrity in the party. 
If there is no celebrity, return -1.


Example1 ：Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. 
graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means 
person i does not know person j. The celebrity is the person labeled as 1 because 
both 0 and 2 know him but 1 does not know anybody.

Example 2 ：
Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.

Note:

The directed graph is represented as an adjacency matrix, 
which is an n x n matrix where a[i][j] = 1 means person i knows person j 
while a[i][j] = 0 means the contrary.
Remember that you won't have direct access to the adjacency matrix.

 * */

//思路1:暴力求解
//给每个人一个记录是否是名人的flag（一维数组）,初始值为true,对每个人询问他是否认识剩下的n-1个人，
//一旦出现一个认识的（或者有一个人不认识他的），则那个人不是名人，flag = false，最后如果有人的flag=true则有celebrity


//思路2:设定候选人 res 为0，原理是先遍历一遍，对于遍历到的人i，
//若候选人 res 认识i，则将候选人 res 设为i，完成一遍遍历后，对于当前res,至少他不认识他号码之后的所有人，
//然后再来一遍遍历检测候选人 res 是否真正是名人，
//如果判断不是名人，则返回 -1，如果并没有冲突，返回 res


//思路3:还可以进一步减少 API 的调用量，找候选者的方法跟上面相同，但是在验证的时候，分为两段，
//先验证候选者前面的所有人，若候选者认识任何人，或者任何人不认识候选者，直接返回 -1。
//再验证候选者后面的人，这时候只需要验证是否有人不认识候选者就可以了，
//因为在最开始找候选者的时候就已经保证了候选者不会认识后面的任何人



public class findeTheCelebrity_277 {
	public int findeTheCelebrity(int n) {
		//解法1:
//		boolean[] flag;
//		for (int i = 0; i < n; i++) {//给每个人一个记录是否是名人的flag
//			flag[i] = true; 
//		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				if(flag[i] && i!=j) {//当i!=j时即看i和除了自己的其他人是否认识
//					if(knows(i,j) || !knows(j,i)) {//一旦出现i认识j的（或者j不认识i），则i不是名人
//						flag[i] = false;
//						break;//对于当前i不用在往下看了，进入下一个循环
//					} else  {//反过来，如果i不认识j,或者j认识i，j一定不是名人
//						flag[j] = false;
//					}
//					
//				}
//			}
//			//如果对除i外的剩下的n-1人询问后，flag[i] = true，则i是名人
//			if(flag[i]) return i;
//		}
//		//如果遍历完所有人都没有，返回-1
//		return -1;
		
		
		//解法2:
//		int res = 0;
//		for (int i = 0; i < n; i++) {//找出当前最接近是名人的人，选出的人一定满足他不认识他号码之后的所有人
//			if(knows(res,i)) res = i;
//		}
//		
//		for (int i = 0; i < n; i++) {//再对目前候选人进行一遍遍历，确定他是否是名人
//			if(res != i && (knows(res,i) || !knows(i,res)) return -1;
//		}
//		return res;
		
		
		//解法3:
		int res = 0;
		for (int i = 0; i < n; i++) {//找出当前最接近是名人的人，选出的人一定满足他不认识他号码之后的所有人
			if(knows(res,i)) res = i;
		}
		
		for(int i = 0;i<res;i++) {//检查res是否认识他号码之前的人
			if(knows(res,i)) return -1;
		}
		for(int i=0;i<n;i++) {//检查是否有人不认识res
			if(!knows(i,res)) return -1;
		}
		return res;
	}
	
	
	

}
