package modernjavainaction.chap07.test02;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = { "-Xms4G", "-Xmx4G" })
@Measurement(iterations = 2)
@Warmup(iterations = 3)
public class JmhTest {

    @Benchmark
    public long parallelSum(){
        return LongStream.rangeClosed(0, 100000000)
                .reduce(0L, Long::sum);
    }

    @TearDown(Level.Invocation)
    public void tearDown(){
        System.gc();
    }
}
