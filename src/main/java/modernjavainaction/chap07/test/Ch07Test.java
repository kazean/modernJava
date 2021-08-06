package modernjavainaction.chap07.test;

import modernjavainaction.chap06.Dish;

import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.*;
public class Ch07Test {
    public static void main(String[] args) {
        Stream.of("a","b").collect(Collectors.reducing(null,null,null));
        Stream.of("a","b").collect(Collectors.reducing(null,null));
        Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));

        int a = Spliterator.ORDERED;

        List<String> list = List.of("a","b");

    }
}
