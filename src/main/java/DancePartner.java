import com.sun.org.apache.regexp.internal.REUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName DancePartner
 * @Author ChangLu
 * @Date 2021/3/25 15:47
 * @Description TODO
 */
class Dancer{
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

class ArrQueue{
    private Dancer[] queue;
    private int front;
    private int rear;
    public int count;

    public ArrQueue(int queueSize) {
        queue = new Dancer[queueSize];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public boolean queueEmpty(){
        return count == 0;
    }

    public boolean queueFull(){
        return  (count>0&&rear == front);
    }

    //入队操作
    public void enQueue(Dancer e){
        if(queueFull()){
            System.out.println("Queue overflow!");
        }else{
            queue[rear] = e;
            rear = (rear + 1)%queue.length;
            count++;
        }
    }

    //出队操作
    public Dancer deQueue(){
        Dancer dancer = null;
        if(queueEmpty()){
            System.out.println("Queue empty!");
        }else{
            dancer = queue[front];
            front = (front+1)%queue.length;
            count--;
        }
        return dancer;
    }

    //取出队头
    public Dancer queueFront(){
        //判断是否为空
        if(queueEmpty()){
            System.out.println("Queue full!");
            return null;
        }
        return queue[front];
    }
}

public class DancePartner {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入男舞者的人数：");
        int mNum = Integer.parseInt(br.readLine());
        System.out.println("请输入女舞者的人数：");
        int fNum = Integer.parseInt(br.readLine());
        int num = fNum + mNum;
        //放入所有舞者的数组
        Dancer dancer[] = new Dancer[num];
        for (int i = 0; i < num; i++) {
            dancer[i] = new Dancer();
            System.out.println("请输入舞者的姓名:");
            String name = br.readLine();
            dancer[i].setName(name);
            System.out.println("请输入舞者的性别");
            String sex = br.readLine();
            dancer[i].setSex(sex);
        }
        //创建男、女队列
        ArrQueue mDancers = new ArrQueue(mNum);
        ArrQueue fDancers = new ArrQueue(fNum);
        for (int i = 0; i < num; i++) {
            if(dancer[i].getSex().equals("男")){
                mDancers.enQueue(dancer[i]);
            }else{
                fDancers.enQueue(dancer[i]);
            }
        }
        System.out.println("舞伴配对情况为：\n");
        //进行配对
        while(!mDancers.queueEmpty() && !fDancers.queueEmpty()){
            //依次输出男女舞伴
            Dancer p;
            p = mDancers.deQueue();
            System.out.print(p.getName() + "\t");
            p = fDancers.deQueue();
            System.out.println(p.getName());
        }
        //输出留下来的男舞者、女舞者
        if(!fDancers.queueEmpty()){
            System.out.println("还有" + fDancers.count + "位女士等待下一轮");
            Dancer p;
            p = fDancers.queueFront();
            System.out.println(p.getName()+"在一轮中将首先获得舞伴");
        }
        if(!mDancers.queueEmpty()){
            System.out.println("还有" + mDancers.count + "位男士等待下一轮");
            Dancer p;
            p = mDancers.queueFront();
            System.out.println(p.getName()+"在一轮中将首先获得舞伴");
        }

    }
}
