package com.adun.queue;

import java.util.Scanner;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/30 17:15
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
class CircleArrayQueue {
    //数组最大容量
    private int maxSize;
    //队列头指针
    private int front;
    //队列尾指针
    private int rear;
    //数据存放数组，模拟队列
    private int[] arr;

    //创建队列构造器
    public CircleArrayQueue(int arrMaxSize) {
        //队列初始化
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
    }


    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取出数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列中所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空，没有数据~~~");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        //动脑筋
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数


    public int size() {
        //rear=2
        //front=1
        //maxSize=3
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头部数据，注意，不是取数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~~~");
        }
        //队头指针始终指向当前队列头部
        return arr[front];
    }

}
