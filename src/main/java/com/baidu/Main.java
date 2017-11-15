package com.baidu;

import java.util.concurrent.ForkJoinPool;

public class Main {

//关键代码是fjp.invoke(task)来提交一个Fork/Join任务并发执行，然后获得异步执行的结果。
    public static void main(String[] args) {
	// write your code here
    long[] array = new long[400];
    for(int i=0;i<array.length;i++){
        int temp =  (int)(Math.random()*10)+1;//随机产生一个 1~10 的整数
        array[i] = temp;//将产生的数添加到数组
    }
    ForkJoinPool  forkJoinPool = new ForkJoinPool(4);// 最大并发数4
    SumTask sumTask = new SumTask(array,0,array.length-1);
    long startTime = System.currentTimeMillis();
    Long result = forkJoinPool.invoke(sumTask);
    long endTime = System.currentTimeMillis();
    System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");

    }
}
