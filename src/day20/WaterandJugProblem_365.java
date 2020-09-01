package day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/*
 * You are given two jugs with capacities x and y litres(升). There is an infinite amount of water 
 * supply available. You need to determine whether it is possible to measure exactly z litres using 
 * these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both 
buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself 
is empty.

Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True

Example 2:

Input: x = 2, y = 6, z = 5
Output: False
 

Constraints:

0 <= x <= 10^6
0 <= y <= 10^6
0 <= z <= 10^6
 * */

//思路：bfs。这一类游戏相关的问题，用人脑去想，是很难穷尽所有的可能的情况的。因此很多时候需要用到「搜索算法」。
//「搜索算法」一般情况下是在「树」或者「图」结构上的「深度优先遍历」或者「广度优先遍历」。因此，在脑子里，
//更建议动手在纸上画出问题抽象出来的「树」或者「图」的样子。
//在「树」上的「深度优先遍历」就是「回溯算法」，在「图」上的「深度优先遍历」是「flood fill」 算法，深搜比较节约空间。
//这道题由于就是要找到一个符合题意的状态，我们用广搜就好了。这是因为广搜有个性质，一层一层像水波纹一样扩散，路径最短。
//所谓「状态」，就是指当前的任务进行到哪个阶段了，可以用变量来表示，怎么定义状态有的时候需要一定技巧，这道题不难。
//这里分别定义两个水壶为 A 和 B，定义有序整数对 (a, b) 表示当前 A 和 B 两个水壶的水量，它就是一个状态。
//题目说：
//你允许：
//装满任意一个水壶
//清空任意一个水壶
//从一个水壶向另外一个水壶倒水，直到装满或者倒空

//从外部装满任意一个水壶，定义为「操作一」，分为：
//（1）装满 A，包括 A 为空和 A 非空的时候把 A 倒满的情况；
//（2）装满 B，包括 B 为空和 B 非空的时候把 B 倒满的情况。

//清空任意一个水壶，定义为「操作二」，分为
//（1）清空 A；
//（2）清空 B。

//从一个水壶向另外一个水壶倒水，直到装满或者倒空，定义为「操作三」，其实根据描述「装满」或者「倒空」就知道可以分为 4 种情况：

//（1）从 A 到 B，使得 B 满，A 还有剩；
//（2）从 A 到 B，此时 A 的水太少，A 倒尽，B 没有满；
//（3）从 B 到 A，使得 A 满，B 还有剩余；
//（4）从 B 到 A，此时 B 的水太少，B 倒尽，A 没有满。

//因此，从当前「状态」最多可以进行 8 种操作，得到 8 个新「状态」，对这 8 个新「状态」，依然可以扩展，
//一直做下去，直到某一个状态满足题目要求。

//广度优先遍历常见的写法有 2 种，由于这里不用求路径最短的长度，在出队的时候不用读取队列的长度。

//从当前状态可以扩展出 8 种相邻的状态；
//因为状态有重复，因此是一个「有向」且「有环」的图，在遍历的时候，需要判断该结点设置是否访问过；
//有序整数对 (a, b) 可以自定义成一个私有的类；
//图的遍历，可以使用「深度优先遍历」和「广度优先遍历」，因为状态空间很大，广搜是相对较快；
//尽量「剪枝」，跳过不必要的搜索；
//当然最快的是数学方法。


public class WaterandJugProblem_365 {
public boolean canMeasureWater(int x, int y, int z) {
        if(z > x + y)return false;
       if(z == 0 || z == x || z == y)return true;
       
       //用q遍历根据当前状态可能产生的所有下一个状态，如果某个下一个状态已经访问过了，则跳过
       Queue<State> q = new LinkedList<WaterandJugProblem_365.State>();
       //用set保存已经访问过的下一个状态
       Set<State> visited = new HashSet<WaterandJugProblem_365.State>();
       
       State s0 = new State(0, 0);//初始水量都为0
       q.add(s0);
       visited.add(s0);
       
       while(!q.isEmpty()) {//当q不为空，则根据当前从q中取出的状态得到它可能的所有下一个状态，看是否能满足条件
    	   State head = q.poll();

           int curX = head.getX();
           int curY = head.getY();
    	   
    	   if(z == curX || z == curY || z == curX + curY)return true;//满足条件的情况
    	   
    	   //不满足条件，则看当前状态的下一个状态
    	   List<State> list = getStates(curX, curY, x, y);
    	   for (State state : list) {
			//如果下一个状态没有被访问过，则加入q，标记为访问过
    		   if(!visited.contains(state)) {
    			   q.add(state);
    			   visited.add(state);
    		   }//如果下一个状态被访问过，则跳过
		}
    	   
       }
       //如果遍历完了所有可能状态后，还是没有满足条件的状态，则说明不存在满足条件的结果
       return false;
    }

private class State {//state表示不同的状态下x,y容器分别装的当前水量
	private int a;
	private int b;
	public State(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int getX() {
		return this.a;
	}
	public int getY() {
		return this.b;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;//将传入对象强转成state对象，然后比较a,b是否相等，如果都相等则认为是equals
        return a == state.a &&
                b == state.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

}

public List<State> getStates(int curX, int curY, int x, int y) {//通过当前装水量和容量来遍历可能出现的状态
	List<State> list = new ArrayList<WaterandJugProblem_365.State>();
	//操作1:从外部装满任意一个水壶
	State s1 = new State(x, curY);//装满a
	State s2 = new State(curX, y);//装满b
	
	//操作2:清空任意一个水壶(此时水壶不知道是否是满的，但一定有水)
	State s3 = new State(0, curY);//清空a
	State s4 = new State(curX, 0);//清空b
	
	//操作3:从一个水壶向另外一个水壶倒水，直到装满或者倒空
	State s5 = new State(curX - (y - curY), y);//a向b倒，把b装满
	State s6 = new State(x, curY - (x - curX));//b向a倒，把a装满
	State s7 = new State(0, curY + curX);//a向b倒,但a倒完了也不能把b装满
	State s8 = new State(curX + curY, 0);//b向a倒,但b倒完了也不能把a装满
	
	//上述每个状态都必须满足一定的条件才能达到
	
	//操作1
	if(curX < x) {//只有a当前没装满时，才需要装满
		list.add(s1);
	}
	if(curY < y)list.add(s2);//同上
	
	//操作2
	if(curX > 0)list.add(s3);//a有水，才需要清空
	if(curY > 0)list.add(s4);//同上
	
	//操作3
	//a向b倒，把b装满,那么a的当前水量一定是大于等于b装满需要的水量的
	if(curX - (y - curY) >= 0)list.add(s5);
	//同上
	if(curY - (x - curX) >= 0)list.add(s6);
	//a向b倒,但a倒完了也不能把b装满,那么a+b的当前水量一定是小于b的容量的
	if(curX + curY < y)list.add(s7);
	//同上
	if(curX + curY < x)list.add(s8);
	
	return list;
}
}


