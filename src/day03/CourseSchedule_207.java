import java.util.*;
/*
整体思路：
1. 找到不需要任何先修课的课程
2. 把上述课程先学习
3. 以此学习以修课程的进阶课程
4. 在1.2.步骤进行的同时，在总课程数的基础上递减
5. 判断最后的课程总数是否为0

具体实现：(伪代码)
Step1. 找到不需要任何先修课的课程先学习：
    data: 用int[] numOfPre 来记录每一门课所需要的先修课个数
    impl:
        1.1 建立int[] numOfPre, 长度= numCourse, index: 课程编码
        1.2 for(int[] requirement: prerequisites) numOfPre[requirement[0]]++
        1.3 建立queue, 把numOfPre 为0 的课先放到队列里
Step2. 把上述课程先学习
    impl:
        2.1 pre = queue.poll()
        2.2 numCourse--
Step3. 以此学习以修课程的进阶课程（条件：此时的进阶课程不能有其他的先修课）
    data: 用ArrayList<ArrayList<>> requirements 存每一个course 对应的后续课程
          例如：prerequisites:[0,1],[1,2],[0,2]
                requirements:
                    index(course) 1: {0}
                    index(course) 2: {1,0}
                    index(course) 0: null
    impl:
        3.1 建立requirements: 遍历整个prerequisites，requirements(get(requirement[1])).add(requirement[0])
        3.2 找到某一个先修课之后“马上可以上的”后续课程
            3.2.1 为了记录每个课在每个时间点最近的先修课要求：在执行Step2之后要把其对应的后续课程的把numOfPre--
            3.2.2 找到后续课程中numOfPre 为0 的课程入队
step4. BFS 进行step2 和 step3， 直到队列为空
step5. return numCourse==0
*/


public class CourseSchedule_207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length ==0) return true;
        // set up data structures
        int[] numOfPre = new int[numCourses];
        List<ArrayList<Integer>> requirements = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        // for loop numCourses to fill set up a empty requirements
        for(int i =0;i<numCourses;i++){
            requirements.add(new ArrayList<>());
        }
        // for loop prerequisites to fill the above data structures
        for(int[] prerequisite: prerequisites){
            // fill numOfPre
            numOfPre[prerequisite[0]]++;
            // fill requirements
            requirements.get(prerequisite[1]).add(prerequisite[0]);
        }
        // add the courses with 0 numOfPre to queue;
        for(int course=0;course<numCourses;course++){
            if(numOfPre[course]==0) queue.add(course);
        }
        //BFS
        while(!queue.isEmpty()){
            // study the course of the head of queue
            Integer preCourse = queue.poll();
            // mark the number of courses left
            numCourses--;
            // make the the numOfPre of the upper courses 1 less
            for(int course:requirements.get(preCourse)){
                numOfPre[course]--;
                if(numOfPre[course] ==0) queue.add(course);
            }
        }
        return numCourses==0;
    }



    public static void main(String[] args) {
        CourseSchedule_207 test = new CourseSchedule_207();
        int[][] prerequisites = {{1,0},{0,2},{2,0}};
        System.out.println(test.canFinish(3,prerequisites));
    }
}
