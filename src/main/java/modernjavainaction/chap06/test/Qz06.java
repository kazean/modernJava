package modernjavainaction.chap06.test;

import modernjavainaction.chap06.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static modernjavainaction.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

public class Qz06 {
    public static void main(String[] args) {
//        qz0601CollectorReducing();
//        System.out.println(partitionPrimes(20));
        collectorTest();
    }

    public static void qz0601CollectorReducing(){
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
//        1
        shortMenu = menu.stream().map(Dish::getName).collect(reducing((s1,s2)->s1+s2)).get();
        System.out.println(shortMenu);
//        2 error BinaryOperator T T : T
//        shortMenu = menu.stream().collect(reducing((d1,d2)->d1.getName()+d2.getName())).get();
//        3
        shortMenu = menu.stream().collect(reducing("",Dish::getName,(s1,s2)->s1+s2));
        System.out.println(shortMenu);

    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n)
                .boxed()
                .collect(partitioningBy(Qz06::isPrime));
    }

    public static boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt(candidate);
        System.out.println("candidate:" + candidate +", candidateRoot :" + candidateRoot);
        return IntStream.rangeClosed(2,candidateRoot)
                .noneMatch(i -> candidate%i ==0);
    }

    public static void collectorTest(){
        menu.stream()
                .collect(new ToListCollectorTest<>())
                .forEach(System.out::println);

    }

}
