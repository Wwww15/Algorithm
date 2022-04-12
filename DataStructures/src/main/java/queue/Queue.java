package queue;

import java.util.Scanner;

/**
 * 银行排队（队列）
 * 特点：
 *  1.先进先出
 *  2.可以用数组或者是链表实现
 *  3.队尾加数据
 *  4.队首取数据
 */
public class Queue {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            switch (scanner.next()) {
                case "A":
                    break;
            }
        }
    }
}

/**
 * 数组模拟队列（不可复用）
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
        return rear == this.maxSize-1;
    }

    /**
     * 添加
     */
    public void addQueue(int number) {
        if(isFull()) {
            System.out.println("当前队列已满！");
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
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d",i,arr[i]);
        }
    }

}
//数组模拟队列
//1.队首
//2.队尾
//3.数组长度
//4.取数据
//5.放数据
