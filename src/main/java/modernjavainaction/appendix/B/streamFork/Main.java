package modernjavainaction.appendix.B.streamFork;

import modernjavainaction.chap06.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static modernjavainaction.chap06.Dish.*;

public class Main {
    public static void main(String[] args) {
        Stream<Dish> menuStream = menu.stream();
        Results results = new StreamForker<Dish>(menuStream)
                .fork("shortMenu", s->s.map(Dish::getName).collect(Collectors.joining(", ")))
                .fork("totalCaloricDish", s->s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish",s->s.collect(Collectors.reducing((d1,d2)->d1.getCalories()>d2.getCalories()? d1:d2)).get())
                .fork("dishesByType",s->s.collect(Collectors.groupingBy(Dish::getType)))
                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCaloricDish");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu:"+shortMenu);
        System.out.println("Total calories:" +totalCalories);
        System.out.println("Most caloric dish:"+mostCaloricDish);
        System.out.println("Dishes by type:"+dishesByType);
    }
}
