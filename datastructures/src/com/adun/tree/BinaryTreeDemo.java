package com.adun.tree;

/**
 * @author Zhu Dunfeng
 * @date 2022/7/16 20:58
 * <p>
 * 排序二叉树的前中后遍历
 * 排序二叉树的前中后遍历查找
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //先创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面递归创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        //测试 前、中、后序遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();


        //测试 前、中、后序查找
        int no = 5;
        System.out.println("\n\n\n\n前序查找");
        HeroNode resNode = binaryTree.preOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为 %s\n", resNode.toString());
        } else {
            System.out.printf("没有找到编号 %d 的英雄\n", no);
        }

        System.out.println("\n中序查找");
        resNode = binaryTree.infixOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为 %s\n", resNode.toString());
        } else {
            System.out.printf("没有找到编号 %d 的英雄\n", no);
        }

        System.out.println("\n后序查找");
        resNode = binaryTree.postOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为 %s\n", resNode.toString());
        } else {
            System.out.printf("没有找到编号 %d 的英雄\n", no);
        }


        //测试删除节点
        System.out.println("删除前。前序遍历");
        binaryTree.preOrder();
//        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
        User user = new BinaryTreeDemo().new User();

    }

    class User {

    }
}

/**
 * 树
 */
class BinaryTree {
    //根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

        }else {
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
