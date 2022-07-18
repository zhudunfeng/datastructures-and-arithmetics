package com.adun.hashtable;

import java.util.Scanner;

/**
 * @author ADun
 * @date 2022/7/17 20:47
 * <p>
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),
 * 当输入该员工的id要求查找到该员工的所有信息.
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:查找雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.delEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
            System.out.println();
        }

    }
}

/**
 * 创建HashTab,管理多条链表
 */
class HashTab {
    //存放链表的数组
    private EmpLinkedList[] empLinkedListArray;

    //表示有多少条链表
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        this.empLinkedListArray = new EmpLinkedList[size];
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            this.empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id ,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);

        //将emp添加到对应的链表中
        this.empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表，遍历hashtab
    public void list() {
        for (int i = 0; i < this.size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public void findEmpById(int id) {
        //使用散列函数确定在哪条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            //找到
            System.out.printf("在第%d条链表中找到雇员%s\n", empLinkedListNO + 1, emp.toString());
        } else {
            System.out.println("哈希表中不存在此雇员");
        }
    }

    //根据id删除雇员
    public void delEmpById(int id) {
        //使用散列函数确定在哪条链表
        int empLinkedListNO = hashFun(id);
        this.empLinkedListArray[empLinkedListNO].delEmpById(id);

    }

    /**
     * 编写散列函数，使用一个简单的取模法
     *
     * @param id
     * @return
     */
    private int hashFun(int id) {
        return id % size;
    }
}

/**
 * 创建EmpLinkedList，表示链表
 */
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head直接指向第一个Emp
    //默认为null
    private Emp head;

    //添加雇员到链表
    //说明
    //1.假定，当添加雇员时，id是自增长，即id的分配总是从小到大
    // 因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp currEmp = head;
        while (true) {
            //找到链表尾部
            if (currEmp.next == null) {
                break;
            }
            //分身指针后移
            currEmp = currEmp.next;
        }
        //退出时直接将emp加入链表中
        currEmp.next = emp;

    }

    //遍历链表的雇员信息
    public void list(int no) {
        //链表为空
        if (head == null) {
            System.out.printf("第%d链表为空\n", no + 1);
            return;
        }
        System.out.printf("第%d链表的信息为", no + 1);
        //辅助指针
        Emp currEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", currEmp.id, currEmp.name);
            if (currEmp.next == null) {
                //说明currEmp已经是最后节点
                break;
            }
            //后移，遍历链表
            currEmp = currEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断列表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        //辅助指针
        Emp currEmp = head;
        while (true) {
            if (currEmp.id == id) {
                //找到了
                //此时的curEmp就指向要查找的雇员
                break;
            }

            //退出
            if (currEmp.next == null) {
                //说明遍历当前链表没有找到该雇员
                currEmp = null;
                break;
            }
            //后移
            currEmp = currEmp.next;
        }
        return currEmp;
    }

    //根据id删除列表中对应的雇员
    //单向链表，需要找到删除的前一个节点
    public void delEmpById(int id) {
        //判断列表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        //头节点特殊处理，如果头节点是要删除节点，则将第二个节点设置为头节点
        if (head.id == id) {
            head = head.next;
            return;
        }

        //辅助指针
        Emp currEmp = head;

        while (true) {
            //不是头节点，找到删除节点的前一节点
            if (currEmp.next != null && currEmp.next.id == id) {
                currEmp.next = currEmp.next.next;
                break;
            }

            if (currEmp.next == null) {
                //说明遍历当前链表没有找到该雇员
                break;
            }
            //后移
            currEmp = currEmp.next;

        }

    }
}

/**
 * 表示一个雇员
 */
class Emp {
    public int id;
    public String name;
    //默认为null
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
