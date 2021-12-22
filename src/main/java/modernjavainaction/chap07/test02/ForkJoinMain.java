package modernjavainaction.chap07.test02;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;


public class ForkJoinMain {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 100L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        System.out.println(new ForkJoinPool().invoke(task));
    }
}
