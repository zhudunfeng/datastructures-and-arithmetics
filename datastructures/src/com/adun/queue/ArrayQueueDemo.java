package com.adun.queue;

import java.util.Scanner;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/30 17:15
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
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
 * 使用数组模拟队列--编写一个ArrayQueue类
 */
class ArrayQueue {
    //数组最大容量
    private int maxSize;
    //队列头指针
    private int front;
    //队列尾指针
    private int rear;
    //数据存放数组，模拟队列
    private int[] arr;

    //创建队列构造器
    public ArrayQueue(int arrMaxSize) {
        //队列初始化
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        //front执行队列头部，根据队列示意图分析出指向队列头的前一位置
        //rear执行队列尾，根据队列示意图分析出指向队列尾的数据（即就是队列最后一个数据）
        //两值相等，队列为空
        front = rear = -1;
    }


    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到对列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据~~~");
            return;
        }
        //队尾指针后移，尾部放入数据
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取出数据");
        }
        //队头指针后移，取出头部数据
        front++;
        return arr[front];
    }

    //显示队列中所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空，没有数据~~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头部数据，注意，不是取数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~~");
        }
        //队头指针始终指向当前队列头部的前一位置
        return arr[front+1];
    }

}
