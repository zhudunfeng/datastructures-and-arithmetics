package com.adun.sparseArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.*;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/29 22:52
 * 压缩
 * 二维数组--------》稀疏数组
 * <p>
 * 解压
 * 稀疏数组---------》二维数组
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {
        //创建原始二维数组
        int[][] chessArray = new int[11][11];

        //放置非零值
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[3][4] = 2;

        //打印原始二维数组
        for (int[] row : chessArray) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        //转换为稀疏数组
        //创建稀疏数组int[sum+1][3]
        //sum是二维数组中的非零值的个数
        //获取sum
        int sum = 0;
        for (int[] row : chessArray) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        System.out.printf("sum=%d\n", sum);

        //根据sum创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //稀疏数组第1行保存的是原始二维数组的行、列、多少非零值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;

        //填充稀疏数组
        //记录第几个非零值
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    //保证count从1开始
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        //打印稀疏数组
        System.out.println();
        System.out.println("稀疏数组~~~~");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }


//        writeOutput(sparseArray);
//        int[][] o = (int[][]) readInput();
//
//        uncompressArray(sparseArray);

        outputWirter(sparseArray);
        int[][] ints = inputReader();
        uncompressArray(ints);


    }

    public static void uncompressArray(int[][] sparseArray) {
        //稀疏数组转二维数组（解压）
        //创建原始二维数组
        //稀疏数组的第0行，是原始数组的框架数据
        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //使用稀疏数组填充原始二维数组,稀疏数组从第一行开始
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //打印恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组~~~~~~~");
        for (int[] row : chessArray2) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }

    public static String filePath;

    static {
        filePath = System.getProperty("user.dir") + "\\datastructures\\resources\\map.data";
    }

    public static void outputWirter(int[][] sparseArray) throws IOException {
        String jsonString = JSON.toJSONString(sparseArray);
        OutputStream outputStream = null;
        Writer outputStreamWriter = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            outputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStreamWriter.close();
            outputStream.close();
        }
    }

    public static int[][] inputReader() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream fileInputStream = null;
        Reader reader = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            // 设置编码方式为UTF-8
            reader = new InputStreamReader(fileInputStream, "UTF-8");
            // 输出资源内容到相应对象
            int len;
            while ((len = reader.read()) != -1) {
                if (((char) len) != '\r') {
                    stringBuffer.append((char) len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
            fileInputStream.close();
        }
        return JSON.parseObject(stringBuffer.toString(), new TypeReference<int[][]>() {
        });
    }

    public static void writeOutput(Object o) throws IOException {
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            objectOutputStream.close();
            outputStream.close();
        }
    }

    public static Object readInput() throws IOException {
        Object object = null;
        InputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
            fileInputStream.close();
        }
        return object;
    }
}
