package modernjavainaction.chap06.test;

import modernjavainaction.chap06.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;
public class Ch06Test {
    public static void main(String[] args) {
        Comparator<Dish> dishCaloriesComaparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = Dish.menu.stream().collect(maxBy(dishCaloriesComaparator));
        System.out.println("mostCalories >> " + mostCaloriesDish.get().getName() + ": " + mostCaloriesDish.get().getCalories());

    }
}
