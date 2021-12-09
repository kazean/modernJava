package modernjavainaction.chap07.test02;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import static modernjavainaction.chap07.ParallelStreamsHarness.FORK_JOIN_POOL;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length<=THRESHOLD){
            return computeSequentailly();
        }

        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start+length/2);
        left.fork();
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start+length/2, end);
        Long rightVal = right.compute();
        Long leftVal = left.join();
        return leftVal+rightVal;
    }

    private long computeSequentailly(){
        long sum = 0;
        for(int i=start; i<end;i++){
            sum+=numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }
}
