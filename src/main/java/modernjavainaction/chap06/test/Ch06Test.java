package modernjavainaction.chap06.test;

import modernjavainaction.chap06.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;
public class Ch06Test {
    public static void main(String[] args) {
       /*
        Comparator<Dish> dishCaloriesComaparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = Dish.menu.stream().collect(maxBy(dishCaloriesComaparator));
        System.out.println("mostCalories >> " + mostCaloriesDish.get().getName() + ": " + mostCaloriesDish.get().getCalories());
        */


        //reduce
        Stream<Integer> boxedStream = IntStream.rangeClosed(1, 100).boxed();
        int sum = 0;
        sum = boxedStream.reduce(Integer::sum).orElse(0);
//        sum = boxedStream.reduce(0, Integer::sum);
//        sum = boxedStream.reduce(0, Integer::sum, (a,b)->a+b);
        System.out.println("reduce sum = " + sum);

        //collect
        boxedStream = IntStream.rangeClosed(1, 100).boxed();
//        sum = boxedStream.collect(Collectors.summingInt(a->(int) a));
        sum = boxedStream.collect(()->new int[1],(a, t)->a[0]+=t,(a, b)->a[0]+=b[0])[0];
        System.out.println("collect sum = " + sum);
    }
}
