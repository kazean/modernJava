package modernjavainaction.chap05.test;

import modernjavainaction.chap06.Dish;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static modernjavainaction.chap06.Dish.menu;

public class ch05Test {
    public static void main(String[] args) {
        /*
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0,Integer::sum);
        System.out.println("calories : >> " + calories);
        menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int a1 = 3;
        Stream<int[]> tmp = IntStream.rangeClosed(1,100)
                .filter(b-> Math.sqrt(a1*a1 + b*b)%1==0)
                .boxed()
                .map(b-> new int[]{a1,b,(int) Math.sqrt(a1*a1+b*b)});

        System.out.println(tmp.count());
//        tmp.forEach(t -> System.out.println(t[0] +"," + t[1]+"," +t[2]));

        System.out.println(">>");


        IntStream.rangeClosed(1,100)
                .filter(b-> Math.sqrt(a1*a1 + b*b)%1==0 )
                .mapToObj(b-> new int[]{a1,b,(int) Math.sqrt(a1*a1+b*b)})
                .limit(5)
                .forEach(t -> System.out.println(t[0] +"," + t[1]+"," +t[2]));


        System.out.println(">> ");


        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a->
                                IntStream.rangeClosed(a,100)
//                                        .filter(b-> Math.sqrt(a*a+b*b)%1 == 0)
                                        .mapToObj(b-> new int[]{a,b,(int) Math.sqrt(a*a+b+b)})
                                        .filter(t-> t[2] %1 == 0)
                        );

        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0] +"," + t[1]+"," +t[2]));
        */

        Stream.of("user.country","home","java.vm.version")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)))
                .forEach(System.out::println);



        System.out.println("NEXT");

        String[] stringStream = {"hello", "world"};
        Arrays.stream(stringStream)
//                .flatMap(s->s.split(""))
//                .map(s->s.split(""))
//                .flatMap(s-> Arrays.stream(s.split("")))
                .forEach(System.out::println);

        System.out.println(">>>");

        Arrays.stream(stringStream)
                .map(s->s.split(""))
                .flatMap(Arrays::stream)
                .forEach(System.out::println);

    }
}
