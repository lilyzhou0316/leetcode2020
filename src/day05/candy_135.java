package day05;

/*
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:
Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies 
respectively.

Example 2:
Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies 
respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
 * */

//思路1:两遍遍历的解法，首先初始化每个人一个糖果，然后这个算法需要遍历两遍，第一遍从左向右遍历，
//如果右边的小盆友的等级高，等加一个糖果，这样保证了一个方向上高等级的糖果多。然后再从右向左遍历一遍，
//如果相邻两个左边的等级高，而左边的糖果又少的话，则左边糖果数为右边糖果数加一。
//最后再把所有小盆友的糖果数都加起来返回即可

//思路2:一遍遍历，一次遍历的方法，相比于遍历两次的思路简单明了，这种只遍历一次的解法就稍有些复杂了。
//首先我们给第一个同学一个糖果，那么对于接下来的一个同学就有三种情况：

//1. 接下来的同学的rating等于前一个同学，那么给接下来的同学一个糖果就行。

//2. 接下来的同学的rating大于前一个同学，那么给接下来的同学的糖果数要比前一个同学糖果数加1。

//3.接下来的同学的rating小于前一个同学，那么我们此时不知道应该给这个同学多少个糖果，需要看后面的情况。

//对于第三种情况，我们不确定要给几个，因为要是只给1个的话，那么有可能接下来还有rating更小的同学，
//总不能一个都不给吧。也不能直接给前一个同学的糖果数减1，有可能给多了，因为如果后面再没人了的话，
//其实只要给一个就行了。还有就是，如果后面好几个rating越来越小的同学，那么前一个同学的糖果数可能还得追加，
//以保证最后面的同学至少能有1个糖果。来一个例子吧，四个同学，他们的rating如下：

//1 3 2 1

//先给第一个rating为1的同学一个糖果，然后从第二个同学开始遍历，第二个同学rating为3，比1大，
//所以多给一个糖果，第二个同学得到两个糖果。下面第三个同学，他的rating为2，比前一个同学的rating小，
//如果我们此时给1个糖果的话，那么rating更小的第四个同学就得不到糖果了，所以我们要给第四个同学1个糖果，
//而给第三个同学2个糖果，此时要给第二个同学追加1个糖果，使其能够比第三个同学的糖果数多至少一个。
//那么我们就需要统计出多有个连着的同学的rating变小，用变量cnt来记录，找出了最后一个减小的同学，
//那么就可以往前推，每往前一个加一个糖果，这就是个等差数列，我们可以直接利用求和公式算出这些rating减小的
//同学的糖果之和。然后我们还要看第一个开始减小的同学的前一个同学需不需要追加糖果，只要比较cnt和pre的大小，
//pre是之前同学得到的最大糖果数，二者做差加1就是需要追加的糖果数，加到结果res中即可

public class candy_135 {
    public int candy(int[] ratings) {
        //解法1:
//    	int[] res = new int[ratings.length];//记录每个人应该有的糖果数
//    	for (int i = 0; i < res.length; i++) {
//			res[i] = 1;//初始化每个人至少都有一个糖果
//		}
//    	
//    	//从头往尾遍历，如果右边rating比左边大，让右边糖果比左边的多1
//    	for (int i = 0; i < ratings.length - 1; i++) {
//			if (ratings[i+1] > ratings[i]) {
//				res[i+1] = res[i] + 1;
//			}
//		}
//    	
//    	//从尾往头遍历，如果左边rating比右边大，让左边糖果比右边的多1
//    	for (int i = ratings.length - 1; i > 0 ; i--) {
//    		if (ratings[i-1] > ratings[i]) {
//				res[i-1] = Math.max(res[i - 1], res[i] + 1);
//			}
//		}
//    	
//    	//将糖果数量加起来即为答案
//    	int sum = 0;
//    	for (int i : res) {
//			sum += i;
//		}
//    	return sum;
    	
    	//解法2:
    	 int res = 1;
         int pre = 1;
         int des_num = 0;
         for (int i = 1; i < ratings.length; i++) {
             if (ratings[i] >= ratings[i - 1]) {
                 if (des_num > 0) {
                     res += ((1 + des_num) * des_num) / 2;
                     if (des_num >= pre) res += (des_num - pre + 1);
                     pre = 1;
                     des_num = 0;
                 }
                 pre = ratings[i - 1] == ratings[i] ? 1 : pre + 1;
                 res += pre;
             } else des_num++;
         }
         if (des_num > 0) {
             res += ((1 + des_num) * des_num) / 2;
             if (des_num >= pre) res += (des_num - pre + 1);
         }
         return res;

    }
}
