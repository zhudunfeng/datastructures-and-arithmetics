package com.adun.linkedList;

/**
 * 约瑟夫问题
 *
 * @author Zhu Dunfeng
 * @date 2022/1/31 0:14
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

class CircleSingleLinkedList {

    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建一个环形的链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;

        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                //构成环
                first.setNext(first);
                //让curBoy指向第一个小孩
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何一个小孩~~~");
            return;
        }

        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy currBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", currBoy.getNo());
            if (currBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            currBoy = currBoy.getNext();//curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 标识从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true){
            if(helper==first){//说明圈中只有一个节点
                break;
            }

            //让first和helper指针同时的移动countNum-1(m-1)
            for (int j = 0; j < countNum - 1; j++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点helper==first
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这是将first指向小孩节点出圈
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后刘转圈中的小孩编号%d\n",first.getNo());

    }
}


/**
 * 创建一个Boy,用于创建节点
 */
class Boy {

    //编号
    private int no;

    //指向下一个节点，默认是null
    private Boy next = null;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
