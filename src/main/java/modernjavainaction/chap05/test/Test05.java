package modernjavainaction.chap05.test;

import modernjavainaction.chap04.Dish;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Test05 {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal friut", true , 120, Dish.Type.OTHER),
                new Dish("prawns", false , 300, Dish.Type.FISH),
                new Dish("rice", true , 350, Dish.Type.OTHER),
                new Dish("chicken", false , 400, Dish.Type.MEAT),
                new Dish("french fries", true , 530, Dish.Type.OTHER)
        );

        basic(specialMenu);

    }

    public static void quz0501(List<Dish> list){
        list.stream()
                .filter(d->d.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .collect(toList());

    }

    public static void basic(List<Dish> list){
        list.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        list.stream()
                .filter(dish -> dish.getCalories()>320)
                .collect(toList());

        list.stream()
                .takeWhile(dish->dish.getCalories()>320)
                .collect(toList());
        list.stream()
                .dropWhile(dish->dish.getCalories()>320)
                .collect(toList());

        list.stream()
                .filter(d->d.getCalories()>320)
                .limit(10)
                .collect(toList());

        List<String> dishNames = list.stream()
                .map(Dish::getName)
                .collect(toList());

        Arrays.asList(new String[]{"Hello","World"})
                .stream()
                .map(w->w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        boolean isHealthy = list.stream()
                .allMatch(d->d.getCalories()<1000);
        isHealthy = list.stream()
                .noneMatch(d->d.getCalories()>=1000);

        list.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish-> System.out.println(dish.getName()));


        Stream<Integer> numbers= Arrays.stream(new int[]{1,2,3,4,5}).boxed();

        numbers.reduce(0, Integer::sum);
        numbers.reduce(Integer::max);
        numbers.reduce(Integer::min);

        list.stream()
                .map(d->1L)
                .reduce((a,b)->a+b)
                .ifPresent(l-> System.out.println(l));
    }
}
