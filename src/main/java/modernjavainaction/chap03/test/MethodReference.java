package modernjavainaction.chap03.test;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class MethodReference {
    public static void main(String[] args) {
        ToIntFunction<String> stringToInt = Integer::parseInt;
        BiPredicate<List<String>,String> contains = List::contains;
//        Predicate<String> stratsWithNumber = this::



    }
}
