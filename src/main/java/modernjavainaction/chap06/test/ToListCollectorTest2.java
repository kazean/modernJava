package modernjavainaction.chap06.test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class ToListCollectorTest2 implements Collector<Integer,List<Integer>, List<Integer>> {
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1,list2)->{
                list1.addAll(list2);
                return list1;
        };
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT,UNORDERED));
    }
}
