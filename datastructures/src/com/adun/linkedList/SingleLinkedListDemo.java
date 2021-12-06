package com.adun.linkedList;

/**
 * @author Zhu Dunfeng
 * @date 2021/12/3 14:37
 * <p>
 * 实现单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //小试牛刀
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //添加节点
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        //乱序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        //显示
        singleLinkedList.list();


        HeroNode heroNode = singleLinkedList.find(3);
        System.out.println("查找数据~~~");
        System.out.println(heroNode);

        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的数据~~~");
        singleLinkedList.list();

//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
        //singleLinkedList.del(5);
        System.out.println("删除后的数据~~~");
        singleLinkedList.list();

        System.out.println("有效链表节点数~~~");
        System.out.println(getLength(singleLinkedList.getHead()));

        System.out.println("倒数第一个节点数据~~");
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 2));

        //反转数据
        System.out.println("反转后的数据显示~~~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("testTwoLinked~~~~~");
        testTwoLinked();
    }

    public static void testTwoLinked(){
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.add(hero1);
        singleLinkedList1.add(hero4);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(hero2);
        singleLinkedList2.add(hero3);

        mergeTwoLinkList(singleLinkedList1.getHead(), singleLinkedList2.getHead());

        singleLinkedList1.list();

    }


    /**
     * 求单链表的有效节点数
     * （如果是带头节点的链表，需求不统计头节点）
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            //链表为空
            return 0;
        }
        int length = 0;
        //定义头节点的分身指针，进行移动操作
        HeroNode curr = head.next;
        while (curr != null) {
            length++;
            //后移进行遍历，直到链表最后
            curr = curr.next;
        }
        return length;
    }

    /**
     * 查找单链表中倒数第k个节点
     * <p>
     * 思路：
     * 1、编写一个方法，接收head节点，同时接收一个index
     * 2、index表示倒数第index个节点
     * 3、先把链表从头到尾遍历，得到链表的总长度getLength
     * 4、得到size后，我们从链表的第一个开始遍历到（size-index）
     * 5、如果找到了，则返回该节点，否在返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            //链表为空，不存在该节点
            return null;
        }

        //第一次遍历，获取链表的长度（节点个数（不包含头节点））
        int size = getLength(head);
        //校验输入的index
        if (index <= 0 || index > size) {
            //不在有效范围
            return null;
        }
        //定义辅助遍历
        HeroNode curr = head.next;//3
        //倒数第一个  3-1=2
        for (int i = 0; i < (size - index); i++) {
            curr = curr.next;
        }
        return curr;
    }


    /**
     * 单链表反转
     * 思路：
     * 1、先定义一个节点reverseHead=new HeroNode();
     * 2、从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
     * 3、原来的链表的head.next=reverseHead
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需进行反转操作
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助指针，用于遍历原来的链表
        HeroNode curr = head.next;
        HeroNode next = null;//指向当前节点[curr]的下一个节点，保存现场
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (curr != null) {
            //暂时保存当前节点的下一个节点
            next = curr.next;
            //将curr的下一个节点指向新的链表的最前端
            curr.next = reverseHead.next;
            //将curr连接当新的链表上
            reverseHead.next = curr;
            //让curr后移
            curr = next;
        }
        //将head.next指向reverseHead.next
        head.next = reverseHead.next;

    }


    /**
     * 合并两个有序的链表，合并后依然有序
     * 思路：与反转相似，需要新的链表
     */
    public static SingleLinkedList mergeTwoLinkList(HeroNode head1, HeroNode head2) {
        HeroNode prehead = new HeroNode(0, "", "");
        //定义一个辅助指针，用于遍历原来的链表
        HeroNode prev = prehead;
        //为两个链表的头节点创建分身指针
        HeroNode curr1 = head1.next;
        HeroNode curr2 = head2.next;
        while (curr1 != null && curr2 != null) {
            if (curr1.no < curr2.no) {
                prev.next = curr1;
                curr1 = curr1.next;
            } else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }

        //合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = curr1 == null ? curr2 : curr1;

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.head = prehead;
        return singleLinkedList;
    }

}

//链表类
//不考虑排名排序
class SingleLinkedList {
    //初始化头节点
    public HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点
    //不考虑编号时，找到最后一个节点，然后next指向添加的节点
    public void add(HeroNode heroNode) {
        //头节点不能动，需要临时指针用于移动
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到链表尾节点，指针后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向了链表的最后
        temp.next = heroNode;
    }

    //考虑编号添加对应的节点，如果添加的编号已经存在，则不进行添加并打印提示信息

    /**
     * ----------      |--------|
     * |        |--->  |        |
     * ----------      |--------|
     * <p>
     * |--------|       |--------|  next |--------|
     * |        | ----->|  add   |------>|        |
     * |--------|       |--------|       |--------|
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //头节点不能移动，需要临时指针进行遍历
        HeroNode temp = head;
        //flag标志添加的编号是否存在，默认false
        boolean flag = false;
        //找到当前添加节点的前一节点
        while (true) {
            //遍历到链尾
            if (temp.next == null) {
                break;
            }
            //中间添加
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                //添加的编号已经存在，标识为不能添加
                flag = true;
                break;
            }
            //如果没找到，进行指针后移
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            //插入链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //根据编号修改节点信息，no不可被修改
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }

        //找到对应的节点
        //头节点不可移动，需要辅助变量（临时指针）进行遍历
        HeroNode temp = head;
        //标识是否找到，默认为false
        boolean flag = false;
        while (true) {
            //已经变量完链表
            if (temp.next == null) {
                break;
            }

            //找到指定的编号
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }

            //上面指针没有找到，指针后移
            temp = temp.next;
        }

        //判断
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点\n", newHeroNode.no);
        }

    }

    //删除链表中指定的编号
    public void del(int no) {
        //头不能移动，需要辅助指针
        HeroNode temp = head;
        //标识是否找到对应节点(默认false)
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //遍历完链表
                break;
            }
            //找到要删除的节点的前一节点，temp.next指向当前节点
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            //上面没有找打，辅助指针后移
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("链表中不存在编号为%d的节点\n", no);
        }
    }

    //按编号查找对应的节点
    public HeroNode find(int no) {
        //头不能移动，需要辅助指针
        HeroNode temp = head;
        //标识是否找到对应节点(默认false)
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //遍历完链表
                break;
            }

            if (temp.no == no) {
                flag = true;
                break;
            }
            //上边没有找打，临时指针向后移动
            temp = temp.next;
        }
        if (flag) {
            return temp;
        } else {
            System.out.printf("链表中不存在no为%d的节点\n", no);
            return null;
        }
    }

    //遍历节点
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能移动，因此，我们需要一个临时的指针来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到达链表尾部
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，一定要小心，这里不写会造成死锁
            temp = temp.next;
        }
    }


}

//定义HeroNode，每一个HeroNode对象就是一个节点
class HeroNode {
    public int no;//编号
    public String name;
    public String nickName;
    public HeroNode next;//指向下一节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
