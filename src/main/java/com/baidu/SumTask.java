package com.baidu;

import java.util.concurrent.RecursiveTask;

/**
 * User: chanson-pro
 * Date-Time: 2017-11-15 14:02
 * Description:对一个大数组进行求和
 */
public class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 100;
    long[] array;
    int start;
    int end;

    public SumTask(long[] array,int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        //如果任务足够小，直接计算
        long sum = 0 ;
        if(end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum +=array[i];
            }
        }else{
            //任务很大，需要分解开
            int middle = (start+end)/2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask subTask1 = new SumTask(array,start,middle);
            SumTask subTask2 = new SumTask(array,middle+1,end);
            // 执行子任务
           invokeAll(subTask1,subTask2);
            //等待任务执行结束合并其结果
            Long result1 = subTask1.join();
            Long result2 = subTask2.join();
            //合并子任务
            sum = result1 + result2;
            System.out.println("result = " + result1 + " + " + result2 + " ==> " + sum);
        }
        return sum;
    }
}
