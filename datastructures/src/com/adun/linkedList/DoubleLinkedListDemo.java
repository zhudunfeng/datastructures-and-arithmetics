package com.adun.linkedList;

/**
 * @author Zhu Dunfeng
 * @date 2021/12/7 23:05
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.list();

        //修改数据
        System.out.println("======修改后的数据=======");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        //删除数据
//        System.out.println("======删除后的数据=======");
//        doubleLinkedList.del(3);
//        doubleLinkedList.show();
    }
}

//双向链表
class DoubleLinkedList {
    //头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加
    public void add(HeroNode2 heroNode2) {
        //定义头节点的分身指针
        HeroNode2 temp = head;
        //找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //添加新节点,跳出循环指针已经指向
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //按编号顺序添加
    public void addByOrder(HeroNode2 heroNode2) {
        //定义头节点的分身指针与是否找到的标识
        HeroNode2 temp = head;
        boolean flag = false;

        while (true) {
            //链表遍历完成，未找到放置的位置
            if (temp.next == null) {
                break;
            }

            if (temp.next.no > heroNode2.no) {
                break;
            } else if (temp.next.no == heroNode2.no) {
                //添加的编号已经存在，标识为不能添加
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heroNode2.no);
        } else {
            //插入链表中，temp的后面
            //与后面的节点相连
            heroNode2.next = temp.next;
            if( temp.next!=null){
                temp.next.pre=heroNode2;
            }
            //与前面的节点相连
            temp.next = heroNode2;
            heroNode2.pre=temp;

        }

    }

    //修改
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        //创建头节点的分身指针
        HeroNode2 tmp = head.next;
        //标识是否找到对接节点
        boolean flag = false;
        while (true) {
            //链表遍历完，未找到
            if (tmp == null) {
                break;
            }
            if (tmp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        //能找到
        if (flag) {
            tmp.name = newHeroNode.name;
            tmp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点\n", newHeroNode.no);
        }
    }

    //删除
    public void del(int no) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        //定义头节点分身指针与标识
        HeroNode2 tmp = head.next;
        boolean flag = false;
        while (true) {
            //链表遍历完成，未找到
            if (tmp.next == null) {
                break;
            }

            if (tmp.no == no) {
                flag = true;
                break;
            }
            //未找到，指针后移
            tmp = tmp.next;
        }

        if (flag) {
            tmp.pre.next = tmp.next;
            //如果是最后一个节点，不需要执行下面这句，否在会出现空指针
            if (tmp.next != null) {
                tmp.next.pre = tmp.pre;
            }
        } else {

        }
    }

    //遍历数据
    public void list() {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        //创建头节点的分身指针
        HeroNode2 tmp = head.next;
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}

class HeroNode2 {
    public int no;//编号
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一节点
    public HeroNode2 pre;//指向前一节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
