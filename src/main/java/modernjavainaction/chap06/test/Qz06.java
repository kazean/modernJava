package modernjavainaction.chap06.test;

import modernjavainaction.chap06.Dish;
import static modernjavainaction.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

public class Qz06 {
    public static void main(String[] args) {
        qz0601CollectorReducing();
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

}
