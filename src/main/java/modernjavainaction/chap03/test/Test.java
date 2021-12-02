package modernjavainaction.chap03.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

public class Test {
    public static void main(String[] args) {
        List<Apple> appleList = makeAppleList();
        listSort(appleList);

    }

    public static void runAndCall(){
        Runnable r1 = ()-> System.out.println("hello");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello2");
            }
        };
        process(r1);
        process(r2);
    }

    public static void process(Runnable r){
        r.run();
    }

    public static void listSort(List<Apple> appleList){
        appleList.sort((a1, a2)-> Integer.valueOf(a1.getWeight()).compareTo(a2.getWeight()));
        appleList.sort(comparing(Apple::getWeight));
    }

    public static List<Apple> makeAppleList(){
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(100, Color.RED));
        appleList.add(new Apple(150, Color.RED));
        appleList.add(new Apple(20, Color.RED));
        appleList.add(new Apple(200, Color.RED));
        appleList.add(new Apple(250, Color.RED));
        appleList.add(new Apple(300, Color.GREEN));
        appleList.add(new Apple(200, Color.GREEN));
        appleList.add(new Apple(150, Color.GREEN));

        return appleList;
    }
}
