package com.adun.stack;
import java.util.Scanner;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/16 10:57
 */
public class LinkedStackDemo {

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈中取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
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
 * 双向链表（可以实现自我删除）
 */
class LinkedStack {

    //栈的大小
    private int maxSize;

    //表示栈顶，初始化-1,定义栈顶与栈尾指针
    private StackNode head = new StackNode(-1);


    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public StackNode getHead() {
        return head;
    }

    //栈空
    public boolean isEmpty() {
        return head.next==null;
    }

    //栈满
    public boolean isFull() {
        int sum = 0;
        //定义一个辅助指针
        StackNode curr = head.next;
        while (curr != null) {
            sum++;
            curr = curr.next;
        }
        return sum >= maxSize;
    }


    //push（入栈）
    public void push(int num) {
        StackNode stackNode = new StackNode(num);
        if (isFull()) {
            System.out.println("栈满~~~");
            return;
        }
        //定义头节点的分身指针
        StackNode temp = head;
        //找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //添加新节点,跳出循环指针已经指向
        temp.next = stackNode;
        stackNode.pre = temp;

    }

    //pop（出栈）
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空~~~~");
        }
        //定义头节点分身指针与标识
        StackNode temp = head.next;
        //找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.pre.next = temp.next;
        //如果是最后一个节点，不需要执行下面这句，否在会出现空指针
        if (temp.next != null) {
            temp.next.pre = temp.pre;
        }
        return temp.num;
    }

    //打印栈中数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //定义头节点分身指针与标识
        StackNode nextTemp = head.next;
        int sum=0;
        //找到最后
        while (nextTemp.next != null) {
            sum++;
            nextTemp = nextTemp.next;
        }

        //从链表尾部节点遍历到头节点
        while (nextTemp.pre!=null){
            System.out.printf("StackNode[%d]=%d\n",sum,nextTemp.num);
            sum--;
            nextTemp=nextTemp.pre;
        }

//        //定义头节点的分身指针
//        StackNode temp = head.next;
//        while (temp != null) {
//            System.out.println(temp.num);
//            //如果没有找到链表尾节点，指针后移
//            temp = temp.next;
//        }
    }


}


class StackNode {
    //当前节点数据
    public int num;

    //指向前一节点
    public StackNode pre;

    //指向下一节点
    public StackNode next;

    public StackNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "num=" + num +
                ", next=" + next +
                '}';
    }
}
