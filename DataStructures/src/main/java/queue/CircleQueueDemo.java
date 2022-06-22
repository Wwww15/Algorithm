package queue;

import java.util.Scanner;

/**
 * 数据结构：循环队列
 * 数组模拟循环队列
 * 特点：
 * 1.解决列队无法重复利用空间的问题，能循环放入数据
 * 2.实际就是求模的方式实现循环的动作
 */
public class CircleQueueDemo {

    public static void main(String[] args) {
        //初始化循环队列，虽然大小为3，但是有效数据最大为2
        ArrayCircleQueue arrayCircleQueue = new ArrayCircleQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            switch (scanner.next()) {
                case "A":
                    System.out.println("请输入一个数：");
                    int addNum = scanner.nextInt();
                    arrayCircleQueue.addQueue(addNum);
                    break;
                case "S":
                    arrayCircleQueue.showQueue();
                    break;
                case "G":
                    int getNum = arrayCircleQueue.getQueue();
                    System.out.printf("当前获取的数是：%d",getNum);
                    break;
                case "EXIT":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 数组模拟循环队列
 * 1.利用求模的方式实现循环的特征
 * 2.队列为空：rear = front
 * 3.队列已满：(rear+1)%maxSize = front
 * 4.数据个数：(rear+maxSize-front)%maxSize
 * 5.这里rear和front的初始值都为0
 * 6.最大容量为maxSize-1，最后一个空间为保留空间
 */
class ArrayCircleQueue {
    /**
     * 队列
     */
    private int[] arr;
    /**
     * 队首
     */
    private int front;
    /**
     * 队尾
     */
    private int rear;
    /**
     * 最大容量
     */
    private int maxSize;

    public ArrayCircleQueue(int arrMaxSize) {
        this.arr = new int[arrMaxSize];
        this.maxSize = arrMaxSize;
    }

    /**
     * 是否满
     * @return
     */
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    /**
     * 添加
     */
    public void addQueue(int number) {
        if(isFull()) {
            throw new RuntimeException("当前队列已满！");
        }
        arr[rear] = number;
        rear = (rear+1)% maxSize;
    }

    /**
     * 是否空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 获取数据
     */
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("当前队列为空！");
        }
        int result = arr[front];
        front = (front+1)% maxSize;
        return result;
    }

    /**
     * 获取队列首部
     */
    public int getHeader() {
        if(isEmpty()) {
            throw new RuntimeException("当前队列为空！");
        }
        int result = arr[front];
        return result;
    }

    /**
     * 展示queue
     */
    public void showQueue() {
        if(isEmpty()) {
            throw new RuntimeException("当前队列为空！");
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 获取当前有效的数据
     */
    public int size() {
        return (rear+maxSize-front)%maxSize;
    }
}
