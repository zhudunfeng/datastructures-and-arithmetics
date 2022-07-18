package com.adun.tree;

/**
 * @author ADun
 * @date 2022/7/18 22:15
 * <p>
 * <p>
 * 顺序存储二叉树案例
 * 重要知识：
 * 1)顺序二叉树通常只考虑完全二叉树
 * 2)第n个元素的左子节点为2*n+1
 * 3)第n个元素的右子节点为2*n+2
 * 4)第n个元素的父节点为 |(n-1)/ 2| =>取整数
 * 5) n:表示二叉树中的第几个元素(按О开始编号如图所示) 也就是索引
 * <p>
 * 前中后序遍历
 * <p>
 * 后面的堆排序中会有涉及
 */
public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //顺序存储二叉树前序遍历 1,2,4,5,3,6,7
        arrayBinaryTree.preOrder();
        System.out.println("=======================");
        //顺序存储二叉树中序遍历 4,2,5,1,6,3,7
        arrayBinaryTree.infixOrder();
        System.out.println("=======================");

        //顺序存储二叉树后序遍历 4,5,2,6,7,3,1
        arrayBinaryTree.postOrder();
        System.out.println("=======================");
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree {
    //存储数据节点的数组
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //输出当前这个元素
        System.out.println(arr[index]);

        //向左递归遍历 左子树
        if ((2 * index + 1) < arr.length) {
            this.preOrder(2 * index + 1);
        }
        //向右递归遍历 右子树
        if ((2 * index + 2) < arr.length) {
            this.preOrder(2 * index + 2);
        }
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }

        //向左递归遍历 左子树
        if ((2 * index + 1) < arr.length) {
            this.infixOrder(2 * index + 1);
        }

        //输出当前这个元素
        System.out.println(arr[index]);

        //向右递归遍历 右子树
        if ((2 * index + 2) < arr.length) {
            this.infixOrder(2 * index + 2);
        }
    }


    //重载infixOrder
    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }

        //向左递归遍历 左子树
        if ((2 * index + 1) < arr.length) {
            this.postOrder(2 * index + 1);
        }


        //向右递归遍历 右子树
        if ((2 * index + 2) < arr.length) {
            this.postOrder(2 * index + 2);
        }

        //输出当前这个元素
        System.out.println(arr[index]);

    }
}


