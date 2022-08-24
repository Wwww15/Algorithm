package queue;

import java.util.Scanner;

/**
 * 数据结构：银行排队（队列）
 * 特点：
 *  1.先进先出
 *  2.可以用数组或者是链表实现
 *  3.队尾加数据
 *  4.队首取数据
 */
public class QueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            switch (scanner.next()) {
                case "A":
                    System.out.println("请输入一个数：");
                    int addNum = scanner.nextInt();
                    arrayQueue.addQueue(addNum);
                    break;
                case "S":
                    arrayQueue.showQueue();
                    break;
                case "G":
                    int getNum = arrayQueue.getQueue();
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
 * 数组模拟队列（不可复用）
 * 1.注意当前队列的rear和front的初始值
 * 2.rear初始值为0,则队列满的时候,rear的当前值不对
 *
 */
class  ArrayQueue {

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

    public ArrayQueue(int arrMaxSize) {
        this.arr = new int[arrMaxSize];
        this.maxSize = arrMaxSize;
    }

    /**
     * 是否满
     * @return
     */
    public boolean isFull() {
        return rear == this.maxSize;
    }

    /**
     * 添加
     */
    public void addQueue(int number) {
        if(isFull()) {
            throw new RuntimeException("当前队列已满！");
        }
        arr[rear] = number;
        rear++;
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
        front++;
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
        for (int i = front; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

}
