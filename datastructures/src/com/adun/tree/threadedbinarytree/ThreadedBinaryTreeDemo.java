package com.adun.tree.threadedbinarytree;

/**
 * @author ADun
 * @date 2022/7/19 15:12
 * <p>
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //当线索化二叉树后，能在使用原来的遍历方法
//        System.out.println("使用线索化的前序方式遍历 线索化二叉树");
//        threadedBinaryTree.preOrderThreadedList(); // 1,3,8,10,6,4


        infixOrderTest(root, node5);

    }


    public static void infixOrderTest(HeroNode root,HeroNode node5){
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.infixOrderThreadedNodes();

        //测试：以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是=" + leftNode);
        System.out.println("10号节点的后继节点是=" + rightNode);




        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的中序方式遍历 线索化二叉树");
        threadedBinaryTree.infixOrderThreadedList(); // 8, 3, 10, 1, 14, 6
    }
}

/**
 * 定义ThreadedBinaryTree 实现了线索化功能的二叉树
 */
class ThreadedBinaryTree {
    //根节点
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //中序遍历线索化二叉树的方法
    public void infixOrderThreadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            //一直查询左子树节点，直到找到leftType == 1 的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前这个节点
            System.out.println(node);

            //如果当前节点右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的节点,推进
            node = node.getRight();
        }
    }




    //重载
    public void infixOrderThreadedNodes() {
        this.infixOrderThreadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法

    /**
     * @param node 就是当前需要线索化的结点
     */
    public void infixOrderThreadedNodes(HeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }

        //（一）先线索化左子树
        infixOrderThreadedNodes(node.getLeft());

        //（二）线索化当前节点[有难度]

        //处理当前结点的前驱结点
        //以8结点来理解
        //8结点的.left = null , 8结点的.leftType = 1
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //（三）再线索化右子树
        infixOrderThreadedNodes(node.getRight());
    }

    //调用前序遍历
    public void preOrder() {
        //根节点不为null,进行调用
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //调用中序遍历
    public void infixOrder() {
        //根节点不为null,进行调用
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //调用后序遍历
    public void postOrder() {
        //根节点不为null,进行调用
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //调用前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //调用中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //调用后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //调用删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，这里了解判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }

        } else {
            System.out.println("空树，不能删除~");
        }
    }
}

/**
 * 节点
 */
class HeroNode {
    private int no;
    private String name;
    //左指针 默认为null
    private HeroNode left;
    //右指针 默认为null
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        super();
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 前序遍历
     *
     * @return
     */
    public void preOrder() {
        //打印当前节点
        System.out.println(this);
        //如果左子树不为空，递归调用左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //如果右子树不为空，递归调用右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public void infixOrder() {
        //如果左子树不为空，递归调用左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        //打印当前节点
        System.out.println(this);
        //如果右子树不为空，递归调用右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public void postOrder() {
        //如果左子树不为空，递归调用左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //如果右子树不为空，递归调用右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        //打印当前节点
        System.out.println(this);
    }


    private static int preNum = 0;

    /**
     * 前序查找
     *
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        preNum += 1;
        //与当前节点进行比较
        if (this.no == no) {
            System.out.printf("前序需要查找 %d 次\n", preNum);
            return this;
        }

        HeroNode resNode = null;
        //如果左子树不为空，递归查找左子树,找到则返回
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            //说明我们在左子树找到了
            return resNode;
        }
        //如果右子树不为空，递归查找左子树
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    private static int infixNum = 0;

    /**
     * 中序查找
     *
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        //如果左子树不为空，递归查找左子树,找到则返回
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        infixNum++;
        //与当前节点进行比较
        if (this.no == no) {
            System.out.printf("中序需要查找 %d 次\n", infixNum);
            return this;
        }

        //如果右子树不为空，递归查找左子树
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;

    }


    private static int postNum = 0;

    /**
     * 后序查找
     *
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        //如果左子树不为空，递归查找左子树,找到则返回
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        //如果右子树不为空，递归查找左子树
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        postNum++;
        //与当前节点进行比较
        if (this.no == no) {
            System.out.printf("后序需要查找 %d 次\n", postNum);
            return this;
        }

        //都没找到返回null
        return null;

    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    // 2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        //思路
        /*

         1.因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，
         而不能去判断当前这个结点是不是需要删除结点.
         2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，
         就将this.left= null;并且就返回(结束递归删除)
         3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，
         就将this.right=null ;并且就返回(结束递归删除)
         4．如果第⒉和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         5.如果第4步也没有删除结点，则应当向右子树进行递归删除.

         */

        //2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，
        // 就将this.left=null;并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，
        // 就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }


    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}





